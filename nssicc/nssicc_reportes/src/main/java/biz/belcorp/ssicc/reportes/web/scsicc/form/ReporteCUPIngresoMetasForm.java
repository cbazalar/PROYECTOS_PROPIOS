package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCUPIngresoMetasForm extends BaseReporteForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2994446804070035716L;
	
	private String codigoPais;
	
	private String codigoRegion;
	
	private String codigoZona;
	
	private String codigoPeriodoInicial;
	
	private String codigoPeriodoFinal;
	
	private String origenRegistro;
	
	private String estado;

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	public String getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}

	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}

	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}

	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}

	public String getOrigenRegistro() {
		return origenRegistro;
	}

	public void setOrigenRegistro(String origenRegistro) {
		this.origenRegistro = origenRegistro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
