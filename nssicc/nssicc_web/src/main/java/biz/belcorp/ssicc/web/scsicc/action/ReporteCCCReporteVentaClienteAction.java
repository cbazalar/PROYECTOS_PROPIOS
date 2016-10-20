package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCReporteVentaClienteForm;


@ManagedBean
@SessionScoped
public class ReporteCCCReporteVentaClienteAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = 5790182301832072921L;
	private String formatoReporte;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCReporteVentaClienteForm reporteForm = new ReporteCCCReporteVentaClienteForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteCCCReporteVentaClienteForm form = (ReporteCCCReporteVentaClienteForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteCCCVentaClienteXLS";
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCCCReporteVentaClienteAction.prepareParameterMap' method");
		}
		ReporteCCCReporteVentaClienteForm reporteVentaCli = (ReporteCCCReporteVentaClienteForm) this.formReporte;
		formatoReporte = reporteVentaCli.getFormatoExportacion();

		params.put("fechaInicio", reporteVentaCli.getFechaInicio());
		params.put("fechaFin", reporteVentaCli.getFechaFin());
		params.put("titulo",this.getReportResourceMessage("ReporteCCCReporteVentaClienteForm.titulo"));
		return params;

	}
	
	public String setValidarReporte() {
		ReporteCCCReporteVentaClienteForm form = (ReporteCCCReporteVentaClienteForm) this.formReporte;
	    if (form.getFechaFinD().compareTo(form.getFechaInicioD()) < 0 ){
	    	String mensaje =  this.getResourceMessage("errors.compare.dates");
			return mensaje;
	    }	    					
	    return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}
}
