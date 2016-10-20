package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSTOExcepcionesValidacionesForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class ReporteSTOExcepcionesValidacionesAction extends BaseReporteAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7865743156661067027L;
	
	private String formatoReporte;
	private String tipoDocumento;
	private String validaciones;
	private String codigoPeriodo;
	private String codigoConsultora;
	private String fecha;
	

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception 
	{
		ReporteSTOExcepcionesValidacionesForm formReporte = new ReporteSTOExcepcionesValidacionesForm();
		return formReporte;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception 
	{
		if ("XLS".equals(this.formatoReporte))
			return "reporteSTOExcepcionesValidacionesXLS";
		else
			return "reporteMaestroVertical";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception 
	{
		ReporteSTOExcepcionesValidacionesForm reporteForm = (ReporteSTOExcepcionesValidacionesForm) this.formReporte;

		this.formatoReporte = reporteForm.getFormatoExportacion();

		params.put("documento", reporteForm.getTipoDocumento());

		if (StringUtils.isNotBlank(reporteForm.getValidaciones())) {
			params.put("validacion", " AND A.COD_VALI = '" + reporteForm.getValidaciones() + "'");
		} else {
			params.put("validacion", "");
		}

		if (StringUtils.isNotBlank(reporteForm.getCodigoPeriodo())) {
			params.put("periodo", " AND A.COD_PERI = '" + reporteForm.getCodigoPeriodo() + "'");
		} else {
			params.put("periodo", "");
		}

		if (StringUtils.isNotBlank(reporteForm.getCodigoConsultora())) {
			params.put("codigoCliente", " AND A.COD_CLIE = '" + reporteForm.getCodigoConsultora() + "'");
		} else {
			params.put("codigoCliente", "");
		}

		if (StringUtils.isNotBlank(reporteForm.getFecha())) {
			params.put("fecha", " AND TRUNC(A.FEC_DIGI) = TO_DATE('" + reporteForm.getFecha() + "', 'DD/MM/YYYY')");
		} else {
			params.put("fecha", "");
		}

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		ReporteSTOExcepcionesValidacionesForm f = (ReporteSTOExcepcionesValidacionesForm) this.formReporte;
		f.setCodigoPais("");
		this.visualizarReporte = false;
	}
	
	public void setearValores()
	{
		ReporteSTOExcepcionesValidacionesForm f = (ReporteSTOExcepcionesValidacionesForm) this.formReporte;
		f.setTipoDocumento(this.tipoDocumento);
		f.setValidaciones(this.validaciones);
		f.setCodigoConsultora(this.codigoConsultora);
		f.setCodigoPeriodo(this.codigoPeriodo);
		f.setFecha(this.fecha);
		f.setFormatoExportacion(this.formatoReporte);
			
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getValidaciones() {
		return validaciones;
	}

	public void setValidaciones(String validaciones) {
		this.validaciones = validaciones;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
