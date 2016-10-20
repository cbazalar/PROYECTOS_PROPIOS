package biz.belcorp.ssicc.dao.spusicc.flx.model;

import java.io.Serializable;
import java.math.BigDecimal;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class InteresFlexipago extends AuditableBaseObject implements Serializable{
	
	private static final long serialVersionUID = -3076770575845023584L;
	
	
	private String codigoPais;
	private String periodoInicio;
	private String periodoFin;
	private BigDecimal rangoImpoDesde;
	private BigDecimal rangoImpoHasta;
	private BigDecimal valorCosto;
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
	 * @return the periodoInicio
	 */
	public String getPeriodoInicio() {
		return periodoInicio;
	}
	/**
	 * @param periodoInicio the periodoInicio to set
	 */
	public void setPeriodoInicio(String periodoInicio) {
		this.periodoInicio = periodoInicio;
	}
	/**
	 * @return the periodoFin
	 */
	public String getPeriodoFin() {
		return periodoFin;
	}
	/**
	 * @param periodoFin the periodoFin to set
	 */
	public void setPeriodoFin(String periodoFin) {
		this.periodoFin = periodoFin;
	}
	/**
	 * @return the rangoImpoDesde
	 */
	public BigDecimal getRangoImpoDesde() {
		return rangoImpoDesde;
	}
	/**
	 * @param rangoImpoDesde the rangoImpoDesde to set
	 */
	public void setRangoImpoDesde(BigDecimal rangoImpoDesde) {
		this.rangoImpoDesde = rangoImpoDesde;
	}
	/**
	 * @return the rangoImpoHasta
	 */
	public BigDecimal getRangoImpoHasta() {
		return rangoImpoHasta;
	}
	/**
	 * @param rangoImpoHasta the rangoImpoHasta to set
	 */
	public void setRangoImpoHasta(BigDecimal rangoImpoHasta) {
		this.rangoImpoHasta = rangoImpoHasta;
	}
	/**
	 * @return the valorCosto
	 */
	public BigDecimal getValorCosto() {
		return valorCosto;
	}
	/**
	 * @param valorCosto the valorCosto to set
	 */
	public void setValorCosto(BigDecimal valorCosto) {
		this.valorCosto = valorCosto;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((periodoFin == null) ? 0 : periodoFin.hashCode());
		result = prime * result
				+ ((periodoInicio == null) ? 0 : periodoInicio.hashCode());
		result = prime * result
				+ ((rangoImpoDesde == null) ? 0 : rangoImpoDesde.hashCode());
		result = prime * result
				+ ((rangoImpoHasta == null) ? 0 : rangoImpoHasta.hashCode());
		result = prime * result
				+ ((valorCosto == null) ? 0 : valorCosto.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InteresFlexipago other = (InteresFlexipago) obj;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (periodoFin == null) {
			if (other.periodoFin != null)
				return false;
		} else if (!periodoFin.equals(other.periodoFin))
			return false;
		if (periodoInicio == null) {
			if (other.periodoInicio != null)
				return false;
		} else if (!periodoInicio.equals(other.periodoInicio))
			return false;
		if (rangoImpoDesde == null) {
			if (other.rangoImpoDesde != null)
				return false;
		} else if (!rangoImpoDesde.equals(other.rangoImpoDesde))
			return false;
		if (rangoImpoHasta == null) {
			if (other.rangoImpoHasta != null)
				return false;
		} else if (!rangoImpoHasta.equals(other.rangoImpoHasta))
			return false;
		if (valorCosto == null) {
			if (other.valorCosto != null)
				return false;
		} else if (!valorCosto.equals(other.valorCosto))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InteresFlexipago [codigoPais=" + codigoPais
				+ ", periodoInicio=" + periodoInicio + ", periodoFin="
				+ periodoFin + ", rangoImpoDesde=" + rangoImpoDesde
				+ ", rangoImpoHasta=" + rangoImpoHasta + ", valorCosto="
				+ valorCosto + "]";
	}
	
	
	

}
