package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteSTORechazosForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSTOContratosEjecutivasForm;

@ManagedBean
@SessionScoped
public class ReporteSTOContratosEjecutivasAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = -8654284204742599392L;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSTOContratosEjecutivasForm reporteForm = new ReporteSTOContratosEjecutivasForm();

		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.info("ReporteSTOContratosEjecutivasForm - setViewAttributes");
		}
		this.mostrarReporteXLS = true;
		
		this.mostrarReportePDF = false;

	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(this.formatoExportacion))
			return "reporteSTOContratosEjecutivasXLS";
		else
			return null;
	}
	
	@Override
	public String setValidarReporte() {
		String error = "";
		ReporteSTOContratosEjecutivasForm form1 = (ReporteSTOContratosEjecutivasForm) this.formReporte;
		if(form1.getFechaInicioD() != null && form1.getFechaFinD() != null){
			Date fechaDesdeD = form1.getFechaInicioD();
			Date fechaHastaD = form1.getFechaFinD();
			
			if (fechaDesdeD.after(fechaHastaD)) {
				error = this.getResourceMessage("errors.compare.dates");
				return error;
			}
		}
	
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

		ReporteSTOContratosEjecutivasForm reporteForm = (ReporteSTOContratosEjecutivasForm) this.formReporte;
		params.put("condFechaInicio", "");
		params.put("condFechaFin", "");

		String fechaDesde="";
		String fechaHasta="";
		fechaDesde=DateUtil.convertDateToString(reporteForm.getFechaInicioD());
    	fechaHasta=DateUtil.convertDateToString(reporteForm.getFechaFinD());
    	if(reporteForm.getFechaInicioD() != null){
    		String inicio = "AND FEC_PROC >= to_date('"+fechaDesde+"','dd/mm/yyyy')";
    		params.put("condFechaInicio", inicio);
    	}
    	if(reporteForm.getFechaInicioD() != null && reporteForm.getFechaFinD() != null){    		
    		String fin = "AND FEC_PROC <= to_date('"+fechaHasta+"','dd/mm/yyyy')";
    		params.put("condFechaFin", fin);
    	}
    	
		
		params.put("codigoPais", reporteForm.getCodigoPais());
		
		log.info("ReporteSTOContratosEjecutivasForm prepareParameterMap");
		return params;
	}
}
