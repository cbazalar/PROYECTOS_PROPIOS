package biz.belcorp.ssicc.service.aco.ws.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessages;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.LabelArchivos;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatchActu;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;

/**
 * Clase Service abstracta para la ejecucion de Interfaces Hilo SiSiCC usando WS
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar La Rosa</a>
 *                   
 */
public abstract class BaseInterfazHiloAbstractWebService extends BaseInterfazAbstractWebService {
	
	protected ProcesoBatchService procesoBatchService;
	protected String mensajeError;
	protected String estadoProceso;
	
	/* (non-Javadoc)
	 * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
	 */
	protected void onInit() throws ServiceException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'onInit BaseInterfazHiloAbstractWebService' method");
		}
		super.onInit();
		this.procesoBatchService = (ProcesoBatchService) getWebApplicationContext().getBean("scsicc.procesoBatchService");
	}
	
	
	/**
	 * Ejecuta la Interfaz SiSiCC, prepara los parametros para la ejecuci�n y
	 * delega la ejecuci�n al 'InterfazExecutionService'.
	 */
	protected SSiCC_Desfasado_InterfazExecutionResult executeInterfaz(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'executeInterfaz BaseInterfazHiloAbstractWebService' method");
		}
		this.mensajeError = "";
		/* Obteniendo valores a ser seteados al Parametro Map */
		String codigoPais = (String) params.get("codigoPais");
		Pais pais = this.paisService.getPais(codigoPais);
		params = this.prepareParamsBeforeExecute(params, pais);
			
		/******************************************/
		//Se verifica el numero de lote de los archivos
		String verifica = this.verificaNumeroLote(params); 
		
		if(verifica.equals(Constants.NUMERO_UNO)){
			String keyMensaje = "interfaz.numero.lote";
			String mensaje = getWebApplicationContext().getMessage(keyMensaje, null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
			throw new Exception(mensaje);
		
		}
		else{
			if (verifica.equals("-1")){
				ActionMessages errors = new ActionMessages();
				String keyMensaje = "interfaz.numero.archivos.incompletos";
				String mensaje = getWebApplicationContext().getMessage(keyMensaje, null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensaje);
			}
		}
		
		/******************************************/
		/* Invocando Hilo */
		SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult = null;
		interfazExecutionResult = this.executeHilo(params);	
		
		if (!interfazExecutionResult.ejecucionCompletada())
		{
			this.estadoProceso = Constants.ERROR;
			this.mensajeError = Constants.PROCESO_BATCH_ENVIADO_GENERICO_ERRORES;
		}
		else
		{
			this.estadoProceso = Constants.OK;
			this.mensajeError = Constants.ARCHIVO_ENVIADO_GENERICO;		
		}
		
		return interfazExecutionResult;
		
	}
	
	/**
	 * Ejecuta la Interfaz SiSiCC, prepara los parametros para la ejecuci�n y
	 * delega la ejecuci�n al 'InterfazExecutionService'.
	 */
	protected final SSiCC_Desfasado_InterfazExecutionResult executeHilo(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'executeHilo BaseInterfazHiloAbstractWebService' method");
		}
		
		Usuario usuario = (Usuario) params.get("usuario");
		try {
			params = this.executeProcessBeforeInterfaz(params);
			log.info("queryParams=" + params);
		}
		catch (Exception e) {
			params.put("errorInterfaz", Constants.SI);
			updateInterfazRegistroProcesoBatch(params, usuario, e);
			throw new Exception(e);
		}
		
		SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult = null;
		try {
			
			if (continueExecuteInterfaz(params)) {
	            beforeExecuteInterfaz(params);
	            updateEstadoEjecucionInterfazProcesoBatch(params, usuario);
	            interfazExecutionResult = this.interfazExecutionService.executeInterfaz(params);
				afterExecuteInterfaz(params, interfazExecutionResult);
				updateInterfazRegistroProcesoBatch(params, interfazExecutionResult, usuario);
			}
			else {
				finalizeProcesoBatchSinEjecutarInterfaz(params, usuario);
			}
		}
		catch (Exception e) {
			params.put("errorInterfaz", Constants.SI);
			updateInterfazRegistroProcesoBatch(params, usuario, e);
			throw new Exception(e);
		}
		
		if (log.isDebugEnabled()) {
			log.debug("Fin 'executeHilo' method");
		}
		return interfazExecutionResult;
		
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.action.BaseAbstractAction#prepareParamsBeforeExecute(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected Map prepareParamsBeforeExecute(Map params, Pais pais) throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParamsBeforeExecute BaseInterfazHiloAbstractWebService' method");
		}
		params = super.prepareParamsBeforeExecute(params, pais);
		
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
		params.put("indicadorMultiLote", Constants.NO);
		
				
		/* Verificando Indicador de Seleccion de Interfaz */ 
		String codigoPais = (String) params.get("codigoPais");
		String codigoSistema = (String) params.get("codigoSistema");
		String codigoInterfaz = (String) params.get("codigoInterfaz");
		
		
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
		
		List listaProcesoBatchDependientes = procesoBatchService.getProcesoBatchActuDependientesByCriteria(params);
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
		procesoBatchService.insertProcesoBatchActu2(params, usuario);
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
	 * @param request
	 * @param interfazExecutionResult
	 * @param usuario
	 */
	private void updateInterfazRegistroProcesoBatch(Map params, 
			SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult,
			Usuario usuario) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateInterfazRegistroProcesoBatch' method");
		}
		String numeroLote = interfazExecutionResult.getNumeroLote();
		params.put("numeroLote", numeroLote);
		params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);
		params.put("usuario", usuario);
		params.put("descripcionEtapaProceso", Constants.FINALIZADO);
		boolean ejecucionCompletada = interfazExecutionResult.ejecucionCompletada();
		if (ejecucionCompletada) {
			params.put("descripcionLog", Constants.ARCHIVO_ENVIADO_GENERICO);
			params.put("codigoEstadoProceso", Constants.CODIGO_PROCESO_BATCH_OK);
		}
		else {
			params.put("descripcionLog", Constants.PROCESO_BATCH_ENVIADO_GENERICO_ERRORES);
			params.put("codigoEstadoProceso", Constants.CODIGO_PROCESO_BATCH_ERROR);
		}	
		this.procesoBatchService.updateProcesoBatchActu03(params, usuario);
	}
	
	
	/**
	 * Metodo que es invocado para finalizar la ejecucion del proceso, cuando se haya generado una excepcion
	 * @param params
	 * @param request
	 * @param usuario
	 * @param exception
	 */
	private void updateInterfazRegistroProcesoBatch(Map params,
			Usuario usuario,
			Exception exception
			) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateInterfazRegistroProcesoBatch' method");
		}
		params.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);
		params.put("codigoEstadoProceso",Constants.CODIGO_PROCESO_BATCH_ERROR);
		
		log.error(exception);
		String descripcionLog = exception.getMessage();
		
		if (descripcionLog!= null && descripcionLog.length() >= 1000) {
			descripcionLog = descripcionLog.substring(1,999);
		}
		
		params.put("descripcionLog", descripcionLog);
		params.put("usuario", usuario);
		params.put("descripcionEtapaProceso", Constants.FINALIZADO);
		this.procesoBatchService.updateProcesoBatchActu03(params, usuario);
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
	 * Metodo que devuelve el listado de archivos que estan en el directorio de entrada de interfaces
	 * @param interfaz
	 * @param pais
	 * @return
	 */
	private List getListaArchivos(Interfaz interfaz, Pais pais) throws Exception  {
		String mensaje = getWebApplicationContext().getMessage("mensaje.error.noExisteArchivo", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
		List archivosList = new ArrayList();			
		InterfazParams interfazParams = new InterfazParams();
		interfazParams.setInterfaz(interfaz);
		archivosList = interfazParams.getListArchivosEntrada();		
		if(archivosList.size() == 0){
			LabelArchivos labelArchivos = new LabelArchivos();
			labelArchivos.setNombreArchivo(mensaje);
			archivosList.add(labelArchivos);
		}		
		return archivosList;
	}	   	
	
	/**
	 * Metodo que verifica si los numeros de lotes de una interfaz de paquete son iguales para 
	 * los archivos de las interfaces hijas
	 * "0" si es correcto
	 * "1" si no son iguales
	 * "-1" cuando no hay archivos
	 * @param params
	 * @return
	 */
	private String verificaNumeroLote(Map params) throws Exception {
		String valida = "0";
		
		// Se obtiene la interfaz para verificar si es de Tipo Paquete y de Entrada
		String codigoPais = (String) params.get("codigoPais");
		String codigoSistema = (String) params.get("codigoSistema");
		String codigoInterfaz = (String) params.get("codigoInterfaz");
		Pais pais = (Pais) params.get("pais");
		
		InterfazPK interfazPK = new InterfazPK(codigoPais, 	codigoSistema, codigoInterfaz);
		Interfaz interfaz = interfazService.getInterfaz(interfazPK);
		
		if(interfaz.getIndicadorValidarLoteEntrada().equals(Constants.SI)){
			/* INI NUEVO MULTILOTE */
			int numeroMultiLote = 1;
			int numeroMultiLoteActual = numeroMultiLote;
			params.put("numeroMultiLote", numeroMultiLote);
			List listaMultiLote = new ArrayList();
			params.put("listaMultiLote", listaMultiLote);
			/* FIN NUEVO MULTILOTE */
			
			params.put("listaNombresArchivos", null);
			
			if (StringUtils.isBlank(codigoSistema) || StringUtils.isBlank(codigoInterfaz))
				return valida;
			
			if(interfaz.getTipo().equals(Constants.INTERFAZ_TIPO_ENTRADA) && 
			   interfaz.getTipoGeneracion().equals(Constants.TIPO_GENERACION_PAQUETE)){
				
				// Si son de Tipo Paquete y de Entrada se debe validar que los archivos tengan el mismo numero de Lote

				//Obteniendo la lista de interfaces del paquete
				List listPaquete = interfazService.getComponentesInterfazPaquete(interfazPK);
				
				if (listPaquete != null) {
					String[] listaInterfaces = new String[listPaquete.size()];
					String[] listaInterfacesPrefijo = new String[listPaquete.size()];
					String[] listaNombreArchivo = new String[listPaquete.size()];
					String[] listaNombresArchivos = new String[listPaquete.size()];
					List archivos = null;
					
					/* INI NUEVO MULTILOTE */
					String indicadorMultiLote = (String)params.get("indicadorMultiLote");
					if (Constants.SI.equals(indicadorMultiLote)) {
						Interfaz interInicial = (Interfaz)listPaquete.get(0);
						archivos = getListaArchivos(interInicial, pais);
						numeroMultiLote = archivos.size();
						numeroMultiLoteActual = numeroMultiLote;
						params.put("numeroMultiLote", numeroMultiLote); 
					}	
					
					for (int x=0; x < numeroMultiLote; x++) {
						listaNombresArchivos = new String[listPaquete.size()];
					/* FIN NUEVO MULTILOTE */
						
						for (int i = 0; i < listPaquete.size(); i++) {
							Interfaz inter = (Interfaz)listPaquete.get(i);
							
							listaInterfaces[i] = inter.getCodigo();					
							if (inter.getNombreArchivoEntradaSalida() != null){
								listaInterfacesPrefijo[i] = inter.getNombreArchivoEntradaSalida();
							}
							else{
								listaInterfacesPrefijo[i] = inter.getCodigo() + '_';
							}					
							archivos = getListaArchivos(inter, pais);
							
							/* INI NUEVO MULTILOTE */
							//LabelArchivos labelArchivos = (LabelArchivos)archivos.get(0);
							numeroMultiLoteActual = archivos.size();
							if (Constants.SI.equals(indicadorMultiLote)) {
								if (numeroMultiLoteActual != numeroMultiLote) {
									valida = "-1";
									break;
								}
							}	
							LabelArchivos labelArchivos = (LabelArchivos)archivos.get(x);
							/* FIN NUEVO MULTILOTE */
							
							if (labelArchivos.getPesoArchivo() != null ){
								listaNombreArchivo[i] = labelArchivos.getNombreArchivo().substring(listaInterfacesPrefijo[i].length(), labelArchivos.getNombreArchivo().length()-4);
								listaNombresArchivos[i] = labelArchivos.getNombreArchivo();
							}
							else{
								valida = "-1";
								break;
							}
						}
						
						/* INI NUEVO MULTILOTE */
						if (valida.equals(Constants.NUMERO_CERO)){
							String loteAnterior = "";
							//Se compara los numeros de lotes de los archivos de las interfaces
							for (int i = 0; i < listaNombreArchivo.length; i++) {
								if (i!= 0 ){
									if (!listaNombreArchivo[i].equals(loteAnterior)){
										valida = "1";
										break;	
									}	
								}
								loteAnterior = listaNombreArchivo[i];
							}
						}
						if (x==0) {
							params.put("listaNombresArchivos", listaNombresArchivos);
						}
						listaMultiLote.add(listaNombresArchivos);
						/* FIN NUEVO MULTILOTE */
						
					}
										
				}
			}
		}
		
		return valida;
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
