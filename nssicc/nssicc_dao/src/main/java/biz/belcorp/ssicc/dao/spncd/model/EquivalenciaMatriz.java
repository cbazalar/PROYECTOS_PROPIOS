package biz.belcorp.ssicc.dao.spncd.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class EquivalenciaMatriz extends AuditableBaseObject implements
		Serializable {
	/**
	 *  EquivalenciaMatriz
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;

	private String codigoPais;

	private String codigoPrograma;

	private String codigoPeriodo;

	private String codigoCupon;

	private String codigoNivel;

	private String codigoVenta;

	private String codigoProducto;

	private String descripcionProducto;

	private String valorUnitario;
	
	private String valorPrioridad;
	
	private String indicadorLove;
	private String indicadorLoveDefault;
	
	private String valorUnidadesMaximas;
	
	/* INI SA PER-SiCC--2012-0467 */
	private String indicadorKit;
	private String codigoUsuario;
	/* FIN SA PER-SiCC--2012-0467 */
	
	private String vigencia;

	/**
	 * @return Returns the valorPrioridad.
	 */
	public String getValorPrioridad() {
		return valorPrioridad;
	}

	/**
	 * @param valorPrioridad The valorPrioridad to set.
	 */
	public void setValorPrioridad(String valorPrioridad) {
		this.valorPrioridad = valorPrioridad;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof EquivalenciaMatriz)) {
			return false;
		}

		EquivalenciaMatriz rhs = (EquivalenciaMatriz) object;

		return new EqualsBuilder().append(this.codigoPrograma,
				rhs.codigoPrograma).append(this.codigoPais, rhs.codigoPais)
				.append(this.codigoPeriodo, rhs.codigoPeriodo).append(
						this.codigoCupon, rhs.codigoCupon).append(
						this.codigoNivel, rhs.codigoNivel).append(
						this.codigoVenta, rhs.codigoVenta).append(
						this.codigoProducto, rhs.codigoProducto).append(
						this.descripcionProducto, rhs.descripcionProducto)
				.append(this.valorUnitario, rhs.valorUnitario)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(2022562655, 96184653).append(this.valorUnitario)
				.append(this.codigoPais).append(this.codigoPeriodo).append(
						this.codigoPrograma).append(this.codigoVenta).append(
						this.codigoProducto).append(this.codigoNivel).append(
						this.codigoCupon).append(this.descripcionProducto)
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
	 * @return Returns the codigoCupon.
	 */
	public String getCodigoCupon() {
		return codigoCupon;
	}

	/**
	 * @param codigoCupon
	 *            The codigoCupon to set.
	 */
	public void setCodigoCupon(String codigoCupon) {
		this.codigoCupon = codigoCupon;
	}

	/**
	 * @return Returns the codigoNivel.
	 */
	public String getCodigoNivel() {
		return codigoNivel;
	}

	/**
	 * @param codigoNivel
	 *            The codigoNivel to set.
	 */
	public void setCodigoNivel(String codigoNivel) {
		this.codigoNivel = codigoNivel;
	}

	/**
	 * @return Returns the codigoProducto.
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto
	 *            The codigoProducto to set.
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * @return Returns the codigoVenta.
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}

	/**
	 * @param codigoVenta
	 *            The codigoVenta to set.
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	/**
	 * @return Returns the descripcionProducto.
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	/**
	 * @param descripcionProducto
	 *            The descripcionProducto to set.
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Returns the valorUnitario.
	 */
	public String getValorUnitario() {
		return valorUnitario;
	}

	/**
	 * @param valorUnitario The valorUnitario to set.
	 */
	public void setValorUnitario(String valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	/**
	 * @return the indicadorLove
	 */
	public String getIndicadorLove() {
		return indicadorLove;
	}

	/**
	 * @param indicadorLove the indicadorLove to set
	 */
	public void setIndicadorLove(String indicadorLove) {
		this.indicadorLove = indicadorLove;
	}

	/**
	 * @return the indicadorLoveDefault
	 */
	public String getIndicadorLoveDefault() {
		return indicadorLoveDefault;
	}

	/**
	 * @param indicadorLoveDefault the indicadorLoveDefault to set
	 */
	public void setIndicadorLoveDefault(String indicadorLoveDefault) {
		this.indicadorLoveDefault = indicadorLoveDefault;
	}

	/**
	 * @return the valorUnidadesMaximas
	 */
	public String getValorUnidadesMaximas() {
		return valorUnidadesMaximas;
	}

	/**
	 * @param valorUnidadesMaximas the valorUnidadesMaximas to set
	 */
	public void setValorUnidadesMaximas(String valorUnidadesMaximas) {
		this.valorUnidadesMaximas = valorUnidadesMaximas;
	}
	
	/**
	 * @return the indicadorKit
	 */
	public String getIndicadorKit() {
		return indicadorKit;
	}

	/**
	 * @param indicadorKit the indicadorKit to set
	 */
	public void setIndicadorKit(String indicadorKit) {
		this.indicadorKit = indicadorKit;
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

	/**
	 * @return the vigencia
	 */
	public String getVigencia() {
		return vigencia;
	}

	/**
	 * @param vigencia the vigencia to set
	 */
	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}

}
