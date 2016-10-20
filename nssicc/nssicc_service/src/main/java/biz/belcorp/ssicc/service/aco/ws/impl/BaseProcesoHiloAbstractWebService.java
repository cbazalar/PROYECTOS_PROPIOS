package biz.belcorp.ssicc.service.aco.ws.impl;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatchActu;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;

/**
 * Clase Service abstracta para la ejecucion de Interfaces Hilo SiSiCC usando WS
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar La Rosa</a>
 *                   
 */
public abstract class BaseProcesoHiloAbstractWebService extends ServletEndpointSupport {
	
	Log log = LogFactory.getLog(BaseProcesoHiloAbstractWebService.class);
	protected ProcesoBatchService procesoBatchService;
	protected PaisService paisService;
	protected UsuarioService usuarioService;
	protected HistoricoService historicoService;
	
	
	/* (non-Javadoc)
	 * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
	 */
	protected void onInit() throws ServiceException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'onInit BaseInterfazHiloAbstractWebService' method");
		}
		this.usuarioService = (UsuarioService)getWebApplicationContext().getBean("usuarioService");
		this.paisService = (PaisService) getWebApplicationContext().getBean("paisService");
		this.procesoBatchService = (ProcesoBatchService) getWebApplicationContext().getBean("scsicc.procesoBatchService");
		this.historicoService = (HistoricoService) getWebApplicationContext().getBean("sisicc.historicoService");
		
	}
	
	/**
	 * Hook metodo. Utilizado para invocar proceso previos a la ejecucion de la interfaz.
	 * Asimismo dicho metodo actualiza la lista de Procesos Batch Activos de manera que coloca al 
	 * proceso en estado de Ejecucion  
	 */
	protected abstract Map executeProcess(Map params) throws Exception;
	
	
	/**
	 * Ejecuta el Proceso Batch SiSiCC, prepara los parametros para la ejecuci�n 
	 */
	protected void executeProceso(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'executeProceso BaseProcesoHiloAbstractWebService' method");
		}
		
		/* Obteniendo valores a ser seteados al Parametro Map */
		String codigoPais = (String) params.get("codigoPais");
		Pais pais = this.paisService.getPais(codigoPais);
		params = this.prepareParamsBeforeExecute(params, pais);
			
		/******************************************/
		/* Invocando Hilo */
		this.executeHilo(params);	
		return;
		
	}
	
	/**
	 * Ejecuta el proceso Batch SiSiCC, prepara los parametros para la ejecuci�n 
	 */
	protected final void executeHilo(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'executeHilo BaseProcesoHiloAbstractWebService' method");
		}
		
		Usuario usuario = (Usuario) params.get("usuario");
		try {
			params = this.executeProcessBeforeInterfaz(params);
			log.info("queryParams=" + params);
		}
		catch (Exception e) {
			this.updateInterfazRegistroProcesoBatchError(params, usuario, e);
			throw new Exception(e);
		}
		
		try {
			
			params = this.executeProcess(params);
			this.updateInterfazRegistroProcesoBatchOK(params, usuario);		
		}
		catch (Exception e) {
			updateInterfazRegistroProcesoBatchError(params, usuario, e);
			throw new Exception(e);
		}
		
		try {
			this.afterExecuteProcess(params);
		}
		catch (Exception e) {
			throw new Exception(e);
		}	
		
		if (log.isDebugEnabled()) {
			log.debug("Fin 'executeHilo' method");
		}
		return;
		
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.action.BaseAbstractAction#prepareParamsBeforeExecute(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected Map prepareParamsBeforeExecute(Map params, Pais pais) throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParamsBeforeExecute BaseInterfazHiloAbstractWebService' method");
		}
		
		/* Agregando par�metros */
		String codigoUsuario = (String) params.get("codigoUsuario");
		Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);
		//this.usuarioService.getUsuarioByUsername(codigoUsuario);
		params.put("usuario", usuario);
		params.put("pais", pais);
		params.put("codigoPais",pais.getCodigo());
		params.put("descripcionPais",pais.getDescripcion());
		params.put("indicadorEjecucion",Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI);
		params.put("codigoEstadoProceso",Constants.CODIGO_PROCESO_BATCH_EN_EJECUCION);
		params.put("recomendacionError", "    ");
		
			
		/* Verificando que no se encuentre en Ejecucion */
		String keyMensaje = "procesoBatch.error.procesoEnEjecucion";
		List listaProcesoBatch = this.procesoBatchService.getProcesoBatchActuByCriteria(params);
		if (listaProcesoBatch.size() > 0) {
			ProcesoBatchActu procesoBatchActu =  (ProcesoBatchActu) listaProcesoBatch.get(0);
			if (Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI.equals(procesoBatchActu.getIndicadorEjecucion())) {
				String mensaje = getWebApplicationContext().getMessage(keyMensaje, null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensaje);
			}
		}
		
		List listaProcesoBatchDependientes = this.procesoBatchService.getProcesoBatchActuDependientesByCriteria(params);
		if (listaProcesoBatchDependientes.size() > 0) {
			
			String mensaje = getWebApplicationContext().getMessage("procesoBatch.error.procesoDependienteEnEjecucion", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));				
			for (int i = 0; i < listaProcesoBatchDependientes.size(); i++) {
				
				ProcesoBatchActu procesoBatchActu =  (ProcesoBatchActu) listaProcesoBatchDependientes.get(i);
				mensaje  +=  "\n" + procesoBatchActu.getCodigoSistema() + "-" + procesoBatchActu.getProcesoBatch().getCodigoProcesoBatch();
				mensaje  +=  " " + procesoBatchActu.getProcesoBatch().getDescripcionProcesoBatch();
				mensaje += "; ";
			}
			mensaje +=  "\n" + getWebApplicationContext().getMessage("procesoBatch.error.espereProcesoDependienteEnEjecucion", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
			throw new Exception(mensaje);			
		}
		

		/* Obtiene los parametros del proceso batch */
		params.put("procesoBatchParams", this.procesoBatchService.getParametrosProcesoBatch(params));
		
		
        // Remuevo los parametros que no son necesarios del Validator
		params.remove("resultValueMap");
		params.remove("validatorResults");
		params.remove("servletWrapper");
		params.remove("multipartRequestHandler");
		params.remove("class");
		params.remove("page");
		return params;
    }	
	
	
	/**
	 * Hook metodo. Utilizado para invocar proceso previos a la ejecucion de la interfaz.
	 * Asimismo dicho metodo actualiza la lista de Procesos Batch Activos de manera que coloca al 
	 * proceso en estado de Ejecucion  
	 */
	protected Map executeProcessBeforeInterfaz(Map params) throws Exception {
		
        // Paso todos los parametros al map
		Usuario usuario = (Usuario) params.get("usuario");
		params.put("descripcionEtapaProceso",Constants.NUMERO_ETAPA_PROCESO_BATCH_DEFAULT);
		Long idProcesoBatch = this.procesoBatchService.getSecuenciaSiguienteProcesoBatchActu();
		params.put("idProcesoBatch", idProcesoBatch);
		procesoBatchService.deleteProcesoBatchActu(params, usuario);
		procesoBatchService.insertProcesoBatchActu(params, usuario);
		params.put("mostrarPaginaConsultaBatch", Constants.SI);
		return params;
	}

	
	
	
	/**
	 * Metodo que es invocado para cambiar el estado del proceso batch de EN EJECUCION a EN EJECUCION DE INTERFAZ
	 * @param params
	 * @param request
	 * @param usuario
	 */
	private void updateEstadoEjecucionInterfazProcesoBatch(Map params, Usuario usuario) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateInterfazEstadoRegistroProcesoBatch' method");
		}
		params.put("codigoEstadoProceso", Constants.CODIGO_PROCESO_BATCH_EN_GENERACION_INTERFAZ);
		this.procesoBatchService.updateEstadoProcesoBatchActu2(params, usuario);
	}
	
	
	/**
	 * Metodo que es invocado para finalizar la ejecucion del proceso
	 * @param params
	 * @param usuario
	 */
	private void updateInterfazRegistroProcesoBatchOK(Map params, Usuario usuario) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateInterfazRegistroProcesoBatch' method");
		}
		params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);
		params.put("descripcionLog", Constants.ARCHIVO_ENVIADO_GENERICO);
		params.put("codigoEstadoProceso", Constants.CODIGO_PROCESO_BATCH_OK);
		
		this.procesoBatchService.updateProcesoBatchActu(params, usuario);
	}
	
	
	/**
	 * Metodo que es invocado para finalizar la ejecucion del proceso, cuando se haya generado una excepcion
	 * @param params
	 * @param usuario
	 * @param exception
	 */
	private void updateInterfazRegistroProcesoBatchError(Map params, Usuario usuario, Exception exception) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateInterfazRegistroProcesoBatch' method");
		}
		params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);
		String codigoError = this.getCodigoErrorBatch(params);
		if (StringUtils.isNotBlank(codigoError))
			params.put("codigoEstadoProceso",codigoError);
		else 
			params.put("codigoEstadoProceso",Constants.CODIGO_PROCESO_BATCH_ERROR); 
		
		String descripcionLog = exception.getMessage();
		
		if (descripcionLog.length() >= 1000) {
			descripcionLog = descripcionLog.substring(1,999);
		}
		params.put("descripcionLog", descripcionLog);
		this.procesoBatchService.updateProcesoBatchActu(params, usuario);
	}
	
	
	/**
	 * Metodo que es invocado cuando el proceso Batch no ejecuta la interfaz respectiva
	 * a pesar que el proceso Batch haya sido ejecutado correctamente  
	 * Dicho metodo puede ser sobreescrito 
	 * @param params
	 * @param request
	 * @param usuario
	 */
	protected final void finalizeProcesoBatchSinEjecutarInterfaz(Map params, Usuario usuario) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'finalizeProcesoBatchSinInterfaz' method");
		}
		params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);
		params.put("codigoEstadoProceso",Constants.CODIGO_PROCESO_BATCH_OK);
		this.procesoBatchService.updateProcesoBatchActu(params, usuario);
		
	} 
	

	/**
	 * Metodo que coloca Codigo de ERROR en caso el proceso no concluya satisfactoriamente
	 * Se pude sobreescribir para que devuelva otro codigo de Error.
	 * @param params
	 * @return
	 */
	protected String getCodigoErrorBatch(Map params) {
		return Constants.CODIGO_PROCESO_BATCH_ERROR; 
	}
	
	/**
	 * M�todo que permite modifcar el estado del formulario luego de ejecutarse 
	 * el proceso sea satisfactioria o no.
	 * Dicho metodo puede ser sobreescrito  
	 * @param params
	 */
	 protected void afterExecuteProcess(Map params) throws Exception {
		 
	 }

	
	/**
	 * @param codigoIsoIdioma
	 * @return
	 */
	protected final Locale getLocaleIdioma(String codigoIsoIdioma) {
		if (StringUtils.isNotEmpty(codigoIsoIdioma)) {
			if (Constants.EDU_IDIOMA_DEFAULT_ES.equals(codigoIsoIdioma.toLowerCase()))
				return new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);
			else {
				log.debug("codigoIsoIdioma " + codigoIsoIdioma);
				return new Locale(codigoIsoIdioma.toLowerCase());
			}
		}
		log.debug("default " + codigoIsoIdioma);
		return new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);
	}
	
	/**
     * @param listado
     * @param codigo
     * @return
     */
    protected boolean existeCodigoEnLista(List listado, String codigo){
    	for (Object object : listado) {
    		if(((Base)object).getCodigo().equals(codigo)){
				return true;
			}
		}
    	return false;
    }
	
    /**
     * Metodo que permite obtener el usuario por defecto
     * @return Usuario, objeto de respuesta
     */
    protected Usuario obtenerUsuarioByDefault(String login){
    	Idioma idioma = new Idioma();
        idioma.setCodigo("01");
        idioma.setCodigoISO("es");
        idioma.setCodigoSiCC(Constants.EDU_IDIOMA_DEFAULT_ES);
        idioma.setEstado("1");
        
        Usuario usuario = new Usuario();
        usuario.setCodigoIdioma("01");
        usuario.setCodigoPais("");
        usuario.setLogin(login);
        usuario.setCodigo("");
        usuario.setIdioma(idioma);
    	return usuario;
    }
	

}
