package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoRECCronogramaBRForm extends BaseEditForm{
	
	private static final long serialVersionUID = 8625797598453734968L;
	
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoRegion;
	private String fechaInicio;
	private String fechaFin;
	private Date fechaInicioDate;
	private Date fechaFinDate;
	
	
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

	public Date getFechaInicioDate() {
		return fechaInicioDate;
	}

	public void setFechaInicioDate(Date fechaInicioDate) {
		this.fechaInicioDate = fechaInicioDate;
	}

	public Date getFechaFinDate() {
		return fechaFinDate;
	}

	public void setFechaFinDate(Date fechaFinDate) {
		this.fechaFinDate = fechaFinDate;
	}
	
	
}
