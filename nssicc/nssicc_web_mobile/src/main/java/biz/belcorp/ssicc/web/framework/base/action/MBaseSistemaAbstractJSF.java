package biz.belcorp.ssicc.web.framework.base.action;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Menu;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.MenuService;
import biz.belcorp.ssicc.web.MPantallaPrincipalBean;

/**
 * @author cbazalar
 *
 */
public abstract class MBaseSistemaAbstractJSF extends MBaseAbstractJSF  {

	private static final long serialVersionUID = -3009427289377537151L;
	
	//Map que contendra los parametros ingresados por request a la pantalla la cual se cargaran en el
	//Post constructor del manage
	protected Map<String,String> parametrosPantalla;
	
	
	@ManagedProperty(value="#{mPantallaPrincipalBean}")
	protected MPantallaPrincipalBean mPantallaPrincipalBean;
	
	protected String nombreManageBean;
	
	/* Indica la accion a tomar por el manage */ 
	protected String accion;
	protected String codigoMenu;
	protected String codigoProcesoBatch;
	
	protected String ACCION_BUSCAR = "BUSCAR";
	protected String ACCION_NUEVO = "NUEVO";
	protected String ACCION_MODIFICAR = "MODIFICAR";
	protected String ACCION_ELIMINAR = "ELIMINAR";
	protected String ACCION_CONSULTAR = "CONSULTAR";
	protected String ACCION_GRABAR = "GRABAR";
	protected String ACCION_EJECUTAR = "EJECUTAR";
	protected String ACCION_OTRAS = "OTRAS";
	
	protected String accionFuncional = null;
	
	protected boolean mostrarProcesoBatch = true;
	protected boolean mostrarAyuda = false;
	protected boolean mostrarAuditoria = false;

	protected String anyoPeriodo;
	  
	/**
	 * Metodo que se ejecuta luego que se ejecuta el Constructor de la clase
	 */
	@PostConstruct
	public void view() {
		try {
			
			this.parametrosPantalla = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();			
					
			this.accion = (String)this.parametrosPantalla.get("accion");		
			this.codigoMenu = (String)this.parametrosPantalla.get("codigoMenu");
			this.codigoProcesoBatch = (String)this.parametrosPantalla.get("codigoProcesoBatch");
			this.mostrarProcesoBatch = true;
			this.mostrarAyuda = false;
			this.mostrarAuditoria = false;
			
						
			log.debug("this.parametrosPantalla PARAMETROS PANTALLA:" + this.parametrosPantalla);
			log.debug("this.accion:" + this.accion);
								
			this.nombreManageBean = this.getClass().getSimpleName();
			if (this.mPantallaPrincipalBean != null) {
				this.setAnyoPeriodo(this.mPantallaPrincipalBean.getAnyoActualperiodo());
			}
			this.setBeforeViewAtributes();
			this.setViewAtributes();
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * Metodo que libera memoria una vez que se cierra el ManageBean
	 * @param nombreManageBean
	 */
	public void salirManageBean(String nombreManageBean) {
		this.mPantallaPrincipalBean.removeSessionManageBeanTodos();
	}
	
	/**
	 * Metodo de Inicializacion Personalizada de la Clase ManageBeans, cuando se ingresa a la Opción
	 * @throws Exception
	 */
	protected abstract void setViewAtributes() throws Exception; 
	
	
	/**
	 * @throws Exception
	 */
	protected void setBeforeViewAtributes() throws Exception {
		
	}
	
	
	/**
	 * Metodo que agrega la opcion del Menu a la lista de Opciones Favoritas del Usuario
	 */
	public void agregarFavoritos(ActionEvent actionEvent) { 
		log.debug("this.accion: MBaseSistemaAbstractJSF.agregarFavoritos");
		try {
			
			String currentMenu = this.codigoMenu; //this.mPantallaPrincipalBean.getCurrentMenu();
			log.debug("currentMenu : "+currentMenu);
			
			if (StringUtils.isNotBlank(currentMenu)) {
				Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
				
				MenuService service = (MenuService)this.getBeanService("menuService");
	            Menu menu = service.getMenuJSF(currentMenu, usuario.getIdioma().getCodigo());
	            boolean existe = this.mPantallaPrincipalBean.getListaMenuFavoritos().contains(menu);
	            if (!existe)
	            	this.mPantallaPrincipalBean.getListaMenuFavoritos().add(menu);
			}
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	
	/**
	 * Mensaje Estandar de Eliminacion
	 * @return
	 */
	protected String devuelveMensajeKeyEliminarOK() {
		return "datos.delete.ok";
	}	
	
	
	/**
	 * Metodo que se ejecuta al hacer click en el Boton Aceptar de un Popup de Busqueda
	 * @param event
	 */
	public void aceptarPopup(ActionEvent event){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String accion = externalContext.getRequestParameterMap().get("parametroAccion");
		
		log.debug("parametroAccion:" + accion);
		this.setAceptarPopup(event, accion); 
	 
	}
	
	/**
	 * Metodo que se ejecuta al hacer click en el Boton Busqueda de Popup para mostrar el popup respectivo
	 * @param event
	 */
	public void invocarPopup(ActionEvent event){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String accion = externalContext.getRequestParameterMap().get("parametroAccion");
		
		log.debug("parametroAccion:" + accion);
		this.setInvocarPopup(accion); 
	 
	}
		
	/**
	 * Metodo que se ejecuta al hacer click en el Boton Salir del Popup para mostrar el popup respectivo
	 * @param event
	 */
	public void salirPopup(ActionEvent event){
		this.mostrarProcesoBatch = true;
	    this.setSalirPopup();
	}
		
	/**
	 * Metodo Hook que se debe sobreescribir con la logica respectiva al hacer click en el boton
	 * Aceptar del Popup
	 * @param accion
	 */
	protected void setAceptarPopup(ActionEvent event, String accion) {
		
	}
	
	
	/**
	 * Metodo Hook que se debe sobreescribir con la logica respectiva al hacer click en el boton de Busqueda de Popup
	 * @param accion
	 */
	protected void setInvocarPopup(String accion) {
		
	}
	
	
    /**
     * Metodo Hook que se debe sobreescribir con la logica respectiva al hacer click en el boton Salir del Popup
     * @param accion
     */
    protected void setSalirPopup() {
		
	}
    
    /**
     * Muestra Contenido con referencia a la Ayuda de la Opcion Seleccionada
     * @param event
     */
    public void mostrarContenidoAyudaOpcion(ActionEvent event){
		this.mostrarAyuda = true;
		this.mostrarAuditoria = false;
		this.mostrarProcesoBatch= false;
	}
  
 
	
	
	/* GET - SET ATRIBUTOS */
	/**
	 * @return the mPantallaPrincipalBean
	 */
	public MPantallaPrincipalBean getmPantallaPrincipalBean() {
		return mPantallaPrincipalBean;
	}

	/**
	 * @param mPantallaPrincipalBean the mPantallaPrincipalBean to set
	 */
	public void setmPantallaPrincipalBean(
			MPantallaPrincipalBean mPantallaPrincipalBean) {
		this.mPantallaPrincipalBean = mPantallaPrincipalBean;
	}

	/**
	 * @return the parametrosPantalla
	 */
	public Map<String, String> getParametrosPantalla() {
		return parametrosPantalla;
	}

	/**
	 * @param parametrosPantalla the parametrosPantalla to set
	 */
	public void setParametrosPantalla(Map<String, String> parametrosPantalla) {
		this.parametrosPantalla = parametrosPantalla;
	}
	
		
	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}


	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * @return the codigoMenu
	 */
	public String getCodigoMenu() {
		return codigoMenu;
	}

	/**
	 * @param codigoMenu the codigoMenu to set
	 */
	public void setCodigoMenu(String codigoMenu) {
		this.codigoMenu = codigoMenu;
	}

	/**
	 * @return the nombreManageBean
	 */
	public String getNombreManageBean() {
		return nombreManageBean;
	}

	/**
	 * @param nombreManageBean the nombreManageBean to set
	 */
	public void setNombreManageBean(String nombreManageBean) {
		this.nombreManageBean = nombreManageBean;
	}

	/**
	 * @return the mostrarProcesoBatch
	 */
	public boolean isMostrarProcesoBatch() {
		return mostrarProcesoBatch;
	}

	/**
	 * @param mostrarProcesoBatch the mostrarProcesoBatch to set
	 */
	public void setMostrarProcesoBatch(boolean mostrarProcesoBatch) {
		this.mostrarProcesoBatch = mostrarProcesoBatch;
	}
	
	/**
	 * @return the codigoProcesoBatch
	 */
	public String getCodigoProcesoBatch() {
		return codigoProcesoBatch;
	}

	/**
	 * @param codigoProcesoBatch the codigoProcesoBatch to set
	 */
	public void setCodigoProcesoBatch(String codigoProcesoBatch) {
		this.codigoProcesoBatch = codigoProcesoBatch;
	}

	/**
	 * @return the mostrarAyuda
	 */
	public boolean isMostrarAyuda() {
		return mostrarAyuda;
	}

	/**
	 * @param mostrarAyuda the mostrarAyuda to set
	 */
	public void setMostrarAyuda(boolean mostrarAyuda) {
		this.mostrarAyuda = mostrarAyuda;
	}

	public boolean isMostrarAuditoria() {
		return mostrarAuditoria;
	}

	public void setMostrarAuditoria(boolean mostrarAuditoria) {
		this.mostrarAuditoria = mostrarAuditoria;
	}

	/**
	 * @return the accionFuncional
	 */
	public String getAccionFuncional() {
		return accionFuncional;
	}

	/**
	 * @param accionFuncional the accionFuncional to set
	 */
	public void setAccionFuncional(String accionFuncional) {
		this.accionFuncional = accionFuncional;
	}

	/**
	 * @return the anyoPeriodo
	 */
	public String getAnyoPeriodo() {
		return anyoPeriodo;
	}

	/**
	 * @param anyoPeriodo the anyoPeriodo to set
	 */
	public void setAnyoPeriodo(String anyoPeriodo) {
		this.anyoPeriodo = anyoPeriodo;
	}
	
	
	
	
}
