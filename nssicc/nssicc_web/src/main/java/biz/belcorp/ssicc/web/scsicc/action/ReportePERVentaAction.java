package biz.belcorp.ssicc.web.scsicc.action;



import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePERVentaForm;


@ManagedBean
@SessionScoped
public class ReportePERVentaAction extends BaseReporteAbstractAction{
		
	
	private static final long serialVersionUID = 4872696366902683145L;
	
	
	private String formatoReporte;

	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePERVentaForm reporteForm = new ReportePERVentaForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF=false;
		

		ReportePERVentaForm f = (ReportePERVentaForm) this.formReporte;		

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codPais=pais.getCodigo();
		f.setCodigoPais(codPais);		

		log.debug("Todo Ok: Redireccionando");
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {		
		if ("XLS".equals(formatoReporte))
		   return "reportePERVentaXLS";
		else
			return " ";			
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return " ";					
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReportePERVentaForm f = (ReportePERVentaForm) this.formReporte;
		this.formatoReporte = f.getFormatoExportacion();
		
		String ndesde=DateUtil.convertDateToString(f.getFechaDesdeD());
		String nhasta=DateUtil.convertDateToString(f.getFechaHastaD());
		
		params.put("fechaDesde",ndesde);
		params.put("fechaHasta",nhasta);
		params.put("titulo", getReportResourceMessage("reportePERVentaForm.title"));	
		return params;
		
	}
	
	public String setValidarReporte() {
		
		ReportePERVentaForm form = (ReportePERVentaForm) this.formReporte;
	    if (form.getFechaHastaD().compareTo(form.getFechaDesdeD()) < 0 ){
	    	String mensaje =  this.getResourceMessage("errors.compare.dates");
			return mensaje;
	    }	    					
	    return null;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}
	
	
}
