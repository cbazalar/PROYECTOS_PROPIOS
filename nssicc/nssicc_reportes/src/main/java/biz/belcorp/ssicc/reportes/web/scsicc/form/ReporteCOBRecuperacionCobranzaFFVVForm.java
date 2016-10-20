package biz.belcorp.ssicc.reportes.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCOBRecuperacionCobranzaFFVVForm extends BaseReporteForm{

	/**
	 * JPPS
	 */
	private static final long serialVersionUID = -6402902591912368987L;

	private String codigoPais;
	private String codigoPeriodo;
	private String codigoRegion;
	

	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	public String getCodigoRegion() {
		return codigoRegion;
	}
	
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	
}
