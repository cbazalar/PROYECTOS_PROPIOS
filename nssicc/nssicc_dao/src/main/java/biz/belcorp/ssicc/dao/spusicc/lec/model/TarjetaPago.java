/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.lec.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="dtorres@sigcomt.com">Diego Torres</a>
 *
 */ 
public class TarjetaPago extends AuditableBaseObject {

	private String codigoTarjeta;
	private String numeroTarjeta;
	private String codigoEstado;
	private String campanaCreacion;

	/**
	 * @return the codigoTarjeta
	 */
	public String getCodigoTarjeta() {
		return codigoTarjeta;
	}
	/**
	 * @param codigoTarjeta the codigoTarjeta to set
	 */
	public void setCodigoTarjeta(String codigoTarjeta) {
		this.codigoTarjeta = codigoTarjeta;
	}
	/**
	 * @return the numeroTarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	/**
	 * @param numeroTarjeta the numeroTarjeta to set
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	/**
	 * @return the codigoEstado
	 */
	public String getCodigoEstado() {
		return codigoEstado;
	}
	/**
	 * @param codigoEstado the codigoEstado to set
	 */
	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}
	/**
	 * @return the campanaCreacion
	 */
	public String getCampanaCreacion() {
		return campanaCreacion;
	}
	/**
	 * @param campanaCreacion the campanaCreacion to set
	 */
	public void setCampanaCreacion(String campanaCreacion) {
		this.campanaCreacion = campanaCreacion;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TarjetaPago [numeroTarjeta=" + numeroTarjeta + ", codigoTarjeta="
				+ codigoTarjeta + ", codigoEstado="
				+ codigoEstado + ", campanaCreacion="
				+ campanaCreacion + "]";
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
		TarjetaPago other = (TarjetaPago) obj;
		if (numeroTarjeta == null) {
			if (other.numeroTarjeta != null)
				return false;
		}
		if (codigoTarjeta == null) {
			if (other.codigoTarjeta != null)
				return false;
		}
		if (codigoEstado == null) {
			if (other.codigoEstado != null)
				return false;
		}
		if (campanaCreacion == null) {
			if (other.campanaCreacion != null)
				return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((numeroTarjeta == null) ? 0
						: numeroTarjeta.hashCode());
		result = prime * result
				+ ((codigoTarjeta == null) ? 0 : codigoTarjeta.hashCode());
		result = prime * result
				+ ((codigoEstado == null) ? 0 : codigoEstado.hashCode());
		result = prime * result
				+ ((campanaCreacion == null) ? 0 : campanaCreacion.hashCode());
		return result;
	}

}
