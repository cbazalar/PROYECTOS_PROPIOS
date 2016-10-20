package biz.belcorp.ssicc.dao.spncd.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class ProgramaPeriodo extends AuditableBaseObject implements
		Serializable {
	/**
	 * ProgramaPeriodo
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoPrograma;

	private String codigoPeriodo;

	private String estadoProgramaPeriodo;

	private String nivel;

	private String nivelesAsignados;

	private String[] cuponesAsignados;

	private String[] cuponesNoAsignados;
	
	private String valorUnidad;
	
	private String valorUnidadPremioElectivo;

	/**
	 * @return the valorUnidad
	 */
	public String getValorUnidad() {
		return valorUnidad;
	}

	/**
	 * @param valorUnidad the valorUnidad to set
	 */
	public void setValorUnidad(String valorUnidad) {
		this.valorUnidad = valorUnidad;
	}

	/**
	 * @return Returns the cuponesAsignados.
	 */
	public String[] getCuponesAsignados() {
		return cuponesAsignados;
	}

	/**
	 * @param cuponesAsignados
	 *            The cuponesAsignados to set.
	 */
	public void setCuponesAsignados(String[] cuponesAsignados) {
		this.cuponesAsignados = cuponesAsignados;
	}

	/**
	 * @return Returns the cuponesNoAsignados.
	 */
	public String[] getCuponesNoAsignados() {
		return cuponesNoAsignados;
	}

	/**
	 * @param cuponesNoAsignados
	 *            The cuponesNoAsignados to set.
	 */
	public void setCuponesNoAsignados(String[] cuponesNoAsignados) {
		this.cuponesNoAsignados = cuponesNoAsignados;
	}

	/**
	 * @return Returns the nivel.
	 */
	public String getNivel() {
		return nivel;
	}

	/**
	 * @param nivel
	 *            The nivel to set.
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ProgramaPeriodo)) {
			return false;
		}

		ProgramaPeriodo rhs = (ProgramaPeriodo) object;

		return new EqualsBuilder().append(this.codigoPrograma,
				rhs.codigoPrograma).append(this.codigoPais, rhs.codigoPais)
				.append(this.codigoPeriodo, rhs.codigoPeriodo).append(
						this.estadoProgramaPeriodo, rhs.estadoProgramaPeriodo)
				.append(this.nivel, rhs.nivel).append(this.cuponesAsignados,
						rhs.cuponesAsignados).append(this.cuponesNoAsignados,
						rhs.cuponesNoAsignados).append(this.nivelesAsignados,
						rhs.nivelesAsignados).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(2022562655, 96184653).append(
				this.estadoProgramaPeriodo).append(this.codigoPais).append(
				this.codigoPeriodo).append(this.codigoPrograma).append(
				this.nivel).append(this.cuponesAsignados).append(
				this.cuponesNoAsignados).append(this.nivelesAsignados)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).toString();
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the codigoPrograma.
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	/**
	 * @param codigoPrograma
	 *            The codigoPrograma to set.
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	/**
	 * @return Returns the estadoProgramaPeriodo.
	 */
	public String getEstadoProgramaPeriodo() {
		return estadoProgramaPeriodo;
	}

	/**
	 * @param estadoProgramaPeriodo
	 *            The estadoProgramaPeriodo to set.
	 */
	public void setEstadoProgramaPeriodo(String estadoProgramaPeriodo) {
		this.estadoProgramaPeriodo = estadoProgramaPeriodo;
	}

	/**
	 * @return Returns the nivelesAsignados.
	 */
	public String getNivelesAsignados() {
		return nivelesAsignados;
	}

	/**
	 * @param nivelesAsignados
	 *            The nivelesAsignados to set.
	 */
	public void setNivelesAsignados(String nivelesAsignados) {
		this.nivelesAsignados = nivelesAsignados;
	}

	/**
	 * @return the valorUnidadPremioElectivo
	 */
	public String getValorUnidadPremioElectivo() {
		return valorUnidadPremioElectivo;
	}

	/**
	 * @param valorUnidadPremioElectivo the valorUnidadPremioElectivo to set
	 */
	public void setValorUnidadPremioElectivo(String valorUnidadPremioElectivo) {
		this.valorUnidadPremioElectivo = valorUnidadPremioElectivo;
	}
}
