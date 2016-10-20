package org.sistema.framework.web.base.manage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.el.ELContext;
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

import org.sistema.framework.dao.Constants;
import org.sistema.framework.dao.model.LabelValue;
import org.sistema.framework.service.util.ConvertUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author cbazalar
 *
 */
public abstract class MBaseAbstractJSF implements Serializable  {

	private static final long serialVersionUID = 5439826023829031793L;
	protected transient final Log log = LogFactory.getLog(getClass());
	
	private final String FACES_REDIRECT = "?faces-redirect=true";
	
	// the Spring application context 
	protected ApplicationContext appContext;
	
	//Extension de la pagina xhtml
	protected final String extensionPagina = ".xhtml";
	
	private  static final String ARCHIVO_APPLICATION = "org.sistema.framework.i18n.ApplicationResources";
	private  static final String ARCHIVO_RESOURCE = "org.sistema.framework.i18n.resources";
	private  static final String ARCHIVO_REPORT_RESOURCE = "org.sistema.framework.i18n.ReportResources";
	
		
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
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", detail));  
    }  
  
    /**
     * Mensaje Warning General
     * @param message
     * @param detail
     */
    public void addWarn(String message, String detail) {  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", detail));
    }  
  
    /**
     * Mensaje Error General
     * @param message
     * @param detail
     */
    public void addError(String message, String detail) {  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", detail));  
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
		String retorno = null;
		if (StringUtils.isNotBlank(e.getMessage()))
			return e.getMessage();
		if (StringUtils.isNotBlank(e.getLocalizedMessage()))
			return e.getLocalizedMessage();
		retorno = e.toString();
		return retorno;
	}
	
	
	
	/* METODOS PARA OBTENER DATOS DEL ARCHIVO DE RECURSOS */
	
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
			if (StringUtils.isBlank(mensaje)) {
				ResourceBundle res3 = ResourceBundle.getBundle(this.ARCHIVO_REPORT_RESOURCE);
				mensaje = (String)res3.getObject(key);
			}
			log.debug("mensaje: " + mensaje);
			return mensaje;
		}
		catch (Exception e) {
			try {
				ResourceBundle res2 = ResourceBundle.getBundle(this.ARCHIVO_RESOURCE);
				String mensaje= (String)res2.getObject(key);
				if (StringUtils.isBlank(mensaje)) {
					ResourceBundle res3 = ResourceBundle.getBundle(this.ARCHIVO_REPORT_RESOURCE);
					mensaje = (String)res3.getObject(key);
				}
				return mensaje;
			}	
			catch (Exception excepcion) {
				try {
					ResourceBundle res2 = ResourceBundle.getBundle(this.ARCHIVO_REPORT_RESOURCE);
					String mensaje= (String)res2.getObject(key);
					return mensaje;
				}	
				catch (Exception e1) {
					return "";
				}
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
		String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		String path = contextPath + "/pages/" + paginaXhtml;
		FacesContext.getCurrentInstance().getExternalContext().redirect(path);
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
		
		String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		String path = contextPath + "/pages/" + paginaXhtml;
		if (StringUtils.isBlank(parametros)) 
			paginaRedirecciona = path;
		else
			paginaRedirecciona = path + parametros;	
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
		
		String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		String path = contextPath + "/pages/" + paginaXhtml;
		if (StringUtils.isBlank(parametros)) 
			paginaRedirecciona = path + "?accion=" + accion;
		else
			paginaRedirecciona = path + "?accion=" + accion + parametros;	
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
	 * @see org.sistema.framework.util.ConvertUtil#convert(java.lang.Object)
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
	 * @param beanName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T findManageBean(String beanName) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
	}
	
	/**
	 * Invocar a un managebean mapeado en el contexto
	 * @param beanName
	 * @return
	 */
	public static Object getManageBean(String beanName){
	    Object bean = null;
	    FacesContext fc = FacesContext.getCurrentInstance();
	    if(fc!=null){
	         ELContext elContext = fc.getELContext();
	         bean = elContext.getELResolver().getValue(elContext, null, beanName);
	    }

	    return bean;
	}
	
	
	/**
	 * Remuenve un manage beans
	 * @param beanName
	 */
	public static void removeManagedBean(final String beanName) {
	    FacesContext fc = FacesContext.getCurrentInstance();
	    fc.getELContext().getELResolver().setValue(fc.getELContext(), null, beanName, null);
	}
	
	/**
	 * Metodo que invoca el Request del Contexto
	 * @return
	 */
	public RequestContext getRequestContext(){
		return RequestContext.getCurrentInstance();
	}
	
	/**
	 * Metodo para generar Response en base a un Archivo
	 * @param filePath
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	protected void generarResponseOutputStream(String filePath) throws IOException, FileNotFoundException {
		HttpServletResponse response = this.getResponse();
		
		File srcFile = new File(filePath);
		OutputStream os = response.getOutputStream();
		byte[] buf = new byte[8192];
		InputStream is = new FileInputStream(srcFile);
		int c = 0;
		while ((c = is.read(buf, 0, buf.length)) > 0) {
		    os.write(buf, 0, c);
		    os.flush();
		}
		os.close();
		is.close();
		
		this.responseComplete();
	}
	
	
    
    /**
     * @param path
     * @return
     */
    protected String getNameFile(String path){
    	String [] temp = null;
    	try {
    		temp = path.split(Constants.EDU_FILE_SEPARATOR);    	
        	return temp[temp.length-1];
		} catch (Exception e) {
		}	
    	return path;
    }    

    
    /**
     * @param lista
     * @param codigo
     * @param descripcion
     * @return
     */
    protected LabelValue[] insertarVacioListaLabelValue(LabelValue[] lista, String codigo, String descripcion) {
    	int tam = lista.length;
    	LabelValue[] result = null;
    	result = new LabelValue[tam + 1];
    	LabelValue lv = new LabelValue(codigo, descripcion);
		result[0] = lv;
		
		for (int i = 1; i <= tam; i++) {
			result[i] = lista[i - 1];
		}
		return result;
    }
    
    
    
    /* GET -SET */
    
	
	
	
	
	
}
