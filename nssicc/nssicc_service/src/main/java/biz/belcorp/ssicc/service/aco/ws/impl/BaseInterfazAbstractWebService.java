package biz.belcorp.ssicc.service.aco.ws.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.LabelArchivos;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_InterfazExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;

/**
 * Clase WebService abstracta para las Interfaces SiSiCC
 * 
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>"
 */
public abstract class BaseInterfazAbstractWebService extends ServletEndpointSupport  {

	Log log = LogFactory.getLog(BaseInterfazAbstractWebService.class);
	
	protected PaisService paisService;
	protected SSiCC_Desfasado_InterfazExecutionService interfazExecutionService;
	protected InterfazService interfazService;
	protected MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	protected UsuarioService usuarioService;
	protected HistoricoService historicoService;
	
	
	
	/* (non-Javadoc)
	 * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
	 */
	protected void onInit() throws ServiceException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'onInit BaseInterfazAbstractWebService' method");
		}
		this.usuarioService = (UsuarioService)getWebApplicationContext().getBean("usuarioService");
		this.paisService = (PaisService) getWebApplicationContext().getBean("paisService");
		this.interfazExecutionService = (SSiCC_Desfasado_InterfazExecutionService) getWebApplicationContext().getBean("sisicc_desfasado.interfazExecutionService");
		this.interfazService = (InterfazService) getWebApplicationContext().getBean("sisicc.interfazService");
		this.mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService) getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		this.historicoService = (HistoricoService) getWebApplicationContext().getBean("sisicc.historicoService");
		
	}
	
	/**
	 * Ejecuta la Interfaz SiSiCC, prepara los parametros para la ejecuci�n y
	 * delega la ejecuci�n al 'InterfazExecutionService'.
	 */
	protected SSiCC_Desfasado_InterfazExecutionResult executeInterfaz(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'executeInterfaz BaseInterfazAbstractWebService' method");
		}
		
		String codigoPais = (String) params.get("codigoPais");
		Pais pais = this.paisService.getPais(codigoPais);
		params = this.prepareParamsBeforeExecute(params, pais);
		SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult = null;
		log.info("queryParams=" + params);
		if (continueExecuteInterfaz(params)) {
            beforeExecuteInterfaz(params);
			interfazExecutionResult = this.interfazExecutionService.executeInterfaz(params);
			afterExecuteInterfaz(params, interfazExecutionResult);
		}
		
		return interfazExecutionResult;
	}
	
	
	/**
     * Hook method, invocado antes de la llamada a
     * 'interfazExecutionService.executeInterfaz' dentro del metodo
     * 'executeInterfaz'.
     * 
     * @param params
     *            parametros de la interfaz
     */
    protected void beforeExecuteInterfaz(Map params) {
    }
    
	/**
	 * Hook method, invocado despues de la llamada a
	 * 'interfazExecutionService.executeInterfaz' dentro del metodo
	 * 'executeInterfaz'.
	 * 
	 * @param params
	 *            parametros de la interfaz
	 */
    protected void afterExecuteInterfaz(Map params, SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult) throws Exception {
	}
	
    /**
     * @param params
     * @param pais
     * @return
     * @throws Exception
     */
    protected Map prepareParamsBeforeExecute(Map params, Pais pais) throws Exception {
    	if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParamsBeforeExecute BaseInterfazAbstractWebService' method");
		}
    	
    	/* Verificando Indicador de Seleccion de Interfaz */ 
		String codigoPais = (String) params.get("codigoPais");
		String codigoSistema = (String) params.get("codigoSistema");
		String codigoInterfaz = (String) params.get("codigoInterfaz");
		
		if (StringUtils.isBlank(codigoSistema)) {
			if (StringUtils.isNotBlank(codigoInterfaz)) {
				codigoSistema = codigoInterfaz.substring(0, 3);
			    params.put("codigoSistema", codigoSistema);
			}    
		}
		String keyMensaje = "";
		InterfazPK interfazEjecucionPK = new InterfazPK(codigoPais,	codigoSistema, codigoInterfaz);
		Interfaz interfazEjecucion = interfazService.getInterfaz(interfazEjecucionPK);
		
		// Validacion del estado de la interfaz
		if (interfazEjecucion.getEstado().trim().equalsIgnoreCase(Constants.ESTADO_ACTIVO)) {
			if (interfazEjecucion.getFlagHabilitado().trim().equalsIgnoreCase(Constants.NO)) {
				keyMensaje = "interfazSiCC.error.interfaz.desabilitada";
				String mensajeError = getWebApplicationContext().getMessage(keyMensaje, null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
		} else {
			// Interface eliminada
			keyMensaje = "interfazSiCC.error.interfaz.eliminada";
			String mensajeError = getWebApplicationContext().getMessage(keyMensaje, null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
			throw new Exception(mensajeError);
		}
				
		//--------------
		if(/*interfaz.getFlagEnvioArchivo().equals(Constants.NUMERO_UNO) &&*/ interfazEjecucion.getTipo().equals(Constants.INTERFAZ_TIPO_ENTRADA)){
			// Seteo las interfaces del paquete en sesion en caso la interfaz sea de
			// paquete
			
			if (interfazEjecucion.getTipoGeneracion().equals(Constants.TIPO_GENERACION_PAQUETE)) {
				List listPaquete = interfazService.getComponentesInterfazPaquete(interfazEjecucionPK);
				log.info("Interfaces empaquetadas =" + listPaquete);
				if (listPaquete == null) {
					// Interfaces Deshabilitadas
					keyMensaje = "interfazSiCC.error.interfaz.desabilitada";
					String mensajeError = getWebApplicationContext().getMessage(keyMensaje, null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
					throw new Exception(mensajeError);
					
				} else {
					//Obtiene los archivos asociados a cada interfaz del paquete
					String[] listaInterfaces = new String[listPaquete.size()];
					String[] listaInterfacesSeleccionadas = new String[listPaquete.size()];
					
					for (int i = 0; i < listPaquete.size(); i++) {
							Interfaz interf = (Interfaz)listPaquete.get(i);
							interf.setArchivos(getListArchivos(interf, pais));
							listaInterfaces[i] = interf.getCodigo();
							listaInterfacesSeleccionadas[i] = interf.getCodigo();
					}
					
					params.put("ListaInterfaces", listaInterfaces);
					params.put("listaInterfacesSeleccionadas", listaInterfacesSeleccionadas);
					params.put("checkAllListaInterfaces", Constants.SI);
					
				}
			}
			else{
				/**
				 * Valida si no existe archivo en directorio
				 */
				List lista = this.getListArchivos(interfazEjecucion, pais);			
				
				// Si el archivo se envia x RED y es una interfaz de entrada
				// Se setea la lista de archivos disponibles para cargar
			}
		}
		else{
			List listPaquete = interfazService.getComponentesInterfazPaquete(interfazEjecucionPK);
			log.info("Interfaces empaquetadas =" + listPaquete);
			if (listPaquete == null) {
				// Interfaces Deshabilitadas
				keyMensaje = "interfazSiCC.error.interfaz.desabilitada";
				String mensajeError = getWebApplicationContext().getMessage(keyMensaje, null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			} else {
				
				String[] listaInterfaces = new String[listPaquete.size()];
				String[] listaInterfacesSeleccionadas = new String[listPaquete.size()];
				for (int i = 0; i < listPaquete.size(); i++) {
					Interfaz interf = (Interfaz)listPaquete.get(i);
					listaInterfaces[i] = interf.getCodigo();
					listaInterfacesSeleccionadas[i] = interf.getCodigo();
				}
				
				params.put("ListaInterfaces", listaInterfaces);
				params.put("listaInterfacesSeleccionadas", listaInterfacesSeleccionadas);
				params.put("checkAllListaInterfaces", Constants.SI);
			}	
		}
		//--------------
				
		if (StringUtils.isNotBlank(codigoInterfaz) && StringUtils.isNotBlank(codigoSistema)) {
			if (interfazEjecucion!= null) {
				if (Constants.SI.equals(interfazEjecucion.getIndicadorSeleccion())) {
					String[] listaInterfacesSeleccionadas = (String[])params.get("listaInterfacesSeleccionadas");
					if (listaInterfacesSeleccionadas == null || listaInterfacesSeleccionadas.length == 0) {
						keyMensaje = "interfaz.sinSeleccionInterfazPaquete";
						String mensajeError = getWebApplicationContext().getMessage(keyMensaje, null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}
			}	
		}	
		
		String checkAllListaInterfaces = (String) params.get("checkAllListaInterfaces");
		if (StringUtils.isBlank(checkAllListaInterfaces))
			params.put("checkAllListaInterfaces", Constants.NO);
		
		/* INI NUEVO MULTILOTE */
		int numeroMultiLote = 1;
		params.put("numeroMultiLote", numeroMultiLote);
		params.put("listaNombresArchivos", null);
		List listaMultiLote = new ArrayList();
		params.put("listaMultiLote", listaMultiLote);
		params.put("indicadorMultiLote", Constants.NO);
		/* FIN NUEVO MULTILOTE */
		return params;
	}


	/**
	 * Hook method para la ejecucion de la Interfaz. Esta implementacion
	 * devuelve siempre true y siempre se ejecuta la Interfaz. En caso que la
	 * Interfaz no se deba ejecutar debido a algun valor en los parametros se
	 * puede sobrescribir este metodo.
	 * 
	 * @param params
	 *            parametros de la Interfaz
	 * @return true si se va a ejecutar la Interfaz, false en caso de que no se
	 *         ejecute
	 */
	protected boolean continueExecuteInterfaz(Map params) {
		return true;
	}

	
	

	/**
	 * Metodo que devuelve el listado de archivos que estan en el directorio de entrada de interfaces
	 * @param interfaz
	 * @param pais
	 * @return
	 */
	protected final List getListArchivos(Interfaz interfaz, Pais pais) throws Exception {
		
		String mensaje = getWebApplicationContext().getMessage("mensaje.error.noExisteArchivo", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
		List archivosList = new ArrayList();			
		InterfazParams interfazParams = new InterfazParams();
		interfazParams.setInterfaz(interfaz);
		archivosList = interfazParams.getListArchivosEntrada();		
		if(archivosList.size() == 0){
//			LabelArchivos labelArchivos = new LabelArchivos();
//			labelArchivos.setNombreArchivo(mensaje);
//			archivosList.add(labelArchivos);
			throw new Exception(mensaje);
		}	
		else {
			if (interfaz.getFlagValidarCargaPrevia().equals(Constants.SI)){
				
				Map criteria = new HashMap();
	        	criteria.put("codigoPais",interfaz.getCodigoPais());  
	          	criteria.put("codigoSistema",interfaz.getCodigoSistema());
				criteria.put("codigoInterfaz",interfaz.getCodigo());
				criteria.put("ejecucionSatisfactoria",Constants.SI);
				
				for (int i = 0; i < archivosList.size(); i++) {
					LabelArchivos labelArchivos = (LabelArchivos)archivosList.get(i);
					criteria.put("nombreArchivoOriginal",labelArchivos.getNombreArchivo());
					int size = this.historicoService.getHistoricosByCriteria(criteria).size();
					labelArchivos.setObservacion("");
					
					mensaje = getWebApplicationContext().getMessage("mensaje.error.archivoCargadoAntes", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
					if (size>0)	labelArchivos.setObservacion(mensaje);
				}
			}
			
		}
		return archivosList;
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
	 * @param params
	 * @return
	 */
	protected final String getDescripcionInterfaz(Map params) {

		String codigoPais = (String) params.get("codigoPais");
		String codigoSistema = (String) params.get("codigoSistema");
		String codigoInterfaz = (String) params.get("codigoInterfaz");
		InterfazPK interfazEjecucionPK = new InterfazPK(codigoPais, codigoSistema, codigoInterfaz);
		Interfaz interfazEjecucion = this.interfazService.getInterfaz(interfazEjecucionPK);
		return (interfazEjecucion != null ? interfazEjecucion.getDescripcion() : "");
	}
	
	/**
	 * @param interfazResult
	 * @return
	 */
	protected final String getNombreArchivoEntradaSalida(InterfazResult interfazResult) {
		String nombreArchivo;

		Interfaz interfaz = interfazResult.getInterfaz();
		String numeroLote = interfazResult.getNumeroLote();

		// Tipo de nombre fijo
		if (interfaz.getTipoNombreArchivo().equalsIgnoreCase(Constants.ARCHIVO_FIJO)) {
			nombreArchivo = interfaz.getNombreArchivo(interfaz.getNombreArchivoEntradaSalida()
					+ numeroLote);

		}
		// Tipo de nombre variable
		else {
			String nombreArchivoSalida = interfaz.getNombreArchivoEntradaSalida();
			if (StringUtils.isEmpty(nombreArchivoSalida)
					|| StringUtils.isEmpty(nombreArchivoSalida)) {
				nombreArchivoSalida = interfaz.getCodigo() + "_";
			}
			nombreArchivo = interfaz.getNombreArchivo(nombreArchivoSalida
					+ numeroLote);
		}
		return nombreArchivo;
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
    
    /**
     * Comporar arrays bean.
     *
     * @param listaAux the lista aux
     * @param listTemp the list temp
     * @return the int
     */
    protected int comporarArraysBean(List listaAux, List listTemp){
    	int cont = 0;
    	for (Object object : listTemp) {
			String valor = (String)object;			
//			for (Object obj : listaAux) {
//				LabelValue labelValue = (LabelValue)obj;
//				if(StringUtils.equals(valor, labelValue.getValue())){
//					cont++;
//				}
//			}			
			EqualPredicate nameEqlPredicate = new EqualPredicate(valor);
			BeanPredicate beanPredicate = new BeanPredicate("value", nameEqlPredicate);
			if(CollectionUtils.exists(listaAux, beanPredicate)){
				cont++;
			}
		}
    	return cont; 
    }
}
