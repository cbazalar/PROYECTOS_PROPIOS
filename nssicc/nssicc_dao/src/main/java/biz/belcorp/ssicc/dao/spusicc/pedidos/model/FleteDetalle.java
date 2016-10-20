package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class FleteDetalle extends AuditableBaseObject implements Serializable {

	private String montoFijo;
	private String rangoInferior;
	private String rangoSuperior;

	private String oidFlete;
	private String oidDetFlete;
	
	private String accion;

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
	 * @return the oidDetFlete
	 */
	public String getOidDetFlete() {
		return oidDetFlete;
	}

	/**
	 * @param oidDetFlete the oidDetFlete to set
	 */
	public void setOidDetFlete(String oidDetFlete) {
		this.oidDetFlete = oidDetFlete;
	}

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
	 * @return the rangoInferior
	 */
	public String getRangoInferior() {
		return rangoInferior;
	}

	/**
	 * @param rangoInferior the rangoInferior to set
	 */
	public void setRangoInferior(String rangoInferior) {
		this.rangoInferior = rangoInferior;
	}

	/**
	 * @return the rangoSuperior
	 */
	public String getRangoSuperior() {
		return rangoSuperior;
	}

	/**
	 * @param rangoSuperior the rangoSuperior to set
	 */
	public void setRangoSuperior(String rangoSuperior) {
		this.rangoSuperior = rangoSuperior;
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
}
