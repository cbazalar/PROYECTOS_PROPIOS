package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class BonoPremioPago extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	 
	private String codigoPais; 
	private String codigoPago;
	private String numeroConcurso;
	private String numeroNivel;
	private String numeroPremio;
	private String codigoVenta;
	private String indicadorBonoCCC;
	private String indicadorBonoBanco;
	private String tipoAbono;
	private String codigoUsuario;
	
	public BonoPremioPago() {
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
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
	 * @return the codigoPago
	 */
	public String getCodigoPago() {
		return codigoPago;
	}

	/**
	 * @param codigoPago the codigoPago to set
	 */
	public void setCodigoPago(String codigoPago) {
		this.codigoPago = codigoPago;
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
	 * @return the numeroNivel
	 */
	public String getNumeroNivel() {
		return numeroNivel;
	}

	/**
	 * @param numeroNivel the numeroNivel to set
	 */
	public void setNumeroNivel(String numeroNivel) {
		this.numeroNivel = numeroNivel;
	}

	/**
	 * @return the numeroPremio
	 */
	public String getNumeroPremio() {
		return numeroPremio;
	}

	/**
	 * @param numeroPremio the numeroPremio to set
	 */
	public void setNumeroPremio(String numeroPremio) {
		this.numeroPremio = numeroPremio;
	}

	/**
	 * @return the codigoVenta
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}

	/**
	 * @param codigoVenta the codigoVenta to set
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	/**
	 * @return the indicadorBonoCCC
	 */
	public String getIndicadorBonoCCC() {
		return indicadorBonoCCC;
	}

	/**
	 * @param indicadorBonoCCC the indicadorBonoCCC to set
	 */
	public void setIndicadorBonoCCC(String indicadorBonoCCC) {
		this.indicadorBonoCCC = indicadorBonoCCC;
	}

	/**
	 * @return the indicadorBonoBanco
	 */
	public String getIndicadorBonoBanco() {
		return indicadorBonoBanco;
	}

	/**
	 * @param indicadorBonoBanco the indicadorBonoBanco to set
	 */
	public void setIndicadorBonoBanco(String indicadorBonoBanco) {
		this.indicadorBonoBanco = indicadorBonoBanco;
	}

	/**
	 * @return the tipoAbono
	 */
	public String getTipoAbono() {
		return tipoAbono;
	}

	/**
	 * @param tipoAbono the tipoAbono to set
	 */
	public void setTipoAbono(String tipoAbono) {
		this.tipoAbono = tipoAbono;
	}

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
