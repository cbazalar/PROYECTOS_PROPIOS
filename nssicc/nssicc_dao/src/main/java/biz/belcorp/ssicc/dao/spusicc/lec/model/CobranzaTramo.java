package biz.belcorp.ssicc.dao.spusicc.lec.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class CobranzaTramo extends AuditableBaseObject {
private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPrograma;
	private String codTramo;
	private String numTramo;
	private String descripcion;
	private String estado;
	private String correlativo;
	private String diasExtra;
	private String nroDias;
	private String accion;
	public String getDiasExtra() {
		return diasExtra;
	}
	public String getNroDias() {
		return nroDias;
	}
	public void setDiasExtra(String diasExtra) {
		this.diasExtra = diasExtra;
	}
	public void setNroDias(String nroDias) {
		this.nroDias = nroDias;
	}
	public String getCodigoPais() {
		return codigoPais;
	}
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	public String getCodTramo() {
		return codTramo;
	}
	public String getNumTramo() {
		return numTramo;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	public void setCodTramo(String codTramo) {
		this.codTramo = codTramo;
	}
	public void setNumTramo(String numTramo) {
		this.numTramo = numTramo;
	}
	@Override
	public String toString() {
		return "CobranzaTramo [codigoPais=" + codigoPais + ", codigoPrograma="
				+ codigoPrograma + ", codTramo=" + codTramo  + ", descripcion=" + descripcion + ", numTramo="
				+ numTramo + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codTramo == null) ? 0 : codTramo.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime * result
				+ ((numTramo == null) ? 0 : numTramo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CobranzaTramo other = (CobranzaTramo) obj;
		if (codTramo == null) {
			if (other.codTramo != null)
				return false;
		} else if (!codTramo.equals(other.codTramo))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (codigoPrograma == null) {
			if (other.codigoPrograma != null)
				return false;
		} else if (!codigoPrograma.equals(other.codigoPrograma))
			return false;
		if (numTramo == null) {
			if (other.numTramo != null)
				return false;
		} else if (!numTramo.equals(other.numTramo))
			return false;
		return true;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	
}
