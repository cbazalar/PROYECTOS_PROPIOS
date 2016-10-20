package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class PedidoAnuladoFacturado extends AuditableBaseObject implements Serializable{
	
	private static final long serialVersionUID = -6800843527108652072L;
	
	private String codigoPais	        ;
	private String codigoPeriodo 		;
	private String codigoCliente		;
	private String nombreCliente		;
	private String numeroLote 		;
	private String fechaFacturacion 	;
	private String numeroBoleta 		;
	private String fechaAnulacion 		;
	private String indicadorAnulacion	;
	
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
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
	public String getFechaAnulacion() {
		return fechaAnulacion;
	}
	public void setFechaAnulacion(String fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	public String getIndicadorAnulacion() {
		return indicadorAnulacion;
	}
	public void setIndicadorAnulacion(String indicadorAnulacion) {
		this.indicadorAnulacion = indicadorAnulacion;
	}
	public String getNumeroBoleta() {
		return numeroBoleta;
	}
	public void setNumeroBoleta(String numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}
	public String getNumeroLote() {
		return numeroLote;
	}
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof PedidoAnuladoFacturado)) {
			return false;
		}
		PedidoAnuladoFacturado rhs = (PedidoAnuladoFacturado) object;
		return new EqualsBuilder().append(
				this.numeroBoleta, rhs.numeroBoleta).append(this.codigoCliente,
				rhs.codigoCliente).append(this.fechaAnulacion,
				rhs.fechaAnulacion).append(this.numeroLote, rhs.numeroLote)
				.append(this.fechaFacturacion, rhs.fechaFacturacion).append(
						this.indicadorAnulacion, rhs.indicadorAnulacion)
				.append(this.codigoPeriodo, rhs.codigoPeriodo).append(
						this.codigoPais, rhs.codigoPais).append(this.auditInfo,
						rhs.auditInfo).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1032561859, 1329005349).append(this.numeroBoleta).append(
				this.codigoCliente).append(this.fechaAnulacion).append(
				this.numeroLote).append(this.fechaFacturacion).append(
				this.indicadorAnulacion).append(this.codigoPeriodo).append(
				this.codigoPais).append(this.auditInfo).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("codigoPais", this.codigoPais)
				.append("codigoCliente", this.codigoCliente).append(
						"auditInfo", this.auditInfo).append("codigoPeriodo",
						this.codigoPeriodo).append("numeroLote",
						this.numeroLote).append("indicadorAnulacion",
						this.indicadorAnulacion).append("fechaFacturacion",
						this.fechaFacturacion).append("numeroBoleta",
						this.numeroBoleta).append("fechaAnulacion",
						this.fechaAnulacion).toString();
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	
	
	
}
