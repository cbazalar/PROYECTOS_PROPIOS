package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.TipoChequeo;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDTipoChequeoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDTipoChequeoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoPEDTipoChequeoAction extends BaseMantenimientoSearchAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4004034803369573660L;
	
	private List tipoChequeoList;
	private DataTableModel tableModel;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception 
	{
		MantenimientoPEDTipoChequeoForm searchForm = new MantenimientoPEDTipoChequeoForm();
		return searchForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarBotonNuevo = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarListaBusqueda = false;
		this.mostrarBotonBuscar = false;
		
		if (log.isDebugEnabled()) 
			log.debug("MantenimientoPEDTipoChequeoAction - Entrando al metodo view");
		
		MantenimientoPEDTipoChequeoService mantenimientoPEDTipoChequeoService = (MantenimientoPEDTipoChequeoService) getBean("spusicc.pedidos.mantenimientoPEDTipoChequeoService");
		
		this.tipoChequeoList = mantenimientoPEDTipoChequeoService.getTipoChequeoAll();
		
		this.tableModel = new DataTableModel(this.tipoChequeoList);		
	}

	/**
	 * @param event
	 */
	public void guardar(ActionEvent event)
	{
		if (log.isDebugEnabled()) 
			log.debug("MantenimientoPEDTipoChequeoAction - Entrando al metodo update");
		
		MantenimientoPEDTipoChequeoService mantenimientoPEDTipoChequeoService = (MantenimientoPEDTipoChequeoService) getBean("spusicc.pedidos.mantenimientoPEDTipoChequeoService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();		
		Map criteria;
//		List tipoChequeoList = mantenimientoPEDTipoChequeoService.getTipoChequeoAll();
		List parametrosList = new ArrayList();
		
		for(int i=0;i<this.tipoChequeoList.size();i++)
		{	
			TipoChequeo tipoChequeo = (TipoChequeo)this.tipoChequeoList.get(i);	
			criteria = new HashMap();
			
			String indicadorConsultorasReincidentes = StringUtils.isNotBlank(tipoChequeo.getIndicadorConsultorasReincidentes()) ? tipoChequeo.getIndicadorConsultorasReincidentes():null;
			String numeroDiasAtrasFaltantes = (tipoChequeo.getNumeroDiasAtrasFaltantes() != null) ? tipoChequeo.getNumeroDiasAtrasFaltantes().toString() : null;
			String minumoReclamosFaltante = (tipoChequeo.getMinumoReclamosFaltante() != null) ? tipoChequeo.getMinumoReclamosFaltante().toString() : null;
			String minimoIndicadorNuevas = (tipoChequeo.getMinimoIndicadorNuevas() != null) ? tipoChequeo.getMinimoIndicadorNuevas() : null;
			
			criteria.put("usuario", usuario.getLogin());
			criteria.put("codigoTipoChequeo", tipoChequeo.getCodigoTipoChequeo());
			criteria.put("indicadorConsultorasReincidentes", indicadorConsultorasReincidentes);
			criteria.put("numeroDiasAtrasFaltantes", StringUtils.isNotBlank(numeroDiasAtrasFaltantes)  ? Integer.valueOf(numeroDiasAtrasFaltantes) : null);
			criteria.put("minumoReclamosFaltante", StringUtils.isNotBlank(minumoReclamosFaltante) ? Integer.valueOf(minumoReclamosFaltante) : null);
			criteria.put("minimoIndicadorNuevas", StringUtils.isNotBlank(minimoIndicadorNuevas) ? minimoIndicadorNuevas : null);
			
			parametrosList.add(criteria);
		}
		
		mantenimientoPEDTipoChequeoService.updateTipoChequeo(parametrosList);
		
		this.addInfo("", this.getResourceMessage("mantenimientoPEDTipoChequeoForm.cabecera.update"));
		
		this.tipoChequeoList = mantenimientoPEDTipoChequeoService.getTipoChequeoAll();
		this.tableModel = new DataTableModel(this.tipoChequeoList);
	}
	
	/**
	 * @return
	 */
	public List getTipoChequeoList() {
		return tipoChequeoList;
	}

	/**
	 * @param tipoChequeoList
	 */
	public void setTipoChequeoList(List tipoChequeoList) {
		this.tipoChequeoList = tipoChequeoList;
	}

	/**
	 * @return
	 */
	public DataTableModel getTableModel() {
		return tableModel;
	}

	/**
	 * @param tableModel
	 */
	public void setTableModel(DataTableModel tableModel) {
		this.tableModel = tableModel;
	}
}
