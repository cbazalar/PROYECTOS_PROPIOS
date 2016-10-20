/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Sigcomt
 *
 */
public class RangoConstanciaProgramaPuntos extends AuditableBaseObject implements Serializable {

	private String codigoPais;
	private String codigoProgramaConstancia;
	private String rangoFinal;
	private String puntosAbono;
	
	private String factorMultiplicador;
	
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
	 * @return the codigoProgramaConstancia
	 */
	public String getCodigoProgramaConstancia() {
		return codigoProgramaConstancia;
	}
	/**
	 * @param codigoProgramaConstancia the codigoProgramaConstancia to set
	 */
	public void setCodigoProgramaConstancia(String codigoProgramaConstancia) {
		this.codigoProgramaConstancia = codigoProgramaConstancia;
	}
	/**
	 * @return the rangoFinal
	 */
	public String getRangoFinal() {
		return rangoFinal;
	}
	/**
	 * @param rangoFinal the rangoFinal to set
	 */
	public void setRangoFinal(String rangoFinal) {
		this.rangoFinal = rangoFinal;
	}
	/**
	 * @return the puntosAbono
	 */
	public String getPuntosAbono() {
		return puntosAbono;
	}
	/**
	 * @param puntosAbono the puntosAbono to set
	 */
	public void setPuntosAbono(String puntosAbono) {
		this.puntosAbono = puntosAbono;
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
		result = prime
				* result
				+ ((codigoProgramaConstancia == null) ? 0
						: codigoProgramaConstancia.hashCode());
		result = prime * result
				+ ((puntosAbono == null) ? 0 : puntosAbono.hashCode());
		result = prime * result
				+ ((rangoFinal == null) ? 0 : rangoFinal.hashCode());
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
		RangoConstanciaProgramaPuntos other = (RangoConstanciaProgramaPuntos) obj;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (codigoProgramaConstancia == null) {
			if (other.codigoProgramaConstancia != null)
				return false;
		} else if (!codigoProgramaConstancia
				.equals(other.codigoProgramaConstancia))
			return false;
		if (puntosAbono == null) {
			if (other.puntosAbono != null)
				return false;
		} else if (!puntosAbono.equals(other.puntosAbono))
			return false;
		if (rangoFinal == null) {
			if (other.rangoFinal != null)
				return false;
		} else if (!rangoFinal.equals(other.rangoFinal))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConstanciaProgramaPuntosRango [codigoPais=" + codigoPais
				+ ", codigoProgramaConstancia=" + codigoProgramaConstancia
				+ ", rangoFinal=" + rangoFinal + ", puntosAbono=" + puntosAbono
				+ ", factorMultiplicador=" + factorMultiplicador + "]";
	}
	/**
	 * @return the factorMultiplicador
	 */
	public String getFactorMultiplicador() {
		return factorMultiplicador;
	}
	/**
	 * @param factorMultiplicador the factorMultiplicador to set
	 */
	public void setFactorMultiplicador(String factorMultiplicador) {
		this.factorMultiplicador = factorMultiplicador;
	}		
}
