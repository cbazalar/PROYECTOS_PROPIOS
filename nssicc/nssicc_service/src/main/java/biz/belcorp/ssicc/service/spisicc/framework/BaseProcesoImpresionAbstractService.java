/*
 * Created on 18/01/2007 05:24:46 PM
 * biz.belcorp.ssicc.spisicc.service.framework.BaseProcesoImpresionAbstractService
 */
package biz.belcorp.ssicc.service.spisicc.framework;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.OrFileFilter;
import org.apache.commons.io.filefilter.WildcardFilter;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spisicc.ProcesoImpresionDAO;
import biz.belcorp.ssicc.dao.spisicc.model.ArchivoImpresion;
import biz.belcorp.ssicc.dao.spisicc.model.ProcesoImpresion;
import biz.belcorp.ssicc.dao.spisicc.model.SubprocesoImpresion;
import biz.belcorp.ssicc.service.exception.ServiceException;
import biz.belcorp.ssicc.service.util.FTPClientWrapper;
import biz.belcorp.ssicc.service.util.ZipUtil;

/**
 * TODO Include class description here.
 * <p>
 * <a href="BaseProcesoImpresionAbstractService.java.html"> <i>View Source </i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("spisicc.baseProcesoImpresionAbstractService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public abstract class BaseProcesoImpresionAbstractService implements BaseProcesoImpresionService {

    protected final Log log = LogFactory.getLog(getClass());

    @Resource(name="spisicc.procesoImpresionDAO")
    protected ProcesoImpresionDAO procesoImpresionDAO;

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.spisicc.service.framework.BaseProcesoImpresionService#executeProcesoImpresion(java.util.Map, biz.belcorp.ssicc.model.Usuario)
     */
    public void executeProcesoImpresion(Map params, Usuario usuario) {
        log.debug("Dentro del metodo 'executeProcesoImpresion'");

        try {
            log.debug("Inicializando proceso de impresion");

            // Obtenemos la informacion del proceso de impresion
            String codigoProcesoImpresion = MapUtils.getString(params,
                    "codigoProcesoImpresion");
            if (StringUtils.isBlank(codigoProcesoImpresion)) {
                throw new Exception(
                        "No se ha enviado un valor para el parametro 'codigoProcesoImpresion'");
            }
            ProcesoImpresion proceso = procesoImpresionDAO
                    .getProcesoImpresion(codigoProcesoImpresion);

            // Hook method a ser sobreescrito por subclases en caso sea
            // necesario
            beforeProcesoImpresion(proceso, params);

            // Obtenemos los archivos de origen
            File[] archivosOrigen = getArchivosProceso(proceso, params);

            // Validamos que exista al menos un archivo de origen a procesar 
            validateArchivosOrigen(archivosOrigen);
            
            // Copiamos los archivos asociados a cada subproceso en el historico
            copyArchivosToHistorico(proceso, params, archivosOrigen);

            // Aqui va la logica particular de generacion de los archivos
            File[] archivosSalida = createArchivosSalida(proceso, params);

            // Validamos que se haya generado al menos un archivo de salida
            if (archivosSalida == null || archivosSalida.length == 0) {
                throw new ServiceException(
                        "El proceso no ha podido generar ningun archivo de salida.");
            }

            // Actualizamos la informacion del proceso de impresion
            updateProcesoImpresion(proceso, usuario);

            // Comprimimos todos los archivos que esta en la carpeta de salida
            File archivoZip = compressArchivosSalida(proceso, params,
                    archivosSalida);
            
            // Copiamos el archivo ZIP en el historico
            copyArchivoZIPToHistorico(proceso, archivoZip);

            // Enviamos los archivos via FTP
            sendArchivoZIP(proceso, params, archivoZip);

            // Eliminamos los archivos de la carpeta de salida
            deleteArchivos(proceso, params, archivosOrigen, archivosSalida,
                    archivoZip);
        } catch (DataAccessException dae) {
            log.error(dae.getMessage());
            throw new ServiceException(dae.getCause().getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @param proceso
     * @param archivoZip
     */
    private void copyArchivoZIPToHistorico(ProcesoImpresion proceso, File archivoZip) throws IOException {
        log.debug("Dentro del metodo 'copyArchivoZIPToHistorico'");
        File directorioHistorico = new File(proceso.getDirectorioHistorico());
        FileUtils.copyFileToDirectory(archivoZip, directorioHistorico);
    }

    /**
     * @param proceso
     */
    private void updateProcesoImpresion(ProcesoImpresion proceso, Usuario usuario) {
        log.debug("Dentro del metodo 'updateProcesoImpresion'");
        long numeroCorrelativoDiario = 1;
        // Aumentamos el correlativo acumulado
        proceso.setNumeroCorrelativoAcumulado(proceso
                .getNumeroCorrelativoAcumulado() + 1);

        // Obtenemos la fecha de ultima ejecucion del proceso
        Date ultimaEjecucion = proceso.getFechaUltimaEjecucion();
        if (ultimaEjecucion != null) {
            // Comparamos la fecha actual con la fecha de ultima ejecucion
            String fechaActual = DateFormatUtils.format(new Date(),
                    "dd/MM/yyyy");
            String fechalUltimaEjecucion = DateFormatUtils.format(
                    ultimaEjecucion, "dd/MM/yyyy");
            // Si las fechas son iguales, aumentamos en 1 el correlativo diario
            if (StringUtils.equals(fechaActual, fechalUltimaEjecucion)) {
                numeroCorrelativoDiario = proceso.getNumeroCorrelativoDiario() + 1;
            }
        }
        proceso.setNumeroCorrelativoDiario(numeroCorrelativoDiario);
        proceso.setFechaUltimaEjecucion(new Date());
        // Actualizamos la informacion en la base de datos
        procesoImpresionDAO.updateProcesoImpresion(proceso, usuario);
    }

    /**
     * Obtiene la relacion de todos los archivos de entrada asociados a un
     * proceso de impresion
     * 
     * @param proceso
     * @param params
     * @return
     */
    protected File[] getArchivosProceso(ProcesoImpresion proceso, Map params) {
        log
                .debug("Obteniendo los archivos del de origen asociados al proceso.");
        File directorioOrigen = new File(proceso.getDirectorioOrigen());
        File[] files = directorioOrigen
                .listFiles(getProcesoFileFilter(proceso));

        if (log.isDebugEnabled()) {
            log.debug("Listando los archivos:");
            log.debug(ArrayUtils.toString(files));
        }

        return files;
    }

    /**
     * @param proceso
     * @param params
     */
    protected abstract File[] createArchivosSalida(ProcesoImpresion proceso,
            Map params);

    /**
     * @param proceso
     * @param params
     */
    private void deleteArchivos(ProcesoImpresion proceso, Map params,
            File[] archivosOrigen, File[] archivosSalida, File archivoZip)
            throws IOException {
        log.debug("Eliminando los archivos de entrada.");
        // Eliminamos los archivos originales
        if (archivosOrigen != null) {
            for (int i = 0; i < archivosOrigen.length; i++) {
                archivosOrigen[i].delete();
            }
        }
        // Eliminamos los archivos generados por el proceso
        if (archivosSalida != null) {
            for (int i = 0; i < archivosSalida.length; i++) {
                archivosSalida[i].delete();
            }
        }
        // Eliminamos el archivo zip siempre y cuando se haya configurado la
        // interfaz para enviarlo via FTP
        if (StringUtils.equals(proceso.getFlagEnvioFTP(), Constants.SI)
                && archivoZip.exists()) {
            archivoZip.delete();
        }
    }

    /**
     * @param proceso
     * @param params
     */
    private void sendArchivoZIP(ProcesoImpresion proceso, Map params,
            File archivoZip) {
        // Validamos si el flag de envio esta activo
        if (StringUtils.equals(proceso.getFlagEnvioFTP(), Constants.SI)) {
            log.debug("Enviando el archivo via FTP");

            if (archivoZip.exists() == false) {
                throw new ServiceException("El archivo ZIP no existe.");
            }

            FTPClientWrapper ftp = new FTPClientWrapper(proceso
                    .getServidorFTP(), proceso.getPuertoFTP(), proceso
                    .getUsuarioFTP(), proceso.getPasswordFTP());
            ftp.sendFile(archivoZip, proceso.getDirectorioFTP(), archivoZip
                    .getName());
            ftp.disconnect();
        } else {
            log
                    .info("El proceso no esta configurado para enviar el archivo via FTP.");
        }
    }

    /**
     * @param proceso
     * @param params
     */
    private File compressArchivosSalida(ProcesoImpresion proceso, Map params,
            File[] archivosSalida) {
        log.debug("Comprimiendo los archivos de salida.");
        // Obtenemos el valor del prefijo
        String prefijo = proceso.getPrefijoArchivo();

        // Formamos el valor del nombre del archivo en base al contador
        long correlativo = proceso.getNumeroCorrelativoDiario();

        String nombreArchivo = prefijo
                + DateFormatUtils.format(new Date(), "yyyyMMdd")
                + StringUtils.leftPad(Long.toString(correlativo), 4, "0")
                + ".zip";

        File zipFile = new File(proceso.getDirectorioDestino(), nombreArchivo);

        // Coprimimos los archivos
        ZipUtil.zipFiles(archivosSalida, zipFile);

        return zipFile;
    }

    /**
     * Metodo a sobrescribir en caso sea necesario
     * 
     * @param proceso
     * @param params
     */
    protected void beforeProcesoImpresion(ProcesoImpresion proceso, Map params) {
    }

    /**
	 * Valida que exista al menos un archivo de origen (puede ser sobrescrito en
	 * caso sea necesario)
	 * 
	 * @param archivosOrigen
	 */
    protected void validateArchivosOrigen(File[] archivosOrigen) {
        if (archivosOrigen == null || archivosOrigen.length == 0) {
            throw new ServiceException(
                    "No existen archivos de impresion a procesar.");
        }
    }
    
    /**
     * Copia los archivos asociados al proceso en la carpeta del historico
     * 
     * @param proceso
     * @param params
     */
    private void copyArchivosToHistorico(ProcesoImpresion proceso, Map params,
            File[] archivosOrigen) throws IOException {
        log.debug("Copiando los archivos al historico");
        // Obtenemos los objetos file de los archivos en base al directorio
        // origen y al patron de los nombres de los archivos asociados al
        // proceso
        File directorioOrigen = new File(proceso.getDirectorioOrigen());
        File directorioHistorico = new File(proceso.getDirectorioHistorico());

        if (archivosOrigen != null) {
            for (int k = 0; k < archivosOrigen.length; k++) {
                FileUtils.copyFileToDirectory(archivosOrigen[k],
                        directorioHistorico);
            }
        }
    }

    private FileFilter getProcesoFileFilter(ProcesoImpresion proceso) {
        List archivos = new ArrayList();

        // Iteramos por cada subproceso
        List subprocesos = proceso.getSubprocesosImpresion();
        if (log.isDebugEnabled()) {
            log.debug("Subprocesos:");
        }
        if (subprocesos != null && subprocesos.size() > 0) {
            if (log.isDebugEnabled()) {
                log.debug("Total subprocesos: " + subprocesos.size());
            }
            for (int i = 0; i < subprocesos.size(); i++) {
                SubprocesoImpresion subproceso = (SubprocesoImpresion) subprocesos
                        .get(i);
                List archivosSubproceso = subproceso.getArchivosImpresion();
                if (log.isDebugEnabled()) {
                    log.debug(i + ") " + subproceso.getDescripcion());
                    log.debug("Archivos:"
                            + (archivosSubproceso == null ? 0
                                    : archivosSubproceso.size()));
                }
                archivos.addAll(archivosSubproceso);
            }
        }

        // Creamos los FileFilters en base a los patrones de los archivos
        List fileFilters = new ArrayList();
        for (int j = 0; j < archivos.size(); j++) {
            ArchivoImpresion ai = (ArchivoImpresion) archivos.get(j);
            FileFilter fileFilter = new WildcardFilter(ai.getPatronNombre());
            fileFilters.add(fileFilter);
        }

        log.debug("File Filters: " + fileFilters.size());

        // Creamos el fileFilter definitivo
        FileFilter procesoFileFilter = new OrFileFilter(fileFilters);
        return procesoFileFilter;
    }
}
