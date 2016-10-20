package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoSICEnviarInscritasWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoRECEnviaMsgReclamoWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public class ACOProcesoSICEnviarInscritasWebServiceImpl extends BaseInterfazAbstractWebService implements ACOProcesoSICEnviarInscritasWebService {

	AjaxService ajaxService;
	InterfazSiCCService interfazSiCCService;
	HistoricoService historicoService;
	
	protected void onInit() throws ServiceException {
		super.onInit();
		this.ajaxService = (AjaxService)getWebApplicationContext().getBean("ajaxService");
		this.interfazSiCCService = (InterfazSiCCService)getWebApplicationContext().getBean("sisicc.interfazSiCCService");		
		this.historicoService = (HistoricoService)getWebApplicationContext().getBean("sisicc.historicoService");
	}

	
	public ACOWebServiceResponse ejecutarProcesoSICEnviarInscritas(
			String codigoUsuario,
			String codigoPais,
			String codigoMarca,
			String codigoCanal,
			String codigoTipoCliente,
			String codigoPeriodo,
			String codigoTipoVinculo,
			String[] codigoSubTipoCliente,
			String[] codigoTipoClasificacion,
			String[] codigoClasificacion) throws RemoteException {
		
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";		
		final String CODIGO_INTERFAZ = Constants.ECM_CODIGO_INTERFAZ_ENVIAR_INSCRITAS;
		final String CODIGO_SISTEMA = Constants.ECM_CODIGO_SISTEMA;
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso ejecutarProcesoSICEnviarInscritas");
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
		    
		    List siccMarcaList = this.interfazSiCCService.getMarcas();
			List siccCanalList = this.interfazSiCCService.getCanalesByCodigoISO(pais.getCodigoIdiomaIso());
			List siccTipoClienteList = this.interfazSiCCService.getTiposClientesByCodigoISO(usuario.getIdioma().getCodigoISO());
			List siccVinculoList = this.interfazSiCCService.getTiposVinculosByPais(pais.getCodigo());
			LabelValue[] siccPeriodoList = this.ajaxService.getPeriodosByPaisMarcaCanalRangos(pais.getCodigo(),
																					Constants.CODIGO_MARCA_DEFAULT,
																					Constants.CODIGO_CANAL_DEFAULT, "0", "1");
						
			
			ArrayList temp = new ArrayList();
			temp = new ArrayList();
			temp.add(Constants.CODIGO_TIPO_CLIENTE_DEFAULT);
			LabelValue[] siccSubTipoClienteList = this.ajaxService.getSubTiposClientesPorPaisTipoClientes(usuario.getIdioma().getCodigoISO(), temp);

			temp = new ArrayList();
			temp.add(Constants.CODIGO_TIPO_CLIENTE_DEFAULT + "-" + Constants.CODIGO_SUBTIPO_CLIENTE_DEFAULT);
			LabelValue[] siccTipoClasificacionList = this.ajaxService.getTiposClasificacionesByCriteriaMultiple(usuario.getIdioma().getCodigoISO(), Constants.CODIGO_TIPO_CLIENTE_DEFAULT, temp);
			
			ArrayList temp2 = new ArrayList();
			temp2 = new ArrayList();
			temp2.add(Constants.CODIGO_TIPO_CLASIFICACION_DUPLA + Constants.CODIGO_TIPO_CLIENTE_DEFAULT	+ Constants.CODIGO_SUBTIPO_CLIENTE_DEFAULT);
			LabelValue[] siccClasificacionList = this.ajaxService.getClasificacionesByCriteriaMultiple(usuario.getIdioma()
							.getCodigoISO(), Constants.CODIGO_TIPO_CLIENTE_DEFAULT,
							temp, temp2);

			/**
			 * Valida codigoMarca
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
			 * Valida codigoCanal
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
			 * Valida codigoTipoCliente
			 */
			if(StringUtils.isBlank(codigoTipoCliente) || StringUtils.isEmpty(codigoTipoCliente)){
				codigoTipoCliente = Constants.CODIGO_TIPO_CLIENTE_DEFAULT;
			}else{
				EqualPredicate nameEqlPredicate = new EqualPredicate(codigoTipoCliente);
				BeanPredicate beanPredicate = new BeanPredicate("codigo", nameEqlPredicate);
				if(siccTipoClienteList.size()!=0){
					if(!CollectionUtils.exists(siccTipoClienteList, beanPredicate)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteTipoCliente",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de codigo tipo cliente vacio.");
				}
			}
			
			/**
			 * Valida codigoPeriodo
			 */
			if(StringUtils.isBlank(codigoPeriodo) || StringUtils.isEmpty(codigoPeriodo)){
				codigoPeriodo = this.ajaxService.getPeriodoDefaultByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT);
			}else{
				codigoPeriodo = this.ajaxService.getPeriodoDefaultByPaisMarcaCanal(pais.getCodigo(), codigoMarca, codigoCanal);
				log.debug("Obteniedo codigo periodo por codigo canal y marca ingresado");
			}
			
			/**
			 * Valida codigoTipoVinculo
			 */
			if(StringUtils.isBlank(codigoTipoVinculo) || StringUtils.isEmpty(codigoTipoVinculo)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarCodTipoVinculo", null,
								getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}else{
				EqualPredicate nameEqlPredicate = new EqualPredicate(codigoTipoVinculo);
				BeanPredicate beanPredicate = new BeanPredicate("codigo", nameEqlPredicate);
				if(siccVinculoList.size()!=0){
					if(!CollectionUtils.exists(siccVinculoList, beanPredicate)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteTipoCliente",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de codigo tipo cliente vacio.");
				}
			}
			
			/**
			 * Valida codigoSubTipoCliente
			 */
			if(codigoSubTipoCliente == null || codigoSubTipoCliente.length == 0){
				 codigoSubTipoCliente = new String[] { Constants.CODIGO_TIPO_CLIENTE_DEFAULT
							+ "-" + Constants.CODIGO_SUBTIPO_CLIENTE_DEFAULT };
			}else{				
				temp = new ArrayList();
				temp.add(codigoTipoCliente);
				siccSubTipoClienteList = this.ajaxService.getSubTiposClientesPorPaisTipoClientes(usuario.getIdioma().getCodigoISO(), temp);
				int cont = 0;
				cont = this.comporarArraysBean(Arrays.asList(siccSubTipoClienteList), Arrays.asList(codigoSubTipoCliente));
				if(cont==0){
					mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteSubTipoCliente",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
					throw new Exception(mensajeError);
				}
			}
			
			/**
			 * Valida codigoTipoClasificacion
			 */
			if(codigoTipoClasificacion == null || codigoTipoClasificacion.length == 0){
				codigoTipoClasificacion = new String[] { Constants.CODIGO_TIPO_CLASIFICACION_DUPLA
						+ Constants.CODIGO_TIPO_CLIENTE_DEFAULT
						+ Constants.CODIGO_SUBTIPO_CLIENTE_DEFAULT };
			}else{
				temp = new ArrayList();
				for(Object object : codigoSubTipoCliente){
					String valor = (String)object;
					temp.add(valor);
				}
				siccTipoClasificacionList = this.ajaxService.getTiposClasificacionesByCriteriaMultiple(usuario.getIdioma().getCodigoISO(), 
												codigoTipoCliente, 
												temp);
				int cont = 0;
				cont = this.comporarArraysBean(Arrays.asList(siccTipoClasificacionList), Arrays.asList(codigoTipoClasificacion));
				if(cont==0){
					mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteTipoClasificacion",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
					throw new Exception(mensajeError);
				}
			}
			
			/**
			 * Valida codigoClasificacion
			 */
			if(codigoClasificacion == null || codigoClasificacion.length == 0){
				codigoClasificacion = new String[] { Constants.CODIGO_TIPO_CLASIFICACION_DUPLA
						+ Constants.CODIGO_TIPO_CLIENTE_DEFAULT
						+ Constants.CODIGO_SUBTIPO_CLIENTE_DEFAULT
						+ Constants.CODIGO_CLASIFICACION_DEFAULT };
			}else{
				temp = new ArrayList();
				temp.addAll(Arrays.asList(codigoSubTipoCliente));
				temp2 = new ArrayList();
				temp2.addAll(Arrays.asList(codigoTipoClasificacion));
				siccTipoClasificacionList = this.ajaxService.getClasificacionesByCriteriaMultiple(usuario.getIdioma().getCodigoISO(), 
											codigoTipoCliente,
											temp, 
											temp2);
				int cont = 0;
				cont = this.comporarArraysBean(Arrays.asList(siccTipoClasificacionList), Arrays.asList(codigoClasificacion));
				if(cont==0){
					mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteTipoClasificacion",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
					throw new Exception(mensajeError);
				}
			}
			
			
			Map criteria = new HashMap();			
			// Asignamos al codigo del periodo el valor por defecto
	        criteria.put("codigoPais", pais.getCodigo());
	        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		    criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
	        criteria.put("codigoUsuario", codigoUsuario);
	        criteria.put("codigoPeriodo", codigoPeriodo);
		    criteria.put("usuario", usuario);
		    criteria.put("codigoTipoClasificacion", codigoTipoClasificacion);
		    criteria.put("codigoSubTipoCliente", codigoSubTipoCliente);
		    criteria.put("codigoClasificacion", codigoClasificacion);
			
	        executionResult = this.executeInterfaz(criteria);
	        
	        List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult) list.get(0);
		
			if (interfazResult != null) {
				estado = interfazResult.isCompletado();
				objetoRespuesta.setEjecucionExitosa(estado);
				objetoRespuesta.setMensaje(interfazResult.getMensaje());
				
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


	@Override
	protected Map prepareParamsBeforeExecute(Map params, Pais pais)
			throws Exception {
		params = super.prepareParamsBeforeExecute(params, pais);
		List historicos = this.historicoService.getUltimoHistoricoByCodInterfaz(params);
		if (historicos != null) {
			if (historicos.size() == 1)
				params.put("fechaInicioUltimoProceso", ((Historico) historicos
						.get(0)).getFechaInicioProceso());
		} else {
			params.put("fechaInicioUltimoProceso", null);
		}		
		return params;
	}	
	
}
