/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazXRXDAO;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;
import biz.belcorp.ssicc.service.util.FileUtil;
import biz.belcorp.ssicc.service.util.ZipUtil;

/**
 * @author Sigcomt
 *
 */

@Service("sisicc.interfazXRXEnviarNotaCreditoElectronicaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazXRXEnviarNotaCreditoElectronicaServiceImpl extends
BaseInterfazSalidaStoredProcedureAbstractService {
	
	@Resource(name="sisicc.interfazXRXDAO")
	private InterfazXRXDAO interfazXRXDAO;
	
	@Resource(name="scsicc.procesoBatchService")
	private ProcesoBatchService procesoBatchService;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#prepareQueryParams(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected Map prepareQueryParams(InterfazParams interfazParams)
			throws InterfazException {
		// Obtenemos los parametros por defecto de la clase padre
		Map queryParams = super.prepareQueryParams(interfazParams);
		
		String fechaProcesoArchivo = (String)queryParams.get("fechaProcesoArchivo");
		
		String nombreArchivo = interfazParams.getInterfaz().getNombreArchivoEntradaSalida()+fechaProcesoArchivo;		
		
		queryParams.put("nombreArchivo", nombreArchivo);
        
		interfazParams.getInterfaz().setNombreArchivoEntradaSalida(nombreArchivo);
		return queryParams;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams)
			throws InterfazException {

		if(log.isDebugEnabled())
			log.debug("afterProcessInterfaz");
	
		Map queryParams = interfazParams.getQueryParams();		
		String nombreArchivo = MapUtils.getString(queryParams, "nombreArchivo");
		String extensionArchivo = interfazParams.getInterfaz().getExtensionArchivoDescripcion();
		
		List listNombreArchivosSalida = interfazParams.getListNombreArchivosGenerados(nombreArchivo, "ZIP");
		List listNombreArchivosSalidaFlg = interfazParams.getListNombreArchivosGenerados(nombreArchivo, Constants.INTERFAZ_XRX_EXTENSION_ARCHIVO_FLAG);
				
		Map params = interfazParams.getQueryParams();	
		
		//Enviar archivos ZIP
		if (listNombreArchivosSalida.size() > 0) {
			
			for(int i=0; i<listNombreArchivosSalida.size(); i++)
			{
				if(log.isDebugEnabled())
					log.debug("Enviando archivo: " + listNombreArchivosSalida.get(i));
				
				//Descomprimimos el archivo y lo renombramos a TXT
				File zippedFile = new File((String)listNombreArchivosSalida.get(i)); 
				File unzippedFile = new File(ZipUtil.unzipFile(zippedFile.getAbsolutePath(), FileUtil.formatDirectory(interfazParams.getInterfaz().getDirectorioProc())));
				File tempFile = new File(StringUtils.replace(unzippedFile.getAbsolutePath(), ".TMP", ".TXT"));
				
				if(unzippedFile.renameTo(tempFile))
				{
					//Enviamos el archivo unzipeado
					//DFD_ASDASDASD_001.TXT
					String []sp1 = StringUtils.split(tempFile.getName(), "_");
					String []sp2 = StringUtils.split(sp1[2], ".");
					
					params.put(Constants.INTERFAZ_XRX_NUMERO_SECUENCIA_LOTE, sp2[0]);
					this.sendOtherFile(interfazParams, tempFile);
				}
				
				//Eliminamos el zipeado y el unzipeado
				zippedFile.delete();
				unzippedFile.delete();
			}
		}
		
		//Enviar archivos FLG
		if (listNombreArchivosSalidaFlg.size() > 0) {
			
			for(int i=0; i<listNombreArchivosSalidaFlg.size(); i++)
			{
				if(log.isDebugEnabled())
					log.debug("Enviando archivo: " + listNombreArchivosSalidaFlg.get(i));
				
				File tempFile = new File((String)listNombreArchivosSalidaFlg.get(i));

				//DFD_ASDASDASD_001.FLG
				String []sp1 = StringUtils.split(tempFile.getName(), "_");
				String []sp2 = StringUtils.split(sp1[2], ".");
				
				params.put(Constants.INTERFAZ_XRX_NUMERO_SECUENCIA_LOTE, sp2[0]);
				interfazParams.getInterfaz().setExtensionArchivoDescripcion(Constants.INTERFAZ_XRX_EXTENSION_ARCHIVO_FLAG);
				
				this.sendOtherFile(interfazParams, tempFile);
			}
		}
		
		//Eliminar el archivo dummy que envio el proceso
		params.put(Constants.INTERFAZ_XRX_NUMERO_SECUENCIA_LOTE, "");
		interfazParams.getInterfaz().setExtensionArchivoDescripcion(extensionArchivo);
		this.deleteSendedFile(interfazParams, nombreArchivo + extensionArchivo);
		//
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazXRXDAO.executeInterfazXRXEnviarNotaCreditoElectronica(params);
		
	}
	
	@Override
	protected void beforeExecuteInterfaz(Map params) throws Exception 
	{
		Usuario usuario = (Usuario) params.get("usuario");
		procesoBatchService.updateProcesoBatchEtapaProceso(params, usuario);
		return;

	}


}
