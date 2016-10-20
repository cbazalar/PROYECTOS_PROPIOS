package biz.belcorp.ssicc.dao.scdf.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.BaseObject;



public class Archivo extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7832414144213031589L;

	String codigoPais;

	String codigoArchivo;

	String nombreArchivo;

	String indicadorLongitudFija;

	String indicadorSeparador;

	String caracterSeparador;

	String rutaDestino;

	String rutaTemporal;

	String indicadorSufijo;

	String patronSufijo;

	String extension;

	String descripcion;

	int numeroColumnas;

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
	 * @return Returns the codigoArchivo.
	 */
	public String getCodigoArchivo() {
		return codigoArchivo;
	}

	/**
	 * @param codigoArchivo
	 *            The codigoArchivo to set.
	 */
	public void setCodigoArchivo(String codigoArchivo) {
		this.codigoArchivo = codigoArchivo;
	}

	/**
	 * @return Returns the indicadorSeparador.
	 */
	public String getIndicadorSeparador() {
		return indicadorSeparador;
	}

	/**
	 * @param indicadorSeparador
	 *            The indicadorSeparador to set.
	 */
	public void setIndicadorSeparador(String indicadorSeparador) {
		this.indicadorSeparador = indicadorSeparador;
	}

	/**
	 * @return Returns the caracterSeparador.
	 */
	public String getCaracterSeparador() {
		return caracterSeparador;
	}

	/**
	 * @param caracterSeparador
	 *            The caracterSeparador to set.
	 */
	public void setCaracterSeparador(String caracterSeparador) {
		this.caracterSeparador = caracterSeparador;
	}

	/**
	 * @return Returns the indicadorLongitudFija.
	 */
	public String getIndicadorLongitudFija() {
		return indicadorLongitudFija;
	}

	/**
	 * @param indicadorLongitudFija
	 *            The indicadorLongitudFija to set.
	 */
	public void setIndicadorLongitudFija(String indicadorLongitudFija) {
		this.indicadorLongitudFija = indicadorLongitudFija;
	}

	/**
	 * @return Returns the indicadorSufijo.
	 */
	public String getIndicadorSufijo() {
		return indicadorSufijo;
	}

	/**
	 * @param indicadorSufijo
	 *            The indicadorSufijo to set.
	 */
	public void setIndicadorSufijo(String indicadorSufijo) {
		this.indicadorSufijo = indicadorSufijo;
	}

	/**
	 * @return Returns the nombreArchivo.
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * @param nombreArchivo
	 *            The nombreArchivo to set.
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * @return Returns the numeroColumnas.
	 */
	public int getNumeroColumnas() {
		return numeroColumnas;
	}

	/**
	 * @param numeroColumnas
	 *            The numeroColumnas to set.
	 */
	public void setNumeroColumnas(int numeroColumnas) {
		this.numeroColumnas = numeroColumnas;
	}

	/**
	 * @return Returns the patronSufijo.
	 */
	public String getPatronSufijo() {
		return patronSufijo;
	}

	/**
	 * @param patronSufijo
	 *            The patronSufijo to set.
	 */
	public void setPatronSufijo(String patronSufijo) {
		this.patronSufijo = patronSufijo;
	}

	/**
	 * @return Returns the rutaDestino.
	 */
	public String getRutaDestino() {
		return rutaDestino;
	}

	/**
	 * @param rutaDestino
	 *            The rutaDestino to set.
	 */
	public void setRutaDestino(String rutaDestino) {
		this.rutaDestino = rutaDestino;
	}

	/**
	 * @return Returns the rutaTemporal.
	 */
	public String getRutaTemporal() {
		return rutaTemporal;
	}

	/**
	 * @param rutaTemporal
	 *            The rutaTemporal to set.
	 */
	public void setRutaTemporal(String rutaTemporal) {
		this.rutaTemporal = rutaTemporal;
	}

	/**
	 * @return Returns the descripcion.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            The descripcion to set.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return Returns the extension.
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * @param extension
	 *            The extension to set.
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Archivo)) {
			return false;
		}
		Archivo rhs = (Archivo) object;
		return new EqualsBuilder().append(this.extension, rhs.extension)
				.append(this.indicadorLongitudFija, rhs.indicadorLongitudFija)
				.append(this.caracterSeparador, rhs.caracterSeparador).append(
						this.numeroColumnas, rhs.numeroColumnas).append(
						this.indicadorSeparador, rhs.indicadorSeparador)
				.append(this.descripcion, rhs.descripcion).append(
						this.patronSufijo, rhs.patronSufijo).append(
						this.codigoArchivo, rhs.codigoArchivo).append(
						this.indicadorSufijo, rhs.indicadorSufijo).append(
						this.rutaDestino, rhs.rutaDestino).append(
						this.codigoPais, rhs.codigoPais).append(
						this.nombreArchivo, rhs.nombreArchivo).append(
						this.rutaTemporal, rhs.rutaTemporal).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-744598423, -1756220019).append(
				this.extension).append(this.indicadorLongitudFija).append(
				this.caracterSeparador).append(this.numeroColumnas).append(
				this.indicadorSeparador).append(this.descripcion).append(
				this.patronSufijo).append(this.codigoArchivo).append(
				this.indicadorSufijo).append(this.rutaDestino).append(
				this.codigoPais).append(this.nombreArchivo).append(
				this.rutaTemporal).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("indicadorSeparador", this.indicadorSeparador).append(
						"codigoPais", this.codigoPais).append(
						"caracterSeparador", this.caracterSeparador).append(
						"rutaTemporal", this.rutaTemporal).append(
						"indicadorLongitudFija", this.indicadorLongitudFija)
				.append("numeroColumnas", this.numeroColumnas).append(
						"descripcion", this.descripcion).append("extension",
						this.extension).append("indicadorSufijo",
						this.indicadorSufijo).append("nombreArchivo",
						this.nombreArchivo).append("patronSufijo",
						this.patronSufijo).append("codigoArchivo",
						this.codigoArchivo).append("rutaDestino",
						this.rutaDestino).toString();
	}

}
