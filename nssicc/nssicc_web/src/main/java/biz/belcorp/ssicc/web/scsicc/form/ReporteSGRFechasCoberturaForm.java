package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteSGRFechasCoberturaForm  extends BaseReporteForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7296741105805325293L;
	private String codigoPais;
	private String codigoPeriodoInicio;
	private String codigoPeriodoFin;
	private String codigoRegion;
	private String descripcionRegion;
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}
	public void setCodigoPeriodoInicio(String codigoPeriodoInicio) {
		this.codigoPeriodoInicio = codigoPeriodoInicio;
	}
	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}
	public String getCodigoRegion() {
		return codigoRegion;
	}
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	public String getDescripcionRegion() {
		return descripcionRegion;
	}
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}
	
	

}
