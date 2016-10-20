/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazLARDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;
import biz.belcorp.ssicc.service.util.FTPUtil;
import biz.belcorp.ssicc.service.util.FileUtil;

/**
 * @author cbazalar
 *
 */
@Service("sisicc.interfazLARRecepcionarInformacionTrackingService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazLARRecepcionarInformacionTrackingServiceImpl extends
		BaseInterfazEntradaAbstractService {

	@Resource(name="sisicc.interfazLARDAO")
	private InterfazLARDAO interfazLARDAO;
	
	
	@Override
	protected int processInterfaz(InterfazParams interfazParams)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'processInterfaz' method");
		File tempFile = null;
		int registrosProcesados = 0;
		Interfaz interfaz = interfazParams.getInterfaz();
		String flagMoverArchivoEntradaTemporal = interfaz.getFlagMoverArchivoEntradaTemporal();
		if (StringUtils.isBlank(flagMoverArchivoEntradaTemporal)) {
			log.info("Convirtiendo flagMoverArchivoEntradaTemporal a N");
			flagMoverArchivoEntradaTemporal = Constants.NO;
		}
		log.info("flagMoverArchivoEntradaTemporal: "+ flagMoverArchivoEntradaTemporal);

		try {
			
			this.getNombreArchivo(interfazParams);			
			registrosProcesados = this.receiveListaFile(interfazParams);
			interfazResult.setCompletadoInterfaz(true); // FRAMEWORK NSSICC
			
		} catch (Exception e) {
			log.error("Error al procesar la interfaz: " + e.getMessage());
			Map params = interfazParams.getQueryParams();
			params.put("errorInterfaz", Constants.SI);	
			
			InterfazException interfazException = new InterfazException(e.getMessage());
			interfazException.setRegistrosProcesados(registrosProcesados);
			throw interfazException;
		} 
			
	
		log.info("Se termino de procesar la interfaz de entrada");
		return registrosProcesados;
	}
	
	
	
    /**
     * @param interfazParams
     * @return
     * @throws InterfazException
     */
    private int receiveListaFile(InterfazParams interfazParams) throws InterfazException {
    	int registrosProcesados = 0;
		if (log.isDebugEnabled()) log.debug("Entering 'receiveFile' method");
		
		Interfaz interfaz = interfazParams.getInterfaz();
		FTPUtil ftpUtil = new FTPUtil();
		int contadorTemporalQuartz = 0;
		try {
			
		    /* Obteniendo lista de archivos del FTP */
			ftpUtil.loguearFTP(interfaz);
			List<String> listaArchivos = ftpUtil.obtenerListaArchivo(
					interfaz.getDirectorioEntradaSalida(), 
					interfaz.getNombreArchivoEntradaSalida(), 
					interfaz.getExtensionArchivoDescripcion(), 
					interfaz.getTipoNombreArchivo());
			ftpUtil.cerrarFTP();
			log.debug("Nro de Archivos encontrados : " + listaArchivos.size() );
			
			/* En caso haya sido lanzado desde Proceso Quatz */
			Map queryParams = interfazParams.getQueryParams();
			String scontadorArchivosServidorQuartz = (String) queryParams.get("nroArchivosProcesarQuartzLARWeb");
			Integer contadorArchivosServidorQuartz = new Integer("0");
			if (StringUtils.isNotBlank(scontadorArchivosServidorQuartz))
				contadorArchivosServidorQuartz = new Integer(scontadorArchivosServidorQuartz);
			
			/* Obteniendo archivo del FTP */
			for (int x=0; x < listaArchivos.size() ;  x++) {
				
				boolean validarArchivo = false;
				String archivoEntradaFileName = listaArchivos.get(x);
	            
				queryParams.put("archivoEntradaFileName", archivoEntradaFileName);
				interfazParams.setQueryParams(queryParams);
	            
	            Integer existeRegistro = interfazLARDAO.getExisteLARControlCargarInformacionTracking(queryParams);
	            if (existeRegistro.intValue() == 0) validarArchivo = true;
	            
				if (!validarArchivo) {
					log.debug(x + ": - archivoEntradaFileName : " + archivoEntradaFileName );
				}
				else {
					log.debug(x + ": - archivoEntradaFileName : " + archivoEntradaFileName + " - Generando proceso");
					File tempFile = new File(interfazParams.getTempDirectory() + archivoEntradaFileName);
					try {
						interfazLARDAO.insertLARControlCargarInformacionTracking(queryParams);
						
						Historico historicoEjecucion = interfazParams.getHistorico();
						historicoEjecucion.setNombreArchivo(archivoEntradaFileName);
						interfazParams.setHistorico(historicoEjecucion);
						
						ftpUtil.loguearFTP(interfaz);
						ftpUtil.copiarArchivoFTPaRed(interfaz.getDirectorioEntradaSalida(), archivoEntradaFileName, interfaz.getDirectorioTemporal(), archivoEntradaFileName);
						ftpUtil.cerrarFTP();
						
						this.copyTempToHistoricoRed(interfazParams, tempFile);
					    
					    List data = new ArrayList();
						this.beforeReadData(interfazParams);
						int procesados = this.readData(interfazParams, tempFile, data);
						registrosProcesados = procesados + registrosProcesados;
						
						// Para liberar las referencias y apresurar al GarbageCollector
						data = null;
						queryParams.put("procesados", procesados);
						interfazLARDAO.updateLARControlCargarInformacionTracking(queryParams);
					}
					catch(Exception e) {
						throw new Exception(e.getMessage());
					}
					finally {
						FileUtil.deleteFile(tempFile);
						tempFile = null;
					}
					
					contadorTemporalQuartz++;
					if (contadorArchivosServidorQuartz.intValue() > 0) {
						if (contadorTemporalQuartz == contadorArchivosServidorQuartz.intValue()) 
							break;
					}
				}
				
			}
			log.debug("Proceso terminado");
			
		} catch (Exception e) {
			throw new InterfazException("Error al recibir el archivo de entrada: " + e.getMessage());
		}
		return registrosProcesados;
	}
	
	
    @Override
    protected String setObtenerNombreGrabarArchivoHistoricoRed(InterfazParams interfazParams) {
    	Interfaz interfaz = interfazParams.getInterfaz();
    	String nombreArchivo = interfazParams.getHistorico().getNombreArchivo();
    	String retorno = FileUtil.formatDirectory(interfaz.getDirectorioHistorico()) + nombreArchivo;
    	return retorno;
    }
    
    @Override
    protected void beforeReadData(InterfazParams interfazParams)
			throws InterfazException {
    	if (log.isDebugEnabled())
			log.debug("Entering 'beforeReadData' method");
    	
    	Historico historico = interfazParams.getHistorico();
    	String nombreArchivo = historico.getNombreArchivo();
    	int pos = StringUtils.indexOf(nombreArchivo, ".");
    	if (pos > 0) {
    		nombreArchivo = nombreArchivo.substring(0, pos);
    	}
    	
    	Map map = interfazParams.getQueryParams();
    	map.put("nombreArchivo", nombreArchivo);
    	
    	log.debug("Entrando a ejecutar mi procedimiento");
    	
    	try {

    		interfazLARDAO.executeLARCargarInformacionTracking(map);
    			
    	} catch (Exception e) {
			throw new InterfazException(e.getMessage());
		}
    	return;
	}
	
}
