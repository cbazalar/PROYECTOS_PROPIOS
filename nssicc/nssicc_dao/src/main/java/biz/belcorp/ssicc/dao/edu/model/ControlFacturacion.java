package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class ControlFacturacion extends AuditableBaseObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6980946731326248620L;

	private String id;
	
	private String codigoPais;

	private String codigoPeriodo;

	private String fechaProceso;

	private String codigoEmpresa;
	
	private String estadoCampanha;

	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
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

	public String getEstadoCampanha() {
		return estadoCampanha;
	}

	public void setEstadoCampanha(String estadoCampanha) {
		this.estadoCampanha = estadoCampanha;
	}

	public String getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("codigoPais", this.codigoPais)
				.append("auditInfo", this.auditInfo).append("estadoCampanha",
						this.estadoCampanha).append("codigoPeriodo",
						this.codigoPeriodo).append("codigoEmpresa",
						this.codigoEmpresa).append("fechaProceso",
						this.fechaProceso).append("id", this.id).toString();
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ControlFacturacion)) {
			return false;
		}
		ControlFacturacion rhs = (ControlFacturacion) object;
		return new EqualsBuilder().append(
				this.codigoEmpresa, rhs.codigoEmpresa).append(this.id, rhs.id)
				.append(this.fechaProceso, rhs.fechaProceso).append(
						this.estadoCampanha, rhs.estadoCampanha).append(
						this.codigoPeriodo, rhs.codigoPeriodo).append(
						this.codigoPais, rhs.codigoPais).append(this.auditInfo,
						rhs.auditInfo).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-391484411, 1136916517).append(this.codigoEmpresa).append(this.id)
				.append(this.fechaProceso).append(this.estadoCampanha).append(
						this.codigoPeriodo).append(this.codigoPais).append(
						this.auditInfo).toHashCode();
	}
	
	

}
