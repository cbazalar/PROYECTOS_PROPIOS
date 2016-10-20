package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteINCDetallePuntosRegionZonaForm extends BaseReporteForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2163005170491432623L;
	private String codigoPais;
	private String numeroConcurso;	
	private String[] codigoCampania;
	private String[] codigoRegion;
	private String[] codigoZona;
	private String criterioAgrupacion;

	private boolean mostrarBotonExcel;

	
	
	public ReporteINCDetallePuntosRegionZonaForm(){
		this.codigoRegion  = null;
		this.codigoZona    = null;
	}



	public String getCodigoPais() {
		return codigoPais;
	}



	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}



	public String getNumeroConcurso() {
		return numeroConcurso;
	}



	public void setNumeroConcurso(String numeroConcurso) {
		this.numeroConcurso = numeroConcurso;
	}



	public String[] getCodigoCampania() {
		return codigoCampania;
	}



	public void setCodigoCampania(String[] codigoCampania) {
		this.codigoCampania = codigoCampania;
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



	public String getCriterioAgrupacion() {
		return criterioAgrupacion;
	}



	public void setCriterioAgrupacion(String criterioAgrupacion) {
		this.criterioAgrupacion = criterioAgrupacion;
	}



	public boolean isMostrarBotonExcel() {
		return mostrarBotonExcel;
	}



	public void setMostrarBotonExcel(boolean mostrarBotonExcel) {
		this.mostrarBotonExcel = mostrarBotonExcel;
	}

	
}

