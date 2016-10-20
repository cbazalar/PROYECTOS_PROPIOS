package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoREUEnviarMaestrosWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.let.MantenimientoLETLideresService;
import biz.belcorp.ssicc.service.spusicc.men.ProcesoMENCargaMasivaInformacionMensajesService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoRECEnviaMsgReclamoWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public class ACOProcesoREUEnviarMaestrosWebServiceImpl extends BaseInterfazHiloAbstractWebService implements ACOProcesoREUEnviarMaestrosWebService{

	InterfazSiCCService interfazSiCCService;
	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	MantenimientoLETLideresService mantenimientoLETLideresService;
	ProcesoMENCargaMasivaInformacionMensajesService procesoMENCargaMasivaInformacionMensajesService;
	AjaxService ajaxService;
	HistoricoService historicoService; 
	
	protected void onInit() throws ServiceException {
		super.onInit();
		this.interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
		this.mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		this.mantenimientoLETLideresService = (MantenimientoLETLideresService)getWebApplicationContext().getBean("spusicc.mantenimientoLETLideresService");
		this.procesoMENCargaMasivaInformacionMensajesService = (ProcesoMENCargaMasivaInformacionMensajesService)getWebApplicationContext().getBean("spusicc.procesoMENCargaMasivaInformacionMensajesService");
		this.ajaxService = (AjaxService)getWebApplicationContext().getBean("ajaxService");
		this.historicoService = (HistoricoService)getWebApplicationContext().getBean("sisicc.historicoService");
	}

	
	public ACOWebServiceResponse ejecutarProcesoREUEnviarMaestros(
			String codigoUsuario, 
			String codigoPais,
			String codigoMarca,
			String codigoCanal,
			String periodoDesde,
			String periodoHasta,
			String codigoActividad,
			String codigoTipoCliente,
			String codigoAcceso,
			String fechaFacturacion,
			String codigoPeriodo) throws RemoteException {
		
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";		
		final String CODIGO_BATCH = "02";
		final String CODIGO_INTERFAZ = "REU-P1";
		final String CODIGO_SISTEMA = "REU";
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ejecutarProcesoREUEnviarMaestros");
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
			
//			Usuario usuario = this.usuarioService.getUsuarioByUsername(codigoUsuario);
			Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);
			
			Map criteria = new HashMap();
			
			// Asignamos al codigo del periodo el valor por defecto
	        criteria.put("codigoPais", pais.getCodigo());
	        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		    criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		    criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
	        criteria.put("codigoUsuario", codigoUsuario);
	        
	        PedidoControlFacturacion pedidoControlFacturacion = this.mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);
	        if (StringUtils.isBlank(codigoPeriodo) || StringUtils.isEmpty(codigoPeriodo)) {
	        	codigoPeriodo = pedidoControlFacturacion.getCodigoPeriodo();
	        	
			}
			criteria.put("codigoPeriodo", codigoPeriodo);
			
			if(StringUtils.isBlank(periodoDesde) || StringUtils.isEmpty(periodoDesde)){
				periodoDesde = pedidoControlFacturacion.getCodigoPeriodo();				
			}
			
			if(StringUtils.isNotBlank(periodoDesde) || StringUtils.isNotEmpty(periodoDesde)){
				Map criteriaOid = new HashMap();
		        criteriaOid.put("codigoPais", pais.getCodigo());
		        criteriaOid.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		        criteriaOid.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		        		
				Map params = new HashMap();
				params.put("codigoPais",pais.getCodigo());
				params.put("codigoPeriodo", periodoDesde);
				params.put("numeroCampanna", 3);
				params.put("oidPais", this.mantenimientoLETLideresService.getOidPaisByCodigoPaisLET(criteriaOid));
				periodoHasta =  this.procesoMENCargaMasivaInformacionMensajesService.getDevuelveCodigoCampanha(params);
			}
			criteria.put("periodoDesde", periodoDesde);
			criteria.put("periodoHasta", periodoHasta);
			    		
			List siccMarcaList = this.interfazSiCCService.getMarcas();
			List siccCanalList = this.interfazSiCCService.getCanalesByCodigoISO(pais.getCodigoIdiomaIso());
			List siccActividadList = this.interfazSiCCService.getActividadesByCodigoISO(usuario.getIdioma().getCodigoISO());
			List siccAccesoList = this.interfazSiCCService.getAccesosByCanalByCodigoISO(usuario.getIdioma().getCodigoISO());
			List siccTipoClienteList = this.interfazSiCCService.getTiposClientesByCodigoISO(usuario.getIdioma().getCodigoISO());
			
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
			
			/**
			 * Si acceso es vacio o en blanco, traer el de por defecto
			 */
			if(StringUtils.isBlank(codigoAcceso) || StringUtils.isEmpty(codigoAcceso)){
				codigoAcceso = Constants.CODIGO_ACCESO_DEFAULT;		
			}else{
				EqualPredicate nameEqlPredicate = new EqualPredicate(codigoAcceso);
				BeanPredicate beanPredicate = new BeanPredicate("codigoAcceso", nameEqlPredicate);					
				if(siccAccesoList.size()!=0){
					if(!CollectionUtils.exists(siccAccesoList,beanPredicate)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteAcceso",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de acceso vacio.");
				}
			}
			
			/**
			 * Si actividad es vacio o en blanco, traer el de por defecto
			 */
			if(StringUtils.isBlank(codigoActividad) || StringUtils.isEmpty(codigoActividad)){
				codigoActividad = Constants.CODIGO_ACTIVIDAD_DEFAULT;		
			}else{
				EqualPredicate nameEqlPredicate = new EqualPredicate(codigoActividad);
				BeanPredicate beanPredicate = new BeanPredicate("codigoActividad", nameEqlPredicate);					
				if(siccActividadList.size()!=0){
					if(!CollectionUtils.exists(siccActividadList,beanPredicate)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteActividad",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de actividad vacio.");
				}
			}
			
			/**
			 * Si tipo cliente es vacio o en blanco, traer el de por defecto
			 */
			if(StringUtils.isBlank(codigoTipoCliente) || StringUtils.isEmpty(codigoTipoCliente)){
				codigoTipoCliente = Constants.CODIGO_TIPO_CLIENTE_DEFAULT;		
			}else{
				EqualPredicate nameEqlPredicate = new EqualPredicate(codigoTipoCliente);
				BeanPredicate beanPredicate = new BeanPredicate("codigo", nameEqlPredicate);					
				if(siccTipoClienteList.size()!=0){
					if(!CollectionUtils.exists(siccTipoClienteList,beanPredicate)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteTipoCliente",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de tipo cliente vacio.");
				}
			}
			
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("codigoAcceso", codigoAcceso);
			criteria.put("codigoActividad", codigoActividad);
			criteria.put("codigoTipoCliente", codigoTipoCliente);
			
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
		List historicos = this.historicoService.getUltimoHistoricoByCodInterfaz(params);
	    if (historicos != null) {
	        if (historicos.size() == 1){
	        	params.put("fechaInicioUltimoProceso", ((Historico) historicos.get(0)).getFechaInicioProceso());
	        }
        } else {
            params.put("fechaInicioUltimoProceso", null);
        }
		return super.prepareParamsBeforeExecute(params, pais);
	}

	protected void afterExecuteInterfaz(Map params,	SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult) throws Exception {
		if(log.isDebugEnabled()) {
            log.debug("Dentro del metodo 'afterExecuteInterfaz'");
        }
		this.interfazSiCCService.updateInterfazREUIndicadorTransferenciaClientes();
		super.afterExecuteInterfaz(params, interfazExecutionResult);
	}
	
}
