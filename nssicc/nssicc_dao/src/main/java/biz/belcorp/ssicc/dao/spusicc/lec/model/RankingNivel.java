/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.lec.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class RankingNivel extends AuditableBaseObject {
	
	private static final long serialVersionUID = -1641495111325698548L;

	private String codigoPais;
	private String codigoPrograma;
	private String codigoTipoRanking;
	private String numeroSecuencia;
	private String codigoNivel;	
	private String indicadorActivo;
	
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
	 * @return the codigoPrograma
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	/**
	 * @param codigoPrograma the codigoPrograma to set
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	/**
	 * @return the codigoTipoRanking
	 */
	public String getCodigoTipoRanking() {
		return codigoTipoRanking;
	}
	/**
	 * @param codigoTipoRanking the codigoTipoRanking to set
	 */
	public void setCodigoTipoRanking(String codigoTipoRanking) {
		this.codigoTipoRanking = codigoTipoRanking;
	}
	/**
	 * @return the numeroSecuencia
	 */
	public String getNumeroSecuencia() {
		return numeroSecuencia;
	}
	/**
	 * @param numeroSecuencia the numeroSecuencia to set
	 */
	public void setNumeroSecuencia(String numeroSecuencia) {
		this.numeroSecuencia = numeroSecuencia;
	}
	/**
	 * @return the codigoNivel
	 */
	public String getCodigoNivel() {
		return codigoNivel;
	}
	/**
	 * @param codigoNivel the codigoNivel to set
	 */
	public void setCodigoNivel(String codigoNivel) {
		this.codigoNivel = codigoNivel;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 13;
		result = prime * result
				+ ((codigoNivel == null) ? 0 : codigoNivel.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime
				* result
				+ ((codigoTipoRanking == null) ? 0 : codigoTipoRanking
						.hashCode());
		result = prime * result
				+ ((indicadorActivo == null) ? 0 : indicadorActivo.hashCode());
		result = prime * result
				+ ((numeroSecuencia == null) ? 0 : numeroSecuencia.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		RankingNivel other = (RankingNivel) obj;
		if (codigoNivel == null) {
			if (other.codigoNivel != null)
				return false;
		} else if (!codigoNivel.equals(other.codigoNivel))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (codigoPrograma == null) {
			if (other.codigoPrograma != null)
				return false;
		} else if (!codigoPrograma.equals(other.codigoPrograma))
			return false;
		if (codigoTipoRanking == null) {
			if (other.codigoTipoRanking != null)
				return false;
		} else if (!codigoTipoRanking.equals(other.codigoTipoRanking))
			return false;
		if (indicadorActivo == null) {
			if (other.indicadorActivo != null)
				return false;
		} else if (!indicadorActivo.equals(other.indicadorActivo))
			return false;
		if (numeroSecuencia == null) {
			if (other.numeroSecuencia != null)
				return false;
		} else if (!numeroSecuencia.equals(other.numeroSecuencia))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RankingNivel [codigoNivel=" + codigoNivel + ", codigoPais="
				+ codigoPais + ", codigoPrograma=" + codigoPrograma
				+ ", codigoTipoRanking=" + codigoTipoRanking
				+ ", indicadorActivo=" + indicadorActivo + ", numeroSecuencia="
				+ numeroSecuencia + "]";
	}
	
}
