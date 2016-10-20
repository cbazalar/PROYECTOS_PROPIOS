package biz.belcorp.ssicc.dao.spusicc.pej.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author aoviedo
 *
 */
public class Porcentaje extends AuditableBaseObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoPrograma;
	private String codigoNivelPorcentaje;
	private String codigoRangoPorcentaje;
	private String codigoGrupoPorcentaje;
	private String codigoFasePorcentaje;
	private String valorPorcentajeComision;
	private String estadoPorcentaje;
	
	private String descripcionNivelPorcentaje;
	private String descripcionRangoPorcentaje;
	private String descripcionGrupoPorcentaje;
	
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
	 * @return the codigoNivelPorcentaje
	 */
	public String getCodigoNivelPorcentaje() {
		return codigoNivelPorcentaje;
	}
	/**
	 * @param codigoNivelPorcentaje the codigoNivelPorcentaje to set
	 */
	public void setCodigoNivelPorcentaje(String codigoNivelPorcentaje) {
		this.codigoNivelPorcentaje = codigoNivelPorcentaje;
	}
	/**
	 * @return the codigoRangoPorcentaje
	 */
	public String getCodigoRangoPorcentaje() {
		return codigoRangoPorcentaje;
	}
	/**
	 * @param codigoRangoPorcentaje the codigoRangoPorcentaje to set
	 */
	public void setCodigoRangoPorcentaje(String codigoRangoPorcentaje) {
		this.codigoRangoPorcentaje = codigoRangoPorcentaje;
	}
	/**
	 * @return the codigoGrupoPorcentaje
	 */
	public String getCodigoGrupoPorcentaje() {
		return codigoGrupoPorcentaje;
	}
	/**
	 * @param codigoGrupoPorcentaje the codigoGrupoPorcentaje to set
	 */
	public void setCodigoGrupoPorcentaje(String codigoGrupoPorcentaje) {
		this.codigoGrupoPorcentaje = codigoGrupoPorcentaje;
	}
	/**
	 * @return the codigoFasePorcentaje
	 */
	public String getCodigoFasePorcentaje() {
		return codigoFasePorcentaje;
	}
	/**
	 * @param codigoFasePorcentaje the codigoFasePorcentaje to set
	 */
	public void setCodigoFasePorcentaje(String codigoFasePorcentaje) {
		this.codigoFasePorcentaje = codigoFasePorcentaje;
	}
	/**
	 * @return the valorPorcentajeComision
	 */
	public String getValorPorcentajeComision() {
		return valorPorcentajeComision;
	}
	/**
	 * @param valorPorcentajeComision the valorPorcentajeComision to set
	 */
	public void setValorPorcentajeComision(String valorPorcentajeComision) {
		this.valorPorcentajeComision = valorPorcentajeComision;
	}
	/**
	 * @return the estadoPorcentaje
	 */
	public String getEstadoPorcentaje() {
		return estadoPorcentaje;
	}
	/**
	 * @param estadoPorcentaje the estadoPorcentaje to set
	 */
	public void setEstadoPorcentaje(String estadoPorcentaje) {
		this.estadoPorcentaje = estadoPorcentaje;
	}
	/**
	 * @return the descripcionNivelPorcentaje
	 */
	public String getDescripcionNivelPorcentaje() {
		return descripcionNivelPorcentaje;
	}
	/**
	 * @param descripcionNivelPorcentaje the descripcionNivelPorcentaje to set
	 */
	public void setDescripcionNivelPorcentaje(String descripcionNivelPorcentaje) {
		this.descripcionNivelPorcentaje = descripcionNivelPorcentaje;
	}
	/**
	 * @return the descripcionRangoPorcentaje
	 */
	public String getDescripcionRangoPorcentaje() {
		return descripcionRangoPorcentaje;
	}
	/**
	 * @param descripcionRangoPorcentaje the descripcionRangoPorcentaje to set
	 */
	public void setDescripcionRangoPorcentaje(String descripcionRangoPorcentaje) {
		this.descripcionRangoPorcentaje = descripcionRangoPorcentaje;
	}
	/**
	 * @return the descripcionGrupoPorcentaje
	 */
	public String getDescripcionGrupoPorcentaje() {
		return descripcionGrupoPorcentaje;
	}
	/**
	 * @param descripcionGrupoPorcentaje the descripcionGrupoPorcentaje to set
	 */
	public void setDescripcionGrupoPorcentaje(String descripcionGrupoPorcentaje) {
		this.descripcionGrupoPorcentaje = descripcionGrupoPorcentaje;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Porcentaje [codigoFasePorcentaje=" + codigoFasePorcentaje
				+ ", codigoGrupoPorcentaje=" + codigoGrupoPorcentaje
				+ ", codigoNivelPorcentaje=" + codigoNivelPorcentaje
				+ ", codigoPais=" + codigoPais + ", codigoPrograma="
				+ codigoPrograma + ", codigoRangoPorcentaje="
				+ codigoRangoPorcentaje + ", descripcionGrupoPorcentaje="
				+ descripcionGrupoPorcentaje + ", descripcionNivelPorcentaje="
				+ descripcionNivelPorcentaje + ", descripcionRangoPorcentaje="
				+ descripcionRangoPorcentaje + ", estadoPorcentaje="
				+ estadoPorcentaje + ", valorPorcentajeComision="
				+ valorPorcentajeComision + "]";
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
				+ ((codigoFasePorcentaje == null) ? 0 : codigoFasePorcentaje
						.hashCode());
		result = prime
				* result
				+ ((codigoGrupoPorcentaje == null) ? 0 : codigoGrupoPorcentaje
						.hashCode());
		result = prime
				* result
				+ ((codigoNivelPorcentaje == null) ? 0 : codigoNivelPorcentaje
						.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime
				* result
				+ ((codigoRangoPorcentaje == null) ? 0 : codigoRangoPorcentaje
						.hashCode());
		result = prime
				* result
				+ ((descripcionGrupoPorcentaje == null) ? 0
						: descripcionGrupoPorcentaje.hashCode());
		result = prime
				* result
				+ ((descripcionNivelPorcentaje == null) ? 0
						: descripcionNivelPorcentaje.hashCode());
		result = prime
				* result
				+ ((descripcionRangoPorcentaje == null) ? 0
						: descripcionRangoPorcentaje.hashCode());
		result = prime
				* result
				+ ((estadoPorcentaje == null) ? 0 : estadoPorcentaje.hashCode());
		result = prime
				* result
				+ ((valorPorcentajeComision == null) ? 0
						: valorPorcentajeComision.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj==null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Porcentaje other = (Porcentaje) obj;
		if (codigoFasePorcentaje == null) {
			if (other.codigoFasePorcentaje != null)
				return false;
		} else if (!codigoFasePorcentaje.equals(other.codigoFasePorcentaje))
			return false;
		if (codigoGrupoPorcentaje == null) {
			if (other.codigoGrupoPorcentaje != null)
				return false;
		} else if (!codigoGrupoPorcentaje.equals(other.codigoGrupoPorcentaje))
			return false;
		if (codigoNivelPorcentaje == null) {
			if (other.codigoNivelPorcentaje != null)
				return false;
		} else if (!codigoNivelPorcentaje.equals(other.codigoNivelPorcentaje))
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
		if (codigoRangoPorcentaje == null) {
			if (other.codigoRangoPorcentaje != null)
				return false;
		} else if (!codigoRangoPorcentaje.equals(other.codigoRangoPorcentaje))
			return false;
		if (descripcionGrupoPorcentaje == null) {
			if (other.descripcionGrupoPorcentaje != null)
				return false;
		} else if (!descripcionGrupoPorcentaje
				.equals(other.descripcionGrupoPorcentaje))
			return false;
		if (descripcionNivelPorcentaje == null) {
			if (other.descripcionNivelPorcentaje != null)
				return false;
		} else if (!descripcionNivelPorcentaje
				.equals(other.descripcionNivelPorcentaje))
			return false;
		if (descripcionRangoPorcentaje == null) {
			if (other.descripcionRangoPorcentaje != null)
				return false;
		} else if (!descripcionRangoPorcentaje
				.equals(other.descripcionRangoPorcentaje))
			return false;
		if (estadoPorcentaje == null) {
			if (other.estadoPorcentaje != null)
				return false;
		} else if (!estadoPorcentaje.equals(other.estadoPorcentaje))
			return false;
		if (valorPorcentajeComision == null) {
			if (other.valorPorcentajeComision != null)
				return false;
		} else if (!valorPorcentajeComision
				.equals(other.valorPorcentajeComision))
			return false;
		return true;
	}
	
}
