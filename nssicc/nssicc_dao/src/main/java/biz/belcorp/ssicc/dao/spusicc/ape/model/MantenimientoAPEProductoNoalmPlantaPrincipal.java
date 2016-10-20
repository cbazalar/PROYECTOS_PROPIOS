/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.BaseObject;


/**
 * @author csoberon
 *
 */
public class MantenimientoAPEProductoNoalmPlantaPrincipal extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2518606788863820217L;
	private String codigoPais;
	private String codigoSAP;
	private String codigoProducto;
	private String descripcionProducto;
	private String codigoEmpresaExterna;
	private String indicadorImprime;
	
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
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}
	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	/**
	 * @return the descripcionProducto
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	/**
	 * @param descripcionProducto the descripcionProducto to set
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	/**
	 * @return the codigoEmpresaExterna
	 */
	public String getCodigoEmpresaExterna() {
		return codigoEmpresaExterna;
	}
	/**
	 * @param codigoEmpresaExterna the codigoEmpresaExterna to set
	 */
	public void setCodigoEmpresaExterna(String codigoEmpresaExterna) {
		this.codigoEmpresaExterna = codigoEmpresaExterna;
	}
	/**
	 * @return the indicadorImprime
	 */
	public String getIndicadorImprime() {
		return indicadorImprime;
	}
	/**
	 * @param indicadorImprime the indicadorImprime to set
	 */
	public void setIndicadorImprime(String indicadorImprime) {
		this.indicadorImprime = indicadorImprime;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		MantenimientoAPEProductoNoalmPlantaPrincipal other = (MantenimientoAPEProductoNoalmPlantaPrincipal) obj;
		if (codigoEmpresaExterna == null) {
			if (other.codigoEmpresaExterna != null)
				return false;
		} else if (!codigoEmpresaExterna.equals(other.codigoEmpresaExterna))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (codigoProducto == null) {
			if (other.codigoProducto != null)
				return false;
		} else if (!codigoProducto.equals(other.codigoProducto))
			return false;
		if (codigoSAP == null) {
			if (other.codigoSAP != null)
				return false;
		} else if (!codigoSAP.equals(other.codigoSAP))
			return false;
		if (descripcionProducto == null) {
			if (other.descripcionProducto != null)
				return false;
		} else if (!descripcionProducto.equals(other.descripcionProducto))
			return false;
		if (indicadorImprime == null) {
			if (other.indicadorImprime != null)
				return false;
		} else if (!indicadorImprime.equals(other.indicadorImprime))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 13;
		result = prime
				* result
				+ ((codigoEmpresaExterna == null) ? 0 : codigoEmpresaExterna
						.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoProducto == null) ? 0 : codigoProducto.hashCode());
		result = prime * result
				+ ((codigoSAP == null) ? 0 : codigoSAP.hashCode());
		result = prime
				* result
				+ ((descripcionProducto == null) ? 0 : descripcionProducto
						.hashCode());
		result = prime
				* result
				+ ((indicadorImprime == null) ? 0 : indicadorImprime.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "MantenimientoAPEProductoNoalmPlantaPrincipal [codigoEmpresaExterna="
				+ codigoEmpresaExterna
				+ ", codigoPais="
				+ codigoPais
				+ ", codigoProducto="
				+ codigoProducto
				+ ", codigoSAP="
				+ codigoSAP
				+ ", descripcionProducto="
				+ descripcionProducto
				+ ", indicadorImprime=" + indicadorImprime + "]";
	}
		
}
