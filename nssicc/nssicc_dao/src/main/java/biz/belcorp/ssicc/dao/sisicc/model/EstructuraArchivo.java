/*
 * Created on 25-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="EstructuraArchivo.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class EstructuraArchivo extends AuditableBaseObject implements
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7607468452024896181L;

	private String codigoPais;

	private String codigoSistema;

	private String codigoInterfaz;

	private String codigo;

	private int posicionCampo;

	private String descripcionCampo;

	private String codigoTipoDato;

	private int longitudCampo;

	private int cantidadDecimales;

	private String observacionCampo;

	private String estado;

	private TipoDato tipoDato;

	private String identificadorCampo;

	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            The codigo to set.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return Returns the cantidadDecimales.
	 */
	public int getCantidadDecimales() {
		return cantidadDecimales;
	}

	/**
	 * @param cantidadDecimales
	 *            The cantidadDecimales to set.
	 */
	public void setCantidadDecimales(int cantidadDecimales) {
		this.cantidadDecimales = cantidadDecimales;
	}

	/**
	 * @return Returns the codigoInterfaz.
	 */
	public String getCodigoInterfaz() {
		return codigoInterfaz;
	}

	/**
	 * @param codigoInterfaz
	 *            The codigoInterfaz to set.
	 */
	public void setCodigoInterfaz(String codigoInterfaz) {
		this.codigoInterfaz = codigoInterfaz;
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
	 * @return Returns the codigoSistema.
	 */
	public String getCodigoSistema() {
		return codigoSistema;
	}

	/**
	 * @param codigoSistema
	 *            The codigoSistema to set.
	 */
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	/**
	 * @return Returns the codigoTipoDato.
	 */
	public String getCodigoTipoDato() {
		return codigoTipoDato;
	}

	/**
	 * @param codigoTipoDato
	 *            The codigoTipoDato to set.
	 */
	public void setCodigoTipoDato(String codigoTipoDato) {
		this.codigoTipoDato = codigoTipoDato;
	}

	/**
	 * @return Returns the descripcionCampo.
	 */
	public String getDescripcionCampo() {
		return descripcionCampo;
	}

	/**
	 * @param descripcionCampo
	 *            The descripcionCampo to set.
	 */
	public void setDescripcionCampo(String descripcionCampo) {
		this.descripcionCampo = descripcionCampo;
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
	 * @return Returns the observacionCampo.
	 */
	public String getObservacionCampo() {
		return observacionCampo;
	}

	/**
	 * @param observacionCampo
	 *            The observacionCampo to set.
	 */
	public void setObservacionCampo(String observacionCampo) {
		this.observacionCampo = observacionCampo;
	}

	/**
	 * @return Returns the posicionCampo.
	 */
	public int getPosicionCampo() {
		return posicionCampo;
	}

	/**
	 * @param posicionCampo
	 *            The posicionCampo to set.
	 */
	public void setPosicionCampo(int posicionCampo) {
		this.posicionCampo = posicionCampo;
	}

	/**
	 * @return Returns the tipoDato.
	 */
	public TipoDato getTipoDato() {
		return tipoDato;
	}

	/**
	 * @param tipoDato
	 *            The tipoDato to set.
	 */
	public void setTipoDato(TipoDato tipoDato) {
		this.tipoDato = tipoDato;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("codigo", codigo).append(
				"codigoTipoDato", codigoTipoDato).append("descripcionCampo",
				descripcionCampo).append("estado", estado).append(
				"identificadorCampo", identificadorCampo).append(
				"longitudCampo", longitudCampo).append("observacionCampo",
				observacionCampo).append("tipoDato", tipoDato).toString();
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return Returns the identificadorCampo.
	 */
	public String getIdentificadorCampo() {
		return identificadorCampo;
	}

	/**
	 * @param identificadorCampo
	 *            The identificadorCampo to set.
	 */
	public void setIdentificadorCampo(String identificadorCampo) {
		this.identificadorCampo = identificadorCampo;
	}

}
