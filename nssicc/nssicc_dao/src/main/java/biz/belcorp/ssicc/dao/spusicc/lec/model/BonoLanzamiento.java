package biz.belcorp.ssicc.dao.spusicc.lec.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class BonoLanzamiento  extends AuditableBaseObject {
	
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoPrograma;
	private String campanaLanzamiento;
	private String tipoMedicion;
	private String codTipoMedicion;
	private String nroLanzamiento;
	private String indActi;
	private String correlativo;
	private String accion;
	
	@Override
	public String toString() {
		return "BonoLanzamiento [codigoPais=" + codigoPais
				+ ", codigoPrograma=" + codigoPrograma
				+ ", campanaLanzamiento=" + campanaLanzamiento
				+ ", tipoMedicion=" + tipoMedicion + ", codTipoMedicion="
				+ codTipoMedicion + ", nroLanzamiento=" + nroLanzamiento
				+ ", indActi=" + indActi + "]";
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
				+ ((codTipoMedicion == null) ? 0 : codTipoMedicion.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime * result
				+ ((nroLanzamiento == null) ? 0 : nroLanzamiento.hashCode());
		result = prime * result
				+ ((tipoMedicion == null) ? 0 : tipoMedicion.hashCode());
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
		BonoLanzamiento other = (BonoLanzamiento) obj;
		if (campanaLanzamiento == null) {
			if (other.campanaLanzamiento != null)
				return false;
		} else if (!campanaLanzamiento.equals(other.campanaLanzamiento))
			return false;
		if (codTipoMedicion == null) {
			if (other.codTipoMedicion != null)
				return false;
		} else if (!codTipoMedicion.equals(other.codTipoMedicion))
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
		if (nroLanzamiento == null) {
			if (other.nroLanzamiento != null)
				return false;
		} else if (!nroLanzamiento.equals(other.nroLanzamiento))
			return false;
		if (tipoMedicion == null) {
			if (other.tipoMedicion != null)
				return false;
		} else if (!tipoMedicion.equals(other.tipoMedicion))
			return false;
		return true;
	}
	public String getCodigoPais() {
		return codigoPais;
	}
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	public String getCampanaLanzamiento() {
		return campanaLanzamiento;
	}
	public String getTipoMedicion() {
		return tipoMedicion;
	}
	public String getCodTipoMedicion() {
		return codTipoMedicion;
	}
	public String getNroLanzamiento() {
		return nroLanzamiento;
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
	public void setTipoMedicion(String tipoMedicion) {
		this.tipoMedicion = tipoMedicion;
	}
	public void setCodTipoMedicion(String codTipoMedicion) {
		this.codTipoMedicion = codTipoMedicion;
	}
	public void setNroLanzamiento(String nroLanzamiento) {
		this.nroLanzamiento = nroLanzamiento;
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
