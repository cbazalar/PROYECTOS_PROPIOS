package biz.belcorp.ssicc.dao.spusicc.let.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author aoviedo
 *
 */
public class Premios extends AuditableBaseObject {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPrograma;
	private String campanyaPremio;
	private String codigoNivelPremio;
	private String descripcionNivelPremio;
	private String variablePremio;
	private String codigoPremio;
	private String descripcionPremio;
	private String precioPremio;
	
	private String correlativo;
	private String estado;
	
	private String codigoPremioAnterior;
	
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
	 * @return the campanyaPremio
	 */
	public String getCampanyaPremio() {
		return campanyaPremio;
	}
	/**
	 * @param campanyaPremio the campanyaPremio to set
	 */
	public void setCampanyaPremio(String campanyaPremio) {
		this.campanyaPremio = campanyaPremio;
	}
	/**
	 * @return the codigoNivelPremio
	 */
	public String getCodigoNivelPremio() {
		return codigoNivelPremio;
	}
	/**
	 * @param codigoNivelPremio the codigoNivelPremio to set
	 */
	public void setCodigoNivelPremio(String codigoNivelPremio) {
		this.codigoNivelPremio = codigoNivelPremio;
	}
	/**
	 * @return the descripcionNivelPremio
	 */
	public String getDescripcionNivelPremio() {
		return descripcionNivelPremio;
	}
	/**
	 * @param descripcionNivelPremio the descripcionNivelPremio to set
	 */
	public void setDescripcionNivelPremio(String descripcionNivelPremio) {
		this.descripcionNivelPremio = descripcionNivelPremio;
	}
	/**
	 * @return the variablePremio
	 */
	public String getVariablePremio() {
		return variablePremio;
	}
	/**
	 * @param variablePremio the variablePremio to set
	 */
	public void setVariablePremio(String variablePremio) {
		this.variablePremio = variablePremio;
	}
	/**
	 * @return the codigoPremio
	 */
	public String getCodigoPremio() {
		return codigoPremio;
	}
	/**
	 * @param codigoPremio the codigoPremio to set
	 */
	public void setCodigoPremio(String codigoPremio) {
		this.codigoPremio = codigoPremio;
	}
	/**
	 * @return the descripcionPremio
	 */
	public String getDescripcionPremio() {
		return descripcionPremio;
	}
	/**
	 * @param descripcionPremio the descripcionPremio to set
	 */
	public void setDescripcionPremio(String descripcionPremio) {
		this.descripcionPremio = descripcionPremio;
	}
	/**
	 * @return the precioPremio
	 */
	public String getPrecioPremio() {
		return precioPremio;
	}
	/**
	 * @param precioPremio the precioPremio to set
	 */
	public void setPrecioPremio(String precioPremio) {
		this.precioPremio = precioPremio;
	}
	/**
	 * @return the correlativo
	 */
	public String getCorrelativo() {
		return correlativo;
	}
	/**
	 * @param correlativo the correlativo to set
	 */
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return the codigoPremioAnterior
	 */
	public String getCodigoPremioAnterior() {
		return codigoPremioAnterior;
	}
	/**
	 * @param codigoPremioAnterior the codigoPremioAnterior to set
	 */
	public void setCodigoPremioAnterior(String codigoPremioAnterior) {
		this.codigoPremioAnterior = codigoPremioAnterior;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((campanyaPremio == null) ? 0 : campanyaPremio.hashCode());
		result = prime
				* result
				+ ((codigoNivelPremio == null) ? 0 : codigoNivelPremio
						.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPremio == null) ? 0 : codigoPremio.hashCode());
		result = prime
				* result
				+ ((codigoPremioAnterior == null) ? 0 : codigoPremioAnterior
						.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime * result
				+ ((correlativo == null) ? 0 : correlativo.hashCode());
		result = prime
				* result
				+ ((descripcionNivelPremio == null) ? 0
						: descripcionNivelPremio.hashCode());
		result = prime
				* result
				+ ((descripcionPremio == null) ? 0 : descripcionPremio
						.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result
				+ ((precioPremio == null) ? 0 : precioPremio.hashCode());
		result = prime * result
				+ ((variablePremio == null) ? 0 : variablePremio.hashCode());
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
		Premios other = (Premios) obj;
		if (campanyaPremio == null) {
			if (other.campanyaPremio != null)
				return false;
		} else if (!campanyaPremio.equals(other.campanyaPremio))
			return false;
		if (codigoNivelPremio == null) {
			if (other.codigoNivelPremio != null)
				return false;
		} else if (!codigoNivelPremio.equals(other.codigoNivelPremio))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (codigoPremio == null) {
			if (other.codigoPremio != null)
				return false;
		} else if (!codigoPremio.equals(other.codigoPremio))
			return false;
		if (codigoPremioAnterior == null) {
			if (other.codigoPremioAnterior != null)
				return false;
		} else if (!codigoPremioAnterior.equals(other.codigoPremioAnterior))
			return false;
		if (codigoPrograma == null) {
			if (other.codigoPrograma != null)
				return false;
		} else if (!codigoPrograma.equals(other.codigoPrograma))
			return false;
		if (correlativo == null) {
			if (other.correlativo != null)
				return false;
		} else if (!correlativo.equals(other.correlativo))
			return false;
		if (descripcionNivelPremio == null) {
			if (other.descripcionNivelPremio != null)
				return false;
		} else if (!descripcionNivelPremio.equals(other.descripcionNivelPremio))
			return false;
		if (descripcionPremio == null) {
			if (other.descripcionPremio != null)
				return false;
		} else if (!descripcionPremio.equals(other.descripcionPremio))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (precioPremio == null) {
			if (other.precioPremio != null)
				return false;
		} else if (!precioPremio.equals(other.precioPremio))
			return false;
		if (variablePremio == null) {
			if (other.variablePremio != null)
				return false;
		} else if (!variablePremio.equals(other.variablePremio))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Premios [codigoPais=" + codigoPais + ", codigoPrograma="
				+ codigoPrograma + ", campanyaPremio=" + campanyaPremio
				+ ", codigoNivelPremio=" + codigoNivelPremio
				+ ", descripcionNivelPremio=" + descripcionNivelPremio
				+ ", variablePremio=" + variablePremio + ", codigoPremio="
				+ codigoPremio + ", descripcionPremio=" + descripcionPremio
				+ ", precioPremio=" + precioPremio + ", correlativo="
				+ correlativo + ", estado=" + estado
				+ ", codigoPremioAnterior=" + codigoPremioAnterior + "]";
	}

}