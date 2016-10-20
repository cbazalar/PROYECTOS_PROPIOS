package biz.belcorp.ssicc.web;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.util.StringUtil;
import biz.belcorp.ssicc.web.framework.base.action.MBaseAbstractJSF;

@ManagedBean
@SessionScoped
public class MRecuperarCuentaBean extends MBaseAbstractJSF {

    private static final long serialVersionUID = -2403138958014741653L;
    protected final static String RECUPERAR_CUENTA_FAILURE_PAGE = "/recuperarCuenta.xhtml";
    protected final String PAQUETE_WEB = "biz.belcorp.ssicc.web";
    
    protected String pantallaBodyXhtml ;  
    protected String pantallaAdicionalesXhtml = this.PAGINA_ADICIONAL_BASE;  
    private String  targetMain;
    
    /* Datos del Usuario Logueado */
    protected String codigoUsuario;
    protected String correoElectronico; 
    protected Locale localeKey;
    protected String nombres;
	protected String apellidos;
	protected boolean mostrarRecuperarClave;
    

	private Idioma currentIdioma;
	
	@PostConstruct
	public void init() {
		this.targetMain = "recuperarCuenta";
		this.mostrarRecuperarClave = false;
		
		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + "/resources/images/";
		log.debug(path);
//		Pais pais = paisService.getPais(this.countryCode);
//		Pais pais = paisService.getPaisByContextRoot(contextRoot);
//		log.debug("Pais = " + pais.getCodigo());
//		log.debug(pais.getCodigo());
		


		
    }  
	/**
     * Ingresar al Sistema SSiCC cuando el usuario hizo aceptar en el Login  
     * @return
     */
    public void buscarUsuario(ActionEvent actionEvent) {
    	
    	log.debug("buscarUsuario" );
    	String retorno = this.buscarUsuario();
    	
    	try {
	    	if (!StringUtils.equals(retorno, this.RECUPERAR_CUENTA_FAILURE_PAGE)) {
	    		this.redireccionarPagina(retorno);
	    	}
    	}
    	catch(Exception e) {
    		this.errorRecuperarCuenta();
    	}
    	log.debug("Fin buscarUsuario");
    }
    
    /**
     * Ingresar al Sistema SSiCC cuando el usuario hizo aceptar en el Login  
     * @return
     */
    public String buscarUsuario() {
    	this.pantallaBodyXhtml = "recuperarCuenta"; 
    	
    	UsuarioService usuarioService = (UsuarioService)this.getBeanService("usuarioService");
        Usuario usuario=null;
        
		try {
			usuario = usuarioService.getUsuarioByUsername(this.codigoUsuario);
			if(usuario != null){
				this.codigoUsuario = usuario.getLogin();
				this.correoElectronico= usuario.getCorreoElectronico();
				this.nombres = usuario.getNombres();
				this.apellidos  =usuario.getApellidos();
	    		
	    		if (correoElectronico == null){
	    			return this.errorRecuperarCuenta();
	    		}
	    		this.mostrarRecuperarClave = true;
			}
			else{
				return this.errorRecuperarCuenta();
			}
			
		}
		catch(Exception e) {
			return this.errorRecuperarCuenta();
		}
		
		
		return this.pantallaBodyXhtml;
    }
    
    /**
     * Ingresar al Sistema SSiCC cuando el usuario hizo aceptar en el Login  
     * @return
     */
    public void recuperarCuenta(ActionEvent actionEvent) {
    	log.debug("INICIO recuperarCuenta");
    	String retorno = this.recuperarCuenta();
    	
    	try {
	    	if (!StringUtils.equals(retorno, this.RECUPERAR_CUENTA_FAILURE_PAGE)) {
	    		this.redireccionarPagina(retorno);
	    	}
    	}
    	catch(Exception e) {
    		this.errorRecuperarCuenta();
    	}
    	log.debug("FIN recuperarCuenta");
    }
    
    /**
     * Cuando el Usuario cancela la operación  
     * @return
     */
    public void cancelar(ActionEvent actionEvent) {
    	
    	try {
    		this.mostrarRecuperarClave = false;
    		this.codigoUsuario ="";
    		this.correoElectronico=""; 
    		this.nombres ="";
    		this.apellidos="";
    		
	    	this.redireccionarPagina("index");
    	}
    	catch(Exception e) {
    		this.errorRecuperarCuenta();
    	}
    }
	
    /**
     * Ingresar al Sistema SSiCC cuando el usuario hizo aceptar en el Login  
     * @return
     */
    public String recuperarCuenta() {
    	this.pantallaBodyXhtml = "recuperarCuenta"; 
    	log.debug("recuperarCuenta" + this.codigoUsuario);
    	UsuarioService usuarioService = (UsuarioService)this.getBeanService("usuarioService");
        Usuario usuarioActual = usuarioService.getUsuarioByUsername(this.codigoUsuario);
    	
    	String correoUsuarioActual = usuarioActual.getCorreoElectronico();
		log.debug("correo usuario>> " + correoUsuarioActual);
		
		//se genera una nueva contraseña alfanumérica aleatoria 
		String claveNuevaAleatoria = StringUtil.generarClaveAleatoriaSegura(2, 2, 8);
		log.debug("Nueva clave Aleatoria >> " + claveNuevaAleatoria);		
			
		//se actualiza la contraseña del usuario
		usuarioActual.setClave(claveNuevaAleatoria);
		usuarioService.updateUsuario(usuarioActual, usuarioActual);
			
		//Se actualiza la fecha de Modificación de la clave
		usuarioService.updateFechaModificacionClaveUsuario(usuarioActual, usuarioActual);
			
		//Se actualiza el número de reintentos fallidos de la contraeña en Cero 
		usuarioActual.setIntentosFallidosClave(Constants.NUMERO_CERO);
		usuarioService.updateIntentosFallidosClaveUsuario(usuarioActual, usuarioActual);
			
			//se envía el correo conla nueva contraseña
		String login = usuarioActual.getLogin();
		String codigoPais = usuarioActual.getPais().getCodigo();
		String nombreCompleto = usuarioActual.getNombres() + " " + usuarioActual.getApellidos();
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("nombreCompleto", nombreCompleto);
		criteria.put("login", login);	
		criteria.put("password", claveNuevaAleatoria);					
		criteria.put("nombreReporte", "procesoEnviarNuevaClave");
		
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		Map paramCorreo = reporteService.getParametrosReporte(criteria);
		if(paramCorreo != null)
		{
			paramCorreo.put("correosDestinos",correoUsuarioActual);
			String subject = (String)paramCorreo.get("subject");						
			paramCorreo.put("subject",subject);
			String enviarCorreo = (String) paramCorreo.get("enviarCorreo");			
			log.debug("enviar correo "+enviarCorreo);
			criteria.put("enviarCorreo", enviarCorreo);
			if (Constants.SI.equals(enviarCorreo)) {
				MailParams mailParams = new MailParams();
				paramCorreo.put("parameterMap",criteria);
								
				String bodyTxt = (String) paramCorreo.get("bodyTxt");
				String bodyHtml = (String) paramCorreo.get("bodyHtml");
				mailParams.setQueryParams(paramCorreo);
									
				BaseMailService mailService = (BaseMailService) this.getBean("sisicc.mailUtil"); 
				criteria.put("bodyTxt", bodyTxt);
				criteria.put("bodyHtml", bodyHtml);
				mailService.enviarMail(mailParams);					
			}
		}
		
		this.mostrarRecuperarClave = false;
		return "index";
    }

    
    /**
	 * En caso hubo Error de Logueo por lo que no se pudo Ingresar al Sistema
	 * @return
	 */
    protected String errorRecuperarCuenta() {
		this.addError("Error: ", this.getResourceMessage("errors.password.mismatch"));
		this.getSession(false).invalidate();
		return this.RECUPERAR_CUENTA_FAILURE_PAGE;
	}
    
    /**
	 * En caso hubo Error de Logueo por lo que no se pudo Ingresar al Sistema
	 * @return
	 */
    protected String errorRecuperarCuenta(Exception e) {
    	String error = this. obtieneMensajeErrorException(e);
		this.addError("Error: ", error);
		this.getSession(false).invalidate();
		return this.RECUPERAR_CUENTA_FAILURE_PAGE;
	}
   
    /**
     * @return
     */
    public boolean isMostrarRecuperarClave() {
		return mostrarRecuperarClave;
	}
	/**
	 * @param mostrarRecuperarClave
	 */
	public void setMostrarRecuperarClave(boolean mostrarRecuperarClave) {
		this.mostrarRecuperarClave = mostrarRecuperarClave;
	}
   
	/**
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
	
	/**
	 * @return the codigoUsuario
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	/**
	 * @return
	 */
	public String getNombres() {
		return nombres;
	}

	/**
	 * @param nombres
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * @return
	 */
	public String getApellidos() {
		return apellidos;
	}


	/**
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	/**
	 * @param pantallaBodyXhtml the pantallaBodyXhtml to set
	 */
	public void setPantallaBodyXhtml(String pantallaBodyXhtml) {
		this.pantallaBodyXhtml = pantallaBodyXhtml;
	}


	/**
	 * @return the localeKey
	 */
	public Locale getLocaleKey() {
		return localeKey;
	}

	/**
	 * @param localeKey the localeKey to set
	 */
	public void setLocaleKey(Locale localeKey) {
		this.localeKey = localeKey;
	}
	
    /**
	 * @return the currentIdioma
	 */
	public Idioma getCurrentIdioma() {
		return currentIdioma;
	}

	/**
	 * @param currentIdioma the currentIdioma to set
	 */
	public void setCurrentIdioma(Idioma currentIdioma) {
		this.currentIdioma = currentIdioma;
	}	

	
	/**
	 * @return the targetMain
	 */
	public String getTargetMain() {
		return targetMain;
	}

	/**
	 * @param targetMain the targetMain to set
	 */
	public void setTargetMain(String targetMain) {
		this.targetMain = targetMain;
	}

	/**
	 * @return the pantallaAdicionalesXhtml
	 */
	public String getPantallaAdicionalesXhtml() {
		return pantallaAdicionalesXhtml;
	}

	/**
	 * @param pantallaAdicionalesXhtml the pantallaAdicionalesXhtml to set
	 */
	public void setPantallaAdicionalesXhtml(String pantallaAdicionalesXhtml) {
		this.pantallaAdicionalesXhtml = pantallaAdicionalesXhtml;
	}
	

}