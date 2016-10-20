package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class EquivalenciaMatrizServicio extends AuditableBaseObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7925429059136789802L;

	private String id;
	
    private String codigoPais              ;
    private String codigoPeriodo           ;
    private String tipoCurso;
    private String codigoClasificacion;
    private String codigoCurso           ;
    
    private String codigoVenta             ;
    private String valorUnitario           ;
    private String descripcionProducto     ;
    private String codigoProducto          ;
    private String codigoEmpresa       ;
    private String estadoRegistro          ;
    
    private String estadoCuv          ;    
    
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
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}
	public void setCodigoEmpresa(String nivelPriorizacion) {
		this.codigoEmpresa = nivelPriorizacion;
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
		if (!(object instanceof EquivalenciaMatrizServicio)) {
			return false;
		}
		EquivalenciaMatrizServicio rhs = (EquivalenciaMatrizServicio) object;
		return new EqualsBuilder().append(
				this.codigoProducto, rhs.codigoProducto).append(
				this.codigoPais, rhs.codigoPais).append(
				this.descripcionProducto, rhs.descripcionProducto).append(
				this.codigoVenta, rhs.codigoVenta).append(this.auditInfo,
				rhs.auditInfo).append(this.estadoRegistro, rhs.estadoRegistro)
				.append(this.codigoEmpresa, rhs.codigoEmpresa).append(
						this.valorUnitario, rhs.valorUnitario).append(this.id,
						rhs.id).append(this.codigoPeriodo, rhs.codigoPeriodo)
				.isEquals();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("nivelPriorizacion",
				this.codigoEmpresa).append("codigoPais", this.codigoPais)
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
				this.estadoRegistro).append(this.codigoEmpresa).append(
				this.valorUnitario).append(this.id).append(this.codigoPeriodo)
				.toHashCode();
	}
	public String getCodigoCurso() {
		return codigoCurso;
	}
	public void setCodigoCurso(String codigoNivel) {
		this.codigoCurso = codigoNivel;
	}
	public String getEstadoCuv() {
		return estadoCuv;
	}
	public void setEstadoCuv(String estadoCuv) {
		this.estadoCuv = estadoCuv;
	}
	/**
	 * @return Returns the codigoClasificacion.
	 */
	public String getCodigoClasificacion() {
		return codigoClasificacion;
	}
	/**
	 * @param codigoClasificacion The codigoClasificacion to set.
	 */
	public void setCodigoClasificacion(String codigoClasificacion) {
		this.codigoClasificacion = codigoClasificacion;
	}
	/**
	 * @return Returns the tipoCurso.
	 */
	public String getTipoCurso() {
		return tipoCurso;
	}
	/**
	 * @param tipoCurso The tipoCurso to set.
	 */
	public void setTipoCurso(String tipoCurso) {
		this.tipoCurso = tipoCurso;
	}
	
	
	
}
