package org.sistema.framework.web.base.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.sistema.framework.dao.model.Base;
import org.sistema.framework.dao.model.LabelValue;
import org.sistema.framework.web.base.form.BaseForm;

/**
 * @author cbazalar
 * 
 */
public abstract class MBaseSistemaAbstractJSF extends MBaseAbstractJSF {

	private static final long serialVersionUID = -3009427289377537151L;
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	// Map que contendra los parametros ingresados por request a la pantalla la
	// cual se cargaran en el
	// Post constructor del manage
	protected Map<String, String> parametrosPantalla;

	protected String nombreManageBean;
	protected String nroSessionManage;


	/* Indica la accion a tomar por el manage */
	protected String accion;
	protected String codigoMenu;

	protected String ACCION_INGRESAR = "INGRESO";
	protected String ACCION_BUSCAR = "BUSCAR";
	protected String ACCION_NUEVO = "NUEVO";
	protected String ACCION_MODIFICAR = "MODIFICAR";
	protected String ACCION_ELIMINAR = "ELIMINAR";
	protected String ACCION_CONSULTAR = "CONSULTAR";
	protected String ACCION_GRABAR = "GRABAR";
	protected String ACCION_EJECUTAR = "EJECUTAR";
	protected String ACCION_REPORTE = "REPORTE";
	protected String ACCION_OTRAS = "OTRAS";

 											
	protected String paginaRutaCompletaXHTML;
	protected String cantidadRowsDefaultDatatable = "10";
	
	protected boolean mostrarBotonBuscar = true;
	protected boolean mostrarBotonNuevo = true;
	protected boolean mostrarBotonModificar = true;
	protected boolean mostrarBotonConsultar = true;
	protected boolean mostrarBotonEliminar = true;
	protected boolean mostrarBotonGrabar = true;
	protected boolean mostrarBotonSalir = true;
	
	protected String mensajeAlertaDefault="";
	protected String mensajeAlertaActionDefault="";
	protected String accionAlertaActionDefault="";
	
	/**
	 * Metodo que se ejecuta luego que se ejecuta el Constructor de la clase
	 */
	@PostConstruct
	public void view() {
		try {
			Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();;
			this.parametrosPantalla = new HashMap<String, String>();
			this.parametrosPantalla.putAll(parametros);
			
			this.adicionarManageListaSession();
			this.viewLogicaNegocio();
			

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}


	/**
	 * Metodo con la logica de negocio del Metodo View
	 */
	public final void viewLogicaNegocio() throws Exception {
		this.accion = (String) this.parametrosPantalla.get("accion");
		this.codigoMenu = (String) this.parametrosPantalla.get("codigoMenu");
		log.debug("this.parametrosPantalla PARAMETROS PANTALLA:"	+ this.parametrosPantalla);
		log.debug("this.accion:" + this.accion);

		this.nombreManageBean = this.getClass().getSimpleName();
		this.setAntesBeforeViewAtributes();
		this.setBeforeViewAtributes();
		this.setViewAtributes();
		
	}
	
	/**
	 * Metodo de Regreso a la pantalla Padre
	 * @param actionEvent
	 */
	public void regresarPantalla(ActionEvent actionEvent) {
		try {
			
			this.setRegresarPantalla();
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	
	/**
	 * Metodo Hook Regreso a la pantalla Padre
	 */
	protected void setRegresarPantalla() throws Exception{
		
	}
	
	/**
	 * Metodo al hacer click en Acetar de la Ventana de Alerta Action
	 * @param actionEvent
	 */
	public void aceptarAlertaDefaultAction(ActionEvent actionEvent) {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'aceptarAlertaDefaultAction' method");
		}
		
		/* Redireccionando a la pagina respectiva */
		try {
			//ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			//String accion = externalContext.getRequestParameterMap().get("parametroAccion");
			this.setAceptarAlertaDefaultAction(this.accionAlertaActionDefault);
			
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			
		}
		if (log.isWarnEnabled()) {
			log.warn("Finish 'aceptarAlertaDefaultAction' method");
		}
		return;
	}
	
	

	/**
	 * Metodo Hook al hacer click en Acetar de la Ventana de Alerta Action
	 */
	protected void setAceptarAlertaDefaultAction(String accion) throws Exception{
		
	}
	
	/**
	 * Adiciona el manage respectivo a la lista de Manages activos en Session
	 */
	protected void adicionarManageListaSession() {
		
	}
	

	
	/**
	 * Metodo de Inicializacion Personalizada de la Clase ManageBeans, cuando se
	 * ingresa a la Opción
	 * 
	 * @throws Exception
	 */
	protected abstract void setViewAtributes() throws Exception;

	
	/**
	 * @throws Exception
	 */
	protected void setAntesBeforeViewAtributes() throws Exception {
		
	}
	
	/**
	 * @throws Exception
	 */
	protected void setBeforeViewAtributes() throws Exception {

	}

	
	/**
	 * Metodo que prepara los parametros a pasar al Service para la ejecucion.
	 * Remueve parametros innecesarios generados en la capa web, y setea los
	 * parametros necesarios para la capa service. Este metodo puede ser
	 * sobreescrito en caso se requieran mas parametros.
	 * 
	 * @param params
	 * @param form
	 * @return
	 * @throws Exception
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(
			Map<String, Object> params, BaseForm form) throws Exception {
		// Paso todos los parametros al map
		params = BeanUtils.describe(form);

		
		// Remuevo los parametros que no son necesarios del Validator
		params.remove("resultValueMap");
		params.remove("validatorResults");
		params.remove("servletWrapper");
		params.remove("multipartRequestHandler");
		params.remove("class");
		params.remove("page");
		params.remove("parametrosPantalla");
		
		return params;
	}

	/**
	 * Descripcion Múltiple para imprimir en los reportes
	 * @param datos
	 * @param lista
	 * @return
	 */
	public String descripcionMultipleLista(String[] datos, List lista) {
		String descripcionLista = "";
		String descripcion = "";
		String[] wdatos = datos;
		for (int i = 0; i < wdatos.length; i++) {
			String dato = wdatos[i];
			for (int j = 0; j < lista.size(); j++) {
				Base base = (Base) lista.get(j);
				String codigo = base.getCodigo();
				if (dato.equals(codigo)) {
					descripcion = base.getDescripcion();
					descripcionLista = descripcionLista + descripcion + "\n";
					break;
				}
			}
		}
		return descripcionLista;
	}

	/**
	 * Descripcion Múltiple para imprimir en los reportes
	 * @param datos
	 * @param lista
	 * @return
	 */
	public String descripcionMultipleLista(String[] datos, LabelValue[] lista) {
		String descripcionList = "";
		String descripcion = "";
		String[] wdatos = datos;
		for (int i = 0; i < wdatos.length; i++) {
			String dato = wdatos[i];
			for (int j = 0; j < lista.length; j++) {
				String codigo = lista[j].getValue();
				if (dato.equals(codigo)) {
					descripcion = lista[j].getLabel();
					descripcionList = descripcionList + descripcion + "\n";
					break;
				}
			}

		}
		return descripcionList;
	}

	
	/**
	 * Descripcion Simple para imprimir en los reportes
	 * @param dato
	 * @param lista
	 * @return
	 */
	public String descripcionSimpleLista(String dato, LabelValue[] lista) {
		String descripcionList = "";
		String descripcion = "";
		for (int j = 0; j < lista.length; j++) {
			String codigo = lista[j].getValue();
			if (dato.equals(codigo)) {
				descripcion = lista[j].getLabel();
				descripcionList = descripcionList + descripcion;
				break;
			}
		}
		return descripcionList;
	}

	/**
	 * Descripcion SIMPLE para imprimir en los reportes
	 * @param dato
	 * @param lista
	 * @return
	 */
	public String descripcionSimpleLista(String dato, List lista) {
		String descripcionLista = "";
		String descripcion = "";
		for (int j = 0; j < lista.size(); j++) {
			Base base = (Base) lista.get(j);
			String codigo = base.getCodigo();
			if (dato.equals(codigo)) {
				descripcion = base.getDescripcion();
				descripcionLista = descripcionLista + descripcion;
				break;
			}
		}
		return descripcionLista;
	}

	
		
	/**
	 * Validacion de Email
	 * @param email
	 * @return
	 */
	public static boolean validateEmail(String email) {
		 
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
 
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
 
    }
	
	
	

	/* GET - SET ATRIBUTOS */


	/**
	 * @return the parametrosPantalla
	 */
	public Map<String, String> getParametrosPantalla() {
		return parametrosPantalla;
	}

	/**
	 * @param parametrosPantalla
	 *            the parametrosPantalla to set
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
	 * @param accion
	 *            the accion to set
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
	 * @param codigoMenu
	 *            the codigoMenu to set
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
	 * @param nombreManageBean
	 *            the nombreManageBean to set
	 */
	public void setNombreManageBean(String nombreManageBean) {
		this.nombreManageBean = nombreManageBean;
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
	 * @return the nroSessionManage
	 */
	public String getNroSessionManage() {
		return nroSessionManage;
	}


	/**
	 * @param nroSessionManage the nroSessionManage to set
	 */
	public void setNroSessionManage(String nroSessionManage) {
		this.nroSessionManage = nroSessionManage;
	}


	/**
	 * @return the cantidadRowsDefaultDatatable
	 */
	public String getCantidadRowsDefaultDatatable() {
		return cantidadRowsDefaultDatatable;
	}


	/**
	 * @param cantidadRowsDefaultDatatable the cantidadRowsDefaultDatatable to set
	 */
	public void setCantidadRowsDefaultDatatable(String cantidadRowsDefaultDatatable) {
		this.cantidadRowsDefaultDatatable = cantidadRowsDefaultDatatable;
	}


	/**
	 * @return the mostrarBotonBuscar
	 */
	public boolean isMostrarBotonBuscar() {
		return mostrarBotonBuscar;
	}


	/**
	 * @param mostrarBotonBuscar the mostrarBotonBuscar to set
	 */
	public void setMostrarBotonBuscar(boolean mostrarBotonBuscar) {
		this.mostrarBotonBuscar = mostrarBotonBuscar;
	}


	/**
	 * @return the mostrarBotonNuevo
	 */
	public boolean isMostrarBotonNuevo() {
		return mostrarBotonNuevo;
	}


	/**
	 * @param mostrarBotonNuevo the mostrarBotonNuevo to set
	 */
	public void setMostrarBotonNuevo(boolean mostrarBotonNuevo) {
		this.mostrarBotonNuevo = mostrarBotonNuevo;
	}


	/**
	 * @return the mostrarBotonModificar
	 */
	public boolean isMostrarBotonModificar() {
		return mostrarBotonModificar;
	}


	/**
	 * @param mostrarBotonModificar the mostrarBotonModificar to set
	 */
	public void setMostrarBotonModificar(boolean mostrarBotonModificar) {
		this.mostrarBotonModificar = mostrarBotonModificar;
	}


	/**
	 * @return the mostrarBotonConsultar
	 */
	public boolean isMostrarBotonConsultar() {
		return mostrarBotonConsultar;
	}


	/**
	 * @param mostrarBotonConsultar the mostrarBotonConsultar to set
	 */
	public void setMostrarBotonConsultar(boolean mostrarBotonConsultar) {
		this.mostrarBotonConsultar = mostrarBotonConsultar;
	}


	/**
	 * @return the mostrarBotonEliminar
	 */
	public boolean isMostrarBotonEliminar() {
		return mostrarBotonEliminar;
	}


	/**
	 * @param mostrarBotonEliminar the mostrarBotonEliminar to set
	 */
	public void setMostrarBotonEliminar(boolean mostrarBotonEliminar) {
		this.mostrarBotonEliminar = mostrarBotonEliminar;
	}


	/**
	 * @return the mostrarBotonGrabar
	 */
	public boolean isMostrarBotonGrabar() {
		return mostrarBotonGrabar;
	}


	/**
	 * @param mostrarBotonGrabar the mostrarBotonGrabar to set
	 */
	public void setMostrarBotonGrabar(boolean mostrarBotonGrabar) {
		this.mostrarBotonGrabar = mostrarBotonGrabar;
	}


	/**
	 * @return the mostrarBotonSalir
	 */
	public boolean isMostrarBotonSalir() {
		return mostrarBotonSalir;
	}


	/**
	 * @param mostrarBotonSalir the mostrarBotonSalir to set
	 */
	public void setMostrarBotonSalir(boolean mostrarBotonSalir) {
		this.mostrarBotonSalir = mostrarBotonSalir;
	}


	/**
	 * @return the mensajeAlertaDefault
	 */
	public String getMensajeAlertaDefault() {
		return mensajeAlertaDefault;
	}


	/**
	 * @param mensajeAlertaDefault the mensajeAlertaDefault to set
	 */
	public void setMensajeAlertaDefault(String mensajeAlertaDefault) {
		this.mensajeAlertaDefault = mensajeAlertaDefault;
	}


	/**
	 * @return the mensajeAlertaActionDefault
	 */
	public String getMensajeAlertaActionDefault() {
		return mensajeAlertaActionDefault;
	}


	/**
	 * @param mensajeAlertaActionDefault the mensajeAlertaActionDefault to set
	 */
	public void setMensajeAlertaActionDefault(String mensajeAlertaActionDefault) {
		this.mensajeAlertaActionDefault = mensajeAlertaActionDefault;
	}


	/**
	 * @return the accionAlertaActionDefault
	 */
	public String getAccionAlertaActionDefault() {
		return accionAlertaActionDefault;
	}


	/**
	 * @param accionAlertaActionDefault the accionAlertaActionDefault to set
	 */
	public void setAccionAlertaActionDefault(String accionAlertaActionDefault) {
		this.accionAlertaActionDefault = accionAlertaActionDefault;
	}

	
	

}
