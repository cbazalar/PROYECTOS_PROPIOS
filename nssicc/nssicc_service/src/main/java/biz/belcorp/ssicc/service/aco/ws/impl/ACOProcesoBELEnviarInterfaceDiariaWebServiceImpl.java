package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoBELEnviarInterfaceDiariaWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoBELEnviarInterfaceDiariaWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
public class ACOProcesoBELEnviarInterfaceDiariaWebServiceImpl extends BaseInterfazHiloAbstractWebService implements ACOProcesoBELEnviarInterfaceDiariaWebService {

	InterfazSiCCService interfazSiCCService;
	HistoricoService historicoService;
	AjaxService ajaxService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazHiloAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		this.interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
		this.mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		this.historicoService = (HistoricoService) getWebApplicationContext().getBean("sisicc.historicoService");
		this.ajaxService = (AjaxService) getWebApplicationContext().getBean("ajaxService");
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoBELEnviarInterfaceDiariaWebService#ejecutarProcesoBELEnviarInterfaceDiaria(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoBELEnviarInterfaceDiaria(
			String codigoPais, String codigoMarca, String codigoCanal,
			String codigoAcceso, String codigoPeriodo, String fechaFacturacion,
			String codigoTipoCliente, String codigoUsuario)
			throws RemoteException {

		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";		
		final String CODIGO_BATCH = Constants.BEL_CODIGO_PROCESO_BATCH_ENVIAR_INTERFACES_DIARIAS;
		final String CODIGO_INTERFAZ = Constants.BEL_CODIGO_INTERFAZ_ENVIAR_INTERFACES_DIARIAS;
		final String CODIGO_SISTEMA = Constants.BEL_CODIGO_SISTEMA;
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ejecutarProcesoBELEnviarInterfaceDiaria");
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
			
			String conexionExterna = pais.getCodigoConexionExterna();
			Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);
			
			Map criteria = new HashMap();
			
			// Asignamos al codigo del periodo el valor por defecto
	        criteria.put("codigoPais", pais.getCodigo());
	        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		    criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		    criteria.put("codigoISO",usuario.getIdioma().getCodigoISO());
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
	    	criteria.put("codigoUsuario", codigoUsuario);
		    
			List siccMarcaList = this.interfazSiCCService.getMarcas();
			List siccCanalList = this.interfazSiCCService.getCanalesByCodigoISO(pais.getCodigoIdiomaIso());
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
			 * Tipo cliente
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
									
			if (StringUtils.isBlank(codigoPeriodo) || StringUtils.isEmpty(codigoPeriodo)) {
	        	codigoPeriodo = ajaxService.getPeriodoDefaultByPaisMarcaCanalAcceso(codigoPais, codigoMarca, codigoCanal, codigoAcceso);
			}
			if(StringUtils.isBlank(fechaFacturacion) || StringUtils.isEmpty(fechaFacturacion)){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				fechaFacturacion = sdf.format(new Date());
	        }
			
			//Validamos si la fecha esta en el rango
			String fechas[] = ajaxService.getIntervalosFechaFacturasCabecera(codigoPeriodo, codigoPais, codigoMarca, codigoCanal, codigoAcceso);
			String fechaInicio;
			String fechaFin;
			if(fechas != null && fechas.length > 0)
			{
				fechaInicio = fechas[0];
				fechaFin = fechas[1];
			
				if(StringUtils.isNotBlank(fechaInicio) && StringUtils.isNotBlank(fechaFin))
				{	
					Date d_ffa = DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, fechaFacturacion);
		        	Date d_fi = DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, fechaInicio);
					Date d_ff = DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT, fechaFin);
					
					if(!(d_ffa.compareTo(d_fi) >= 0 && d_ffa.compareTo(d_ff) <= 0))
					{
						mensajeError = getWebApplicationContext().getMessage("interfazBELEnviarInterfaceDiariaForm.error.rango.fechaFacturacion",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
			        }
		        }
				
			}
			//
			
			criteria.put("fechaFacturacion", fechaFacturacion);
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoAcceso", codigoAcceso);
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
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazHiloAbstractWebService#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.model.Pais)
	 */
	@Override
	protected Map prepareParamsBeforeExecute(Map params, Pais pais) throws Exception {
				
		List historicos = historicoService.getUltimoHistoricoByCodInterfaz(params);
		if (historicos != null) {
	            if (historicos.size() == 1)
	                params.put("fechaInicioUltimoProceso", ((Historico) historicos
	                        .get(0)).getFechaInicioProceso());
	        } else {
	            params.put("fechaInicioUltimoProceso", null);
	        }
		
		params.put("codigoIdiomaIso", pais.getCodigoIdiomaIso());
		params.put("codigoIso", pais.getCodigoIdiomaIso());
		
		return super.prepareParamsBeforeExecute(params, pais);
	}	
}
