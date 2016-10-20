package biz.belcorp.ssicc.dao.spusicc.lec.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class BonoLanzamientoProducto extends AuditableBaseObject {
	
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoPrograma;
	private String campanaLanzamiento;
	private String tipoMedicion;
	private String codTipoMedicion;
	private String nroLanzamiento;
	private String nroSecuenciaProducto;
	private String codSap;
	private String correlativo;
	private String descripcionProducto;
	private String estado;
	private String accion;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((campanaLanzamiento == null) ? 0 : campanaLanzamiento
						.hashCode());
		result = prime * result + ((codSap == null) ? 0 : codSap.hashCode());
		result = prime * result
				+ ((codTipoMedicion == null) ? 0 : codTipoMedicion.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime * result
				+ ((nroLanzamiento == null) ? 0 : nroLanzamiento.hashCode());
		result = prime
				* result
				+ ((nroSecuenciaProducto == null) ? 0 : nroSecuenciaProducto
						.hashCode());
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
		BonoLanzamientoProducto other = (BonoLanzamientoProducto) obj;
		if (campanaLanzamiento == null) {
			if (other.campanaLanzamiento != null)
				return false;
		} else if (!campanaLanzamiento.equals(other.campanaLanzamiento))
			return false;
		if (codSap == null) {
			if (other.codSap != null)
				return false;
		} else if (!codSap.equals(other.codSap))
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
		if (nroSecuenciaProducto == null) {
			if (other.nroSecuenciaProducto != null)
				return false;
		} else if (!nroSecuenciaProducto.equals(other.nroSecuenciaProducto))
			return false;
		if (tipoMedicion == null) {
			if (other.tipoMedicion != null)
				return false;
		} else if (!tipoMedicion.equals(other.tipoMedicion))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "BonoLanzamientoProducto [codigoPais=" + codigoPais
				+ ", codigoPrograma=" + codigoPrograma
				+ ", campanaLanzamiento=" + campanaLanzamiento
				+ ", tipoMedicion=" + tipoMedicion + ", codTipoMedicion="
				+ codTipoMedicion + ", nroLanzamiento=" + nroLanzamiento
				+ ", nroSecuenciaProducto=" + nroSecuenciaProducto
				+ ", codSap=" + codSap + ", correlativo=" + correlativo
				+ ", descripcionProducto=" + descripcionProducto + ", estado="
				+ estado + "]";
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
	public String getNroSecuenciaProducto() {
		return nroSecuenciaProducto;
	}
	public String getCodSap() {
		return codSap;
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
	public void setNroSecuenciaProducto(String nroSecuenciaProducto) {
		this.nroSecuenciaProducto = nroSecuenciaProducto;
	}
	public void setCodSap(String codSap) {
		this.codSap = codSap;
	}
	public String getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}

	

}
