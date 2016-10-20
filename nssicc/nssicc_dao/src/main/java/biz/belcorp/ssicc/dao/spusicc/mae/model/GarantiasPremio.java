package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;


/**
 * @author <a href="kgomez@sigcomt.com">Karina Gomez</a>
 *
 */

public class GarantiasPremio extends AuditableBaseObject implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String codigoPais;
	private String numDias;
	private String indicadorRegistro;
	private String codigoSAP;
	
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
	 * @return the numDias
	 */
	public String getNumDias() {
		return numDias;
	}
	/**
	 * @param numDias the numDias to set
	 * 
	 */
	public void setNumDias(String numDias) {
		this.numDias = numDias;
	}
	
	/**
	 * @return the indicadorRegistro
	 */
	public String getIndicadorRegistro() {
		return indicadorRegistro;
	}
	
	/**
	 * @param indicadorRegistro the indicadorRegistro to set
	 */
	public void setIndicadorRegistro(String indicadorRegistro) {
		this.indicadorRegistro = indicadorRegistro;
	}
	
	/**
	 * @return the codigoSAP
	 */
	public String getCodigoSAP() {
		return codigoSAP;
	}
	
	/**
	 * @param codigoSAP the codigoSAP to set
	 * 
	 */
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "TipoBloqueo [codigoPais=" + codigoPais + ", codigoSAP=" + codigoSAP
				+ ", indicadorRegistro=" + indicadorRegistro
				+ ", numDias=" + numDias  + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GarantiasPremio other = (GarantiasPremio) obj;
		if (codigoSAP == null) {
			if (other.codigoSAP != null)
				return false;
		} else if (!codigoSAP.equals(other.codigoSAP))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (numDias == null) {
			if (other.numDias != null)
				return false;
		} else if (!numDias.equals(other.numDias))
			return false;
		if (indicadorRegistro == null) {
			if (other.indicadorRegistro != null)
				return false;
		} else if (!indicadorRegistro
				.equals(other.indicadorRegistro))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoSAP == null) ? 0 : codigoSAP.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime
				* result
				+ ((indicadorRegistro == null) ? 0
						: indicadorRegistro.hashCode());
		result = prime * result
				+ ((numDias == null) ? 0 : numDias.hashCode());
		
		return result;
	}

}
