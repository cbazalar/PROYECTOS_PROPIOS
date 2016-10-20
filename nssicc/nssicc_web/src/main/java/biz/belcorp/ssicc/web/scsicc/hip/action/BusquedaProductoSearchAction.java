package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.BusquedaProductoSearchForm;

@ManagedBean  
@SessionScoped
public class BusquedaProductoSearchAction extends BasePopupAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		BusquedaProductoSearchForm f = new BusquedaProductoSearchForm();
		return f;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'BusquedaProductoSearchAction - setViewAtributes' method");
		}
		
		BusquedaProductoSearchForm searchForm = (BusquedaProductoSearchForm)this.formBusqueda;
		limpiar(searchForm);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'BusquedaHIPClientePOPUPSearchAction - setFindAttributes' method");
		}
		
		BusquedaProductoSearchForm searchForm = (BusquedaProductoSearchForm)this.formBusqueda;
		
		Map criteria = BeanUtils.describe(searchForm);

		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());

		// La busqueda solo la realizaremos en las interfaces activas
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		List resultado = service.getProductosByCriteria(criteria);

		return resultado;
				
	}
	
	
	/**
	 * Limpia los campos del formulario
	 * 
	 * @param searchForm
	 */
	public void limpiar(BusquedaProductoSearchForm searchForm) {
		searchForm.setAnyoPeriodo("");
		searchForm.setCodigoPais("");
		searchForm.setCodigoPeriodo("");
		searchForm.setCodigoSap("");
		searchForm.setCodigoSapPopup("");
		searchForm.setDescripcionCorta("");
		searchForm.setDescripcionCortaPopup("");
	}
	
	/**
	 * Limpiar para el salir del popup
	 * 
	 * 
	 */
	public void limpiarSalir() {
		BusquedaProductoSearchForm searchForm = (BusquedaProductoSearchForm)this.formBusqueda;
		searchForm.setCodigoSap("");
		searchForm.setDescripcionCorta("");
	}
	
	

}
