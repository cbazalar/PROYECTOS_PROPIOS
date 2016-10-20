/**
 * 
 */
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
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.edu.model.RegionCursoCapacitacion;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoDATEnviarArchivosEducacionWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUCursoCapacitacionService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUCalificacionAptasAutomaticaService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;

/**
 * 
 * TODO Include class description here. 
 * 
 * <p>
 * <a href="ACOProcesoDATEnviarArchivosEducacionWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public class ACOProcesoDATEnviarArchivosEducacionWebServiceImpl extends BaseInterfazAbstractWebService implements ACOProcesoDATEnviarArchivosEducacionWebService {

	MantenimientoEDUGenericoService mantenimientoEDUGenericoService;
	ProcesoEDUCalificacionAptasAutomaticaService procesoEDUCalificacionAptasAutomaticaService;
	MantenimientoEDUCursoCapacitacionService mantenimientoEDUCursoCapacitacionService;
	AjaxService ajaxService;	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		this.mantenimientoEDUGenericoService = (MantenimientoEDUGenericoService)getWebApplicationContext().getBean("edu.mantenimientoEDUGenericoService");
		this.procesoEDUCalificacionAptasAutomaticaService = (ProcesoEDUCalificacionAptasAutomaticaService)getWebApplicationContext().getBean("edu.procesoEDUCalificacionAptasAutomaticaService");
		this.ajaxService = (AjaxService)getWebApplicationContext().getBean("ajaxService");
		this.mantenimientoEDUCursoCapacitacionService = (MantenimientoEDUCursoCapacitacionService)getWebApplicationContext().getBean("edu.mantenimientoEDUCursoCapacitacionService");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoDATEnviarArchivosEducacionWebService#ejecutarProceso(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProceso(String codigoPais,
			String codigoUsuario,
			String codigoEmpresa, 
			String codigoPeriodo, 
			String codigoRegion,
			String tipoEnvio)
			throws RemoteException {
		
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";
		String codigoEmpresaView = "";
		String codigoPeriodoView = "";
		String codigoRegionView = "";
		final String CODIGO_INTERFAZ = Constants.DAT_CODIGO_INTERFAZ_ENVIAR_ARCHIVOS_EDUCACION;
		final String CODIGO_SISTEMA = Constants.CODIGO_SISTEMA_DAT;
		final String INDICADOR_CIERRE = "0";
		final String INDICADOR_SISTEMA = "1";
				
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ACOProcesoDATEnviarArchivosEducacion");
		}
		try{
			Pais pais = this.paisService.getPais(codigoPais);
			if(StringUtils.isBlank(codigoPais) || StringUtils.isEmpty(codigoPais)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null,
				getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			
	        /**
			 * Validar codigoEmpresa
			 */
			EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();
			parametroEmpresa.setCodigoPais(pais.getCodigo());
			parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);				
			List listaEmpresa = this.mantenimientoEDUGenericoService.getEmpresasComercializadorasByPais(parametroEmpresa);
			if(StringUtils.isBlank(codigoEmpresa) || StringUtils.isEmpty(codigoEmpresa)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteTipoProceso",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}else{		    	
				EqualPredicate nameEqlPredicate = new EqualPredicate(codigoEmpresa);
				BeanPredicate beanPredicate = new BeanPredicate("codigoEmpresa", nameEqlPredicate);	
				if(listaEmpresa.size()!=0){
					if(!CollectionUtils.exists(listaEmpresa, beanPredicate)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCodigoEmpresa",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de codigo de empresa vacio.");
				}				
			}
			
			if (listaEmpresa != null && listaEmpresa.size() > 0) {
				EmpresaComercializadora empresa = (EmpresaComercializadora) listaEmpresa.get(0);
				codigoEmpresa = empresa.getCodigoEmpresa();
				// Obteniendo Campa�a de Proceso					
				Map criteria = new HashMap();
				criteria.put("codigoPais", codigoPais);
				criteria.put("codigoEmpresa", codigoEmpresa);
				codigoPeriodo = this.procesoEDUCalificacionAptasAutomaticaService.getCampannaActualProceso(criteria);				
			}
		   
			/**
		     * Validar codigoEnvio
		     */
	    	String []tiposProceso = new String[]{Constants.EDU_TIPO_PROCESO_NORMAL, Constants.EDU_TIPO_PROCESO_REPROCESO, Constants.EDU_TIPO_PROCESO_REENVIO};
	    	if(!ArrayUtils.contains(tiposProceso, tipoEnvio)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteTipoProceso",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
	    	}
		   

		    /**
		     * Valida codigoPeriodo
		     */
	    	/*Periodo Actual*/
			codigoPeriodo= this.ajaxService.getCampannaActualEDUByPaisEmpresa(pais.getCodigo(), codigoEmpresa);
					    
			/**
		     * Valida region
		     */
			/* Encontrando Regiones */
			RegionCursoCapacitacion regionCursoCapacitacion = new RegionCursoCapacitacion();
			regionCursoCapacitacion.setCodigoPais(pais.getCodigo());
			regionCursoCapacitacion.setCodigoEmpresa(codigoEmpresa);
			LabelValue[] regiones = this.getRegiones(regionCursoCapacitacion);
			List listadoRegiones = new ArrayList();
			listadoRegiones.add(new LabelValue("Todos", "00"));
			if(StringUtils.isBlank(codigoRegion)|| StringUtils.isEmpty(codigoRegion)){
				codigoRegion = "00";
			}else{
				if(regiones.length > 0){
					listadoRegiones.addAll(Arrays.asList(regiones));
					EqualPredicate nameEqlPredicate = new EqualPredicate(codigoRegion);
					BeanPredicate beanPredicate = new BeanPredicate("value", nameEqlPredicate);
					if(!CollectionUtils.exists(listadoRegiones, beanPredicate)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCodigoRegion",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
			        	throw new Exception(mensajeError);
					}
				}else{
					log.debug("Lista de codigo de region vacio.");
				}				
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
			
	        criteria.put("codigoPais", pais.getCodigo());
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
	    	criteria.put("codigoUsuario", codigoUsuario);			
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("codigoEmpresa", codigoEmpresa);
			criteria.put("tipoEnvio", tipoEnvio);
			criteria.put("codigoRegion", codigoRegion);
			criteria.put("codigoPrograma", Constants.EDU_PARAMETROS_PROGRAMA_LBEL);
			criteria.put("usuarioLogin", usuario.getLogin());
			criteria.put("indicadorSistema", INDICADOR_SISTEMA);
			criteria.put("indicadorCierre", INDICADOR_CIERRE);
			criteria.put("usuario", usuario);
			criteria.put("tipoEnvio",tipoEnvio);
			
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
		Usuario usuario = this.obtenerUsuarioByDefault((String)params.get("codigoUsuario"));
		params.put("codigoPrograma", Constants.EDU_PARAMETROS_PROGRAMA_LBEL );
		params.put("usuarioLogin", usuario.getLogin());
		params.put("codigoRegion", ((String)params.get("codigoRegion")).equals("00")? null : (String)params.get("codigoRegion"));
		
		String tipoEnvio = (String) params.get("tipoEnvio");
		
		/* En caso sea un reproceso se borra primero Tabla de Historico de Interfaz Datamart */
		//debido auq ya no hya reenvio 
		this.mantenimientoEDUGenericoService.deleteTemporalInterfazDatamart(params);
		
		if (Constants.EDU_TIPO_ENVIO_REPROCESO.equals(tipoEnvio)) {
			//service.deleteTemporalInterfazDatamart(params);
			params.put("tipoEnvio",Constants.EDU_TIPO_ENVIO_NORMAL);
		}	
		return params;
	}

	@Override
	protected void afterExecuteInterfaz(Map params,
			SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult) throws Exception {
		super.afterExecuteInterfaz(params, interfazExecutionResult);
		if (log.isDebugEnabled()) {
			log.debug("Entering 'afterExecuteInterfaz' method");
			log.debug(params);
		}
		 
		if (interfazExecutionResult.ejecucionCompletada()) {
			String tipoEnvio = (String) params.get("tipoEnvio");
			if (Constants.EDU_TIPO_ENVIO_NORMAL.equals(tipoEnvio)) {
				this.mantenimientoEDUGenericoService.updateNroLoteInterfazDatamart(params);
			}	
		}
		log.debug("Fin 'afterExecuteInterfaz' method");
	}
	
	private LabelValue[] getRegiones(RegionCursoCapacitacion regionCursoCapacitacion) {
		LabelValue[] result = null;
			try {
				List regiones = this.mantenimientoEDUCursoCapacitacionService.getRegion(regionCursoCapacitacion);
				if (regiones != null && regiones.size() > 0) {
						result = new LabelValue[regiones.size()];
						for (int i = 0; i < regiones.size(); i++) {
							Base region = new Base();
							RegionCursoCapacitacion detalle = new RegionCursoCapacitacion();
							detalle = (RegionCursoCapacitacion) regiones.get(i);
							region.setCodigo(detalle.getCodigoRegion());
							region.setDescripcion(detalle.getCodigoRegion()+" - "+detalle.getDescripcionRegion());
							// Construimos la descripcion
							LabelValue lv = new LabelValue(region.getDescripcion(), region.getCodigo());
							result[i] = lv;
					}
				} else {
					// Creamos una primera opci�n vac�a
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}

			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		return result;
	}
}
