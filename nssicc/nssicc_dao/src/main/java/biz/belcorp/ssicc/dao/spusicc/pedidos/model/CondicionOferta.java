/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Ivan Tocto Jaimes
 *
 */
public class CondicionOferta extends AuditableBaseObject implements
		Serializable {

	private String oid;
	private String oidOferta;
	private String numero;
	private String factorCuadre;
	private String oidTipoCuadre;
	
	
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
	 * @return the oidOferta
	 */
	public String getOidOferta() {
		return oidOferta;
	}

	/**
	 * @param oidOferta the oidOferta to set
	 */
	public void setOidOferta(String oidOferta) {
		this.oidOferta = oidOferta;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the factorCuadre
	 */
	public String getFactorCuadre() {
		return factorCuadre;
	}

	/**
	 * @param factorCuadre the factorCuadre to set
	 */
	public void setFactorCuadre(String factorCuadre) {
		this.factorCuadre = factorCuadre;
	}


	/**
	 * @return the oidTipoCuadre
	 */
	public String getOidTipoCuadre() {
		return oidTipoCuadre;
	}

	/**
	 * @param oidTipoCuadre the oidTipoCuadre to set
	 */
	public void setOidTipoCuadre(String oidTipoCuadre) {
		this.oidTipoCuadre = oidTipoCuadre;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CondicionOferta [oid=" + oid + ", oidOferta=" + oidOferta
				+ ", numero=" + numero + ", factorCuadre=" + factorCuadre
				+ ", oidTipoCuadre=" + oidTipoCuadre + "]";
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
		CondicionOferta other = (CondicionOferta) obj;
		if (factorCuadre == null) {
			if (other.factorCuadre != null)
				return false;
		} else if (!factorCuadre.equals(other.factorCuadre))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (oidOferta == null) {
			if (other.oidOferta != null)
				return false;
		} else if (!oidOferta.equals(other.oidOferta))
			return false;
		if (oidTipoCuadre == null) {
			if (other.oidTipoCuadre != null)
				return false;
		} else if (!oidTipoCuadre.equals(other.oidTipoCuadre))
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
				+ ((factorCuadre == null) ? 0 : factorCuadre.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result
				+ ((oidOferta == null) ? 0 : oidOferta.hashCode());
		result = prime * result
				+ ((oidTipoCuadre == null) ? 0 : oidTipoCuadre.hashCode());
		return result;
	}

}
