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
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoRETEnviarInformacionRetailWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.sisicc.InterfazDATService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoRECEnviaMsgReclamoWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public class ACOProcesoRETEnviarInformacionRetailWebServiceImpl extends BaseInterfazHiloAbstractWebService implements ACOProcesoRETEnviarInformacionRetailWebService{

	InterfazSiCCService interfazSiCCService;
	InterfazDATService interfazDATService; 
	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazHiloAbstractWebService#onInit()
	 */
	protected void onInit() throws ServiceException {
		super.onInit();
		this.interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
		this.interfazDATService = (InterfazDATService)getWebApplicationContext().getBean("sisicc.interfazDATService");
		this.mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
	}

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoOCREnvArchivosOCRDirWebService#ejecutarProcesoOCREnvArchivosOCRDir(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoRETEnviarInformacionRetail(
			String codigoUsuario,
			String codigoPais,			
			String campanaFacturacion,
			String fechaFacturacion) throws RemoteException {
		
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";		
		final String CODIGO_BATCH = Constants.RET_CODIGO_PROCESO_BATCH_ENVIAR_INFORMACION_RETAIL;
		final String CODIGO_INTERFAZ = Constants.RET_CODIGO_INTERFAZ_ENVIAR_INFORMACION_RETAIL;
		final String CODIGO_SISTEMA = Constants.CODIGO_SISTEMA_RET;
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz InterfazRETEnviarInformacionRetail");
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
			
			Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);
			
			Map criteria = new HashMap();
			
			// Asignamos al codigo del periodo el valor por defecto
	        criteria.put("codigoPais", pais.getCodigo());
	        criteria.put("estadoCampanha",Constants.DAT_PARAM_STA_CAMP_CERO); // Indica Campanha Activa 
		    criteria.put("indicadorActiva",Constants.DAT_PARAM_IND_CAMP_ACT_UNO); // Indica Campanha activa q se procesa actualmente
		    criteria.put("codigoISO",usuario.getIdioma().getCodigoISO());
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
	    	criteria.put("codigoUsuario", codigoUsuario);
			
			PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);
			campanaFacturacion = controlFacturacion.getCodigoPeriodo();
			if(StringUtils.isBlank(fechaFacturacion) || StringUtils.isEmpty(fechaFacturacion)){
				fechaFacturacion = controlFacturacion.getFechaProceso();
	        }
			
			criteria.put("tipoCierre", Constants.LET_TIPO_CIERRE_CAMPANHA);
			criteria.put("estadoCierre", Constants.DAT_ESTADO_CIERRE);			
			criteria.put("codigoPeriodo", campanaFacturacion);
			criteria.put("fechaFacturacion", fechaFacturacion);
			criteria.put("tipoProceso", "I");
			
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
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazAbstractWebService#beforeExecuteInterfaz(java.util.Map)
	 */
	@Override
	protected void beforeExecuteInterfaz(Map params) {
		super.beforeExecuteInterfaz(params);    	
		this.interfazDATService.executeCargarTablaInterfaz(params);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazHiloAbstractWebService#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.model.Pais)
	 */
	@Override
	protected Map prepareParamsBeforeExecute(Map params, Pais pais)
			throws Exception {
		Map param = super.prepareParamsBeforeExecute(params, pais);		
		params.put("codigoIdiomaIso", pais.getCodigoIdiomaIso());
		params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		return param;
	}
	
}
