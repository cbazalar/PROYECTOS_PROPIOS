/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author pejflorencio
 *
 */
public class PagoBancarioCabecera extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoSociedad;		
	private String codigoCuentaCorrienteBancaria;
	private String codigoTipoOrigen;
	private String numeroLote;
	private String codigoUsuario;
		

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
	 * @return the codigoSociedad
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @param codigoSociedad the codigoSociedad to set
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}

	/**
	 * @return the codigoCuentaCorrienteBancaria
	 */
	public String getCodigoCuentaCorrienteBancaria() {
		return codigoCuentaCorrienteBancaria;
	}

	/**
	 * @param codigoCuentaCorrienteBancaria the codigoCuentaCorrienteBancaria to set
	 */
	public void setCodigoCuentaCorrienteBancaria(String codigoCuentaCorrienteBancaria) {
		this.codigoCuentaCorrienteBancaria = codigoCuentaCorrienteBancaria;
	}

	/**
	 * @return the codigoTipoOrigen
	 */
	public String getCodigoTipoOrigen() {
		return codigoTipoOrigen;
	}

	/**
	 * @param codigoTipoOrigena the codigTipoOrigen to set
	 */
	public void setCodigoTipoOrigen(String codigoTipoOrigen) {
		this.codigoTipoOrigen = codigoTipoOrigen;
	}

	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}


	/**
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
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
		PagoBancarioCabecera other = (PagoBancarioCabecera) obj;
		if (codigoCuentaCorrienteBancaria == null) {
			if (other.codigoCuentaCorrienteBancaria != null)
				return false;
		} else if (!codigoCuentaCorrienteBancaria
				.equals(other.codigoCuentaCorrienteBancaria))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (codigoSociedad == null) {
			if (other.codigoSociedad != null)
				return false;
		} else if (!codigoSociedad.equals(other.codigoSociedad))
			return false;
		if (codigoTipoOrigen == null) {
			if (other.codigoTipoOrigen != null)
				return false;
		} else if (!codigoTipoOrigen.equals(other.codigoTipoOrigen))
			return false;
		if (codigoUsuario == null) {
			if (other.codigoUsuario != null)
				return false;
		} else if (!codigoUsuario.equals(other.codigoUsuario))
			return false;
		if (numeroLote == null) {
			if (other.numeroLote != null)
				return false;
		} else if (!numeroLote.equals(other.numeroLote))
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
		result = prime
				* result
				+ ((codigoCuentaCorrienteBancaria == null) ? 0
						: codigoCuentaCorrienteBancaria.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoSociedad == null) ? 0 : codigoSociedad.hashCode());
		result = prime
				* result
				+ ((codigoTipoOrigen == null) ? 0 : codigoTipoOrigen.hashCode());
		result = prime * result
				+ ((codigoUsuario == null) ? 0 : codigoUsuario.hashCode());
		result = prime * result
				+ ((numeroLote == null) ? 0 : numeroLote.hashCode());
		return result;
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PagoBancarioCabecera [codigoPais=" + codigoPais
				+ ", codigoSociedad=" + codigoSociedad
				+ ", codigoCuentaCorrienteBancaria="
				+ codigoCuentaCorrienteBancaria + ", codigoTipoOrigen="
				+ codigoTipoOrigen + ", numeroLote=" + numeroLote
				+ ", codigoUsuario=" + codigoUsuario + "]";
	}

	

}
