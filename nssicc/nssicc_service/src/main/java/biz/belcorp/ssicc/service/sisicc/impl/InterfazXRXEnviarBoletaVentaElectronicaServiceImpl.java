/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazXRXDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;

/**
 * @author Sigcomt
 *
 */

@Service("sisicc.interfazXRXEnviarBoletaVentaElectronicaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazXRXEnviarBoletaVentaElectronicaServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazXRXDAO")
	private InterfazXRXDAO interfazXRXDAO;
	
	@Resource(name="scsicc.procesoBatchService")
	private ProcesoBatchService procesoBatchService;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#prepareQueryParams(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	
	protected Map prepareQueryParams(InterfazParams interfazParams) throws InterfazException {
		
		// Obtenemos los parametros por defecto de la clase padre
		Map queryParams = super.prepareQueryParams(interfazParams);
		
		String fechaProceso = (String)queryParams.get("fechaProcesoArchivo");
		String fechaProcesoHora = (String) queryParams.get("fechaProcesoHora");
		
		String nombreArchivo = interfazParams.getInterfaz().getNombreArchivoEntradaSalida()+ fechaProceso+"_"+fechaProcesoHora; 
		queryParams.put("nombreArchivo", nombreArchivo );
		interfazParams.getInterfaz().setNombreArchivoEntradaSalida(nombreArchivo);
		return queryParams;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		if (log.isDebugEnabled()) {
			log.debug(params);
		}
		interfazXRXDAO.executeInterfazXRXEnviarBoletaVentaElectronica(params);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams)
			throws InterfazException {
		
		//ENVIAR EL ARCHIVO FLAG
		if(log.isDebugEnabled())
			log.debug("afterProcessInterfaz");
	
		Map queryParams = interfazParams.getQueryParams();		
		String nombreArchivo = MapUtils.getString(queryParams, "nombreArchivo");
		
		List listNombreArchivosSalida = interfazParams.getListNombreArchivosGenerados(nombreArchivo, Constants.INTERFAZ_XRX_EXTENSION_ARCHIVO_FLAG);
		
		if (listNombreArchivosSalida.size() > 0) {
			
			if(log.isDebugEnabled())
				log.debug("Enviando archivo: " + listNombreArchivosSalida.get(0));
			
			File tempFile = new File((String)listNombreArchivosSalida.get(0));
			interfazParams.getInterfaz().setExtensionArchivoDescripcion(Constants.INTERFAZ_XRX_EXTENSION_ARCHIVO_FLAG);
			
			this.sendOtherFile(interfazParams, tempFile);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazHiloAbstractAction#executeProcessBeforeInterfaz(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, java.util.Map)
	 */
		
	protected void beforeExecuteInterfaz(Map params) throws Exception {		
		super.beforeExecuteInterfaz(params);
		Usuario usuario = (Usuario) params.get("usuario");		
		procesoBatchService.updateProcesoBatchEtapaProceso(params, usuario);				
	}
	
	/**
	 * @param interfazXRXDAO the interfazXRXDAO to set
	 */
	public void setInterfazXRXDAO(InterfazXRXDAO interfazXRXDAO) {
		this.interfazXRXDAO = interfazXRXDAO;
	}

}
