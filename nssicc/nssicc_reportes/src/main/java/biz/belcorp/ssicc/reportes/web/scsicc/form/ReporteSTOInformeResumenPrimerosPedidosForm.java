package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteSTOInformeResumenPrimerosPedidosForm extends BaseReporteForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;	
	private String codigoPeriodo;	
	private String fechaDesde;	
	private Date fechaDesdeD;	
	private String fechaHasta;
	private Date fechaHastaD;
	
	
	public Date getFechaDesdeD() {
		return fechaDesdeD;
	}
	public void setFechaDesdeD(Date fechaDesdeD) {
		this.fechaDesdeD = fechaDesdeD;
	}
	public Date getFechaHastaD() {
		return fechaHastaD;
	}
	public void setFechaHastaD(Date fechaHastaD) {
		this.fechaHastaD = fechaHastaD;
	}
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
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}	
}
