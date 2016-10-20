package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Jesse James Rios Franco
 */

public class ResultadoChequeo extends AuditableBaseObject implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoResultadoChequeo;
	private String descripcionResultadoChequeo;
	
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
	public String getCodigoResultadoChequeo() {
		return codigoResultadoChequeo;
	}

	/**
	 * @param codigoResultadoChequeo
	 */
	public void setCodigoResultadoChequeo(String codigoResultadoChequeo) {
		this.codigoResultadoChequeo = codigoResultadoChequeo;
	}

	/**
	 * @return
	 */
	public String getDescripcionResultadoChequeo() {
		return descripcionResultadoChequeo;
	}

	/**
	 * @param descripcionResultadoChequeo
	 */
	public void setDescripcionResultadoChequeo(String descripcionResultadoChequeo) {
		this.descripcionResultadoChequeo = descripcionResultadoChequeo;
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