package biz.belcorp.ssicc.service.sisicc.impl;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazIMPDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;
import biz.belcorp.ssicc.service.util.FileUtil;
import biz.belcorp.ssicc.service.util.ZipUtil;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazIMPEnviarFacturasDetallePeruImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
@Service("sisicc.interfazIMPEnviarFacturasDetallePeruService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazIMPEnviarFacturasDetallePeruServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {
	
	@Resource(name="sisicc.interfazIMPDAO")
	private InterfazIMPDAO interfazIMPDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#prepareQueryParams(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected Map prepareQueryParams(InterfazParams interfazParams)
			throws InterfazException {
		// Obtenemos los parametros por defecto de la clase padre
		Map queryParams = super.prepareQueryParams(interfazParams);
		
		String numeroLote = (String)queryParams.get("numeroLote");
		
		String nombreArchivo = interfazParams.getInterfaz().getNombreArchivoEntradaSalida()+numeroLote;		
		
		queryParams.put("nombreArchivo", nombreArchivo);
        
		interfazParams.getInterfaz().setNombreArchivoEntradaSalida(nombreArchivo);
		
		return queryParams;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		String codigoPeriodo = (String) params.get("codigoPeriodo");
		String fechaFacturacion = (String)params.get("fechaFacturacion");
		if (StringUtils.isNotBlank(codigoPeriodo) && StringUtils.isNotBlank(fechaFacturacion))
			interfazIMPDAO.executeInterfazIMPEnviarFacturasDetallePeru(params);
		else
			this.log.info("No se ejecuto proceso ppr valores nulos en los parametros de Periodo o Fecha de Facturacin");
	}
    
        /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams)
			throws InterfazException {

		Map queryParams = interfazParams.getQueryParams();		
		String nombreArchivo = MapUtils.getString(queryParams, "nombreArchivo");
		String extensionArchivo = interfazParams.getInterfaz().getExtensionArchivoDescripcion();
		
		List listNombreArchivosSalida = interfazParams.getListNombreArchivosGenerados(nombreArchivo, "ZIP");

		Collections.sort(listNombreArchivosSalida);
		
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
					//DFD_LOTE001.TXT					
					String []sp1 = StringUtils.split(tempFile.getName(), ".");
					String grupo = StringUtils.right(sp1[0], 3);
					
					params.put(Constants.INTERFAZ_XRX_NO_USAR_SEPARADOR_SECUENCIA, Constants.ESTADO_ACTIVO);
					params.put(Constants.INTERFAZ_XRX_NUMERO_SECUENCIA_LOTE, grupo);
					this.sendOtherFile(interfazParams, tempFile);
				}
				
				//Eliminamos el zipeado y el unzipeado
				zippedFile.delete();
				unzippedFile.delete();
			}
		}
		
		//Eliminar el archivo dummy que envio el proceso
		//ESTE ARCHIVO SOLO ESTA PRESENTE EN EL DIRECTORIO LOCAL Y SOLO DEBE DE ELIMINARSE CUANDO EL ELVIO ES POR RED
		params.put(Constants.INTERFAZ_XRX_NUMERO_SECUENCIA_LOTE, "");
		params.put(Constants.INTERFAZ_XRX_NO_USAR_SEPARADOR_SECUENCIA, "");
		interfazParams.getInterfaz().setExtensionArchivoDescripcion(extensionArchivo);
		
		this.deleteSendedFile(interfazParams, nombreArchivo + "." + extensionArchivo);
		//
	}
}
