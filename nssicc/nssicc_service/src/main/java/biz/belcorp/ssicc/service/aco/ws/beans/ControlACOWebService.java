package biz.belcorp.ssicc.service.aco.ws.beans;

import java.io.Serializable;

public class ControlACOWebService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6466888775161640355L;
	private String codigoPeriodo;
	private String fechaFacturacion;
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
	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	
	
	
}
