package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;


import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


public class ReportePEDFacturadosConsultoraForm extends BaseReporteForm implements Serializable{

	private static final long serialVersionUID = 851374686506968594L;
	
	private String fecha;
	private Date fechaDate;
	private String codigoPeriodo;
	
	
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the fechaDate
	 */
	public Date getFechaDate() {
		return fechaDate;
	}
	/**
	 * @param fechaDate the fechaDate to set
	 */
	public void setFechaDate(Date fechaDate) {
		this.fechaDate = fechaDate;
	}
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	

}
