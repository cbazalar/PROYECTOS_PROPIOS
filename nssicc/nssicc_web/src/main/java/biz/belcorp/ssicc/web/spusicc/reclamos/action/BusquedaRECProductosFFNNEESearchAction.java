package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECProductosFFNNEEService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.BusquedaRECProductoFFNNEESearchForm;

@ManagedBean
@SessionScoped
public class BusquedaRECProductosFFNNEESearchAction extends BasePopupAbstractAction {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4896536188927999348L;
	private String  codigoPeriodoInicio;
	private String  codigoPeriodoFin;
	private String  codigoRegion;
	
	private List recProductosFFNNEELits;

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		BusquedaRECProductoFFNNEESearchForm busquedaRECProductoFFNNEESearchForm = new BusquedaRECProductoFFNNEESearchForm();
		return busquedaRECProductoFFNNEESearchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		BusquedaRECProductoFFNNEESearchForm f=(BusquedaRECProductoFFNNEESearchForm) formBusqueda;
		f.setCodigoPais(pais.getCodigo());
		 
		Map parameterMap = new HashMap();
		parameterMap.put("codigoPais", pais.getCodigo());
		parameterMap.put("codigoPeriodoInicio", this.codigoPeriodoInicio);
		parameterMap.put("codigoPeriodoFin", this.codigoPeriodoFin);
		parameterMap.put("codigoRegion", this.codigoRegion);
		
		MantenimientoRECProductosFFNNEEService service = (MantenimientoRECProductosFFNNEEService)  getBean("spusicc.mantenimientoRECProductosFFNNEEService");
		
		recProductosFFNNEELits =service.getProductosFFNNEEList(parameterMap);
		return recProductosFFNNEELits;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
	}

	/**
	 * @return the codigoPeriodoInicio
	 */
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}

	/**
	 * @param codigoPeriodoInicio the codigoPeriodoInicio to set
	 */
	public void setCodigoPeriodoInicio(String codigoPeriodoInicio) {
		this.codigoPeriodoInicio = codigoPeriodoInicio;
	}

	/**
	 * @return the codigoPeriodoFin
	 */
	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}

	/**
	 * @param codigoPeriodoFin the codigoPeriodoFin to set
	 */
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}

	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the recProductosFFNNEELits
	 */
	public List getRecProductosFFNNEELits() {
		return recProductosFFNNEELits;
	}

	/**
	 * @param recProductosFFNNEELits the recProductosFFNNEELits to set
	 */
	public void setRecProductosFFNNEELits(List recProductosFFNNEELits) {
		this.recProductosFFNNEELits = recProductosFFNNEELits;
	}


}
