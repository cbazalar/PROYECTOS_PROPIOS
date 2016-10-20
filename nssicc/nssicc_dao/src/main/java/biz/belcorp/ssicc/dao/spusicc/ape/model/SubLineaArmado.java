package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

/**
 * @author Jose Luis Rodriguez
 */

public class SubLineaArmado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2714413209737532939L;
	private String codigoPais;
	private String oidCentroDistribucion;
	private String oidLineaArmado;
	private String oidSubLinea;
	private String codigoSubLinea;
	private String descripcionSubLinea;
	private String codigoImpresora;
	private String codigoSistemaPicado;
	private String numeroImpresoraPTL;
	private String indicadorPostVenta;
	private String numeroOrdenImpresion;
	private String codigoLetra;
	private String indicadorListaPicado;
	private String indicadorFrenteEspalda;
	private String numeroBahiaFrente;
	private String numeroBahiaEspalda;
	private String numeroNivelFrente;
	private String numeroNivelEspalda;
	private String numeroColumnaFrente;
	private String numeroColumnaEspalda;
	private String numeroOrdenBalanceo;
	private String codigoTipoCaja;
	private String capacidadTipoCaja;
	private String codigoSubLineaAframe;
	private String numeroMaximoChanels;
	
	/**
	 * @return numeroMaximoChanels
	 */ 
	public String getNumeroMaximoChanels() {
		return numeroMaximoChanels;
	}
	/**
	 * @param numeroMaximoChanels
	 */
	public void setNumeroMaximoChanels(String numeroMaximoChanels) {
		this.numeroMaximoChanels = numeroMaximoChanels;
	}
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
	 * @return the oidCentroDistribucion
	 */
	public String getOidCentroDistribucion() {
		return oidCentroDistribucion;
	}
	/**
	 * @param oidCentroDistribucion the oidCentroDistribucion to set
	 */
	public void setOidCentroDistribucion(String oidCentroDistribucion) {
		this.oidCentroDistribucion = oidCentroDistribucion;
	}
	/**
	 * @return the oidLineaArmado
	 */
	public String getOidLineaArmado() {
		return oidLineaArmado;
	}
	/**
	 * @param oidLineaArmado the oidLineaArmado to set
	 */
	public void setOidLineaArmado(String oidLineaArmado) {
		this.oidLineaArmado = oidLineaArmado;
	}
	/**
	 * @return the oidSubLinea
	 */
	public String getOidSubLinea() {
		return oidSubLinea;
	}
	/**
	 * @param oidSubLinea the oidSubLinea to set
	 */
	public void setOidSubLinea(String oidSubLinea) {
		this.oidSubLinea = oidSubLinea;
	}
	/**
	 * @return the codigoSubLinea
	 */
	public String getCodigoSubLinea() {
		return codigoSubLinea;
	}
	/**
	 * @param codigoSubLinea the codigoSubLinea to set
	 */
	public void setCodigoSubLinea(String codigoSubLinea) {
		this.codigoSubLinea = codigoSubLinea;
	}
	/**
	 * @return the descripcionSubLinea
	 */
	public String getDescripcionSubLinea() {
		return descripcionSubLinea;
	}
	/**
	 * @param descripcionSubLinea the descripcionSubLinea to set
	 */
	public void setDescripcionSubLinea(String descripcionSubLinea) {
		this.descripcionSubLinea = descripcionSubLinea;
	}
	/**
	 * @return the codigoImpresora
	 */
	public String getCodigoImpresora() {
		return codigoImpresora;
	}
	/**
	 * @param codigoImpresora the codigoImpresora to set
	 */
	public void setCodigoImpresora(String codigoImpresora) {
		this.codigoImpresora = codigoImpresora;
	}
	/**
	 * @return the codigoSistemaPicado
	 */
	public String getCodigoSistemaPicado() {
		return codigoSistemaPicado;
	}
	/**
	 * @param codigoSistemaPicado the codigoSistemaPicado to set
	 */
	public void setCodigoSistemaPicado(String codigoSistemaPicado) {
		this.codigoSistemaPicado = codigoSistemaPicado;
	}
	/**
	 * @return the numeroImpresoraPTL
	 */
	public String getNumeroImpresoraPTL() {
		return numeroImpresoraPTL;
	}
	/**
	 * @param numeroImpresoraPTL the numeroImpresoraPTL to set
	 */
	public void setNumeroImpresoraPTL(String numeroImpresoraPTL) {
		this.numeroImpresoraPTL = numeroImpresoraPTL;
	}
	/**
	 * @return the indicadorPostVenta
	 */
	public String getIndicadorPostVenta() {
		return indicadorPostVenta;
	}
	/**
	 * @param indicadorPostVenta the indicadorPostVenta to set
	 */
	public void setIndicadorPostVenta(String indicadorPostVenta) {
		this.indicadorPostVenta = indicadorPostVenta;
	}
	/**
	 * @return the codigoLetra
	 */
	public String getCodigoLetra() {
		return codigoLetra;
	}
	/**
	 * @param codigoLetra the codigoLetra to set
	 */
	public void setCodigoLetra(String codigoLetra) {
		this.codigoLetra = codigoLetra;
	}
	/**
	 * @return the indicadorListaPicado
	 */
	public String getIndicadorListaPicado() {
		return indicadorListaPicado;
	}
	/**
	 * @param indicadorListaPicado the indicadorListaPicado to set
	 */
	public void setIndicadorListaPicado(String indicadorListaPicado) {
		this.indicadorListaPicado = indicadorListaPicado;
	}
	/**
	 * @return the indicadorFrenteEspalda
	 */
	public String getIndicadorFrenteEspalda() {
		return indicadorFrenteEspalda;
	}
	/**
	 * @param indicadorFrenteEspalda the indicadorFrenteEspalda to set
	 */
	public void setIndicadorFrenteEspalda(String indicadorFrenteEspalda) {
		this.indicadorFrenteEspalda = indicadorFrenteEspalda;
	}
	/**
	 * @return the numeroBahiaFrente
	 */
	public String getNumeroBahiaFrente() {
		return numeroBahiaFrente;
	}
	/**
	 * @param numeroBahiaFrente the numeroBahiaFrente to set
	 */
	public void setNumeroBahiaFrente(String numeroBahiaFrente) {
		this.numeroBahiaFrente = numeroBahiaFrente;
	}
	/**
	 * @return the numeroBahiaEspalda
	 */
	public String getNumeroBahiaEspalda() {
		return numeroBahiaEspalda;
	}
	/**
	 * @param numeroBahiaEspalda the numeroBahiaEspalda to set
	 */
	public void setNumeroBahiaEspalda(String numeroBahiaEspalda) {
		this.numeroBahiaEspalda = numeroBahiaEspalda;
	}
	/**
	 * @return the numeroNivelFrente
	 */
	public String getNumeroNivelFrente() {
		return numeroNivelFrente;
	}
	/**
	 * @param numeroNivelFrente the numeroNivelFrente to set
	 */
	public void setNumeroNivelFrente(String numeroNivelFrente) {
		this.numeroNivelFrente = numeroNivelFrente;
	}
	/**
	 * @return the numeroNivelEspalda
	 */
	public String getNumeroNivelEspalda() {
		return numeroNivelEspalda;
	}
	/**
	 * @param numeroNivelEspalda the numeroNivelEspalda to set
	 */
	public void setNumeroNivelEspalda(String numeroNivelEspalda) {
		this.numeroNivelEspalda = numeroNivelEspalda;
	}
	/**
	 * @return the numeroColumnaFrente
	 */
	public String getNumeroColumnaFrente() {
		return numeroColumnaFrente;
	}
	/**
	 * @param numeroColumnaFrente the numeroColumnaFrente to set
	 */
	public void setNumeroColumnaFrente(String numeroColumnaFrente) {
		this.numeroColumnaFrente = numeroColumnaFrente;
	}
	/**
	 * @return the numeroColumnaEspalda
	 */
	public String getNumeroColumnaEspalda() {
		return numeroColumnaEspalda;
	}
	/**
	 * @param numeroColumnaEspalda the numeroColumnaEspalda to set
	 */
	public void setNumeroColumnaEspalda(String numeroColumnaEspalda) {
		this.numeroColumnaEspalda = numeroColumnaEspalda;
	}
	/**
	 * @return the numeroOrdenBalanceo
	 */
	public String getNumeroOrdenBalanceo() {
		return numeroOrdenBalanceo;
	}
	/**
	 * @param numeroOrdenBalanceo the numeroOrdenBalanceo to set
	 */
	public void setNumeroOrdenBalanceo(String numeroOrdenBalanceo) {
		this.numeroOrdenBalanceo = numeroOrdenBalanceo;
	}
	/**
	 * @return the codigoTipoCaja
	 */
	public String getCodigoTipoCaja() {
		return codigoTipoCaja;
	}
	/**
	 * @param codigoTipoCaja the codigoTipoCaja to set
	 */
	public void setCodigoTipoCaja(String codigoTipoCaja) {
		this.codigoTipoCaja = codigoTipoCaja;
	}
	/**
	 * @return the capacidadTipoCaja
	 */
	public String getCapacidadTipoCaja() {
		return capacidadTipoCaja;
	}
	/**
	 * @param capacidadTipoCaja the capacidadTipoCaja to set
	 */
	public void setCapacidadTipoCaja(String capacidadTipoCaja) {
		this.capacidadTipoCaja = capacidadTipoCaja;
	}
	/**
	 * @return the numeroOrdenImpresion
	 */
	public String getNumeroOrdenImpresion() {
		return numeroOrdenImpresion;
	}
	/**
	 * @param numeroOrdenImpresion the numeroOrdenImpresion to set
	 */
	public void setNumeroOrdenImpresion(String numeroOrdenImpresion) {
		this.numeroOrdenImpresion = numeroOrdenImpresion;
	}
	/**
	 * @return the codigoSubLineaAframe
	 */
	public String getCodigoSubLineaAframe() {
		return codigoSubLineaAframe;
	}
	/**
	 * @param codigoSubLineaAframe the codigoSubLineaAframe to set
	 */
	public void setCodigoSubLineaAframe(String codigoSubLineaAframe) {
		this.codigoSubLineaAframe = codigoSubLineaAframe;
	}
	
}