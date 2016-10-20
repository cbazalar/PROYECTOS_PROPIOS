package biz.belcorp.ssicc.dao.spusicc.lec.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class CampanaExigencia extends AuditableBaseObject {
	
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoPrograma;
	private String tipoExigencia;
	private String campanaInicio;
	private String campanaFin;
	private String indActi;
	private String accion;
	
	
	@Override
	public String toString() {
		return "CampanaExigencia [codigoPais=" + codigoPais
				+ ", codigoPrograma=" + codigoPrograma + ", tipoExigencia="
				+ tipoExigencia + ", campanaInicio=" + campanaInicio
				+ ", campanaFin=" + campanaFin + ", indActi=" + indActi + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((campanaFin == null) ? 0 : campanaFin.hashCode());
		result = prime * result
				+ ((campanaInicio == null) ? 0 : campanaInicio.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime * result + ((indActi == null) ? 0 : indActi.hashCode());
		result = prime * result
				+ ((tipoExigencia == null) ? 0 : tipoExigencia.hashCode());
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
		CampanaExigencia other = (CampanaExigencia) obj;
		if (campanaFin == null) {
			if (other.campanaFin != null)
				return false;
		} else if (!campanaFin.equals(other.campanaFin))
			return false;
		if (campanaInicio == null) {
			if (other.campanaInicio != null)
				return false;
		} else if (!campanaInicio.equals(other.campanaInicio))
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
		if (indActi == null) {
			if (other.indActi != null)
				return false;
		} else if (!indActi.equals(other.indActi))
			return false;
		if (tipoExigencia == null) {
			if (other.tipoExigencia != null)
				return false;
		} else if (!tipoExigencia.equals(other.tipoExigencia))
			return false;
		return true;
	}
	public String getCodigoPais() {
		return codigoPais;
	}
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	public String getTipoExigencia() {
		return tipoExigencia;
	}
	public String getCampanaInicio() {
		return campanaInicio;
	}
	public String getCampanaFin() {
		return campanaFin;
	}
	public String getIndActi() {
		return indActi;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	public void setTipoExigencia(String tipoExigencia) {
		this.tipoExigencia = tipoExigencia;
	}
	public void setCampanaInicio(String campanaInicio) {
		this.campanaInicio = campanaInicio;
	}
	public void setCampanaFin(String campanaFin) {
		this.campanaFin = campanaFin;
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
