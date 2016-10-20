package biz.belcorp.ssicc.web.framework.base.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.ReporteEnviadoMail;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;

/**
 * Clase ManageBeans Abstracta, contiene metodos comunes para el flujo de ejecucion
 * de Venatanas Popup
 * @author cbazalar
 *
 */
public abstract class BasePopupAbstractAction extends MBaseSistemaAbstractJSF  {

	private static final long serialVersionUID = -4069920993316967221L;

	//Bean donde se colocaran los criterios de Busqueda
	protected BaseSearchForm formBusqueda;  
	
	//List donde se guardara el resultado de la busqueda y será mostrada en el Datatable
	protected List listaBusqueda;  
	
	//Atributo DataTableModel usado en el Datatable la cual contiene la lista guardada en listaBusqueda
	protected DataTableModel datatableBusqueda; 
	
	
	//Bean que contendra el registro que haya sido seleccionado en el DataTable
	protected Object beanRegistroSeleccionado ; 
	
	//Verifica que el registro haya sido seleccionado
	//Para ello en el xhtml deben incluirlo con inputHidden
	protected boolean seleccionoRegistro= false;
	
	//Indicador si los registros de la tabla son seleccionables o no
	protected boolean seleccionable = true;	
	
	protected boolean mostrarWarnignBusqueda = true;
	
	protected boolean viewReporteMedia;
	
	protected boolean visualizarReporte = true;
	protected boolean validacionPrevia = false;
	protected ReporteEnviadoMail currentReporteEnviadoMail;
	protected boolean viewListaEnviados = false;
	private String outfileMedia = null;
	
	protected boolean mostrarBotonBuscar = true;
	protected boolean mostrarListaBusqueda = true;
	
	protected boolean activarBuscar = true;
	
	/**
	 * Metodo que verifica si se ha seleccionado algun registro del Datatable
	 * @return
	 */
	protected final boolean verificarRegistroSeleccionado() {
		boolean verificar= true;
		try {
			if (this.beanRegistroSeleccionado == null)
				verificar = false;
		}	
		catch (Exception e) {		
			verificar = false;
		}
		if (!verificar) 
			this.addWarn("Warning: ",this.getResourceMessage("errors.select.item"));
		else {
			this.mPantallaPrincipalBean.setCriteriosBusqueda(this.formBusqueda);
		}		
		return verificar;
	}
	
	
	/**
	 * Metodo que verifica que haya sido seleccionado algun registro del Datatable de Busqueda
	 * @param actionEvent
	 */
	public void verificarRegistro(ActionEvent actionEvent) {
		this.seleccionoRegistro= true;
		try {
			if (this.beanRegistroSeleccionado == null)
				seleccionoRegistro = false;
		}	
		catch (Exception e) {		
			seleccionoRegistro = false;
		}
		if (!seleccionoRegistro) 
			this.addWarn("Warning: ", this.getResourceMessage("errors.select.item"));	
		else {
			this.mPantallaPrincipalBean.setCriteriosBusqueda(this.formBusqueda);
		}		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setBeforeInitAtributes()
	 */
	@Override
	protected final void setBeforeViewAtributes() throws Exception {
		super.setBeforeViewAtributes();
		this.formBusqueda = this.devuelveFormBusqueda();
		this.listaBusqueda = new ArrayList();
		if (this.formBusqueda != null) {
			this.formBusqueda.setAnyoPeriodo(this.mPantallaPrincipalBean.getAnyoActualperiodo());
			this.formBusqueda.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		}
		
		this.viewReporteMedia = false;		
		this.visualizarReporte = true;		
		this.validacionPrevia = false;
		this.currentReporteEnviadoMail = null;
		this.viewListaEnviados = false;
		this.outfileMedia = null;
		
		this.setKeyMensajeAlertaDefault("reporte.enEjecucion");
	}
	
	/**
	 * Metodo que se ejecuta para cargar data inicial del Manage a traves de AJAX
	 * @param actionEvent
	 */
	public void viewAjax(ActionEvent actionEvent)  {
		log.debug("Entering view (Ajax)' - method");
		try {
			this.viewLogicaNegocio();
			this.mostrarWarnignBusqueda = false;
			if(this.activarBuscar)
				this.find();
			this.mostrarWarnignBusqueda = true;
		}
		catch(Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	
	/**
	 *  Metodo que limpia la Grilla de Busqueda
	 * de Busqueda
	 * Para las busquedas tipo AJAX
	 */
	public void limpiarFind(ActionEvent actionEvent) {
		this.limpiarFind();
	}
	
	/**
	 *  Metodo que limpia la Grilla de Busqueda
	 * de Busqueda
	 * Para las busquedas tipo AJAX
	 */
	public void limpiarFind() {
		try {	
			this.listaBusqueda = new ArrayList();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			this.setLimpiarFind();
		}
		catch(Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	
	/**
	 *  Metodo principal que efectua la busqueda en base a los filtros seleccionados en la pantalla
	 * de Busqueda
	 * Para las busquedas tipo AJAX
	 */
	public void find(ActionEvent actionEvent) {
		this.find();
	}
	
	public void find(String mensaje) {
		this.find();
		this.addInfo("Info: ", mensaje);
	}
	
	
	/**
	 * Metodo principal que efectua la busqueda en base a los filtros seleccionados en la pantalla
	 * de Busqueda
	 * Para las busquedas tipo ACTION
	 */
	public String find() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'find' method");
		}
		this.datatableBusqueda = null;
		this.listaBusqueda = new ArrayList();
		
		if(!this.validarFind()){
			this.addWarn("Warning: ", this.getMensajeAlertaDefault());
			return null;
		}
				
		try {			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			String accion = externalContext.getRequestParameterMap().get("accion"); 
  		    this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			if ((this.listaBusqueda == null) ||(this.listaBusqueda.size() == 0)){
				if (mostrarWarnignBusqueda)
					this.addWarn("Warning: ", this.getResourceMessage("errors.datos.fuentes.busqueda"));
			}
				
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		if (log.isDebugEnabled()) {
			log.debug("Finish 'find' method");
		}
		return null;
	}
	
	/**
	 * Realiza acciones personalizadas de validación previa, de existir, antes de realizar Busqueda
	 * @return
	 */
	private boolean validarFind(){
		boolean validacion = true;	
		String lsMensajeError = this.setValidarFind();
		if (StringUtils.isNotBlank(lsMensajeError)) {
			validacion = false;	
			this.setMensajeAlertaDefault(lsMensajeError);
		}
		return validacion;
	}
	
	/**
	 * Devuelve Mensaje de error personalizado de validacion extra antes de realizar busqueda
	 * @return
	 */
	public String setValidarFind(){
		return "";
	}
	
	
	
	/**
	 * Aqui se debe asociar la clase FORM al Manage Beans, la cual internamente se asociara al
	 * atributo formBusqueda
	 * @return
	 * @throws Exception
	 */
	protected abstract BaseSearchForm devuelveFormBusqueda() throws Exception;
	
	/**
	 * Hook method para la ejecucion de la Busqueda. Esta implementacion devuelve una lista con los valores
	 * respectivos de acuerdo a los filtros seleccionados. Dicho metodo es obligatorio sobreescribirlo 
	 * La busqueda por defecto es por AJAX
	 * @throws Exception
	 */
	protected abstract List setFindAttributes() throws Exception;
	
	
	
	/* GET - SET ATRIBUTOS */	
	/**
	 * @return the beanRegistroSeleccionado
	 */
	public Object getBeanRegistroSeleccionado() {
		return beanRegistroSeleccionado;
	}

	/**
	 * @param beanRegistroSeleccionado the beanRegistroSeleccionado to set
	 */
	public void setBeanRegistroSeleccionado(Object beanRegistroSeleccionado) {
		this.beanRegistroSeleccionado = beanRegistroSeleccionado;
	}

	/**
	 * @return the listaBusqueda
	 */
	public List getListaBusqueda() {
		return listaBusqueda;
	}

	/**
	 * @param listaBusqueda the listaBusqueda to set
	 */
	public void setListaBusqueda(List listaBusqueda) {
		this.listaBusqueda = listaBusqueda;
	}

	/**
	 * @return the formBusqueda
	 */
	public BaseSearchForm getFormBusqueda() {
		return formBusqueda;
	}

	/**
	 * @param formBusqueda the formBusqueda to set
	 */
	public void setFormBusqueda(BaseSearchForm formBusqueda) {
		this.formBusqueda = formBusqueda;
	}

	/**
	 * @return the datatableBusqueda
	 */
	public DataTableModel getDatatableBusqueda() {
		return datatableBusqueda;
	}


	/**
	 * @param datatableBusqueda the datatableBusqueda to set
	 */
	public void setDatatableBusqueda(DataTableModel datatableBusqueda) {
		this.datatableBusqueda = datatableBusqueda;
	}

	/**
	 * @return the seleccionoRegistro
	 */
	public boolean isSeleccionoRegistro() {
		return seleccionoRegistro;
	}


	/**
	 * @param seleccionoRegistro the seleccionoRegistro to set
	 */
	public void setSeleccionoRegistro(boolean seleccionoRegistro) {
		this.seleccionoRegistro = seleccionoRegistro;
	}


	/**
	 * @return the seleccionable
	 */
	public boolean isSeleccionable() {
		return seleccionable;
	}


	/**
	 * @param seleccionable the seleccionable to set
	 */
	public void setSeleccionable(boolean seleccionable) {
		this.seleccionable = seleccionable;
	}


	/**
	 * @return the mostrarWarnignBusqueda
	 */
	public boolean isMostrarWarnignBusqueda() {
		return mostrarWarnignBusqueda;
	}


	/**
	 * @param mostrarWarnignBusqueda the mostrarWarnignBusqueda to set
	 */
	public void setMostrarWarnignBusqueda(boolean mostrarWarnignBusqueda) {
		this.mostrarWarnignBusqueda = mostrarWarnignBusqueda;
	}


	public boolean isViewReporteMedia() {
		return viewReporteMedia;
	}


	public void setViewReporteMedia(boolean viewReporteMedia) {
		this.viewReporteMedia = viewReporteMedia;
	}


	public boolean isVisualizarReporte() {
		return visualizarReporte;
	}


	public void setVisualizarReporte(boolean visualizarReporte) {
		this.visualizarReporte = visualizarReporte;
	}


	public boolean isValidacionPrevia() {
		return validacionPrevia;
	}


	public void setValidacionPrevia(boolean validacionPrevia) {
		this.validacionPrevia = validacionPrevia;
	}


	public ReporteEnviadoMail getCurrentReporteEnviadoMail() {
		return currentReporteEnviadoMail;
	}


	public void setCurrentReporteEnviadoMail(
			ReporteEnviadoMail currentReporteEnviadoMail) {
		this.currentReporteEnviadoMail = currentReporteEnviadoMail;
	}


	public boolean isViewListaEnviados() {
		return viewListaEnviados;
	}


	public void setViewListaEnviados(boolean viewListaEnviados) {
		this.viewListaEnviados = viewListaEnviados;
	}


	public String getOutfileMedia() {
		return outfileMedia;
	}


	public void setOutfileMedia(String outfileMedia) {
		this.outfileMedia = outfileMedia;
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
	 * @return the mostrarListaBusqueda
	 */
	public boolean isMostrarListaBusqueda() {
		return mostrarListaBusqueda;
	}


	/**
	 * @param mostrarListaBusqueda the mostrarListaBusqueda to set
	 */
	public void setMostrarListaBusqueda(boolean mostrarListaBusqueda) {
		this.mostrarListaBusqueda = mostrarListaBusqueda;
	}


	public boolean isActivarBuscar() {
		return activarBuscar;
	}


	public void setActivarBuscar(boolean activarBuscar) {
		this.activarBuscar = activarBuscar;
	}
	
	

		
}
