package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteVENResumenRegistroVentasForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteVENResumenRegistroVentasAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteVENResumenRegistroVentasXLS";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteVENResumenRegistroVentasForm form = (ReporteVENResumenRegistroVentasForm) this.formReporte;
		if (form.getFechaHastaD().compareTo(form.getFechaDesdeD()) < 0) {
			String mensaje = this.getResourceMessage("errors.compare.dates");
			return mensaje;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteVENResumenRegistroVentasForm reporteVENResumenRegistroVentasForm = (ReporteVENResumenRegistroVentasForm) this.formReporte;

		String fecha1, fecha2;
		fecha1 = DateUtil.getDate(reporteVENResumenRegistroVentasForm
				.getFechaDesdeD());
		fecha2 = DateUtil.getDate(reporteVENResumenRegistroVentasForm
				.getFechaHastaD());

		params.put("fechaDesde",fecha1);
		params.put("fechaHasta",fecha2);

		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteVENResumenRegistroVentasForm form = new ReporteVENResumenRegistroVentasForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		ReporteVENResumenRegistroVentasForm form = (ReporteVENResumenRegistroVentasForm) this.formReporte;
		form.setFechaDesdeD(new Date(System.currentTimeMillis()));
		form.setFechaHastaD(new Date(System.currentTimeMillis()));
	}
}
