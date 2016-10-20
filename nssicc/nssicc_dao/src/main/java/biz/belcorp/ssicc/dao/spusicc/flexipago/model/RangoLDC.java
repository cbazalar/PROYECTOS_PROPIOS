package biz.belcorp.ssicc.dao.spusicc.flexipago.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="itocto@sigcomt.com">Ivan Tocto</a>
 *
 */
public class RangoLDC extends AuditableBaseObject implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String oid;
	private String numeroSegmento;
	private String valorDesde;
	private String valorHasta;
	private String valorFactor;
	private String valorSegmento;
	private String estado;
	
	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}
	/**
	 * @param oid the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}
	/**
	 * @return the numeroSegmento
	 */
	public String getNumeroSegmento() {
		return numeroSegmento;
	}
	/**
	 * @param numeroSegmento the numeroSegmento to set
	 */
	public void setNumeroSegmento(String numeroSegmento) {
		this.numeroSegmento = numeroSegmento;
	}
	/**
	 * @return the valorDesde
	 */
	public String getValorDesde() {
		return valorDesde;
	}
	/**
	 * @param valorDesde the valorDesde to set
	 */
	public void setValorDesde(String valorDesde) {
		this.valorDesde = valorDesde;
	}
	/**
	 * @return the valorHasta
	 */
	public String getValorHasta() {
		return valorHasta;
	}
	/**
	 * @param valorHasta the valorHasta to set
	 */
	public void setValorHasta(String valorHasta) {
		this.valorHasta = valorHasta;
	}
	/**
	 * @return the valorFactor
	 */
	public String getValorFactor() {
		return valorFactor;
	}
	/**
	 * @param valorFactor the valorFactor to set
	 */
	public void setValorFactor(String valorFactor) {
		this.valorFactor = valorFactor;
	}
	/**
	 * @return the valorSegmento
	 */
	public String getValorSegmento() {
		return valorSegmento;
	}
	/**
	 * @param valorSegmento the valorSegmento to set
	 */
	public void setValorSegmento(String valorSegmento) {
		this.valorSegmento = valorSegmento;
	}
		
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((numeroSegmento == null) ? 0 : numeroSegmento.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result
				+ ((valorDesde == null) ? 0 : valorDesde.hashCode());
		result = prime * result
				+ ((valorFactor == null) ? 0 : valorFactor.hashCode());
		result = prime * result
				+ ((valorHasta == null) ? 0 : valorHasta.hashCode());
		result = prime * result
				+ ((valorSegmento == null) ? 0 : valorSegmento.hashCode());
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
		RangoLDC other = (RangoLDC) obj;
		if (numeroSegmento == null) {
			if (other.numeroSegmento != null)
				return false;
		} else if (!numeroSegmento.equals(other.numeroSegmento))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (valorDesde == null) {
			if (other.valorDesde != null)
				return false;
		} else if (!valorDesde.equals(other.valorDesde))
			return false;
		if (valorFactor == null) {
			if (other.valorFactor != null)
				return false;
		} else if (!valorFactor.equals(other.valorFactor))
			return false;
		if (valorHasta == null) {
			if (other.valorHasta != null)
				return false;
		} else if (!valorHasta.equals(other.valorHasta))
			return false;
		if (valorSegmento == null) {
			if (other.valorSegmento != null)
				return false;
		} else if (!valorSegmento.equals(other.valorSegmento))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RangoLDC [oid=" + oid + ", numeroSegmento=" + numeroSegmento
				+ ", valorDesde=" + valorDesde + ", valorHasta=" + valorHasta
				+ ", valorFactor=" + valorFactor + ", valorSegmento="
				+ valorSegmento + "]";
	}
		
}

