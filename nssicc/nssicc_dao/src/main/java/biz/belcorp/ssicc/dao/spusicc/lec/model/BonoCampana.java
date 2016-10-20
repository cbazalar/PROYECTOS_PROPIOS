package biz.belcorp.ssicc.dao.spusicc.lec.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class BonoCampana extends AuditableBaseObject {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPrograma;
	private String campanaLanzamiento;
	private String indActi;
	private String correlativo;
	private String accion;
	public String getCodigoPais() {
		return codigoPais;
	}
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	public String getCampanaLanzamiento() {
		return campanaLanzamiento;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	public void setCampanaLanzamiento(String campanaLanzamiento) {
		this.campanaLanzamiento = campanaLanzamiento;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((campanaLanzamiento == null) ? 0 : campanaLanzamiento
						.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
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
		BonoCampana other = (BonoCampana) obj;
		if (campanaLanzamiento == null) {
			if (other.campanaLanzamiento != null)
				return false;
		} else if (!campanaLanzamiento.equals(other.campanaLanzamiento))
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
		
		return true;
	}
	
	@Override
	public String toString() {
		return "BonoCampana [codigoPais=" + codigoPais + ", codigoPrograma="
				+ codigoPrograma + ", campanaLanzamiento=" + campanaLanzamiento
				+ ", indActi=" + indActi + "]";
	}
	public String getIndActi() {
		return indActi;
	}
	public void setIndActi(String indActi) {
		this.indActi = indActi;
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
