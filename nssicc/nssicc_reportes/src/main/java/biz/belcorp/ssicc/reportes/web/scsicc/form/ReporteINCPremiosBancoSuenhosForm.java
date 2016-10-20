package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


public class ReporteINCPremiosBancoSuenhosForm extends BaseReporteForm implements Serializable{
		
	
	private static final long serialVersionUID = -3687596301394759539L;
	
	
	private String codigoPais;
	private String codigoConcurso;	
	private String[] codigoRegion;
	private String descripcionRegion;
	private String[] codigoZona;
	private String descripcionZona;
	
	
	public ReporteINCPremiosBancoSuenhosForm() {
		this.codigoRegion  = null;
		this.codigoZona    = null;
	}
	
	
	public String getCodigoPais() {
		return codigoPais;
	}

	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	
	public String getCodigoConcurso() {
		return codigoConcurso;
	}

	
	public void setCodigoConcurso(String codigoConcurso) {
		this.codigoConcurso = codigoConcurso;
	}

	
	public String[] getCodigoRegion() {
		return codigoRegion;
	}

	
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	
	public String getDescripcionRegion() {
		return descripcionRegion;
	}

	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}

	
	public String[] getCodigoZona() {
		return codigoZona;
	}

	
	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
	}

	
	public String getDescripcionZona() {
		return descripcionZona;
	}

	
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}

}
