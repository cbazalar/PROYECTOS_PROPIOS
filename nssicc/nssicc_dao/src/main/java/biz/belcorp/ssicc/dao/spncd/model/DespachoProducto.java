package biz.belcorp.ssicc.dao.spncd.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class DespachoProducto extends AuditableBaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6852030888646266230L;
	private String id;
    private String codigoPais;
    private String codigoPeriodo;
    private String codigoPrograma;
    private String codigoNivel;
    private String codigoVenta;
    private String valorUnitario;
    private String descripcionProducto;
    private String codigoProducto;
    private String nivelPriorizacion;
    private String estadoRegistro;
    private String indicadorRegalo;
    private String indicadorKit;
    
    /* INI SA PER-SiCC--2012-0467 */
    private String actualizarIndicadorKit;
    /* FIN SA PER-SiCC--2012-0467 */
    
    private String indicadorPremioWeb;
    private String tipoDespacho;
    
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	public String getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public String getCodigoVenta() {
		return codigoVenta;
	}
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNivelPriorizacion() {
		return nivelPriorizacion;
	}
	public void setNivelPriorizacion(String nivelPriorizacion) {
		this.nivelPriorizacion = nivelPriorizacion;
	}
	public String getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(String valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof DespachoProducto)) {
			return false;
		}
		DespachoProducto rhs = (DespachoProducto) object;
		return new EqualsBuilder().append(
				this.codigoProducto, rhs.codigoProducto).append(
				this.codigoPais, rhs.codigoPais).append(
				this.descripcionProducto, rhs.descripcionProducto).append(
				this.codigoVenta, rhs.codigoVenta).append(this.auditInfo,
				rhs.auditInfo).append(this.estadoRegistro, rhs.estadoRegistro)
				.append(this.nivelPriorizacion, rhs.nivelPriorizacion).append(
						this.valorUnitario, rhs.valorUnitario).append(this.id,
						rhs.id).append(this.codigoPeriodo, rhs.codigoPeriodo)
				.isEquals();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("nivelPriorizacion",
				this.nivelPriorizacion).append("codigoPais", this.codigoPais)
				.append("id", this.id).append("estadoRegistro",
						this.estadoRegistro).append("codigoProducto",
						this.codigoProducto)
				.append("auditInfo", this.auditInfo).append("valorUnitario",
						this.valorUnitario).append("descripcionProducto",
						this.descripcionProducto).append("codigoVenta",
						this.codigoVenta).append("codigoPeriodo",
						this.codigoPeriodo).toString();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1311526749, -412198911).append(this.codigoProducto).append(
				this.codigoPais).append(this.descripcionProducto).append(
				this.codigoVenta).append(this.auditInfo).append(
				this.estadoRegistro).append(this.nivelPriorizacion).append(
				this.valorUnitario).append(this.id).append(this.codigoPeriodo)
				.toHashCode();
	}
	public String getCodigoNivel() {
		return codigoNivel;
	}
	public void setCodigoNivel(String codigoNivel) {
		this.codigoNivel = codigoNivel;
	}
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	/**
	 * @return the indicadorRegalo
	 */
	public String getIndicadorRegalo() {
		return indicadorRegalo;
	}
	/**
	 * @param indicadorRegalo the indicadorRegalo to set
	 */
	public void setIndicadorRegalo(String indicadorRegalo) {
		this.indicadorRegalo = indicadorRegalo;
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
	 * @return the actualizarIndicadorKit
	 */
	public String getActualizarIndicadorKit() {
		return actualizarIndicadorKit;
	}
	/**
	 * @param actualizarIndicadorKit the actualizarIndicadorKit to set
	 */
	public void setActualizarIndicadorKit(String actualizarIndicadorKit) {
		this.actualizarIndicadorKit = actualizarIndicadorKit;
	}
	/**
	 * @return the indicadorPremioWeb
	 */
	public String getIndicadorPremioWeb() {
		return indicadorPremioWeb;
	}
	/**
	 * @param indicadorPremioWeb the indicadorPremioWeb to set
	 */
	public void setIndicadorPremioWeb(String indicadorPremioWeb) {
		this.indicadorPremioWeb = indicadorPremioWeb;
	}
	/**
	 * @return the tipoDespacho
	 */
	public String getTipoDespacho() {
		return tipoDespacho;
	}
	/**
	 * @param tipoDespacho the tipoDespacho to set
	 */
	public void setTipoDespacho(String tipoDespacho) {
		this.tipoDespacho = tipoDespacho;
	}
}