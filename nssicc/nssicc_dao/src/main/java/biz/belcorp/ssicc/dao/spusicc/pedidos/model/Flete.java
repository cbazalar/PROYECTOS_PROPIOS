package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class Flete extends AuditableBaseObject implements Serializable {

	private String codigoPais;
	private String oidFlete;
	private String montoFijo;
	private String tasa;
	private String fleteMinimo;
	private String fleteMaximo;
	private String recargo;
	private String oidCanal;
	private String oidTipoDespacho;
	private String oidMarca;
	private String oidClasificacion;
	private String oidSubTipoCliente;
	private String oidTipoCliente;
	private String oidTipoClasificacion;
	private String codigoZona;
	private String codigoRegion;
	private String ubigeo;

	private String accion;

	/**
	 * @return the montoFijo
	 */
	public String getMontoFijo() {
		return montoFijo;
	}

	/**
	 * @param montoFijo the montoFijo to set
	 */
	public void setMontoFijo(String montoFijo) {
		this.montoFijo = montoFijo;
	}

	/**
	 * @return the tasa
	 */
	public String getTasa() {
		return tasa;
	}

	/**
	 * @param tasa the tasa to set
	 */
	public void setTasa(String tasa) {
		this.tasa = tasa;
	}

	/**
	 * @return the fleteMinimo
	 */
	public String getFleteMinimo() {
		return fleteMinimo;
	}

	/**
	 * @param fleteMinimo the fleteMinimo to set
	 */
	public void setFleteMinimo(String fleteMinimo) {
		this.fleteMinimo = fleteMinimo;
	}

	/**
	 * @return the fleteMaximo
	 */
	public String getFleteMaximo() {
		return fleteMaximo;
	}

	/**
	 * @param fleteMaximo the fletMaximo to set
	 */
	public void setFleteMaximo(String fletMaximo) {
		this.fleteMaximo = fletMaximo;
	}

	/**
	 * @return the oidCanal
	 */
	public String getOidCanal() {
		return oidCanal;
	}

	/**
	 * @param oidCanal the oidCanal to set
	 */
	public void setOidCanal(String oidCanal) {
		this.oidCanal = oidCanal;
	}

	/**
	 * @return the oidTipoDespacho
	 */
	public String getOidTipoDespacho() {
		return oidTipoDespacho;
	}

	/**
	 * @param oidTipoDespacho the oidTipoDespacho to set
	 */
	public void setOidTipoDespacho(String oidTipoDespacho) {
		this.oidTipoDespacho = oidTipoDespacho;
	}

	/**
	 * @return the oidMarca
	 */
	public String getOidMarca() {
		return oidMarca;
	}

	/**
	 * @param oidMarca the oidMarca to set
	 */
	public void setOidMarca(String oidMarca) {
		this.oidMarca = oidMarca;
	}

	/**
	 * @return the oidClasificacion
	 */
	public String getOidClasificacion() {
		return oidClasificacion;
	}

	/**
	 * @param oidClasificacion the oidClasificacion to set
	 */
	public void setOidClasificacion(String oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
	}

	/**
	 * @return the oidSubTipoCliente
	 */
	public String getOidSubTipoCliente() {
		return oidSubTipoCliente;
	}

	/**
	 * @param oidSubTipoCliente the oidSubTipoCliente to set
	 */
	public void setOidSubTipoCliente(String oidSubTipoCliente) {
		this.oidSubTipoCliente = oidSubTipoCliente;
	}

	/**
	 * @return the oidTipoCliente
	 */
	public String getOidTipoCliente() {
		return oidTipoCliente;
	}

	/**
	 * @param oidTipoCliente the oidTipoCliente to set
	 */
	public void setOidTipoCliente(String oidTipoCliente) {
		this.oidTipoCliente = oidTipoCliente;
	}

	/**
	 * @return the oidTipoClasificacion
	 */
	public String getOidTipoClasificacion() {
		return oidTipoClasificacion;
	}

	/**
	 * @param oidTipoClasificacion the oidTipoClasificacion to set
	 */
	public void setOidTipoClasificacion(String oidTipoClasificacion) {
		this.oidTipoClasificacion = oidTipoClasificacion;
	}

	/**
	 * @return the ubigeo
	 */
	public String getUbigeo() {
		return ubigeo;
	}

	/**
	 * @param ubigeo the ubigeo to set
	 */
	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
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

	/**
	 * @return the oidFlete
	 */
	public String getOidFlete() {
		return oidFlete;
	}

	/**
	 * @param oidFlete the oidFlete to set
	 */
	public void setOidFlete(String oidFlete) {
		this.oidFlete = oidFlete;
	}

	/**
	 * @return the recargo
	 */
	public String getRecargo() {
		return recargo;
	}

	/**
	 * @param recargo the recargo to set
	 */
	public void setRecargo(String recargo) {
		this.recargo = recargo;
	}

	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
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
}
