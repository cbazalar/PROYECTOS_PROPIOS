/*
 * Created on 09-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="Periodo.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
public class Periodo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 716820201574920088L;
	private String codigoPais;
	private String codigoMarca;
	private String codigoCanal;
	private String codigoAcceso;
	private String codigoPeriodo;
	private String fechaInicioPeriodo;
	private String fechaFinPeriodo;

	
	/**
	 * @return Returns the codigoAcceso.
	 */
	public String getCodigoAcceso() {
		return codigoAcceso;
	}
	/**
	 * @param codigoAcceso The codigoAcceso to set.
	 */
	public void setCodigoAcceso(String codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}
	/**
	 * @return Returns the codigoCanal.
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}
	/**
	 * @param codigoCanal The codigoCanal to set.
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
	/**
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}
	/**
	 * @param codigoMarca The codigoMarca to set.
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return Returns the fechaFinPeriodo.
	 */
	public String getFechaFinPeriodo() {
		return fechaFinPeriodo;
	}
	/**
	 * @param fechaFinPeriodo The fechaFinPeriodo to set.
	 */
	public void setFechaFinPeriodo(String fechaFinPeriodo) {
		this.fechaFinPeriodo = fechaFinPeriodo;
	}
	/**
	 * @return Returns the fechaInicioPeriodo.
	 */
	public String getFechaInicioPeriodo() {
		return fechaInicioPeriodo;
	}
	/**
	 * @param fechaInicioPeriodo The fechaInicioPeriodo to set.
	 */
	public void setFechaInicioPeriodo(String fechaInicioPeriodo) {
		this.fechaInicioPeriodo = fechaInicioPeriodo;
	}
}
