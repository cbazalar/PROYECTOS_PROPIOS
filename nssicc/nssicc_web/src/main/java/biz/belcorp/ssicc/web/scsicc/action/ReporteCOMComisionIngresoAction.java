package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOMComisionIngresoForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;


@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCOMComisionIngresoAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 5452137025798023586L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOMComisionIngresoForm reporteForm = new ReporteCOMComisionIngresoForm();
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

		ReporteCOMComisionIngresoForm reporteForm = (ReporteCOMComisionIngresoForm) this.formReporte;
		String formatoReporte = reporteForm.getFormatoExportacion();

		if (formatoReporte.equals("XLS") || formatoReporte.equals("CSV")
				|| formatoReporte.equals("XLSX")) {
			return "reporteCOMComisionIngresoXLS";
		}

		return "reporteMaestroHorizontalComision";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {

		ReporteCOMComisionIngresoForm reporteForm = (ReporteCOMComisionIngresoForm) this.formReporte;
		String formatoReporte = reporteForm.getFormatoExportacion();

		if (formatoReporte.equals("XLS") || formatoReporte.equals("CSV")
				|| formatoReporte.equals("XLSX")) {
			return "";
		}

		return "reporteCOMComisionIngreso";
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
			this.log.debug("Entering 'ReporteCOMComisionIngresoAction.setViewAtributes' method");
		}

		this.mostrarReporteXLS = true;

		// Seteo de valores por default de nuevos registros
		ReporteCOMComisionIngresoForm reporteForm = (ReporteCOMComisionIngresoForm) this.formReporte;

		reporteForm.setCodigoPais(this.mPantallaPrincipalBean
				.getCurrentCountry().getDescripcion());

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
			log.debug("Entering 'ReporteCOMComisionIngresoAction.prepareParameterMap' method");
		}
		ReporteCOMComisionIngresoForm reporteForm = (ReporteCOMComisionIngresoForm) this.formReporte;
		reporteForm
				.setTitulo(this
						.getReportResourceMessage("reporteCOMComisionIngresoForm.titulo"));
		reporteForm.setNroReporte("");

		params.put("NroReporte", " ");
		params.put(
				"titulo",
				this.getReportResourceMessage("reporteCOMComisionIngresoForm.titulo"));

		return params;
	}
}
