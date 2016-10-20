package biz.belcorp.ssicc.web.spusicc.comision.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCOMAvanceRecuperacionCobranzaForm extends BaseReporteForm {
	
	/**
	 * JPPS
	 */
	private static final long serialVersionUID = -5174544979535053461L;
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoPeriodoRecup;
	private String[] codigoRegion;
	private String[] codigoZona;

	
	
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


	public String getCodigoPeriodoRecup() {
		return codigoPeriodoRecup;
	}


	public void setCodigoPeriodoRecup(String codigoPeriodoRecup) {
		this.codigoPeriodoRecup = codigoPeriodoRecup;
	}


	public String[] getCodigoRegion() {
		return codigoRegion;
	}


	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}


	public String[] getCodigoZona() {
		return codigoZona;
	}


	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
	}



	
}
