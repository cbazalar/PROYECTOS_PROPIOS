package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoRECCierreProcesosDiariosWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.scdf.ControlProcesoService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoRECEnviaMsgReclamoWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public class ACOProcesoRECCierreProcesosDiariosWebServiceImpl extends BaseProcesoHiloAbstractWebService implements ACOProcesoRECCierreProcesosDiariosWebService {

	PaisService paisService;
	ControlProcesoService controlProcesoService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		this.paisService = (PaisService)this.getWebApplicationContext().getBean("paisService");
		this.controlProcesoService = (ControlProcesoService) this.getWebApplicationContext().getBean("scdf.controlProcesoService");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoRECCierreProcesosDiariosWebService#ejecutarProcesoRECCierreProcesosDiarios(java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoRECCierreProcesosDiarios(String codigoPais, String codigoUsuario) throws RemoteException {
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		String mensajeError = "";
		String CODIGO_BATCH = Constants.GEN_CODIGO_PROCESO_BATCH_CIERRE_PROCESOS_DIARIOS_SCDF;
		String CODIGO_SISTEMA = Constants.SISTEMA_GEN;
		
		if(log.isDebugEnabled()){
			log.debug("ejecutarProcesoRECCierreProcesosDiarios");
		}
		try{
			Pais pais = this.paisService.getPais(codigoPais);			
			if(StringUtils.isBlank(codigoPais) || StringUtils.isEmpty(codigoPais)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null,
				getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			
			/**
		     * Valida usuario
		     */
		    if(StringUtils.isBlank(codigoUsuario)|| StringUtils.isEmpty(codigoUsuario)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
		    }
			
			Map criteria = new HashMap();
			
			// Asignamos al codigo del periodo el valor por defecto
	        criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        criteria.put("codigoUsuario", codigoUsuario);
	        
	        this.executeProceso(criteria);
		    
	        objetoRespuesta.setEjecucionExitosa(true);
	     
		}catch (Exception e) {			
			log.error(e.getMessage());
			objetoRespuesta.setMensaje(e.getMessage());
			objetoRespuesta.setRespuestaWebService(null);			
			objetoRespuesta.setEjecucionExitosa(estado);
		}finally{
			log.debug("Estado del servicio: " + estado);
			if(estado){
				log.info("Se ejecuto el servicio con exito.");					
			}else{
				log.error("Excepcion en el servicio.");				
			}						
		}	
		return objetoRespuesta;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#executeProcess(java.util.Map)
	 */
	@Override
	protected Map executeProcess(Map params) throws Exception {
		
		this.controlProcesoService.executeCierreProcesosDiarios(MapUtils.getString(params, "codigoPais"));
		
		return params;
	}
	
		
}
