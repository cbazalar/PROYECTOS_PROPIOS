package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOExcepcionValidaDeudaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOExcepcionValidaDeudaForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOExcepcionValidaDeudaSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoSTOExcepcionValidaDeudaSearchAction extends BaseMantenimientoSearchAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 960524848685706712L;
		
	//flags
	private boolean flagValidador;
	
	private DataTableModel listaModel = new DataTableModel();
	private List lista = new ArrayList();
	private List seleccionado = new ArrayList();
	
	@Override
	protected String getSalirForward() 
	{
		return "mantenimientoSTOExcepcionValidaDeudaList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception 
	{
		return "mantenimientoSTOExcepcionValidaDeudaForm";
	}

	@Override 
	protected BaseSearchForm devuelveFormBusqueda() throws Exception
	{
		MantenimientoSTOExcepcionValidaDeudaSearchForm searchForm = new MantenimientoSTOExcepcionValidaDeudaSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		log.debug("MantenimientoSTOExcepcionValidaDeudaSearchAction - setFindAttributes");

		MantenimientoSTOExcepcionValidaDeudaSearchForm f = (MantenimientoSTOExcepcionValidaDeudaSearchForm) this.formBusqueda;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoSTOExcepcionValidaDeudaService service = (MantenimientoSTOExcepcionValidaDeudaService) getBean("spusicc.mantenimientoSTOExcepcionValidaDeudaService");

		List lista = new ArrayList();
		Map criteria = new HashMap();

		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoCliente", f.getCodigoCliente());

		try {
			if (StringUtils.isNotBlank(f.getCodigoCliente()))
				criteria.put("oidCliente", reporteService.getOidString(
						"getOidClienteByCodigoCliente", criteria));
			else
				criteria.put("oidCliente", null);

			if (StringUtils.isNotBlank(f.getCodigoPeriodo()))
				criteria.put("oidPeriodo", reporteService.getOidString(
						"getOidPeriodoByCodigoPeriodo", criteria));
			else
				criteria.put("oidPeriodo", null);

			lista = service.getExcepcionValidaDeudaList(criteria);
			this.lista= lista;
			this.listaModel = new DataTableModel(this.lista);
			
			return lista;

		} catch (Exception e) 
		{
			String error = e.getMessage();
			if (StringUtils.isBlank(error))	error = e.getLocalizedMessage();
			throw new Exception(this.getResourceMessage("errors.detail", new Object[] { error }));
		}
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception 
	{

		log.debug("MantenimientoSTOExcepcionValidaDeudaSearchAction - setDeleteAttributes");
		
		MantenimientoSTOExcepcionValidaDeudaSearchForm f  = (MantenimientoSTOExcepcionValidaDeudaSearchForm)this.formBusqueda;
		String[] oidClienExcepValiDeud = new String[this.seleccionado.size()];
		
		for (int i = 0; i < this.seleccionado.size(); i++) {
			Map registroSeleccionado = (Map)this.seleccionado.get(i);
			oidClienExcepValiDeud[i] = registroSeleccionado.get("oidClienExcepValiDeud").toString();
		}
		
		
		MantenimientoSTOExcepcionValidaDeudaService service = (MantenimientoSTOExcepcionValidaDeudaService)getBean("spusicc.mantenimientoSTOExcepcionValidaDeudaService");
		
		Map parametros = new HashMap();
		try{
			parametros.put("idSeleccionados", oidClienExcepValiDeud);
			
			service.deleteExcepcionValidaDeuda(parametros);
				
		}catch(Exception e)
		{
			String error = e.getMessage();
			if (StringUtils.isBlank(error))	error = e.getLocalizedMessage();
			throw new Exception(this.getResourceMessage("errors.detail", new Object[] { error }));			
		}
		return true;		
	}

	@Override
	protected boolean setSaveAttributes() throws Exception 
	{
		MantenimientoSTOExcepcionValidaDeudaForm f = (MantenimientoSTOExcepcionValidaDeudaForm)this.formMantenimiento;
		ReporteService reporteService = (ReporteService)getBean("scsicc.reporteService");
		
		MantenimientoSTOExcepcionValidaDeudaService service = (MantenimientoSTOExcepcionValidaDeudaService)getBean("spusicc.mantenimientoSTOExcepcionValidaDeudaService");
		
		Map criteria = new HashMap();
		if(this.accion.equals(this.ACCION_NUEVO))
		{
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			criteria.put("codigoCliente", f.getCodigoCliente());
			
			if(StringUtils.isNotBlank(f.getCodigoPeriodo()))
				criteria.put("oidPeriodo", reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria));
			else 
				criteria.put("oidPeriodo", null);
			
			if(StringUtils.isNotBlank(f.getCodigoCliente()))
				criteria.put("oidCliente", reporteService.getOidString("getOidClienteByCodigoCliente", criteria));
			else
				criteria.put("oidCliente", null);
			

				service.insertExcepValidDeuda(criteria);
				return true;
		}else
			return false;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {

		String mensaje = null;
		if(StringUtils.equals(accion, "ELIMINAR")){
			if(this.seleccionado == null || this.seleccionado.size() <= 0){
				mensaje = this.getResourceMessage("errors.select.item");			
			}else
				this.beanRegistroSeleccionado = this.seleccionado.get(0);
			
		}
		
		return mensaje;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		log.debug("MantenimientoSTOExcepcionValidaDeudaAction - setAddAttributes");
		
		MantenimientoSTOExcepcionValidaDeudaForm f = new MantenimientoSTOExcepcionValidaDeudaForm();
		f.setCodigoCliente(null);
		f.setCodigoPeriodo(null);

		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarMantenimientoEnPopup = true;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarListaBusqueda = false;
		
		this.salirGrabarPantallaPadre = true;
		
		log.debug("MantenimientoSTOExcepcionValidaDeudaSearchAction - setViewAttributes");

		MantenimientoSTOExcepcionValidaDeudaSearchForm f = (MantenimientoSTOExcepcionValidaDeudaSearchForm) this.formBusqueda;

		cleanForm(f);
	}
	
	/**
	 * @param f
	 */
	private void cleanForm(MantenimientoSTOExcepcionValidaDeudaSearchForm f) {
		f.setCodigoCliente("");
		f.setCodigoPeriodo("");
	}
	
	/*
	 * @param  
	 */
	public void consultar()
	{
		MantenimientoSTOExcepcionValidaDeudaForm f = (MantenimientoSTOExcepcionValidaDeudaForm)this.formMantenimiento;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		String dato = null;
		
		if(f == null)
		{
			MantenimientoSTOExcepcionValidaDeudaSearchForm f1 = (MantenimientoSTOExcepcionValidaDeudaSearchForm) this.formBusqueda;	
			dato =  ajax.getNombreCliente(f1.getCodigoCliente());
		}else
			dato =  ajax.getNombreCliente(f.getCodigoCliente());
		
		if(dato == null)
		{
			this.addError("", this.getResourceMessage("mantenimientoSTOExcepcionValidaDeudaForm.validaCliente"));
			this.flagValidador = false;
		}else
			this.flagValidador = true;
	}
	
	@Override
	public String setValidarFind() {
		MantenimientoSTOExcepcionValidaDeudaSearchForm f = (MantenimientoSTOExcepcionValidaDeudaSearchForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		String dato = null, mensaje = null;
		
		if(StringUtils.isNotBlank(f.getCodigoCliente()))
		{
			dato =  ajax.getNombreCliente(f.getCodigoCliente());
			
			if(dato == null)
			{
				mensaje = this.getResourceMessage("mantenimientoSTOExcepcionValidaDeudaForm.validaCliente");
				return mensaje;
			}
		}
		
		return mensaje;
	}
	
	@Override
	public String setValidarMantenimiento() 
	{
		String mensaje = null;
		MantenimientoSTOExcepcionValidaDeudaForm f = (MantenimientoSTOExcepcionValidaDeudaForm)this.formMantenimiento;
		
		if(StringUtils.isBlank(f.getCodigoCliente()))
		{
			mensaje = "campo cliente es requerido";
			return mensaje;
		}else
		{
			if(!this.flagValidador){
				mensaje = "Antes de Guardar por favor corrija el Codigo del Cliente";
				return mensaje;
			}			
		}	
		
		if(StringUtils.isBlank(f.getCodigoPeriodo()))
		{
			mensaje = "Periodo es un campo requerido.";
			return mensaje;
		}
		
		return mensaje;
	}

	public boolean getFlagValidador() {
		return flagValidador;
	}

	public void setFlagValidador(boolean flagValidador) {
		this.flagValidador = flagValidador;
	}

	/**
	 * @return the listaModel
	 */
	public DataTableModel getListaModel() {
		return listaModel;
	}

	/**
	 * @param listaModel the listaModel to set
	 */
	public void setListaModel(DataTableModel listaModel) {
		this.listaModel = listaModel;
	}

	/**
	 * @return the lista
	 */
	public List getLista() {
		return lista;
	}

	/**
	 * @param lista the lista to set
	 */
	public void setLista(List lista) {
		this.lista = lista;
	}

	/**
	 * @return the seleccionado
	 */
	public List getSeleccionado() {
		return seleccionado;
	}

	/**
	 * @param seleccionado the seleccionado to set
	 */
	public void setSeleccionado(List seleccionado) {
		this.seleccionado = seleccionado;
	}
}
