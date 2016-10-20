/*
 * Created on 18-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazMyEbelMovimientosCuentaCorriente.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class InterfazMyEbelMovimientosCuentaCorriente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5693336882265229900L;
	private String codigoPais;
	private String codigoConsultora;
	private String numeroLote;
	private String tipoOperacion;
	private Timestamp fechaOperacion;
	private Timestamp fechaVencimiento;
	private String anyoCampanya;
	private String descripcionTransaccion;
	private Double montoOperacion;
	
	
	/**
	 * @return Returns the anyoCampanya.
	 */
	public String getAnyoCampanya() {
		return anyoCampanya;
	}
	/**
	 * @param anyoCampanya The anyoCampanya to set.
	 */
	public void setAnyoCampanya(String anyoCampanya) {
		this.anyoCampanya = anyoCampanya;
	}
	/**
	 * @return Returns the codigoConsultora.
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}
	/**
	 * @param codigoConsultora The codigoConsultora to set.
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
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
	 * @return Returns the descripcionTransaccion.
	 */
	public String getDescripcionTransaccion() {
		return descripcionTransaccion;
	}
	/**
	 * @param descripcionTransaccion The descripcionTransaccion to set.
	 */
	public void setDescripcionTransaccion(String descripcionTransaccion) {
		this.descripcionTransaccion = descripcionTransaccion;
	}
	/**
	 * @return Returns the fechaOperacion.
	 */
	public Timestamp getFechaOperacion() {
		return fechaOperacion;
	}
	/**
	 * @param fechaOperacion The fechaOperacion to set.
	 */
	public void setFechaOperacion(Timestamp fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	/**
	 * @return Returns the fechaVencimiento.
	 */
	public Timestamp getFechaVencimiento() {
		return fechaVencimiento;
	}
	/**
	 * @param fechaVencimiento The fechaVencimiento to set.
	 */
	public void setFechaVencimiento(Timestamp fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	/**
	 * @return Returns the montoOperacion.
	 */
	public Double getMontoOperacion() {
		return montoOperacion;
	}
	/**
	 * @param montoOperacion The montoOperacion to set.
	 */
	public void setMontoOperacion(Double montoOperacion) {
		this.montoOperacion = montoOperacion;
	}
	/**
	 * @return Returns the numeroLote.
	 */
	public String getNumeroLote() {
		return numeroLote;
	}
	/**
	 * @param numeroLote The numeroLote to set.
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}
	/**
	 * @return Returns the tipoOperacion.
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	/**
	 * @param tipoOperacion The tipoOperacion to set.
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
}
