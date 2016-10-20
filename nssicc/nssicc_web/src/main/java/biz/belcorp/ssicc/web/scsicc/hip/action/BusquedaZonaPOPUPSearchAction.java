package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.spusicc.ventas.model.GrupoZona;
import biz.belcorp.ssicc.service.spusicc.ventas.GrupoZonaVENService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.BusquedaZonaPOPUPSearchForm;

@ManagedBean  
@SessionScoped
public class BusquedaZonaPOPUPSearchAction extends BasePopupAbstractAction {

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		BusquedaZonaPOPUPSearchForm f = new BusquedaZonaPOPUPSearchForm();
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
		
		BusquedaZonaPOPUPSearchForm searchForm = (BusquedaZonaPOPUPSearchForm)this.formBusqueda;
		limpiar(searchForm);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'BusquedaZonaPOPUPSearchAction - setFindAttributes' method");
		}
		
		BusquedaZonaPOPUPSearchForm searchForm = (BusquedaZonaPOPUPSearchForm)this.formBusqueda;
		GrupoZona bgrupozona=new GrupoZona();
		List resultado=new ArrayList();
		
		GrupoZonaVENService service = (GrupoZonaVENService) getBean("spusicc.grupoZonaVENService");
		BeanUtils.copyProperties(bgrupozona,searchForm);
		
		if(!StringUtils.isBlank(searchForm.getDescripcionZona()) || !StringUtils.isBlank(searchForm.getCodigoZona())){//Se obtiene el secuencial de la zona
			resultado = service.getZona(bgrupozona);
		}
		return resultado;
		
//		Map criteria = BeanUtils.describe(searchForm);
//
//		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
//		criteria.put("codigoPais", pais.getCodigo());
//
//		// La busqueda solo la realizaremos en las interfaces activas
//		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
//
//		List resultado = service.getProductosByCriteria(criteria);
//
//		return resultado;
				
	}
	
	
	/**
	 * Limpia los campos del formulario
	 * 
	 * @param searchForm
	 */
	private void limpiar(BusquedaZonaPOPUPSearchForm searchForm) {
		searchForm.setAnyoPeriodo("");
		searchForm.setCodigoPais("");
		searchForm.setCodigoPeriodo("");
	}

}
