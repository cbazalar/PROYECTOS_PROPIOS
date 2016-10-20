/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;


/**
 * The Class EstructuraCOBCargarCronograma.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 06/05/2014
 */
public class EstructuraCOBCargarCronograma implements Serializable{
	
	private static final long serialVersionUID = 8996792931326814300L;
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoEtapa;
	private String descripcionEtapa;
	private String codigoRegion;
	private String fechaInicio;
	private String fechaCierre;
	private String observacion;
	private boolean registroOK;
	
	private int fila;
	
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
	 * @return the codigoEtapa
	 */
	public String getCodigoEtapa() {
		return codigoEtapa;
	}

	/**
	 * @param codigoEtapa the codigoEtapa to set
	 */
	public void setCodigoEtapa(String codigoEtapa) {
		this.codigoEtapa = codigoEtapa;
	}

	/**
	 * @return the descripcionEtapa
	 */
	public String getDescripcionEtapa() {
		return descripcionEtapa;
	}

	/**
	 * @param descripcionEtapa the descripcionEtapa to set
	 */
	public void setDescripcionEtapa(String descripcionEtapa) {
		this.descripcionEtapa = descripcionEtapa;
	}

	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaCierre
	 */
	public String getFechaCierre() {
		return fechaCierre;
	}

	/**
	 * @param fechaCierre the fechaCierre to set
	 */
	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the fila
	 */
	public int getFila() {
		return fila;
	}

	/**
	 * @param fila the fila to set
	 */
	public void setFila(int fila) {
		this.fila = fila;
	}

	/**
	 * @return the registroOK
	 */
	public boolean isRegistroOK() {
		return registroOK;
	}

	/**
	 * @param registroOK the registroOK to set
	 */
	public void setRegistroOK(boolean registroOK) {
		this.registroOK = registroOK;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	
	


}
