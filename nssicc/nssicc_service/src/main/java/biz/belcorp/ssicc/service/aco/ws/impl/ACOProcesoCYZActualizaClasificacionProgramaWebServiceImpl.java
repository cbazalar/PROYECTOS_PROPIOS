/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoCYZActualizaClasificacionProgramaWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.spusicc.ProcesoCYZProgramaDuplaService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDUWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public class ACOProcesoCYZActualizaClasificacionProgramaWebServiceImpl extends ServletEndpointSupport implements ACOProcesoCYZActualizaClasificacionProgramaWebService {

	Log log = LogFactory.getLog(ACOProcesoCYZActualizaClasificacionProgramaWebServiceImpl.class);
	private static final String CODIGO_PROGRAMA = "001";
	private static final String CODIGO_PROGRAMA_CUMPLEANYOS = "006";
	private static final String CODIGO_PROGRAMA_WELCOME_PACK = "007";
	private static final String CODIGO_PROGRAMA_CUMPLEANYOS_CONSULTORAS = "008";
	
    ProcesoCYZProgramaDuplaService procesoCYZProgramaDuplaService;
    PaisService paisService;
	UsuarioService usuarioService;
	HistoricoService historicoService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
	 */
	protected void onInit() throws ServiceException {
		this.procesoCYZProgramaDuplaService = (ProcesoCYZProgramaDuplaService) getWebApplicationContext().getBean("spusicc.procesoCYZProgramaDuplaService");
		this.usuarioService = (UsuarioService)getWebApplicationContext().getBean("usuarioService");
		this.paisService = (PaisService) getWebApplicationContext().getBean("paisService");
		this.historicoService = (HistoricoService) getWebApplicationContext().getBean("sisicc.historicoService");
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoAbstractAction#executeProcess(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, java.util.Map)
	 */
	protected Map executeProcess(Map params) throws Exception {
		log.debug("Ejecutando la actualizacion de clasificaciones del programa dupla cyzone.");
        this.procesoCYZProgramaDuplaService.executeProcesarClasificacionProgramaDupla(params);
        return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.aco.ws.ProcesoACOWebService#
	 * ejecutarActualizacionClasificacionProgramaDuplas(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoCYZActualizaClasificacionPrograma(
			String codigoPais, String codigoPeriodo, String codigoUsuario) throws RemoteException {
		ACOWebServiceResponse resultado = new ACOWebServiceResponse();
		String mensajeError = "";
		try {
			Pais pais = this.paisService.getPais(codigoPais);

			if (StringUtils.isBlank(codigoPais)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			if (StringUtils.isBlank(codigoPeriodo)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarCodigoPeriodo", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}

			/**
		     * Valida usuario
		     */
		    if(StringUtils.isBlank(codigoUsuario)|| StringUtils.isEmpty(codigoUsuario)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}

			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			params.put("codigoPeriodo", codigoPeriodo);
			params.put("codigoPrograma", CODIGO_PROGRAMA);
			params.put("codigoProgramaCumpleanyos", CODIGO_PROGRAMA_CUMPLEANYOS);
			params.put("codigoProgramaWelcomePack", CODIGO_PROGRAMA_WELCOME_PACK);
			params.put("codigoProgramaCumpleanyosConsultoras", CODIGO_PROGRAMA_CUMPLEANYOS_CONSULTORAS);
			params.put("codigoUsuario", codigoUsuario);
			
			// ejecuta proceso
			this.executeProcess(params);
			resultado.setEjecucionExitosa(true);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resultado.setEjecucionExitosa(false);
			resultado.setMensaje(e.getMessage());
		}
		return resultado;
	}
	
	/**
	 * @param codigoIsoIdioma
	 * @return
	 */
	private final Locale getLocaleIdioma(String codigoIsoIdioma) {
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
