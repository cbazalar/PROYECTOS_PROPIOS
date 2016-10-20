package biz.belcorp.ssicc.dao.spusicc.pej.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class PorcentajeComisionesDetalle extends AuditableBaseObject implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPorcentaje;
	private String codigoNivel;
	private Integer numeroNivel;
	private String montoDesde;
	private String montoHasta;
	private String valorPorc;
	
	private String descripcionNivel;
	 
	/**
	 * @return
	 */
	public String getDescripcionNivel() {
		return descripcionNivel;
	}

	/**
	 * @param descripcionNivel
	 */
	public void setDescripcionNivel(String descripcionNivel) {
		this.descripcionNivel = descripcionNivel;
	}

	/**
	 * @return
	 */
	public String getMontoDesde() {
		return montoDesde;
	}

	/**
	 * @param montoDesde
	 */
	public void setMontoDesde(String montoDesde) {
		this.montoDesde = montoDesde;
	}

	/**
	 * @return
	 */
	public String getCodigoPorcentaje() {
		return codigoPorcentaje;
	}

	/**
	 * @param codigoPorcentaje
	 */
	public void setCodigoPorcentaje(String codigoPorcentaje) {
		this.codigoPorcentaje = codigoPorcentaje;
	}

	/**
	 * @return
	 */
	public String getCodigoNivel() {
		return codigoNivel;
	}

	public void setCodigoNivel(String codigoNivel) {
		this.codigoNivel = codigoNivel;
	}

	/**
	 * @return
	 */
	public Integer getNumeroNivel() {
		return numeroNivel;
	}

	/**
	 * @param numeroNivel
	 */
	public void setNumeroNivel(Integer numeroNivel) {
		this.numeroNivel = numeroNivel;
	}

	/**
	 * @return
	 */
	public String getMontoHasta() {
		return montoHasta;
	}

	/**
	 * @param montoHasta
	 */
	public void setMontoHasta(String montoHasta) {
		this.montoHasta = montoHasta;
	}

	/**
	 * @return
	 */
	public String getValorPorc() {
		return valorPorc;
	}

	/**
	 * @param valorPorc
	 */
	public void setValorPorc(String valorPorc) {
		this.valorPorc = valorPorc;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return false;
	}
	 
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		return 0;
	}
	 
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}