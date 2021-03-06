package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCVentasPorDiaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes" })
public class ReporteCCCVentasPorDiaAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -315750563493857602L;
	private String formatoReporte;

	/**
	 * @return
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCVentasPorDiaForm reporteForm = new ReporteCCCVentasPorDiaForm();
		return reporteForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {
		return "reporteCCCVentasPorDiaXLS";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteCCCVentasPorDiaForm form = (ReporteCCCVentasPorDiaForm) this.formReporte;
		Date fecha1D, fecha2D;
		fecha2D = form.getFechaDocumentoDesdeD();
		fecha1D = form.getFechaDocumentoHastaD();
		if (fecha2D.compareTo(fecha1D) > 0) {
			String mensaje = this.getResourceMessage("errors.compare.dates");
			return mensaje;
		}

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
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteCCCVentasPorDiaForm.setViewAtributes' method");
		}
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteCCCVentasPorDiaForm form = (ReporteCCCVentasPorDiaForm) this.formReporte;
		// reset
		form.setCodigoPais(pais.getCodigo());
		form.setFechaDocumentoDesdeD(new Date(System.currentTimeMillis()));
		form.setFechaDocumentoHastaD(new Date(System.currentTimeMillis()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteAPEOrdenImpresionForm.prepareParameterMap' method");
		}
		ReporteCCCVentasPorDiaForm form = (ReporteCCCVentasPorDiaForm) this.formReporte;
		form.setFechaDocumentoDesde(DateUtil.getDate(form
				.getFechaDocumentoDesdeD()));
		form.setFechaDocumentoHasta((DateUtil.getDate(form
				.getFechaDocumentoHastaD())));

		params.put("fechaDocumentoDesde", form.getFechaDocumentoDesde());
		params.put("fechaDocumentoHasta", form.getFechaDocumentoHasta());
		log.debug(" Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");

		return params;

	}

}