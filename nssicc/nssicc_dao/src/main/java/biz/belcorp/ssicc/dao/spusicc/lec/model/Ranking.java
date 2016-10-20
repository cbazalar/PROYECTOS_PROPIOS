/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.lec.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class Ranking extends AuditableBaseObject {
	
	private static final long serialVersionUID = 9086905498878178199L;
	
	private String codigoPais;
	private String codigoPrograma;
	private String codigoTipoRanking;
	private String numeroSecuencia;
	private String campanyaInicio;
	private String campanyaFin;
	private String numeroCampanyasCumplimentoPedido;
	private String codigoTipoMedicion;	
	private String indicadorActivo;
	
	private String descripcionTipoRanking;
	
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
	 * @return the campanyaInicio
	 */
	public String getCampanyaInicio() {
		return campanyaInicio;
	}

	/**
	 * @param campanyaInicio the campanyaInicio to set
	 */
	public void setCampanyaInicio(String campanyaInicio) {
		this.campanyaInicio = campanyaInicio;
	}

	/**
	 * @return the campanyaFin
	 */
	public String getCampanyaFin() {
		return campanyaFin;
	}

	/**
	 * @param campanyaFin the campanyaFin to set
	 */
	public void setCampanyaFin(String campanyaFin) {
		this.campanyaFin = campanyaFin;
	}

	/**
	 * @return the numeroCampanyasCumplimentoPedido
	 */
	public String getNumeroCampanyasCumplimentoPedido() {
		return numeroCampanyasCumplimentoPedido;
	}

	/**
	 * @param numeroCampanyasCumplimentoPedido the numeroCampanyasCumplimentoPedido to set
	 */
	public void setNumeroCampanyasCumplimentoPedido(
			String numeroCampanyasCumplimentoPedido) {
		this.numeroCampanyasCumplimentoPedido = numeroCampanyasCumplimentoPedido;
	}

	/**
	 * @return the codigoTipoMedicion
	 */
	public String getCodigoTipoMedicion() {
		return codigoTipoMedicion;
	}

	/**
	 * @param codigoTipoMedicion the codigoTipoMedicion to set
	 */
	public void setCodigoTipoMedicion(String codigoTipoMedicion) {
		this.codigoTipoMedicion = codigoTipoMedicion;
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
	 * @return the descripcionTipoRanking
	 */
	public String getDescripcionTipoRanking() {
		return descripcionTipoRanking;
	}

	/**
	 * @param descripcionTipoRanking the descripcionTipoRanking to set
	 */
	public void setDescripcionTipoRanking(String descripcionTipoRanking) {
		this.descripcionTipoRanking = descripcionTipoRanking;
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
		Ranking other = (Ranking) obj;
		if (campanyaFin == null) {
			if (other.campanyaFin != null)
				return false;
		} else if (!campanyaFin.equals(other.campanyaFin))
			return false;
		if (campanyaInicio == null) {
			if (other.campanyaInicio != null)
				return false;
		} else if (!campanyaInicio.equals(other.campanyaInicio))
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
		if (codigoTipoMedicion == null) {
			if (other.codigoTipoMedicion != null)
				return false;
		} else if (!codigoTipoMedicion.equals(other.codigoTipoMedicion))
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
		if (numeroCampanyasCumplimentoPedido == null) {
			if (other.numeroCampanyasCumplimentoPedido != null)
				return false;
		} else if (!numeroCampanyasCumplimentoPedido
				.equals(other.numeroCampanyasCumplimentoPedido))
			return false;
		if (numeroSecuencia == null) {
			if (other.numeroSecuencia != null)
				return false;
		} else if (!numeroSecuencia.equals(other.numeroSecuencia))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 13;
		result = prime * result
				+ ((campanyaFin == null) ? 0 : campanyaFin.hashCode());
		result = prime * result
				+ ((campanyaInicio == null) ? 0 : campanyaInicio.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime
				* result
				+ ((codigoTipoMedicion == null) ? 0 : codigoTipoMedicion
						.hashCode());
		result = prime
				* result
				+ ((codigoTipoRanking == null) ? 0 : codigoTipoRanking
						.hashCode());
		result = prime * result
				+ ((indicadorActivo == null) ? 0 : indicadorActivo.hashCode());
		result = prime
				* result
				+ ((numeroCampanyasCumplimentoPedido == null) ? 0
						: numeroCampanyasCumplimentoPedido.hashCode());
		result = prime * result
				+ ((numeroSecuencia == null) ? 0 : numeroSecuencia.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ranking [campanyaFin=" + campanyaFin + ", campanyaInicio="
				+ campanyaInicio + ", codigoPais=" + codigoPais
				+ ", codigoPrograma=" + codigoPrograma
				+ ", codigoTipoMedicion=" + codigoTipoMedicion
				+ ", codigoTipoRanking=" + codigoTipoRanking
				+ ", indicadorActivo=" + indicadorActivo
				+ ", numeroCampanyasCumplimentoPedido="
				+ numeroCampanyasCumplimentoPedido + ", numeroSecuencia="
				+ numeroSecuencia + "]";
	}

}
