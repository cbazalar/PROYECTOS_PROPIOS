package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class Local extends AuditableBaseObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4834430549319275804L;
	private String  codigoPais;
	private String  codigoRegion;
	private String  codigoEmpresa;
	private String  nombreEmpresa;
	private String  codigoLocal;
	private String  descripcionLocal;
	private String  direccion;
	private Integer capacidadLocal;
	private String 	telefono;
	private String  estadoActividad;
	private String  estadoRegistro;
//para la sala
	private String estadoSalaActividad;
	private String capacidadSalaLocal;
	private String descripcionSalaLocal;
	private String codigoSalaLocal;
	
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
	 * @return Returns the capacidadLocal.
	 */
	public Integer getCapacidadLocal() {
		return capacidadLocal;
	}
	/**
	 * @param capacidadLocal The capacidadLocal to set.
	 */
	public void setCapacidadLocal(Integer capacidadLocal) {
		this.capacidadLocal = capacidadLocal;
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
	 * @return Returns the descripcionLocal.
	 */
	public String getDescripcionLocal() {
		return descripcionLocal;
	}
	/**
	 * @param descripcionLocal The descripcionLocal to set.
	 */
	public void setDescripcionLocal(String descripcionLocal) {
		this.descripcionLocal = descripcionLocal;
	}
	/**
	 * @return Returns the direccion.
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion The direccion to set.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
	public String toString() {
		 return new ToStringBuilder(this)
		 			.append("codigoLocal", this.codigoLocal)
		 			.append("descripcionLocal", this.descripcionLocal)
	                .append("direccion", this.direccion)
	                .append("capacidadLocal", this.capacidadLocal)
	                .append("telefono", this.telefono)
	                .toString();
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
	 * @return Returns the telefono.
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono The telefono to set.
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return Returns the capacidadSalaLocal.
	 */
	public String getCapacidadSalaLocal() {
		return capacidadSalaLocal;
	}
	/**
	 * @param capacidadSalaLocal The capacidadSalaLocal to set.
	 */
	public void setCapacidadSalaLocal(String capacidadSalaLocal) {
		this.capacidadSalaLocal = capacidadSalaLocal;
	}
	/**
	 * @return Returns the codigoSalaLocal.
	 */
	public String getCodigoSalaLocal() {
		return codigoSalaLocal;
	}
	/**
	 * @param codigoSalaLocal The codigoSalaLocal to set.
	 */
	public void setCodigoSalaLocal(String codigoSalaLocal) {
		this.codigoSalaLocal = codigoSalaLocal;
	}
	/**
	 * @return Returns the descripcionSalaLocal.
	 */
	public String getDescripcionSalaLocal() {
		return descripcionSalaLocal;
	}
	/**
	 * @param descripcionSalaLocal The descripcionSalaLocal to set.
	 */
	public void setDescripcionSalaLocal(String descripcionSalaLocal) {
		this.descripcionSalaLocal = descripcionSalaLocal;
	}
	/**
	 * @return Returns the estadoSalaActividad.
	 */
	public String getEstadoSalaActividad() {
		return estadoSalaActividad;
	}
	/**
	 * @param estadoSalaActividad The estadoSalaActividad to set.
	 */
	public void setEstadoSalaActividad(String estadoSalaActividad) {
		this.estadoSalaActividad = estadoSalaActividad;
	}
	
	/**
	 * @return Codigo de region del local
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	
	/**
	 * @param codigoRegion Codigo de eregion para el local 
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	
}
