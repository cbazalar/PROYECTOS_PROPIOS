package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class OrigenChequeo extends AuditableBaseObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoOrigenChequeo;
	private String descripcionOrigenChequeo;
	private Integer secuenciaEvaluacion;
	private String nombreProcesoEjecutar;
	
	/**
	 * @return
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return
	 */
	public String getCodigoOrigenChequeo() {
		return codigoOrigenChequeo;
	}

	/**
	 * @param codigoOrigenChequeo
	 */
	public void setCodigoOrigenChequeo(String codigoOrigenChequeo) {
		this.codigoOrigenChequeo = codigoOrigenChequeo;
	}

	/**
	 * @return
	 */
	public String getDescripcionOrigenChequeo() {
		return descripcionOrigenChequeo;
	}

	/**
	 * @param descripcionOrigenChequeo
	 */
	public void setDescripcionOrigenChequeo(String descripcionOrigenChequeo) {
		this.descripcionOrigenChequeo = descripcionOrigenChequeo;
	}

	/**
	 * @return
	 */
	public Integer getSecuenciaEvaluacion() {
		return secuenciaEvaluacion;
	}

	/**
	 * @param secuenciaEvaluacion
	 */
	public void setSecuenciaEvaluacion(Integer secuenciaEvaluacion) {
		this.secuenciaEvaluacion = secuenciaEvaluacion;
	}

	/**
	 * @return
	 */
	public String getNombreProcesoEjecutar() {
		return nombreProcesoEjecutar;
	}

	/**
	 * @param nombreProcesoEjecutar
	 */
	public void setNombreProcesoEjecutar(String nombreProcesoEjecutar) {
		this.nombreProcesoEjecutar = nombreProcesoEjecutar;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}	
}