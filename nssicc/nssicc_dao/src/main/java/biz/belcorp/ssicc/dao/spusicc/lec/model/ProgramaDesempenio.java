package biz.belcorp.ssicc.dao.spusicc.lec.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class ProgramaDesempenio extends AuditableBaseObject {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPrograma;
	private String codigoTipoDesemp;
	private String descripcionTipoDesemp;
	private String ranInicio;
	private String ranFin;
	private String indActi;
	private String correlativo;
	private String accion;
	@Override
	public String toString() {
		return "ProgramaDesempenio [codigoPais=" + codigoPais
				+ ", codigoPrograma=" + codigoPrograma + ", codigoTipoDesemp="
				+ codigoTipoDesemp + ", ranInicio=" + ranInicio + ", ranFin="
				+ ranFin + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime
				* result
				+ ((codigoTipoDesemp == null) ? 0 : codigoTipoDesemp.hashCode());
		result = prime * result + ((ranFin == null) ? 0 : ranFin.hashCode());
		result = prime * result
				+ ((ranInicio == null) ? 0 : ranInicio.hashCode());
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
		ProgramaDesempenio other = (ProgramaDesempenio) obj;
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
		if (codigoTipoDesemp == null) {
			if (other.codigoTipoDesemp != null)
				return false;
		} else if (!codigoTipoDesemp.equals(other.codigoTipoDesemp))
			return false;
		if (ranFin == null) {
			if (other.ranFin != null)
				return false;
		} else if (!ranFin.equals(other.ranFin))
			return false;
		if (ranInicio == null) {
			if (other.ranInicio != null)
				return false;
		} else if (!ranInicio.equals(other.ranInicio))
			return false;
		return true;
	}
	public String getCodigoPais() {
		return codigoPais;
	}
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	public String getCodigoTipoDesemp() {
		return codigoTipoDesemp;
	}
	public String getRanInicio() {
		return ranInicio;
	}
	public String getRanFin() {
		return ranFin;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	public void setCodigoTipoDesemp(String codigoTipoDesemp) {
		this.codigoTipoDesemp = codigoTipoDesemp;
	}
	public void setRanInicio(String ranInicio) {
		this.ranInicio = ranInicio;
	}
	public void setRanFin(String ranFin) {
		this.ranFin = ranFin;
	}
	public String getDescripcionTipoDesemp() {
		return descripcionTipoDesemp;
	}
	public void setDescripcionTipoDesemp(String descripcionTipoDesemp) {
		this.descripcionTipoDesemp = descripcionTipoDesemp;
	}
	public String getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	public String getIndActi() {
		return indActi;
	}
	public void setIndActi(String indActi) {
		this.indActi = indActi;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}

	
}
