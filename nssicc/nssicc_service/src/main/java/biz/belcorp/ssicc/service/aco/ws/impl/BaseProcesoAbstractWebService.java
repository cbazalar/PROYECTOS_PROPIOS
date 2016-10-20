package biz.belcorp.ssicc.service.aco.ws.impl;

import java.util.Locale;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.PaisService;

/**
 * Clase Service abstracta para la ejecucion de Interfaces Hilo SiSiCC usando WS
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar La Rosa</a>
 *                   
 */
public abstract class BaseProcesoAbstractWebService extends ServletEndpointSupport {
	
	Log log = LogFactory.getLog(BaseProcesoAbstractWebService.class);
	protected PaisService paisService;
	protected HistoricoService historicoService;
	
	
	/* (non-Javadoc)
	 * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
	 */
	protected void onInit() throws ServiceException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'onInit BaseProcesoAbstractWebService' method");
		}
		this.paisService = (PaisService) getWebApplicationContext().getBean("paisService");
		this.historicoService = (HistoricoService) getWebApplicationContext().getBean("sisicc.historicoService");
		
	}
	
	/**
	 * Ejecuta el Proceso Batch SiSiCC, prepara los parametros para la ejecuci�n 
	 */
	protected void executeProceso(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'executeProceso BaseProcesoAbstractWebService' method");
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
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.action.BaseAbstractAction#prepareParamsBeforeExecute(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected Map prepareParamsBeforeExecute(Map params, Pais pais) throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParamsBeforeExecute BaseProcesoAbstractWebService' method");
		}
		
		/* Agregando par�metros */
		String codigoUsuario = (String) params.get("codigoUsuario");		
		params.put("pais", pais);
		params.put("codigoPais",pais.getCodigo());
		params.put("descripcionPais",pais.getDescripcion());
		params.put("recomendacionError", "    ");
				
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
		params.put("mostrarPaginaConsultaBatch", Constants.SI);
		return params;
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

}
