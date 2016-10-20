/*
 * Created on 19/01/2007 04:35:40 PM
 * biz.belcorp.ssicc.spisicc.service.impl.ProcesoImpresionLaserServiceImpl
 */
package biz.belcorp.ssicc.service.spisicc.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spisicc.model.ProcesoImpresion;
import biz.belcorp.ssicc.dao.spisicc.model.SubprocesoImpresion;
import biz.belcorp.ssicc.service.exception.ServiceException;
import biz.belcorp.ssicc.service.spisicc.framework.BaseProcesoImpresionAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoImpresionLaserServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */
@Service("spisicc.procesoImpresionLaserColorService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoImpresionLaserColorServiceImpl extends BaseProcesoImpresionAbstractService {

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spisicc.service.framework.BaseProcesoImpresionAbstractService#createArchivosSalida(biz.belcorp.ssicc.model.ProcesoImpresion, java.util.Map)
     */
    protected File[] createArchivosSalida(ProcesoImpresion proceso, Map params) {
        log.debug("Dentro del mtodo 'createArchivosSalida'");

        File[] archivosSalida = new File[0];
        List archivosSalidaList = new ArrayList();
        File[] archivosProceso = getArchivosProceso(proceso, params);
        
        // Ordenamos los archivos en base al tamao
        if(archivosProceso != null && archivosProceso.length > 0) {
        	Arrays.sort(archivosProceso, new FileSizeComparator());
        }
        
        List subprocesos = proceso.getSubprocesosImpresion();
        if(subprocesos == null) {
            throw new ServiceException("No existe ningun subproceso asociado al proceso de impresion laser");
        }
        SubprocesoImpresion subproceso = (SubprocesoImpresion)subprocesos.get(0);
        String nombreArchivo = subproceso.getNombreArchivo();
        
     
        
        // Valor del parametro que indica el numero de archivos a generar 
 		String numeroArchivosSalida = Constants.PROCESO_IMPRESION_NRO_ARCHIVOS_SALIDA;

    	
        try {
        	//antes de generar en el teporar ya se elimino el teporarl	
            // Colocamos los parametros para generar el archivo (TXT)
            params.put("nombreArchivo", nombreArchivo);
            params.put("directorio", proceso.getDirectorioDestino());
            params.put("numeroDocumento", Constants.PROCESO_IMPRESION_NRO_PAQUETE_NORMAL);
            params.put("numeroArchivosSalida", numeroArchivosSalida);
            procesoImpresionDAO.executeGenerarPaqueteDocumentarioColor(params);
            
            // Agregamos el(los) archivo(s) de salida principal(es) a la lista
            archivosSalidaList.addAll(getFilesArchivosSalida(nombreArchivo,
					proceso.getDirectorioDestino(), numeroArchivosSalida));
            
            
            // Hacemos la conversion de list a array
            archivosSalida = (File[])archivosSalidaList.toArray(new File[0]);
            
        }
        catch(Exception e) {
            log.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        
        return archivosSalida;
    }
    
  

	/**
	 * @param nombreArchivo
	 * @param nombreDirectorio
	 * @param numeroArchivosSalida
	 * @return
	 */
	protected List getFilesArchivosSalida(String nombreArchivo, String nombreDirectorio, String numeroArchivosSalida) {
    	List filesArchivosSalida = new ArrayList();
    	int contador = Integer.parseInt(numeroArchivosSalida);
    	String separador = ".";
    	
    	if(contador > 1) {
    		String nombre = StringUtils.substringBeforeLast(nombreArchivo, separador);
    		String extension = StringUtils.substringAfterLast(nombreArchivo, separador);
    		if(log.isDebugEnabled()) {
    			log.debug("nombre: " + nombre);
    			log.debug("extension: " + extension);
    		}
    		
    		for(int i = 1; i <= contador; i++) {
    			String nombreCompleto = nombre + "_" + i + separador + extension;
    			if(log.isDebugEnabled()) {
    				log.debug("nombreCompleto: " + nombreCompleto);
    			}
    			File file = new File(nombreDirectorio, nombreCompleto);
    			filesArchivosSalida.add(file);
    		}
    	}
    	else {
    		File file = new File(nombreDirectorio, nombreArchivo);
    		filesArchivosSalida.add(file);
    	}
    	
    	return filesArchivosSalida;
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spisicc.service.framework.BaseProcesoImpresionAbstractService#validateArchivosOrigen(java.io.File[])
     */
    protected void validateArchivosOrigen(File[] archivosOrigen) {
      log.debug("no s evalidan archivos de entrada");
    }
}



