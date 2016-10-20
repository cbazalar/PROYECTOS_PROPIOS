package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class ProductoAgregacion extends AuditableBaseObject implements Serializable{
	
	private static final long serialVersionUID = 63120364242272761L;

	private String id;
	
    private String codigoPais              ;
    private String codigoPeriodo           ;
    private String codigoVenta             ;
    private String valorUnitario           ;
    private String descripcionProducto     ;
    private String codigoProducto          ;
    private String nivelPriorizacion       ;
    private String estadoRegistro          ;
    
    private String indicadorExisteReemplazo;
    
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
	 * @return the indicadorExisteReemplazo
	 */
	public String getIndicadorExisteReemplazo() {
		return indicadorExisteReemplazo;
	}
	/**
	 * @param indicadorExisteReemplazo the indicadorExisteReemplazo to set
	 */
	public void setIndicadorExisteReemplazo(String indicadorExisteReemplazo) {
		this.indicadorExisteReemplazo = indicadorExisteReemplazo;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ProductoAgregacion)) {
			return false;
		}
		ProductoAgregacion rhs = (ProductoAgregacion) object;
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
	
	
}
