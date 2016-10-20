package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;

public class CargaPuntajeConsultora implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3671219968068980764L;
	private String numeroLote;
	private String codigoCliente;
	private String numeroConcurso;
	private String numeroPuntaje;
	private String codigoMotivo;
	private String mensajeError;
	private String indicadorCarga;
	private String indicadorAplicacion;
	//
	private String codigoPeriodo;
	
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
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
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	/**
	 * @return the numeroConcurso
	 */
	public String getNumeroConcurso() {
		return numeroConcurso;
	}
	/**
	 * @param numeroConcurso the numeroConcurso to set
	 */
	public void setNumeroConcurso(String numeroConcurso) {
		this.numeroConcurso = numeroConcurso;
	}
	/**
	 * @return the numeroPuntaje
	 */
	public String getNumeroPuntaje() {
		return numeroPuntaje;
	}
	/**
	 * @param numeroPuntaje the numeroPuntaje to set
	 */
	public void setNumeroPuntaje(String numeroPuntaje) {
		this.numeroPuntaje = numeroPuntaje;
	}
	/**
	 * @return the codigoMotivo
	 */
	public String getCodigoMotivo() {
		return codigoMotivo;
	}
	/**
	 * @param codigoMotivo the codigoMotivo to set
	 */
	public void setCodigoMotivo(String codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}
	/**
	 * @return the mensajeError
	 */
	public String getMensajeError() {
		return mensajeError;
	}
	/**
	 * @param mensajeError the mensajeError to set
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	/**
	 * @return the indicadorCarga
	 */
	public String getIndicadorCarga() {
		return indicadorCarga;
	}
	/**
	 * @param indicadorCarga the indicadorCarga to set
	 */
	public void setIndicadorCarga(String indicadorCarga) {
		this.indicadorCarga = indicadorCarga;
	}
	/**
	 * @return the indicadorAplicacion
	 */
	public String getIndicadorAplicacion() {
		return indicadorAplicacion;
	}
	/**
	 * @param indicadorAplicacion the indicadorAplicacion to set
	 */
	public void setIndicadorAplicacion(String indicadorAplicacion) {
		this.indicadorAplicacion = indicadorAplicacion;
	}
	private String codigoUsuario;

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

}
