package biz.belcorp.ssicc.web.spisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spisicc.form.ReporteLECCargaDatosFormatosForm;

@ManagedBean
@SessionScoped
public class ReporteLECCargaDatosFormatosAction extends  BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;
	private String formatoReporte;
	private String tipoCarga;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLECCargaDatosFormatosForm form = new ReporteLECCargaDatosFormatosForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {		
		if(this.formatoReporte.equals("XLS") && this.tipoCarga.compareTo("01")==0){
			return "reporteLECCargaDatosFormato1XLS";
		}
		if(this.formatoReporte.equals("XLS") && this.tipoCarga.compareTo("02")==0){
			return "reporteLECCargaDatosFormato2XLS";
		}
		if(this.formatoReporte.equals("XLS") && this.tipoCarga.compareTo("03")==0){
			return "reporteLECCargaDatosFormato3XLS";
		}	
		if(this.formatoReporte.equals("XLS") && this.tipoCarga.compareTo("07")==0){
			return "reporteLECCargaDatosFormato7XLS";
		}	
		if(this.formatoReporte.equals("XLS") && this.tipoCarga.compareTo("08")==0){
			return "reporteLECCargaDatosFormato8XLS";
		}	
		if(this.formatoReporte.equals("XLS") && this.tipoCarga.compareTo("09")==0){
			return "reporteLECCargaDatosFormato7XLS";
		}
		if(this.formatoReporte.equals("XLS") && this.tipoCarga.compareTo("10")==0){
			return "reporteLECCargaDatosFormato10XLS";
		}
		
		if(this.formatoReporte.equals("XLS") && (
				StringUtils.equals(this.tipoCarga, "11") ||
				StringUtils.equals(this.tipoCarga, "12") ||
				StringUtils.equals(this.tipoCarga, "13") ||
				StringUtils.equals(this.tipoCarga, "14"))
				){			
			return "reporteLECCargaDatosFormato11XLS";
		}
		if(this.formatoReporte.equals("XLS") && this.tipoCarga.compareTo("15")==0){
			return "reporteLECCargaDatosFormato15XLS";
		}
		if(this.formatoReporte.equals("XLS") && this.tipoCarga.compareTo("16")==0){
			return "reporteLECCargaDatosFormato16XLS";
		}
		if(this.formatoReporte.equals("XLS") && this.tipoCarga.compareTo("17")==0){
			return "reporteLECCargaDatosFormato17XLS";
		}
		
		return "reporteLECCargaDatosFormato1XLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if(this.formatoReporte.equals("XLS") && this.tipoCarga.compareTo("01")==0){
			return "reporteLECCargaDatosFormato1XLS";
		}
		if(this.formatoReporte.equals("XLS") && this.tipoCarga.compareTo("02")==0){
			return "reporteLECCargaDatosFormato2XLS";
		}
		if(this.formatoReporte.equals("XLS") && this.tipoCarga.compareTo("03")==0){
			return "reporteLECCargaDatosFormato3XLS";
		}
		if(this.formatoReporte.equals("XLS") && this.tipoCarga.compareTo("07")==0){
			return "reporteLECCargaDatosFormato7XLS";
		}	
		if(this.formatoReporte.equals("XLS") && this.tipoCarga.compareTo("08")==0){
			return "reporteLECCargaDatosFormato8XLS";
		}
		if(this.formatoReporte.equals("XLS") && this.tipoCarga.compareTo("09")==0){
			return "reporteLECCargaDatosFormato8XLS";
		}
		if(this.formatoReporte.equals("XLS") && this.tipoCarga.compareTo("10")==0){
			return "reporteLECCargaDatosFormato10XLS";
		}
		return "reporteLECCargaDatosFormato1XLS";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		String formato= "XLS";
		this.formatoReporte = formato;
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;		
	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the tipoCarga
	 */
	public String getTipoCarga() {
		return tipoCarga;
	}

	/**
	 * @param tipoCarga the tipoCarga to set
	 */
	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}	
}