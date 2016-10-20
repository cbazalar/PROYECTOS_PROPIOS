package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCAntiguedadSaldosForm;
import biz.belcorp.ssicc.web.scsicc.form.ReporteVENConsolidadoRUVForm;

@ManagedBean
@SessionScoped
public class ReporteVENConsolidadoRUVAction extends
		BaseReporteAbstractAction {


	/**
	 * 
	 */
	private static final long serialVersionUID = -94762680064937214L;
	private String formatoReporte;


	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteVENConsolidadoRUVForm reporteForm = new ReporteVENConsolidadoRUVForm();
		return reporteForm;
	}



	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteVENConsolidadoRUVAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		
		ReporteVENConsolidadoRUVForm f = (ReporteVENConsolidadoRUVForm) this.formReporte;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(new Date(System.currentTimeMillis()));		
		f.setFechaDesde(fecha);
		f.setFechaHasta(fecha);
		f.setFechaDesdeD(new Date(System.currentTimeMillis()));
		f.setFechaHastaD(new Date(System.currentTimeMillis()));


	}
	
	public String setValidarReporte() {
		ReporteVENConsolidadoRUVForm form = (ReporteVENConsolidadoRUVForm)this.formReporte;
	    if (form.getFechaHastaD().compareTo(form.getFechaDesdeD()) < 0 ){
	    	String mensaje =  this.getResourceMessage("errors.compare.dates");
			return mensaje;
	    }				
	    return null;
	}


	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteVENConsolidadoRUVForm form = (ReporteVENConsolidadoRUVForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteVENConsolidadoRUVXLS";
		else
			return "reporteMaestroHorizontal";
	
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteVENConsolidadoRUVForm form = (ReporteVENConsolidadoRUVForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(formReporte.getFormatoExportacion()))
			return "reporteVENConsolidadoRUVPDF";
		else
			return "";

	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
			
		ReporteVENConsolidadoRUVForm f = (ReporteVENConsolidadoRUVForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();		
		String fecha1,fecha2;
		fecha1 = DateUtil.getDate(f.getFechaDesdeD());
		fecha2=DateUtil.getDate(f.getFechaHastaD());
		f.setFechaDesde(fecha1);
		f.setFechaHasta(fecha2);
		params.put("NroReporte", " ");		
		params.put("codigoPais", f.getCodigoPais());
		
		params.put("fechaDesde",f.getFechaDesde());
		params.put("fechaHasta",f.getFechaHasta());
		params.put("titulo",getResourceMessage("reporteVENConsolidadoRUVForm.title"));
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

