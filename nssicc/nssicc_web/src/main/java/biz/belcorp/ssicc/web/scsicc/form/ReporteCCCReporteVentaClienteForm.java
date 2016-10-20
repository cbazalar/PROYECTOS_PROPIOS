package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;



public class ReporteCCCReporteVentaClienteForm extends BaseReporteForm implements Serializable {
	
	
	private static final long serialVersionUID = -6056046170272288550L;
	
	
	private String fechaInicio;
	private String fechaFin;
	
	private Date fechaInicioD;
	private Date fechaFinD;
	
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
		

}


