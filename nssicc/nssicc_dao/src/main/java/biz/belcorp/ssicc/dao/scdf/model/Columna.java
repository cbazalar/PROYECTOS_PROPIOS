package biz.belcorp.ssicc.dao.scdf.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.BaseObject;

public class Columna extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9199715273582518218L;

	String codigoPais;
	
	String codigoArchivo;

	String nombreColumna;

	int posicionColumna;

	String tipoDato;

	int longitudCampo;

	int precisionCampo;

	String indicadorRelleno;

	String caracterRelleno;

	String flagDelimitador;

	String caracterDelimitador;

	String patronFecha;

	String estado;

	String valorDefecto;
	
	String nombreClase;

	/**
	 * @return Returns the nombreClase.
	 */
	public String getNombreClase() {
		return nombreClase;
	}

	/**
	 * @param nombreClase The nombreClase to set.
	 */
	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the posicionColumna.
	 */
	public int getPosicionColumna() {
		return posicionColumna;
	}

	/**
	 * @param posicionColumna
	 *            The posicionColumna to set.
	 */
	public void setPosicionColumna(int posicionColumna) {
		this.posicionColumna = posicionColumna;
	}

	/**
	 * @return Returns the valorDefecto.
	 */
	public String getValorDefecto() {
		return valorDefecto;
	}

	/**
	 * @param valorDefecto
	 *            The valorDefecto to set.
	 */
	public void setValorDefecto(String valorDefecto) {
		this.valorDefecto = valorDefecto;
	}

	/**
	 * @return Returns the caracterDelimitador.
	 */
	public String getCaracterDelimitador() {
		return caracterDelimitador;
	}

	/**
	 * @param caracterDelimitador
	 *            The caracterDelimitador to set.
	 */
	public void setCaracterDelimitador(String caracterDelimitador) {
		this.caracterDelimitador = caracterDelimitador;
	}

	/**
	 * @return Returns the caracterRelleno.
	 */
	public String getCaracterRelleno() {
		return caracterRelleno;
	}

	/**
	 * @param caracterRelleno
	 *            The caracterRelleno to set.
	 */
	public void setCaracterRelleno(String caracterRelleno) {
		this.caracterRelleno = caracterRelleno;
	}

	/**
	 * @return Returns the estado.
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            The estado to set.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return Returns the flagDelimitador.
	 */
	public String getFlagDelimitador() {
		return flagDelimitador;
	}

	/**
	 * @param flagDelimitador
	 *            The flagDelimitador to set.
	 */
	public void setFlagDelimitador(String flagDelimitador) {
		this.flagDelimitador = flagDelimitador;
	}

	/**
	 * @return Returns the indicadorRelleno.
	 */
	public String getIndicadorRelleno() {
		return indicadorRelleno;
	}

	/**
	 * @param indicadorRelleno
	 *            The indicadorRelleno to set.
	 */
	public void setIndicadorRelleno(String indicadorRelleno) {
		this.indicadorRelleno = indicadorRelleno;
	}

	/**
	 * @return Returns the longitudCampo.
	 */
	public int getLongitudCampo() {
		return longitudCampo;
	}

	/**
	 * @param longitudCampo
	 *            The longitudCampo to set.
	 */
	public void setLongitudCampo(int longitudCampo) {
		this.longitudCampo = longitudCampo;
	}

	/**
	 * @return Returns the codigoArchivo.
	 */
	public String getCodigoArchivo() {
		return codigoArchivo;
	}

	/**
	 * @param codigoArchivo
	 *            The codigoArchivo to set.
	 */
	public void setCodigoArchivo(String nombreArchivo) {
		this.codigoArchivo = nombreArchivo;
	}

	/**
	 * @return Returns the nombreColumna.
	 */
	public String getNombreColumna() {
		return nombreColumna;
	}

	/**
	 * @param nombreColumna
	 *            The nombreColumna to set.
	 */
	public void setNombreColumna(String nombreColumna) {
		this.nombreColumna = nombreColumna;
	}

	/**
	 * @return Returns the patronFecha.
	 */
	public String getPatronFecha() {
		return patronFecha;
	}

	/**
	 * @param patronFecha
	 *            The patronFecha to set.
	 */
	public void setPatronFecha(String patronFecha) {
		this.patronFecha = patronFecha;
	}

	/**
	 * @return Returns the precisionCampo.
	 */
	public int getPrecisionCampo() {
		return precisionCampo;
	}

	/**
	 * @param precisionCampo
	 *            The precisionCampo to set.
	 */
	public void setPrecisionCampo(int precisionCampo) {
		this.precisionCampo = precisionCampo;
	}

	/**
	 * @return Returns the tipoDato.
	 */
	public String getTipoDato() {
		return tipoDato;
	}

	/**
	 * @param tipoDato
	 *            The tipoDato to set.
	 */
	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Columna)) {
			return false;
		}
		Columna rhs = (Columna) object;
		return new EqualsBuilder().append(this.posicionColumna,
				rhs.posicionColumna).append(this.indicadorRelleno,
				rhs.indicadorRelleno).append(this.patronFecha, rhs.patronFecha)
				.append(this.caracterRelleno, rhs.caracterRelleno).append(
						this.nombreClase, rhs.nombreClase).append(
						this.caracterDelimitador, rhs.caracterDelimitador)
				.append(this.codigoArchivo, rhs.codigoArchivo).append(
						this.flagDelimitador, rhs.flagDelimitador).append(
						this.tipoDato, rhs.tipoDato).append(this.valorDefecto,
						rhs.valorDefecto).append(this.precisionCampo,
						rhs.precisionCampo).append(this.codigoPais,
						rhs.codigoPais).append(this.nombreColumna,
						rhs.nombreColumna).append(this.longitudCampo,
						rhs.longitudCampo).append(this.estado, rhs.estado)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(446943503, -163120725).append(
				this.posicionColumna).append(this.indicadorRelleno).append(
				this.patronFecha).append(this.caracterRelleno).append(
				this.nombreClase).append(this.caracterDelimitador).append(
				this.codigoArchivo).append(this.flagDelimitador).append(
				this.tipoDato).append(this.valorDefecto).append(
				this.precisionCampo).append(this.codigoPais).append(
				this.nombreColumna).append(this.longitudCampo).append(
				this.estado).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("posicionColumna", this.posicionColumna).append(
						"codigoPais", this.codigoPais).append(
						"indicadorRelleno", this.indicadorRelleno).append(
						"valorDefecto", this.valorDefecto).append(
						"flagDelimitador", this.flagDelimitador).append(
						"caracterRelleno", this.caracterRelleno).append(
						"nombreColumna", this.nombreColumna).append(
						"caracterDelimitador", this.caracterDelimitador)
				.append("estado", this.estado).append("precisionCampo",
						this.precisionCampo).append("tipoDato", this.tipoDato)
				.append("longitudCampo", this.longitudCampo).append(
						"patronFecha", this.patronFecha).append(
						"codigoArchivo", this.codigoArchivo).append(
						"nombreClase", this.nombreClase).toString();
	}

}
