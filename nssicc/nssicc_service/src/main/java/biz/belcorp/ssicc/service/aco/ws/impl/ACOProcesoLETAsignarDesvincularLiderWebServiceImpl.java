package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoLETAsignarDesvincularLiderWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.let.ProcesoLETAsignarDesvincularLiderService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoRECEnviaMsgReclamoWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public class ACOProcesoLETAsignarDesvincularLiderWebServiceImpl extends BaseProcesoHiloAbstractWebService implements ACOProcesoLETAsignarDesvincularLiderWebService {

	InterfazSiCCService interfazSiCCService;
	ProcesoLETAsignarDesvincularLiderService procesoLETAsignarDesvincularLiderService;
	
	protected void onInit() throws ServiceException {
		super.onInit();
		this.interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
		this.procesoLETAsignarDesvincularLiderService = (ProcesoLETAsignarDesvincularLiderService)getWebApplicationContext().getBean("spusicc.procesoLETAsignarDesvincularLiderService");
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoLETAsignarDesvincularLiderWebService#ejecutarProcesoLETAsignarDesvincularLider(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoLETAsignarDesvincularLider(String codigoPais, String codigoPeriodo, String codigoUsuario) throws RemoteException {
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso ejecutarProcesoLETAsignarDesvincularLider");
		}
		
		String mensajeError = "";
		String CODIGO_BATCH = Constants.LET_CODIGO_PROCESO_BATCH_ASIGNAR_DESVINCULAR_LIDER;
		String CODIGO_SISTEMA = Constants.LET_CODIGO_SISTEMA;		

		try {
			
			Pais pais = this.paisService.getPais(codigoPais);
			if (StringUtils.isBlank(codigoPais)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
		    
			/**
		     * Valida usuario
		     */
		    if(StringUtils.isBlank(codigoUsuario)|| StringUtils.isEmpty(codigoUsuario)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
		    }			
			
			Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);
			
			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			params.put("codigoSistema", CODIGO_SISTEMA);
			params.put("codigoProcesoBatch", CODIGO_BATCH);
			params.put("codigoUsuario", codigoUsuario);
			params.put("usuario", usuario);			
			
			String periodoActual = ((HashMap)interfazSiCCService.getPeriodoFechaProcesoActual(params).get(0)).get("cod_peri").toString();
			
			if(StringUtils.isBlank(codigoPeriodo) || StringUtils.isEmpty(codigoPeriodo)){
				codigoPeriodo = periodoActual;
			}
			params.put("codigoPeriodo", codigoPeriodo);
			
			this.executeProceso(params);
		    objetoRespuesta.setEjecucionExitosa(true);

		} catch (Exception e) {
			mensajeError = e.getMessage();
			objetoRespuesta.setEjecucionExitosa(false);
			objetoRespuesta.setMensaje(mensajeError);
		}

		return objetoRespuesta;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#executeProcess(java.util.Map)
	 */
	@Override
	protected Map executeProcess(Map params) throws Exception {
		
		procesoLETAsignarDesvincularLiderService.executeAsignarDesvincularLider(params);
		
		return params;
	}
}
