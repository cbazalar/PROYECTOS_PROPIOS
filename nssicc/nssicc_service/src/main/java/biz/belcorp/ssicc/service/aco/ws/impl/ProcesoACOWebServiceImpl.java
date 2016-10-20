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
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.aco.ws.ProcesoACOWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.spusicc.pedidos.ReportePRYProyeccionFaltanteDiaService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDUWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:rvela@belcorp.biz">Robinson Vela Bardales </a>
 */
public class ProcesoACOWebServiceImpl extends ServletEndpointSupport implements ProcesoACOWebService {

	
	Log log = LogFactory.getLog(ProcesoACOWebServiceImpl.class);

	PaisService paisService;
	UsuarioService usuarioService;
	ReportePRYProyeccionFaltanteDiaService reportePRYProyeccionFaltanteDiaService;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
	 */
	protected void onInit() throws ServiceException {
		this.paisService = (PaisService) getWebApplicationContext().getBean("paisService");
		this.usuarioService = (UsuarioService) getWebApplicationContext().getBean("usuarioService");
		this.reportePRYProyeccionFaltanteDiaService = (ReportePRYProyeccionFaltanteDiaService) getWebApplicationContext().getBean("spusicc.reportePRYProyeccionFaltanteDiaService");
	}


	/**
	 * @param codigoIsoIdioma
	 * @return
	 */
	protected Locale getLocaleIdioma(String codigoIsoIdioma) {
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

	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ProcesoACOWebService#ejecutarProcesoFaltanteFacturacionDia(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoFaltanteFacturacionDia(String codigoPais,
			String fechaFacturacion, String numeroVersion,
			String numeroProdGrupo, String presentacion, 
			String codigoUsuario, String formatoExportacion,
			String tipoReporte)throws RemoteException {
		String mensajeError = "";
		
		boolean estado = false;
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();

		if (log.isDebugEnabled()) {
			log.debug("ejecutarProcesoFaltanteFacturacionDia");
		}

		try {
			Pais pais = this.paisService.getPais(codigoPais);
			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario); 
					//this.usuarioService.getUsuarioByUsername(codigoUsuario);
			params.put("usuarioTemp", usuario);
			params.put("fechaFacturacion", fechaFacturacion);
			
			
			if(StringUtils.isBlank(tipoReporte) 
					|| StringUtils.isEmpty(tipoReporte)){
				tipoReporte = "1";
			}
			
			params.put("tipoReporte", tipoReporte);
			if (StringUtils.isBlank(numeroVersion))
				numeroVersion = "1";

			params.put("numeroVersion", numeroVersion);
			params.put("tipoPresentacion", presentacion);
			
			if(StringUtils.isBlank(formatoExportacion) 
					|| StringUtils.isEmpty(formatoExportacion)){
				formatoExportacion = "VXLS";
			}
			
			params.put("formatoExportacion",formatoExportacion);
			
			if ("1".equals(params.get("tipoReporte"))) {
				params.put("titulo", getWebApplicationContext().getMessage("reportePRYProyeccionFaltanteDiaForm.titulo1", null, getLocaleIdioma(pais.getCodigoIdiomaIso())));
						
			} else if ("2".equals(params.get("tipoReporte"))) {
				params.put("titulo", getWebApplicationContext().getMessage("reportePRYProyeccionFaltanteDiaForm.titulo2", null, getLocaleIdioma(pais.getCodigoIdiomaIso())));
			}

			reportePRYProyeccionFaltanteDiaService.grabarReporte(params);
			estado = true;
		} catch (Exception e) {
			log.error(e.getMessage());
			objetoRespuesta.setMensaje(e.getMessage());
			objetoRespuesta.setRespuestaWebService(null);
			objetoRespuesta.setEjecucionExitosa(estado);
		} finally {
			log.debug("Estado del servicio: " + estado);
			if (estado) {
				log.info("Se ejecuto el servicio con exito.");
			} else {
				log.error("Excepcion en el servicio.");
			}
		}
		return objetoRespuesta;
	}

	   /**
     * Metodo que permite obtener el usuario por defecto
     * @return Usuario, objeto de respuesta
     */
    protected Usuario obtenerUsuarioByDefault(String login){
    	Idioma idioma = new Idioma();
        idioma.setCodigo("01");
        idioma.setCodigoISO("ES");
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
