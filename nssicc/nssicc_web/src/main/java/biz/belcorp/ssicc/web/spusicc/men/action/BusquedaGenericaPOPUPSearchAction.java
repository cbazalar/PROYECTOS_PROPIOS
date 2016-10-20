package biz.belcorp.ssicc.web.spusicc.men.action;

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
import biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENPlantillaService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.form.BusquedaClientesSearchForm;
import biz.belcorp.ssicc.web.spusicc.men.form.BusquedaGenericaSearchForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENConfiguracionProcesosForm;


/**
 * The Class BusquedaGenericaPOPUPSearchAction.
 *
 * @autor: Yahir Rivas L.
 * @version: 1.0
 * 30/12/2014
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@ManagedBean  
@SessionScoped
public class BusquedaGenericaPOPUPSearchAction extends BasePopupAbstractAction {

	private static final long serialVersionUID = 1L;
	
	private DataTableModel dataTableModel = new DataTableModel();
	private Object seleccionado;	
	private String indexPopup;
	private String valorPosible;
	

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		BusquedaGenericaSearchForm f = new BusquedaGenericaSearchForm();
		return f;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes");
		}		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		BusquedaGenericaSearchForm searchForm = (BusquedaGenericaSearchForm) this.formBusqueda;
		searchForm.setCodigoPais(pais.getCodigo());
		
		MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");

		Map criteria = BeanUtils.describe(searchForm);		
		criteria.put("valorPosible", this.valorPosible);
		
		List resultado = service.getSelectPlantilla(criteria);

		return resultado;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		BusquedaGenericaSearchForm searchForm = (BusquedaGenericaSearchForm) this.formBusqueda;
		searchForm.setCodigo("");
		searchForm.setDescripcion("");
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
	 * @return the indexPopup
	 */
	public String getIndexPopup() {
		return indexPopup;
	}

	/**
	 * @param indexPopup the indexPopup to set
	 */
	public void setIndexPopup(String indexPopup) {
		this.indexPopup = indexPopup;
	}

	/**
	 * @return the valorPosible
	 */
	public String getValorPosible() {
		return valorPosible;
	}

	/**
	 * @param valorPosible the valorPosible to set
	 */
	public void setValorPosible(String valorPosible) {
		this.valorPosible = valorPosible;
	}	

	
	
}