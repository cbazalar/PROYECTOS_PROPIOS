package biz.belcorp.ssicc.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.sisicc.model.FeriadoZona;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.GrupoZona;
import biz.belcorp.ssicc.service.spusicc.ventas.GrupoZonaVENService;
import biz.belcorp.ssicc.web.form.BusquedaRegionPOPUPSearchForm;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.BusquedaZonaPOPUPSearchForm;

@ManagedBean  
@SessionScoped
public class BusquedaRegionPOPUPSearchAction extends BasePopupAbstractAction {
	
	private static final long serialVersionUID = -6177941060355438657L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		BusquedaRegionPOPUPSearchForm f = new BusquedaRegionPOPUPSearchForm();
		return f;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'BusquedaZonaPOPUPSearchAction - setViewAtributes' method");
		}
		
		BusquedaRegionPOPUPSearchForm searchForm = (BusquedaRegionPOPUPSearchForm)this.formBusqueda;
		limpiar(searchForm);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'BusquedaRegionPOPUPSearchAction - setFindAttributes' method");
		}
		
		BusquedaRegionPOPUPSearchForm searchForm = (BusquedaRegionPOPUPSearchForm) this.formBusqueda;
		FeriadoZona bferiadozona = new FeriadoZona();
		List lista = new ArrayList();
		
		GrupoZonaVENService service = (GrupoZonaVENService) getBean("spusicc.grupoZonaVENService");
		BeanUtils.copyProperties(bferiadozona, searchForm);
		
		Map criteria = BeanUtils.describe(searchForm);
		criteria.put("codigoRegion", searchForm.getCodigoRegion());
		criteria.put("descripcionRegion", searchForm.getDescripcionRegion());
		if(!StringUtils.isBlank(searchForm.getDescripcionRegion()) || !StringUtils.isBlank(searchForm.getCodigoRegion())){
			lista = service.getRegion((HashMap)criteria);
		}
				
		return lista;
	
		
	}
	
	
	/**
	 * Limpia los campos del formulario
	 * 
	 * @param searchForm
	 */
	private void limpiar(BusquedaRegionPOPUPSearchForm searchForm) {
		searchForm.setAnyoPeriodo("");
		searchForm.setCodigoPais("");
		searchForm.setCodigoPeriodo("");
	}

}
