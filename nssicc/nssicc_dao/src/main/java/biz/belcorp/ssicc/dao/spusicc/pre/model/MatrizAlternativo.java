package biz.belcorp.ssicc.dao.spusicc.pre.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * 
 * @author Sigcomt
 *
 */
public class MatrizAlternativo extends AuditableBaseObject implements Serializable{

	private String oid;
	private String indicadorActivo;
	
	private String oidMatrizPrincipal;
	private String oidMatrizAlternativo;
	private String indicadorMensaje;
	private String numeroOrden ;
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
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}
	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}
	/**
	 * @return the oidMatrizPrincipal
	 */
	public String getOidMatrizPrincipal() {
		return oidMatrizPrincipal;
	}
	/**
	 * @param oidMatrizPrincipal the oidMatrizPrincipal to set
	 */
	public void setOidMatrizPrincipal(String oidMatrizPrincipal) {
		this.oidMatrizPrincipal = oidMatrizPrincipal;
	}
	/**
	 * @return the oidMatrizAlternativo
	 */
	public String getOidMatrizAlternativo() {
		return oidMatrizAlternativo;
	}
	/**
	 * @param oidMatrizAlternativo the oidMatrizAlternativo to set
	 */
	public void setOidMatrizAlternativo(String oidMatrizAlternativo) {
		this.oidMatrizAlternativo = oidMatrizAlternativo;
	}
	/**
	 * @return the indicadorMensaje
	 */
	public String getIndicadorMensaje() {
		return indicadorMensaje;
	}
	/**
	 * @param indicadorMensaje the indicadorMensaje to set
	 */
	public void setIndicadorMensaje(String indicadorMensaje) {
		this.indicadorMensaje = indicadorMensaje;
	}
	/**
	 * @return the numeroOrden
	 */
	public String getNumeroOrden() {
		return numeroOrden;
	}
	/**
	 * @param numeroOrden the numeroOrden to set
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "MatrizAlternativo [oid=" + oid + ", indicadorActivo="
				+ indicadorActivo + ", oidMatrizPrincipal="
				+ oidMatrizPrincipal + ", oidMatrizAlternativo="
				+ oidMatrizAlternativo + ", indicadorMensaje="
				+ indicadorMensaje + ", numeroOrden=" + numeroOrden + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((indicadorActivo == null) ? 0 : indicadorActivo.hashCode());
		result = prime
				* result
				+ ((indicadorMensaje == null) ? 0 : indicadorMensaje.hashCode());
		result = prime * result
				+ ((numeroOrden == null) ? 0 : numeroOrden.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime
				* result
				+ ((oidMatrizAlternativo == null) ? 0 : oidMatrizAlternativo
						.hashCode());
		result = prime
				* result
				+ ((oidMatrizPrincipal == null) ? 0 : oidMatrizPrincipal
						.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatrizAlternativo other = (MatrizAlternativo) obj;
		if (indicadorActivo == null) {
			if (other.indicadorActivo != null)
				return false;
		} else if (!indicadorActivo.equals(other.indicadorActivo))
			return false;
		if (indicadorMensaje == null) {
			if (other.indicadorMensaje != null)
				return false;
		} else if (!indicadorMensaje.equals(other.indicadorMensaje))
			return false;
		if (numeroOrden == null) {
			if (other.numeroOrden != null)
				return false;
		} else if (!numeroOrden.equals(other.numeroOrden))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (oidMatrizAlternativo == null) {
			if (other.oidMatrizAlternativo != null)
				return false;
		} else if (!oidMatrizAlternativo.equals(other.oidMatrizAlternativo))
			return false;
		if (oidMatrizPrincipal == null) {
			if (other.oidMatrizPrincipal != null)
				return false;
		} else if (!oidMatrizPrincipal.equals(other.oidMatrizPrincipal))
			return false;
		return true;
	}
	
	
	
	
	

	
	
	
	
	
	
	
	
	
}
