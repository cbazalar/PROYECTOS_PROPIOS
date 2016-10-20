package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCUPNuevaUnidadAtendidaForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteCUPNuevaUnidadAtendidaAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9079401676930567626L;
	private String formatoReporte;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCUPNuevaUnidadAtendidaForm reporteForm = new ReporteCUPNuevaUnidadAtendidaForm();

		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteCUPNuevaUnidadAtendidaAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReporteOCSV = true;
		this.mostrarReportePDF = false;

		log.debug("Todo OK: Redireccionando");

	}

	protected String devuelveBeanReporteService() {
		return "reportes.reporteCUPNuevaUnidadAtendidaService";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteCUPNuevaUnidadAtendidaForm form = (ReporteCUPNuevaUnidadAtendidaForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteCUPNuevaUnidadAtendidaXLS";
		else
			return null;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReporteCUPNuevaUnidadAtendidaForm reporteCUPForm = (ReporteCUPNuevaUnidadAtendidaForm) this.formReporte;

		formatoReporte = reporteCUPForm.getFormatoExportacion();

		params.put("formatoreporte", reporteCUPForm.getFormatoExportacion());
		params.put("codigoPeriodo", reporteCUPForm.getCodigoPeriodo());
		params.put("titulo",
				getResourceMessage("reporteCUPNuevaUnidadAtendidaForm.titulo"));

		return params;
	}

	


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

}
