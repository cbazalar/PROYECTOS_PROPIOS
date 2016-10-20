package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang.StringUtils;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoLETEjecProcWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
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
public class ACOProcesoLETEjecProcWebServiceImpl extends BaseInterfazHiloAbstractWebService implements ACOProcesoLETEjecProcWebService {

	InterfazSiCCService interfazSiCCService;
	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	AjaxService ajaxService;
	
	protected void onInit() throws ServiceException {
		super.onInit();
		this.interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
		this.mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		this.ajaxService = (AjaxService) getWebApplicationContext().getBean("ajaxService");
	}
	
	public ACOWebServiceResponse ejecutarProcesoLETEjecutarProcesos(
			String codigoUsuario,
			String codigoPais,
			String codigoMarca,
			String codigoCanal,
			String codigoPeriodo,
			String codigoRegion) throws RemoteException {
		
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";		
		final String CODIGO_BATCH = Constants.LET_CODIGO_PROCESO_BATCH_EJECUTAR_PROCESOS_LET;
		final String CODIGO_INTERFAZ = Constants.LET_CODIGO_INTERFAZ_EJECUTAR_PROCESOS_LET;
		final String CODIGO_SISTEMA = Constants.LET_CODIGO_SISTEMA;
		final String TIPO_CIERRE = "R";
		final String TIPO_TRANSACCION = "1";
		final String TIPO_OPERACION = "2";
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso ejecutarProcesoLETEjecutarProcesos");
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
	        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		    criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
	        criteria.put("tipoCierre", TIPO_CIERRE);
	        criteria.put("tipoTransaccion", TIPO_TRANSACCION);
	        criteria.put("tipoOperacion", TIPO_OPERACION);
	    	criteria.put("codigoUsuario", codigoUsuario);
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
			
			
			PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);
			if (StringUtils.isBlank(codigoPeriodo) || StringUtils.isEmpty(codigoPeriodo)) {
	        	codigoPeriodo = controlFacturacion.getCodigoPeriodo();
			}
							
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("codigoMarca", codigoMarca);
						
			if(StringUtils.isNotBlank(codigoPeriodo) &&
				StringUtils.isNotBlank(codigoMarca) &&
				StringUtils.isNotBlank(codigoCanal)){
				LabelValue[] labelValues = this.ajaxService.getRegionesActivasCerradasByPeriodo(codigoPais, codigoPeriodo, codigoMarca, codigoCanal);
				if(labelValues.length != 0){
					List listado = Arrays.asList(labelValues);
					EqualPredicate nameEqlPredicate = new EqualPredicate(codigoRegion);
					BeanPredicate beanPredicate = new BeanPredicate("value", nameEqlPredicate);
					if(!CollectionUtils.exists(listado, beanPredicate)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCodigoRegion",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("lista de regiones vac�o.");
				}
			}
			
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
	
	protected Map prepareParamsBeforeExecute(Map params, Pais pais) throws Exception {
		//Nombre servidor
		SimpleJaxWsServiceExporter exporter = new SimpleJaxWsServiceExporter();
		log.debug(exporter.DEFAULT_BASE_ADDRESS);
		
		Pattern pattern = Pattern.compile("(https?://)([^:^/]*)(:\\d*)?(.*)?");
		Matcher matcher = pattern.matcher(exporter.DEFAULT_BASE_ADDRESS);

		matcher.find();			
		String servidor = matcher.group(2);
		params.put("nombreServidor", servidor);
		
		String tipoCierre = (String)params.get("tipoCierre");		
		if(tipoCierre != null && tipoCierre.equals(Constants.LET_TIPO_CIERRE_REGION)){
			params.put("codigoRegion", (String)params.get("codigoRegion"));
		}else
			params.put("codigoRegion", "-1");
		/* INI JJ PER-SiCC-2012-0297 */
		
		String tipoTransaccion = (String)params.get("tipoTransaccion");
		String tipoOperacion = (String)params.get("tipoOperacion");
		if(tipoTransaccion != null && tipoOperacion != null && tipoTransaccion.equals("1") && tipoOperacion.equals("2")){
			params.put("codigoCliente", "-1");
		}
		/* FIN JJ PER-SiCC-2012-0297 */
		return super.prepareParamsBeforeExecute(params, pais);
	}
	
}
