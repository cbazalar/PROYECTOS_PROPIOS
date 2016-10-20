/**
 * 
 */
package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoRECEnviarBoletaRecojoWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;

/**
 * 
 * TODO Include class description here. 
 * 
 * <p>
 * <a href="ACOProcesoRECEnviarBoletaRecojoWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public class ACOProcesoRECEnviarBoletaRecojoWebServiceImpl extends BaseInterfazHiloAbstractWebService implements ACOProcesoRECEnviarBoletaRecojoWebService{

	InterfazSiCCService interfazSiCCService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		this.interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoRECEnviarBoletaRecojoWebService#ejecutarProceso(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProceso(String codigoPais, String codigoMarca, String codigoCanal, String codigoPeriodo, String codigoUsuario) throws RemoteException {
		
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";
		final String CODIGO_BATCH = Constants.REC_CODIGO_PROCESO_BATCH_ENVIAR_BOLETAS_DE_RECOJO;
		final String CODIGO_INTERFAZ = Constants.REC_CODIGO_INTERFAZ_ENVIAR_BOLETAS_DE_RECOJO;
		final String CODIGO_SISTEMA = Constants.CODIGO_SISTEMA_REC;
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ACOProcesoRECEnviarBoletaRecojo");
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
		    /**
		     * Valida campa�a
		     */
		    if(StringUtils.isBlank(codigoPeriodo)|| StringUtils.isEmpty(codigoPeriodo)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarCodigoPeriodo",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
		    }		    
			
			Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);
			
			Map criteria = new HashMap();
			
			// Asignamos al codigo del periodo el valor por defecto
	        criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
	    	criteria.put("codigoUsuario", codigoUsuario);
			criteria.put("codigoPeriodo", codigoPeriodo);
	    	
			List siccMarcaList = this.interfazSiCCService.getMarcas();
			List siccCanalList = this.interfazSiCCService.getCanalesByCodigoISO(pais.getCodigoIdiomaIso());
			
			/**
			 * Si marca es vacio o en blanco, traer el de por defecto
			 */
			if(StringUtils.isBlank(codigoMarca) || StringUtils.isEmpty(codigoMarca)){
				codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
			}else{
				if(siccMarcaList.size()!=0){
					if(!existeCodigoEnLista(siccMarcaList, codigoMarca)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteMarca",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de marca vacio.");
				}
			}
			
			/**
			 * Si canal es vacio o en blanco, traer el de por defecto
			 */
			if(StringUtils.isBlank(codigoCanal) || StringUtils.isEmpty(codigoCanal)){
				codigoCanal = Constants.CODIGO_CANAL_DEFAULT;				
			}else{
				if(siccCanalList.size()!=0){
					if(!existeCodigoEnLista(siccCanalList, codigoCanal)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCanal",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de canal vacio.");
				}
			}
			
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("codigoMarca", codigoMarca);
	        
	        executionResult = this.executeInterfaz(criteria);
	        
	        List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult) list.get(0);
		
			if (interfazResult != null) {
				if(StringUtils.equals(this.estadoProceso, Constants.OK)){
					estado = true;
				}
				objetoRespuesta.setEjecucionExitosa(estado);
				objetoRespuesta.setMensaje(this.mensajeError);
				log.debug(objetoRespuesta.getMensaje());
				
				List<ParametroACOWebService> respuestaWebService =  new ArrayList<ParametroACOWebService>();				
				
				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("numeroRegistros");
				parametroACOWebService.setValor(String.valueOf(interfazResult.getRegistrosProcesados()));
				respuestaWebService.add(parametroACOWebService);
				
				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("nombreArchivoEntradaSalida");
				parametroACOWebService.setValor(getNombreArchivoEntradaSalida(interfazResult));
				respuestaWebService.add(parametroACOWebService);
				
				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("numeroLote");
				parametroACOWebService.setValor(interfazResult.getNumeroLote());
				respuestaWebService.add(parametroACOWebService);
				
				objetoRespuesta.setRespuestaWebService(respuestaWebService.toArray(new ParametroACOWebService[respuestaWebService.size()]));				
			}
	     
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
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazAbstractWebService#afterExecuteInterfaz(java.util.Map, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazExecutionResult)
	 */
	@Override
	protected void afterExecuteInterfaz(Map params, SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult) throws Exception {
		super.afterExecuteInterfaz(params, interfazExecutionResult);
		interfazSiCCService.executeGenerarXMLBoletasRecojo(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazAbstractWebService#beforeExecuteInterfaz(java.util.Map)
	 */
	@Override
	protected void beforeExecuteInterfaz(Map params) {
		super.beforeExecuteInterfaz(params);
		interfazSiCCService.executeGenerarBoletasRecojo(params);
	}

}
