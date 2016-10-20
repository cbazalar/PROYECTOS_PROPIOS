package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.form.BusquedaClientesSearchForm;


/**
 * The Class BusquedaClientesPOPUPSearchAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 30/12/2014
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@ManagedBean  
@SessionScoped
public class BusquedaClientesPOPUPSearchAction extends BasePopupAbstractAction {

	private static final long serialVersionUID = 1L;
	
	private DataTableModel dataTableModel = new DataTableModel();
	private Object seleccionado;	
	private List zonaList;
	

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		BusquedaClientesSearchForm f = new BusquedaClientesSearchForm();
		return f;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes");
		}		
		
        BusquedaClientesSearchForm searchForm = (BusquedaClientesSearchForm) this.formBusqueda;
        MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		log.debug(searchForm.toString());		

		Map criteria = BeanUtils.describe(searchForm);		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo() );		
		criteria.put("oidPais", clienteService.getOidPais(criteria));		
		criteria.put("codigoCliente", searchForm.getCodigoCliente());
		criteria.put("nombre1", searchForm.getNombre1());
		criteria.put("nombre2", searchForm.getNombre2());
		criteria.put("apellido1", searchForm.getApellido1());
		criteria.put("apellido2", searchForm.getApellido2());
		criteria.put("criterioBusqueda1", searchForm.getCriterioBusqueda1());
		criteria.put("criterioBusqueda2", searchForm.getCriterioBusqueda2());
		criteria.put("documentoIdentidad", searchForm.getNumeroDocIdentidad());
		criteria.put("codigoZona", searchForm.getCodigoZona());		

		// La busqueda solo la realizaremos en las interfaces activas
		List resultado = clienteService.getClientesByCriteria(criteria);
		
		return resultado;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
        criteria.put("codigoPais", pais.getCodigo());
        criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
        criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
        criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
		this.zonaList = clienteService.getZonasByPaisMarcaCanal(criteria);
	}

	
	
	/**
	 * @return the dataTableModel
	 */
	public DataTableModel getDataTableModel() {
		return dataTableModel;
	}

	/**
	 * @param dataTableModel the dataTableModel to set
	 */
	public void setDataTableModel(DataTableModel dataTableModel) {
		this.dataTableModel = dataTableModel;
	}

	/**
	 * @return the seleccionado
	 */
	public Object getSeleccionado() {
		return seleccionado;
	}

	/**
	 * @param seleccionado the seleccionado to set
	 */
	public void setSeleccionado(Object seleccionado) {
		this.seleccionado = seleccionado;
	}

	/**
	 * @return the zonaList
	 */
	public List getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList the zonaList to set
	 */
	public void setZonaList(List zonaList) {
		this.zonaList = zonaList;
	}	

}