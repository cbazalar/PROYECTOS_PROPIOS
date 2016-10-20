
package org.sistema.sisgen.web.base.manage;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.sistema.framework.dao.model.Base;
import org.sistema.framework.web.base.manage.MBaseSistemaAbstractJSF;

@ManagedBean
@SessionScoped
public class MPantallaPrincipalBean extends MBaseSistemaAbstractJSF {

    private static final long serialVersionUID = -2403138958014741653L;
    protected final static String LOGIN_FAILURE_PAGE = "/index.xhtml";
    protected final String PAQUETE_WEB = "org.sistema.sisgen.web";
    
    protected String ACCION_INGRESAR = "INGRESO";
    private String locale = new String("es") ; 
    private InetAddress address;
    private String contextRoot;
    private String paginaRutaCompletaXHTML;
    
    private int nroSession = 0;
    private List<Base> listaManageBeanSession = new ArrayList<Base>();
    private String nroSessionManageLimpiar = "0";
    
    /* Datos de configuracion del Sistema */
    private boolean mostrarEncabezadoSistema = true;
    private boolean mostrarTextoBotones = false;  //Para mostrar texto o no en los botones
    private final String IMG_OCULTAR_ENCABEZADO_SISTEMA = "arribaForm03.png";
    private final String IMG_MOSTRAR_ENCABEZADO_SISTEMA = "abajoForm03.png";
    private String imgMostrarEncabezadoSistema;
    private boolean indicadorMenuHorizontal = true;
    
    /* Atributos a Borrar */
    private String valorPrueba01;
    private String valorPrueba02;
   

	@Override
	protected void setViewAtributes() throws Exception {
		/* obteniendo Host */
		try {
			this.address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			this.address =null;
		}
		String hostName = "";
		if (this.address!=null) 	
			hostName = this.address.getHostName();
		log.debug("Host Name: " + hostName);
        this.contextRoot = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        log.debug(contextRoot);
        this.imgMostrarEncabezadoSistema = IMG_OCULTAR_ENCABEZADO_SISTEMA;
        
        
        valorPrueba01 = this.getResourceMessage("sistema.procesar.informacion");
		valorPrueba02 = this.getResourceMessage("button.edu.exonerar.asist");
		
	}

		    
    
	/**
     * Ingresar al Sistema cuando el usuario hizo aceptar en el Login  
     * @return
     */
    public String ingresarSistema() {    
		return "";
    }
    

    
    /**
     * Salir del Sistema
     * @return
     */
    public String salirSistema() {
    	String retorno = "/index.xhtml?faces-redirect=true";
    	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    	FacesContext.getCurrentInstance().getPartialViewContext().setRenderAll(true);
    	FacesContext.getCurrentInstance().renderResponse();
		return retorno;
	}
	
	
	/**
	 * Metodo que se ejecuta cuando se hace click en el Menu
	 * @param actionEvent
	 */
	public String ingresarOpcionMenu() {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ingresarOpcionMenu02' method");
        } 
		this.removeSessionManageBeanTodos();	
		String parametroXHTML = "";
		String codigoMenu = "";
		String paginaXHTML = "";
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			parametroXHTML = externalContext.getRequestParameterMap().get("parametroXHTML");
			codigoMenu = externalContext.getRequestParameterMap().get("codigoMenu");
			paginaXHTML = externalContext.getRequestParameterMap().get("paginaXHTML");
			this.redireccionarPagina(paginaXHTML);
			//FacesContext.getCurrentInstance().getExternalContext().redirect(parametroXHTML);
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			return null;
		}
		this.paginaRutaCompletaXHTML = parametroXHTML;
		return paginaXHTML;
		
	}
	
	public void ingresarOpcionMenu(ActionEvent actionEvent) {
		this.ingresarOpcionMenu();
	}
	
	
	/**
	 * Remueve los manageBeans en SessionScoped a excepcion del Manage ingresado como parametro
	 * @param manageActivo
	 */
	public void removeSessionManageBeanExcepto(String manageActivo) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'removeSessionManageBeanExcepto' method");
		}
		
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Iterator itr = sessionMap.entrySet().iterator();
		manageActivo = manageActivo.toUpperCase();
		log.debug("manage ini: " + manageActivo);
		while (itr.hasNext()) {
			Map.Entry e = (Map.Entry)itr.next();
			String key = e.getKey().toString().trim();
			String valor = e.getValue().toString().trim();
			boolean remover = true;
			log.debug("clave: "+e.getKey()+"valor:"+e.getValue());
			log.debug("clave: "+e.getKey()+"valor:"+e.getValue());
			if (StringUtils.equals("mPantallaPrincipalBean", key)) remover= false;
			if (StringUtils.equals(manageActivo, key.toUpperCase())) remover= false;
			if (StringUtils.contains(manageActivo, key.toUpperCase())) remover= false;
			if (remover) {
				if (StringUtils.contains(valor, PAQUETE_WEB)) {
					removeManagedBean(key);
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
					log.debug(" REMOVIDO");
				}	
			}
			
		}
	}
	
	
	/**
	 * Remueve los manageBeans en SessionScoped
	 * @param actionEvent
	 */
	public void removeSessionManageBeanTodos(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'removeSessionManageBeanTodos' method");
		}
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Iterator itr = sessionMap.entrySet().iterator();
		String pantallaPrincipal  = "mPantallaPrincipalBean";
		String pantallaAplicacion = "mAplicacionBean";
		String nroSessionActual = new Integer(this.nroSession).toString().trim();
		while (itr.hasNext()) {
			boolean remover = true;
			Map.Entry e = (Map.Entry)itr.next();
			String key = e.getKey().toString().trim();
			String valor = e.getValue().toString().trim();
			log.debug("clave: "+e.getKey()+"  valor:"+e.getValue());
			
			if (StringUtils.equals(pantallaPrincipal, key)) remover= false;
			if (StringUtils.equals(pantallaAplicacion, key)) remover= false;
		    if (remover) {
				if (StringUtils.contains(valor, PAQUETE_WEB)) {
					this.verificarRemoverManage(nroSessionActual, key);
				}	
			}
			
		}		
	}
	
	/**
	 * Remueve los manageBeans en SessionScoped
	 */
	public void removeSessionManageBeanTodos() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'removeSessionManageBeanTodos' method");
		}
		Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();;
		Map parametrosPantalla = new HashMap<String, String>();
		parametrosPantalla.putAll(parametros);
		String nroSessionActual = (String) parametrosPantalla.get("nroSession");
		if (StringUtils.isBlank(nroSessionActual)) {
		    nroSessionActual = this.nroSessionManageLimpiar;
		}
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Iterator itr = sessionMap.entrySet().iterator();
		String pantallaPrincipal  = "mPantallaPrincipalBean";
		String pantallaAplicacion = "mAplicacionBean";
		while (itr.hasNext()) {
			boolean remover = true;
			Map.Entry e = (Map.Entry)itr.next();
			String key = e.getKey().toString().trim();
			String valor = e.getValue().toString().trim();
			log.debug("clave: "+e.getKey()+"  valor:"+e.getValue());
			
			if (StringUtils.equals(pantallaPrincipal, key)) remover= false;
			if (StringUtils.equals(pantallaAplicacion, key)) remover= false;
			if (remover) {
				if (StringUtils.contains(valor, PAQUETE_WEB)) {
					this.verificarRemoverManage(nroSessionActual, key);
				}	
			}
			
		}		
	}

	/**
	 * @param nroSessionActual
	 * @param key
	 */
	private void verificarRemoverManage(String nroSessionActual, String key) {
		int contador = -1;
		for (int i=0; i < this.listaManageBeanSession.size(); i++) {
			Base beanSession = (Base) this.listaManageBeanSession.get(i);
			String manage = beanSession.getDescripcion();
			String nroSession = beanSession.getCodigo();
			if (StringUtils.equals(nroSession, nroSessionActual)) {
				if (StringUtils.equals(key.toLowerCase(), manage)) {
					contador = i;
					break;
				}
			}
		}
		
		boolean removerManage = true;
		for (int i=0; i < this.listaManageBeanSession.size(); i++) {
			Base beanSession = (Base) this.listaManageBeanSession.get(i);
			String manage = beanSession.getDescripcion();
			String nroSession = beanSession.getCodigo();
			if (!StringUtils.equals(nroSession, nroSessionActual)) {
				if (StringUtils.equals(key.toLowerCase(), manage)) {
					removerManage = false;
				}
			}
		}
		
		if (removerManage) {
			removeManagedBean(key);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
			if (contador > -1) {
				this.listaManageBeanSession.remove(contador);
			}
			log.debug(" REMOVIDO");
		}
	}
		
	
	/**
	 * @param actionEvent
	 */
	public void mostrarOcultarEncabezadoSistema(ActionEvent actionEvent) {
		this.mostrarEncabezadoSistema = !this.mostrarEncabezadoSistema;
		if (this.mostrarEncabezadoSistema)
			this.imgMostrarEncabezadoSistema = IMG_OCULTAR_ENCABEZADO_SISTEMA;
		else 
			this.imgMostrarEncabezadoSistema = IMG_MOSTRAR_ENCABEZADO_SISTEMA;
		return;
	}
	
	public void cambiarMeuHorizontal(ActionEvent actionEvent) {
		this.indicadorMenuHorizontal = true;
		try {
			this.redireccionarPagina("main");
		}
		catch (Exception e) {
			
		}
	}
	
	public void cambiarMeuVertical(ActionEvent actionEvent) {
		this.indicadorMenuHorizontal = false;
		try {
			this.redireccionarPagina("main");
		}
		catch (Exception e) {
			
		}
	}
	
	
	/* GET - SET */
 
	/**
	 * @return the mostrarTextoBotones
	 */
	public boolean isMostrarTextoBotones() {
		return mostrarTextoBotones;
	}

	/**
	 * @param mostrarTextoBotones the mostrarTextoBotones to set
	 */
	public void setMostrarTextoBotones(boolean mostrarTextoBotones) {
		this.mostrarTextoBotones = mostrarTextoBotones;
	}

	
	
	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}



	/**
	 * @return the address
	 */
	public InetAddress getAddress() {
		return address;
	}



	/**
	 * @param address the address to set
	 */
	public void setAddress(InetAddress address) {
		this.address = address;
	}



	/**
	 * @return the contextRoot
	 */
	public String getContextRoot() {
		return contextRoot;
	}



	/**
	 * @param contextRoot the contextRoot to set
	 */
	public void setContextRoot(String contextRoot) {
		this.contextRoot = contextRoot;
	}



	/**
	 * @return the paginaRutaCompletaXHTML
	 */
	public String getPaginaRutaCompletaXHTML() {
		return paginaRutaCompletaXHTML;
	}



	/**
	 * @param paginaRutaCompletaXHTML the paginaRutaCompletaXHTML to set
	 */
	public void setPaginaRutaCompletaXHTML(String paginaRutaCompletaXHTML) {
		this.paginaRutaCompletaXHTML = paginaRutaCompletaXHTML;
	}



	/**
	 * @return the nroSession
	 */
	public int getNroSession() {
		return nroSession;
	}



	/**
	 * @param nroSession the nroSession to set
	 */
	public void setNroSession(int nroSession) {
		this.nroSession = nroSession;
	}



	/**
	 * @return the listaManageBeanSession
	 */
	public List<Base> getListaManageBeanSession() {
		return listaManageBeanSession;
	}



	/**
	 * @param listaManageBeanSession the listaManageBeanSession to set
	 */
	public void setListaManageBeanSession(List<Base> listaManageBeanSession) {
		this.listaManageBeanSession = listaManageBeanSession;
	}



	/**
	 * @return the nroSessionManageLimpiar
	 */
	public String getNroSessionManageLimpiar() {
		return nroSessionManageLimpiar;
	}



	/**
	 * @param nroSessionManageLimpiar the nroSessionManageLimpiar to set
	 */
	public void setNroSessionManageLimpiar(String nroSessionManageLimpiar) {
		this.nroSessionManageLimpiar = nroSessionManageLimpiar;
	}

	/**
	 * @return the mostrarEncabezadoSistema
	 */
	public boolean isMostrarEncabezadoSistema() {
		return mostrarEncabezadoSistema;
	}



	/**
	 * @param mostrarEncabezadoSistema the mostrarEncabezadoSistema to set
	 */
	public void setMostrarEncabezadoSistema(boolean mostrarEncabezadoSistema) {
		this.mostrarEncabezadoSistema = mostrarEncabezadoSistema;
	}

	/**
	 * @return the imgMostrarEncabezadoSistema
	 */
	public String getImgMostrarEncabezadoSistema() {
		return imgMostrarEncabezadoSistema;
	}



	/**
	 * @param imgMostrarEncabezadoSistema the imgMostrarEncabezadoSistema to set
	 */
	public void setImgMostrarEncabezadoSistema(String imgMostrarEncabezadoSistema) {
		this.imgMostrarEncabezadoSistema = imgMostrarEncabezadoSistema;
	}


	/**
	 * @return the indicadorMenuHorizontal
	 */
	public boolean isIndicadorMenuHorizontal() {
		return indicadorMenuHorizontal;
	}



	/**
	 * @param indicadorMenuHorizontal the indicadorMenuHorizontal to set
	 */
	public void setIndicadorMenuHorizontal(boolean indicadorMenuHorizontal) {
		this.indicadorMenuHorizontal = indicadorMenuHorizontal;
	}
	
	
	
	
	
	
	
	
	
	

	


	/**
	 * @return the valorPrueba01
	 */
	public String getValorPrueba01() {
		return valorPrueba01;
	}



	/**
	 * @param valorPrueba01 the valorPrueba01 to set
	 */
	public void setValorPrueba01(String valorPrueba01) {
		this.valorPrueba01 = valorPrueba01;
	}



	/**
	 * @return the valorPrueba02
	 */
	public String getValorPrueba02() {
		return valorPrueba02;
	}



	/**
	 * @param valorPrueba02 the valorPrueba02 to set
	 */
	public void setValorPrueba02(String valorPrueba02) {
		this.valorPrueba02 = valorPrueba02;
	}



	



	
	
	
	
}