package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class TipoChequeo extends AuditableBaseObject implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoTipoChequeo;
	private String descripcionTipoChequeo;
	private String indicadorConsultorasReincidentes;
	private Integer numeroDiasAtrasFaltantes;
	private Integer minumoReclamosFaltante;
	private String minimoIndicadorNuevas;
	
	/**
	 * @return
	 */
	public Integer getNumeroDiasAtrasFaltantes() {
		return numeroDiasAtrasFaltantes;
	}

	/**
	 * @param numeroDiasAtrasFaltantes
	 */
	public void setNumeroDiasAtrasFaltantes(Integer numeroDiasAtrasFaltantes) {
		this.numeroDiasAtrasFaltantes = numeroDiasAtrasFaltantes;
	}

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
	public String getCodigoTipoChequeo() {
		return codigoTipoChequeo;
	}

	/**
	 * @param codigoTipoChequeo
	 */
	public void setCodigoTipoChequeo(String codigoTipoChequeo) {
		this.codigoTipoChequeo = codigoTipoChequeo;
	}

	/**
	 * @return
	 */
	public String getDescripcionTipoChequeo() {
		return descripcionTipoChequeo;
	}

	/**
	 * @param descripcionTipoChequeo
	 */
	public void setDescripcionTipoChequeo(String descripcionTipoChequeo) {
		this.descripcionTipoChequeo = descripcionTipoChequeo;
	}

	/**
	 * @return
	 */
	public String getIndicadorConsultorasReincidentes() {
		return indicadorConsultorasReincidentes;
	}

	/**
	 * @param indicadorConsultorasReincidentes
	 */
	public void setIndicadorConsultorasReincidentes(
			String indicadorConsultorasReincidentes) {
		this.indicadorConsultorasReincidentes = indicadorConsultorasReincidentes;
	}

	/**
	 * @return
	 */
	public Integer getMinumoReclamosFaltante() {
		return minumoReclamosFaltante;
	}

	/**
	 * @param minumoReclamosFaltante
	 */
	public void setMinumoReclamosFaltante(Integer minumoReclamosFaltante) {
		this.minumoReclamosFaltante = minumoReclamosFaltante;
	}

	/**
	 * @return
	 */
	public String getMinimoIndicadorNuevas() {
		return minimoIndicadorNuevas;
	}

	/**
	 * @param minimoIndicadorNuevas
	 */
	public void setMinimoIndicadorNuevas(String minimoIndicadorNuevas) {
		this.minimoIndicadorNuevas = minimoIndicadorNuevas;
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