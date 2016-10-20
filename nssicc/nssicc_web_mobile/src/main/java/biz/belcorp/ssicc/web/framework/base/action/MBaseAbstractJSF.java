package biz.belcorp.ssicc.web.framework.base.action;

import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import biz.belcorp.ssicc.service.util.ConvertUtil;

/**
 * @author cbazalar
 *
 */
public abstract class MBaseAbstractJSF implements Serializable  {

	private static final long serialVersionUID = 5439826023829031793L;
	private final String FACES_REDIRECT = "?faces-redirect=true";
	
	protected transient final Log log = LogFactory.getLog(getClass());
	
	// the Spring application context 
	protected ApplicationContext appContext;
	
	//Extension de la pagina xhtml
	protected final String extensionPagina = ".xhtml";
		
	/**
	 * Constructor de la clase
	 */
	public MBaseAbstractJSF() {
		ServletContext context = getServletContext();
		this.appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
	}
	
	/** METODOS PARA SPRING **/
	/**
	 * Lookup service based on service bean name.
	 * 
	 * @param serviceBeanName the service bean name
	 * @return the service bean
	 */
	public Object getBeanService(String serviceBeanName) {
		return appContext.getBean(serviceBeanName);
	}
	
	
	/**
	 * Lookup service based on service bean name.
	 * @param serviceBeanName
	 * @return
	 */
	public Object getBean(String serviceBeanName) {
		return appContext.getBean(serviceBeanName);
	}
	
	/* METODOS PARA OBTENER ATRIBUTOS DEL FACECONTEXT */
	
	/**
	 * Retorna objeto Aplicacion
	 * @return
	 */
	protected Application getApplication() {
		ApplicationFactory appFactory = (ApplicationFactory)FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);		
		return appFactory.getApplication(); 
	}
	
	/**
	 * Obtiene el ServletContext
	 * @return
	 */
	public ServletContext getServletContext() {
		return (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
	}
	
	/**
	 * Obtiene Request
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	/**
	 * Obtiene Response
	 * @return
	 */
	public HttpServletResponse getResponse() {
		HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		return response;
	}  
	
	/**
	 * Obtiene Session
	 * @return
	 */
	public HttpSession getSession(boolean create) {
		FacesContext f = FacesContext.getCurrentInstance();
		ExternalContext e = f.getExternalContext();
		return (HttpSession) e.getSession(create);
	}
	
	
	
	
	/* METODOS PARA CONTROL DE ERRORES */
	/**
	 * Mensaje informativo General 
	 * @param message
	 * @param detail
	 */
	public void addInfo(String message, String detail) {  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, detail));  
    }  
  
    /**
     * Mensaje Warning General
     * @param message
     * @param detail
     */
    public void addWarn(String message, String detail) {  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, detail));  
    }  
  
    /**
     * Mensaje Error General
     * @param message
     * @param detail
     */
    public void addError(String message, String detail) {  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, detail));  
    }  
  
    /**
     * Mensaje Fatal General
     * @param message
     * @param detail
     */
    public void addFatal(String message, String detail) {  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, detail));  
    }  
    
    /**
	 * Setea la variable de Mensaje de Error en base a la excepcion ingresada como parametro
	 * @param e
	 */
	protected String obtieneMensajeErrorException(Exception e) {
		if (StringUtils.isNotBlank(e.getMessage()))
			return e.getMessage();
		else
			return e.getLocalizedMessage();
	}
	
	
	
	/* METODOS PARA OBTENER DATOS DEL ARCHIVO DE RECURSOS */
	private  static final String ARCHIVO_APPLICATION = "biz.belcorp.ssicc.i18n.ApplicationResources";
	private  static final String ARCHIVO_RESOURCE = "biz.belcorp.ssicc.i18n.resources";
	private  static final String ARCHIVO_REPORT_RESOURCE = "biz.belcorp.ssicc.i18n.ReportResources";
	
	/**
	 * Retorna Mensaje en base a key ingresado. Dicho key busca en el archivo de Recursos 
	 * del Sistema
	 * @param key
	 * @return
	 */
	public String getResourceMessage(String key) { 
		if (log.isDebugEnabled()) {
			log.debug("Entering 'getResourceMessage' method");
		}
		try {
			ResourceBundle res = ResourceBundle.getBundle(this.ARCHIVO_APPLICATION);
			String mensaje = (String)res.getObject(key);
			if (StringUtils.isBlank(mensaje)) {
				ResourceBundle res2 = ResourceBundle.getBundle(this.ARCHIVO_RESOURCE);
				mensaje = (String)res2.getObject(key);
			}
			log.debug("mensaje: " + mensaje);
			return mensaje;
		}
		catch (Exception e) {
			try {
				ResourceBundle res2 = ResourceBundle.getBundle(this.ARCHIVO_RESOURCE);
				String mensaje= (String)res2.getObject(key);
				return mensaje;
			}	
			catch (Exception excepcion) {
				return "";
			}

		}
	}
	
	public String getReportResourceMessage(String key){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'getReportResourceMessage' method");
		}
		try {
			ResourceBundle res = ResourceBundle.getBundle(this.ARCHIVO_REPORT_RESOURCE);					
			String mensaje = (String)res.getObject(key);			
			log.debug("mensaje: " + mensaje);
			return mensaje;
		}
		catch (Exception e) {
			log.error("Error to get Resource Boundle "+e);
			return "";
		}
	}
	
	/**
	 * @param key
	 * @param args
	 * @return
	 */
	public String getResourceMessage(String key, Object[] args) {
		String iString = null;
		String value = getResourceMessage(key);

		try {
			Object nonNullArgs[] = args;
	        for (int i=0; i<args.length; i++) {
	        	if (args[i] == null) {
	        		if (nonNullArgs==args) nonNullArgs=(Object[])args.clone();
	        			nonNullArgs[i] = "null";
				}
		    }
	        
	        iString = MessageFormat.format(value, nonNullArgs);
		} 
		catch (IllegalArgumentException iae) {
		    StringBuffer buf = new StringBuffer();
		    buf.append(value);
		    
		    for (int i = 0; i < args.length; i++) {
		    	buf.append(" arg[" + i + "]=" + args[i]);
		    }
		    
		    iString = buf.toString();
		}
		return iString;
	}
	
	
	
	/* METODOS DE REDIRECCION DE PAGINA, PARA EL CASO DE BOTONES DE AJAX */
	
	
	/**
	 * Coloca la extension xhtml a la pagina en caso no hubiera
	 * @param paginaXhtml
	 * @return
	 */
	protected String colocarExtensionPaginaXHTML(String paginaXhtml) {
		if (StringUtils.isBlank(paginaXhtml)) return paginaXhtml;
		if (!this.extensionPagina.equals(StringUtils.right(paginaXhtml, 6))) {
			paginaXhtml = paginaXhtml + this.extensionPagina;
		}
		return paginaXhtml;
	}
	
	
	/**
	 * Metodo que adiciona parametros a la pagina a redireccionar
	 * @param paginaXhtml
	 * @param parametros
	 * @return
	 * @throws IOException
	 */
	public String adicionaParametrosPagina(String paginaXhtml, String parametros) throws IOException {
		String paginaRedirecciona = new String();
		if (StringUtils.isBlank(paginaXhtml)) return null;
		if (StringUtils.isBlank(parametros)) 
			paginaRedirecciona = paginaXhtml;
		else 
			paginaRedirecciona = paginaXhtml + FACES_REDIRECT + parametros;	
		return paginaRedirecciona;
	}
	
	
	/**
	 * Metodo que adiciona parametros a la pagina a redireccionar
	 * @param paginaXhtml
	 * @param accion
	 * @param parametros
	 * @return
	 * @throws IOException
	 */
	public String adicionaParametrosPagina(String paginaXhtml, String accion, String parametros) throws IOException {
		String paginaRedirecciona = new String();
		if (StringUtils.isBlank(paginaXhtml)) return null;		
		if (StringUtils.isBlank(accion)) return paginaXhtml;	
		if (StringUtils.isBlank(parametros)) 
			paginaRedirecciona = paginaXhtml + FACES_REDIRECT + "&accion=" + accion;
		else
			paginaRedirecciona = paginaXhtml + FACES_REDIRECT + "&accion=" + accion + parametros;	
		return paginaRedirecciona;
	}
	
	/**
	 * Metodo que redirecciona de una pagina xhtml a otra
	 * @param paginaXhtml
	 * @throws IOException
	 */
	public void redireccionarPagina(String paginaXhtml) throws IOException {
		if (StringUtils.isBlank(paginaXhtml)) return;
		paginaXhtml = this.colocarExtensionPaginaXHTML(paginaXhtml);		
		FacesContext.getCurrentInstance().getExternalContext().redirect(paginaXhtml);
	}

	
	
	/**
	 * Metodo que redirecciona de una pagina xhtml a otra
	 * @param paginaXhtml
	 * @param parametros
	 * @throws IOException
	 */
	public void redireccionarPagina(String paginaXhtml, String parametros) throws IOException {
		String paginaRedirecciona = new String();
		if (StringUtils.isBlank(paginaXhtml)) return;
		paginaXhtml = this.colocarExtensionPaginaXHTML(paginaXhtml);
		if (StringUtils.isBlank(parametros)) 
			paginaRedirecciona = paginaXhtml;
		else
			paginaRedirecciona = paginaXhtml + parametros;	
		FacesContext.getCurrentInstance().getExternalContext().redirect(paginaRedirecciona);
	}
	
	
	/**
	 * Metodo que redirecciona de una pagina xhtml a otra
	 * @param paginaXhtml
	 * @param accion
	 * @param parametros
	 * @throws IOException
	 */
	public void redireccionarPagina(String paginaXhtml, String accion, String parametros) throws IOException {
		String paginaRedirecciona = new String();
		if (StringUtils.isBlank(paginaXhtml)) return;		
		if (StringUtils.isBlank(accion)) return;	
		paginaXhtml = this.colocarExtensionPaginaXHTML(paginaXhtml);
		if (StringUtils.isBlank(parametros)) 
			paginaRedirecciona = paginaXhtml + "?accion=" + accion;
		else
			paginaRedirecciona = paginaXhtml + "?accion=" + accion + parametros;	
		FacesContext.getCurrentInstance().getExternalContext().redirect(paginaRedirecciona);
	}
	
	/**
	 * Metodo que redirecciona a una pagina en ontra ventana
	 * @throws IOException
	 */
	public void responseComplete() throws IOException {	
		FacesContext.getCurrentInstance().responseComplete();		
	}
	
	/**
	 * @see biz.belcorp.ssicc.util.ConvertUtil#convert(java.lang.Object)
	 */
	protected Object convert(Object o) throws Exception {
		return ConvertUtil.convert(o);
	}
	
	
	/**
	 * Devuelve el Mensaje de Error producto de las validaciones de un componente
	 * @param clientId
	 * @return
	 */
	public String getErrorMessage(final String clientId) {
		Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages(clientId);
		if (iter.hasNext()) {
		    return iter.next().getDetail(); // or getSummary()
		}
		return "";
	}
	
	
	/**
	 * Invocar a un managebean mapeado en el contexto
	 * @param manage
	 * @return
	 */
	public MBaseAbstractJSF getManageBeans(String manage) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ELContext ec = ctx.getELContext();
		Application app = this.getApplication();
		ExpressionFactory ef = app.getExpressionFactory();
		ValueExpression ve = ef.createValueExpression(ec, manage, MBaseAbstractJSF.class);
		MBaseAbstractJSF ub = (MBaseAbstractJSF) ve.getValue(ec);
		return ub;
	}
	
}
