/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pre.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.BaseObject;

/**
 * @author Sigcomt
 *
 */
public class ProductoMatriz extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8345333773154408751L;
	private String cuv;
	private String codigoSAP;
	private String descripcion;
	private String oidMatriz;
	private String flagRecuperableExiste;
			
	/**
	 * @return the cuv
	 */
	public String getCuv() {
		return cuv;
	}

	/**
	 * @param cuv the cuv to set
	 */
	public void setCuv(String cuv) {
		this.cuv = cuv;
	}

	/**
	 * @return the codigoSAP
	 */
	public String getCodigoSAP() {
		return codigoSAP;
	}

	/**
	 * @param codigoSAP the codigoSAP to set
	 */
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the oidMatriz
	 */
	public String getOidMatriz() {
		return oidMatriz;
	}

	/**
	 * @param oidMatriz the oidMatriz to set
	 */
	public void setOidMatriz(String oidMatriz) {
		this.oidMatriz = oidMatriz;
	}

	/**
	 * @return the flagRecuperableExiste
	 */
	public String getFlagRecuperableExiste() {
		return flagRecuperableExiste;
	}

	/**
	 * @param flagRecuperableExiste the flagRecuperableExiste to set
	 */
	public void setFlagRecuperableExiste(String flagRecuperableExiste) {
		this.flagRecuperableExiste = flagRecuperableExiste;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductoMatriz [cuv=" + cuv + ", codigoSAP=" + codigoSAP
				+ ", descripcion=" + descripcion + ", oidMatriz=" + oidMatriz
				+ ", flagRecuperableExiste=" + flagRecuperableExiste + "]";
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
		ProductoMatriz other = (ProductoMatriz) obj;
		if (codigoSAP == null) {
			if (other.codigoSAP != null)
				return false;
		} else if (!codigoSAP.equals(other.codigoSAP))
			return false;
		if (cuv == null) {
			if (other.cuv != null)
				return false;
		} else if (!cuv.equals(other.cuv))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (flagRecuperableExiste == null) {
			if (other.flagRecuperableExiste != null)
				return false;
		} else if (!flagRecuperableExiste.equals(other.flagRecuperableExiste))
			return false;
		if (oidMatriz == null) {
			if (other.oidMatriz != null)
				return false;
		} else if (!oidMatriz.equals(other.oidMatriz))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoSAP == null) ? 0 : codigoSAP.hashCode());
		result = prime * result + ((cuv == null) ? 0 : cuv.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime
				* result
				+ ((flagRecuperableExiste == null) ? 0 : flagRecuperableExiste
						.hashCode());
		result = prime * result
				+ ((oidMatriz == null) ? 0 : oidMatriz.hashCode());
		return result;
	}

}
