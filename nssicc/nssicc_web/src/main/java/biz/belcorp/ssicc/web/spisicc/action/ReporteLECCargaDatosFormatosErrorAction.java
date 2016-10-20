/**
 * 
 */
package biz.belcorp.ssicc.web.spisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spisicc.form.ReporteLECCargaDatosFormatosForm;

/**
 * @author fochoa
 *
 */

@ManagedBean
@SessionScoped
public class ReporteLECCargaDatosFormatosErrorAction  extends  BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9140827506839853898L;

	private String formatoReporte;
	private String tipoCarga;
	private String numeroCarga;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLECCargaDatosFormatosForm form = new ReporteLECCargaDatosFormatosForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if(formatoReporte.equals("XLS") && tipoCarga.compareTo("1")==0){
			return "reporteLECCargaDatosFormatoError1XLS";
		}
		if(formatoReporte.equals("XLS") && tipoCarga.compareTo("2")==0){
			return "reporteLECCargaDatosFormatoError2XLS";
		}
		if(formatoReporte.equals("XLS") && tipoCarga.compareTo("3")==0){
			return "reporteLECCargaDatosFormatoError3XLS";
		}		
		return "reporteLECCargaDatosFormatoError1XLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if(formatoReporte.equals("XLS") && tipoCarga.compareTo("1")==0){
			return "reporteLECCargaDatosFormatoError1XLS";
		}
		if(formatoReporte.equals("XLS") && tipoCarga.compareTo("2")==0){
			return "reporteLECCargaDatosFormatoError2XLS";
		}
		if(formatoReporte.equals("XLS") && tipoCarga.compareTo("3")==0){
			return "reporteLECCargaDatosFormatoError3XLS";
		}
		return "reporteLECCargaDatosFormatoError1XLS";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		

//		formatoReporte = request.getParameter("formatoExportacion");
//		tipoCarga = request.getParameter("tipoCarga");
		//numCarga = request.getParameter("numCarga");
		ReporteLECCargaDatosFormatosForm f = (ReporteLECCargaDatosFormatosForm) this.formReporte;
		f.setNumeroCarga(numeroCarga);
		params.put("formatoExportacion",formatoReporte);
		params.put("tipo_carga",tipoCarga);
		Integer num_carga=Integer.parseInt(f.getNumeroCarga());		
		params.put("num_carga",num_carga);
		

		return params;

	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
//		this.mostrarReportePDF = false;
//		this.mostrarReporteXLS = true;
		
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

	/**
	 * @return the numeroCarga
	 */
	public String getNumeroCarga() {
		return numeroCarga;
	}

	/**
	 * @param numeroCarga the numeroCarga to set
	 */
	public void setNumeroCarga(String numeroCarga) {
		this.numeroCarga = numeroCarga;
	}
	
	
}
