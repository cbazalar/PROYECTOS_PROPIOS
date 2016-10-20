package biz.belcorp.ssicc.web.framework.base.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoAuditoria;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;

/**
 * Clase ManageBeans Abstracta, contiene metodos comunes para el flujo de ejecucion
 * de Consultas
 * @author cbazalar
 *
 */
public abstract class BaseConsultaAbstractAction extends MBaseSistemaAbstractJSF  {

	private static final long serialVersionUID = -4069920993316975221L;

	//Bean donde se colocaran los criterios de Busqueda
	protected BaseSearchForm formBusqueda;
	
	//List donde se guardara el resultado de la busqueda y será mostrada en el Datatable
	protected List listaBusqueda;  
	protected boolean mostrarListaBusqueda = true;
	
	//Atributo DataTableModel usado en el Datatable la cual contiene la lista guardada en listaBusqueda
	protected DataTableModel datatableBusqueda; 
	
	
	//Bean que contendra el registro que haya sido seleccionado en el DataTable
	protected Object beanRegistroSeleccionado ; 
	
	//Verifica que el registro haya sido seleccionado
	//Para ello en el xhtml deben incluirlo con inputHidden
	protected boolean seleccionoRegistro= false;

	protected boolean mostrarBotonBuscar = true;
	protected boolean mostrarBotonSave = false;
	protected boolean mostrarBotonSalir = false;
	protected boolean salirPopup = true;
	
	protected boolean mostrarPaginacionDatatableSpinner = false;
	protected int nroPaginacionDatatable = 10; 
			
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
		this.formBusqueda.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		
		this.listaBusqueda = new ArrayList();
		if (this.mPantallaPrincipalBean != null) {
			this.mPantallaPrincipalBean.setCurrentMenu(this.parametrosPantalla.get("codigoMenu"));
		}
	}
	
	/**
	 *  Metodo que limpia la Grilla de Busqueda
	 * de Busqueda
	 * Para las busquedas tipo AJAX
	 */
	public void limpiarFind(ActionEvent actionEvent) {
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
		if (log.isWarnEnabled()) {
			log.warn("Entering 'find' method");
		}
		
		if(!this.validarConsulta()){
			this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
			return null;
		}
		try {			
		
			this.datatableBusqueda = null;
			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			if ((this.listaBusqueda == null) ||(this.listaBusqueda.size() == 0)){
				this.addWarn("Warning: ", this.getResourceMessage("errors.datos.fuentes.busqueda"));
			}
				
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		if (log.isWarnEnabled()) {
			log.warn("Finish 'find' method");
		}
		return null;
	}
	
	
	/**
	 * Realiza acciones personalizadas de validación previa, de existir, antes de realizar la verificacion de ejecucion de la consulta
	 * @return
	 */
	private boolean validarConsulta(){
		this.validacionPrevia = true;	
		String lsMensajeError = this.setValidarConsulta();
		if (StringUtils.isNotBlank(lsMensajeError)) {
			this.validacionPrevia = false;	
			this.setMensajeAlertaDefault(lsMensajeError);
		}
		return this.validacionPrevia;
	}
	
	/**
	 * Realiza acciones personalizadas de validación previa, de existir, antes de realizar la verificacion de ejecucion de la consulta
	 * Devuelve Mensaje de error personalizado de validacion extra antes de la verificación de la ejecución del reporte 
	 * @return
	 */
	public String setValidarConsulta(){
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
	
	
	/**
	 * Metodo Salir
	 * @return
	 */
	public void salir(ActionEvent actionEvent)  {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'Salir' method");
		}
		if (this.salirPopup) {
			String ventana = "PF('popupConsultaForm').hide()";
			this.getRequestContext().execute(ventana);
			return;
		}
		
		/* Redireccionando a la pagina respectiva */
		try{			
			this.redireccionarPagina(this.getSalirForward());	
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			return;
		}
		this.accion = this.ACCION_BUSCAR;
		return;
	}
	
	/**
	 * Hook method donde debera colocarse la pagina xhtml a la cual redireccionar 
	 * luego que se haya hecho click en el boton cancelar
	 * Solo colocar el nombre de la pagina sin extension xhtml
	 * @return
	 */
	protected String getSalirForward() {
		return null;
	}
	
	
	
	
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
	 * @return the mostrarBotonSave
	 */
	public boolean isMostrarBotonSave() {
		return mostrarBotonSave;
	}


	/**
	 * @param mostrarBotonSave the mostrarBotonSave to set
	 */
	public void setMostrarBotonSave(boolean mostrarBotonSave) {
		this.mostrarBotonSave = mostrarBotonSave;
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
	 * @return the mostrarPaginacionDatatableSpinner
	 */
	public boolean isMostrarPaginacionDatatableSpinner() {
		return mostrarPaginacionDatatableSpinner;
	}


	/**
	 * @param mostrarPaginacionDatatableSpinner the mostrarPaginacionDatatableSpinner to set
	 */
	public void setMostrarPaginacionDatatableSpinner(
			boolean mostrarPaginacionDatatableSpinner) {
		this.mostrarPaginacionDatatableSpinner = mostrarPaginacionDatatableSpinner;
	}


	/**
	 * @return the nroPaginacionDatatable
	 */
	public int getNroPaginacionDatatable() {
		return nroPaginacionDatatable;
	}


	/**
	 * @param nroPaginacionDatatable the nroPaginacionDatatable to set
	 */
	public void setNroPaginacionDatatable(int nroPaginacionDatatable) {
		this.nroPaginacionDatatable = nroPaginacionDatatable;
	}
	
	

}
