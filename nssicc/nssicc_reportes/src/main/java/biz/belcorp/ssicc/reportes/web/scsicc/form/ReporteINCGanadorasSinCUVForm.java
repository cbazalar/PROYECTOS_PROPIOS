package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteINCGanadorasSinCUVForm extends BaseReporteForm implements Serializable{
	
	
	private static final long serialVersionUID = -2169457589487046808L;
	
	private String codigoPais;
	private String numeroConcurso;	
	private String[] codigoRegion;
	private String descripcionRegion;
	private String[] codigoZona;
	private String descripcionZona;
	private String codigoPeriodo;
	private String codigoCUV;
	
	private boolean mostrarBotonExcel;

	
	
	public ReporteINCGanadorasSinCUVForm(){
		this.codigoRegion  = null;
		this.codigoZona    = null;
	}

	
	public String getCodigoPais() {
		return codigoPais;
	}

	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
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
	
	

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	
	public boolean isMostrarBotonExcel() {
		return mostrarBotonExcel;
	}

	
	public void setMostrarBotonExcel(boolean mostrarBotonExcel) {
		this.mostrarBotonExcel = mostrarBotonExcel;
	}

	
	public String getNumeroConcurso() {
		return numeroConcurso;
	}

	public void setNumeroConcurso(String numeroConcurso) {
		this.numeroConcurso = numeroConcurso;
	}
	
	public String getCodigoCUV() {
		return codigoCUV;
	}
	
	public void setCodigoCUV(String codigoCUV) {
		this.codigoCUV = codigoCUV;
	}
	
}
