package biz.belcorp.ssicc.web.framework.base.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

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


		
		
}
