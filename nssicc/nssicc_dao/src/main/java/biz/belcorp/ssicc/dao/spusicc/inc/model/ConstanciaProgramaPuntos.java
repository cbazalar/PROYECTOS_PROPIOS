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
public class ConstanciaProgramaPuntos extends AuditableBaseObject implements Serializable {

	private String codigoPais;
	private String codigo;
	private String campanyaInicio;
	private String campanyaFin;
	private String codigoMultiPunto;
	private String montoMinimo;
	private String indicadorActivo;
	private String evaluacionPrograma;
	private String campanyaMaxima;
		
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
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	 * @return the codigoMultiPunto
	 */
	public String getCodigoMultiPunto() {
		return codigoMultiPunto;
	}

	/**
	 * @param codigoMultiPunto the codigoMultiPunto to set
	 */
	public void setCodigoMultiPunto(String codigoMultiPunto) {
		this.codigoMultiPunto = codigoMultiPunto;
	}

	/**
	 * @return the montoMinimo
	 */
	public String getMontoMinimo() {
		return montoMinimo;
	}

	/**
	 * @param montoMinimo the montoMinimo to set
	 */
	public void setMontoMinimo(String montoMinimo) {
		this.montoMinimo = montoMinimo;
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
		ConstanciaProgramaPuntos other = (ConstanciaProgramaPuntos) obj;
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
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (codigoMultiPunto == null) {
			if (other.codigoMultiPunto != null)
				return false;
		} else if (!codigoMultiPunto.equals(other.codigoMultiPunto))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (indicadorActivo == null) {
			if (other.indicadorActivo != null)
				return false;
		} else if (!indicadorActivo.equals(other.indicadorActivo))
			return false;
		if (montoMinimo == null) {
			if (other.montoMinimo != null)
				return false;
		} else if (!montoMinimo.equals(other.montoMinimo))
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
				+ ((campanyaFin == null) ? 0 : campanyaFin.hashCode());
		result = prime * result
				+ ((campanyaInicio == null) ? 0 : campanyaInicio.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime
				* result
				+ ((codigoMultiPunto == null) ? 0 : codigoMultiPunto.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((indicadorActivo == null) ? 0 : indicadorActivo.hashCode());
		result = prime * result
				+ ((montoMinimo == null) ? 0 : montoMinimo.hashCode());
		return result;
	}

	/**
	 * @return the evaluacionPrograma
	 */
	public String getEvaluacionPrograma() {
		return evaluacionPrograma;
	}

	/**
	 * @param evaluacionPrograma the evaluacionPrograma to set
	 */
	public void setEvaluacionPrograma(String evaluacionPrograma) {
		this.evaluacionPrograma = evaluacionPrograma;
	}

	/**
	 * @return the campanyaMaxima
	 */
	public String getCampanyaMaxima() {
		return campanyaMaxima;
	}

	/**
	 * @param campanyaMaxima the campanyaMaxima to set
	 */
	public void setCampanyaMaxima(String campanyaMaxima) {
		this.campanyaMaxima = campanyaMaxima;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConstanciaProgramaPuntos [codigoPais=" + codigoPais
				+ ", codigo=" + codigo + ", campanyaInicio=" + campanyaInicio
				+ ", campanyaFin=" + campanyaFin + ", codigoMultiPunto="
				+ codigoMultiPunto + ", montoMinimo=" + montoMinimo
				+ ", indicadorActivo=" + indicadorActivo
				+ ", evaluacionPrograma=" + evaluacionPrograma
				+ ", campanyaMaxima=" + campanyaMaxima + "]";
	}

}
