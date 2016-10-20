package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class Sala extends AuditableBaseObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6531608477381046781L;
	private String  codigoPais;
	private String  codigoEmpresa;
	private String  nombreEmpresa;
	private String  codigoSala;
	private String  descripcionSala;
	private Integer capacidadSala;
	private String  estadoActividad;
	private String  estadoRegistro;
	private String  codigoLocal;

	

	public String toString() {
		return null;
	}
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * @return Returns the capacidadSala.
	 */
	public Integer getCapacidadSala() {
		return capacidadSala;
	}
	/**
	 * @param capacidadSala The capacidadSala to set.
	 */
	public void setCapacidadSala(Integer capacidadSala) {
		this.capacidadSala = capacidadSala;
	}
	/**
	 * @return Returns the codigoEmpresa.
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}
	/**
	 * @param codigoEmpresa The codigoEmpresa to set.
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}
	/**
	 * @return Returns the codigoLocal.
	 */
	public String getCodigoLocal() {
		return codigoLocal;
	}
	/**
	 * @param codigoLocal The codigoLocal to set.
	 */
	public void setCodigoLocal(String codigoLocal) {
		this.codigoLocal = codigoLocal;
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
	 * @return Returns the codigoSala.
	 */
	public String getCodigoSala() {
		return codigoSala;
	}
	/**
	 * @param codigoSala The codigoSala to set.
	 */
	public void setCodigoSala(String codigoSala) {
		this.codigoSala = codigoSala;
	}
	/**
	 * @return Returns the descripcionSala.
	 */
	public String getDescripcionSala() {
		return descripcionSala;
	}
	/**
	 * @param descripcionSala The descripcionSala to set.
	 */
	public void setDescripcionSala(String descripcionSala) {
		this.descripcionSala = descripcionSala;
	}
	/**
	 * @return Returns the estadoActividad.
	 */
	public String getEstadoActividad() {
		return estadoActividad;
	}
	/**
	 * @param estadoActividad The estadoActividad to set.
	 */
	public void setEstadoActividad(String estadoActividad) {
		this.estadoActividad = estadoActividad;
	}
	/**
	 * @return Returns the estadoRegistro.
	 */
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	/**
	 * @param estadoRegistro The estadoRegistro to set.
	 */
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
	/**
	 * @return Returns the nombreEmpresa.
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	/**
	 * @param nombreEmpresa The nombreEmpresa to set.
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	
}
