/*
 * Created on 23/01/2007 10:06:47 AM
 * biz.belcorp.ssicc.spisicc.service.impl.ProcesoImpresionNotaCreditoServiceImpl
 */
package biz.belcorp.ssicc.service.spisicc.impl;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.filefilter.OrFileFilter;
import org.apache.commons.io.filefilter.WildcardFilter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spisicc.model.ArchivoImpresion;
import biz.belcorp.ssicc.dao.spisicc.model.ProcesoImpresion;
import biz.belcorp.ssicc.dao.spisicc.model.SubprocesoImpresion;
import biz.belcorp.ssicc.service.exception.ServiceException;
import biz.belcorp.ssicc.service.spisicc.framework.BaseProcesoImpresionAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoImpresionNotaCreditoServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("spisicc.procesoImpresionNotaCreditoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoImpresionNotaCreditoServiceImpl extends BaseProcesoImpresionAbstractService {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.spisicc.service.framework.BaseProcesoImpresionAbstractService#createArchivosSalida(biz.belcorp.ssicc.model.ProcesoImpresion,
     *      java.util.Map)
     */
    protected File[] createArchivosSalida(ProcesoImpresion proceso, Map params) {
        log.debug("Dentro del mtodo 'createArchivosSalida'");

        File[] archivosSalida = new File[0];
        List archivosConsolidados = new ArrayList();

        // Para los procesos de nota de credito, obtenemos primero los
        // subprocesos ya que se procesaran los archivos de cada uno de ellos de
        // forma independiente
        List subprocesos = proceso.getSubprocesosImpresion();

        try {
            if (subprocesos != null) {
                for (int i = 0; i < subprocesos.size(); i++) {
                    SubprocesoImpresion subproceso = (SubprocesoImpresion) subprocesos
                            .get(i);

                    // Procesamos los archivos asociados al subproceso
                    File[] archivosSubproceso = getArchivosSubproceso(proceso
                            .getDirectorioOrigen(), subproceso
                            .getArchivosImpresion());
                    // Limpiamos la tabla conteniendo el contenido de los
                    // archivos
                    // del subproceso producto de ejecuciones anteriores
                    if (log.isDebugEnabled()) {
                        log.debug("Truncando la tabla de Notas de Credito");
                    }
                    // Se comenta la eliminacion de notas de credito para
                    // mantener el historico de notas cargadas
                    //procesoImpresionDAO.removeNotasCredito();

                    // Cargamos cada uno de los archivos en la tabla
                    if (archivosSubproceso != null
                            && archivosSubproceso.length > 0) {
                        for (int j = 0; j < archivosSubproceso.length; j++) {
                            params.put("nombreArchivo", archivosSubproceso[j]
                                    .getName());
                            params.put("directorio", archivosSubproceso[j]
                                    .getParent());
                            params.put("numeroSerie", new Integer(i));
                            if (log.isDebugEnabled()) {
                                log.debug("Cargando el archivo "
                                        + archivosSubproceso[j].getName());
                            }
                            procesoImpresionDAO
                                    .executeCargarNotaCredito(params);
                        }

                        // Una vez cargados los archivos, realizamos la
                        // generacion
                        // del
                        // archivo compaginado en la ruta de salida del proceso
                        File archivoConsolidado = new File(proceso
                                .getDirectorioDestino(), subproceso
                                .getNombreArchivo());
                        params.put("nombreArchivo", archivoConsolidado
                                .getName());
                        params
                                .put("directorio", archivoConsolidado
                                        .getParent());
                        params.put("numeroSerie", new Integer(i));
                        if (log.isDebugEnabled()) {
                            log
                                    .debug("Consolidando las notas de credito del subproceso"
                                            + subproceso.getCodigo());
                        }
                        procesoImpresionDAO
                                .executeGenerarNotaCreditoConsolidada(params);
                        archivosConsolidados.add(archivoConsolidado);
                    }
                }

                archivosSalida = (File[]) archivosConsolidados
                        .toArray(archivosSalida);
            }
        } catch (DataIntegrityViolationException dive) {
            throw new ServiceException("Existen archivos con el mismo Numero de Documento Interno");
        }

        return archivosSalida;
    }

    /**
     * Obtiene las referencias a los archivos asociados al subproceso
     * 
     * @param directorioOrigen
     *            Directorio en donde se buscaran los archivos del subproceso
     * @param archivos
     *            Lista conteniendo la informacion de los archivos del
     *            subproceso
     * @return
     */
    private File[] getArchivosSubproceso(String directorioOrigen, List archivos) {
        // Obtenemos los patrones de nombre de los archivos
        List fileFilters = new ArrayList();
        File[] archivosSubproceso = new File[0];

        if (archivos != null) {
            // Creamos los FileFilters en base a los patrones de los archivos
            for (int j = 0; j < archivos.size(); j++) {
                ArchivoImpresion ai = (ArchivoImpresion) archivos.get(j);
                FileFilter fileFilter = new WildcardFilter(ai.getPatronNombre());
                fileFilters.add(fileFilter);
            }

            // Creamos el filter definitivo y obtenemos la lista de archivos
            FileFilter subprocesoFileFilter = new OrFileFilter(fileFilters);
            File directorio = new File(directorioOrigen);
            archivosSubproceso = directorio.listFiles(subprocesoFileFilter);
        }

        return archivosSubproceso;
    }

}
