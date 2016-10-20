package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteINCProvisionContableIngresosForm extends BaseReporteForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2163005170491432623L;
	private String codigoPais;
	private String numeroConcurso;	
	private String fechaInicio;
	private String fechaFin;
	private Date fechaInicioD;
	private Date fechaFinD;

	private boolean mostrarBotonExcel;

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

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicioD() {
		return fechaInicioD;
	}

	public void setFechaInicioD(Date fechaInicioD) {
		this.fechaInicioD = fechaInicioD;
	}

	public Date getFechaFinD() {
		return fechaFinD;
	}

	public void setFechaFinD(Date fechaFinD) {
		this.fechaFinD = fechaFinD;
	}

	public boolean isMostrarBotonExcel() {
		return mostrarBotonExcel;
	}

	public void setMostrarBotonExcel(boolean mostrarBotonExcel) {
		this.mostrarBotonExcel = mostrarBotonExcel;
	}

	

	
}


