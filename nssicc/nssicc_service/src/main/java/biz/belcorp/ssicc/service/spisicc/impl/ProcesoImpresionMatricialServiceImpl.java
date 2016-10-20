/*
 * Created on 18/01/2007 07:41:40 PM
 * biz.belcorp.ssicc.spisicc.service.impl.ProcesoImpresionMatricialServiceImpl
 */
package biz.belcorp.ssicc.service.spisicc.impl;

import java.io.File;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spisicc.model.ProcesoImpresion;
import biz.belcorp.ssicc.service.exception.ServiceException;
import biz.belcorp.ssicc.service.spisicc.framework.BaseProcesoImpresionAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoImpresionMatricialServiceImpl.java.html"> <i>View Source </i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("spisicc.procesoImpresionMatricialService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoImpresionMatricialServiceImpl extends BaseProcesoImpresionAbstractService {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.spisicc.service.framework.BaseProcesoImpresionAbstractService#createArchivosSalida(biz.belcorp.ssicc.model.ProcesoImpresion,
     *      java.util.Map)
     */
    protected File[] createArchivosSalida(ProcesoImpresion proceso, Map params) {
        log.debug("Dentro del mtodo 'createArchivosSalida'");

        File[] archivosSalida = new File[0];
        // Para el caso de los archivos matriciales, lo que hacemos
        // es renombar los archivos de la carpeta de entrada
        File[] archivosProceso = getArchivosProceso(proceso, params);

        try {
            if (archivosProceso != null) {
                archivosSalida = new File[archivosProceso.length];
                for (int i = 0; i < archivosProceso.length; i++) {
                    // Renombramos el archivo quitandole el correlativo
                    String nombreArchivo = archivosProceso[i].getName();
                    String nuevoNombreArchivo = StringUtils.substringBefore(
                            nombreArchivo, "_")
                            + "."
                            + StringUtils
                                    .substringAfterLast(nombreArchivo, ".");
                    if (log.isDebugEnabled()) {
                        log.debug("Renombrando el archivo " + nombreArchivo
                                + " al valor " + nuevoNombreArchivo);
                    }
                    // Si ya existe un archivo con ese nombre lo borrarmos
                    File nuevoArchivoSalida = new File(archivosProceso[i].getParentFile(),
                            nuevoNombreArchivo);
                    if (nuevoArchivoSalida.exists()) {
                        nuevoArchivoSalida.delete();
                    }
                    if (archivosProceso[i].renameTo(nuevoArchivoSalida) == false) {
                        throw new ServiceException(
                                "No se pudo renombrar el archivo "
                                        + archivosProceso[i].getName());
                    }

                    archivosSalida[i] = nuevoArchivoSalida;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }

        return archivosSalida;
    }

}
