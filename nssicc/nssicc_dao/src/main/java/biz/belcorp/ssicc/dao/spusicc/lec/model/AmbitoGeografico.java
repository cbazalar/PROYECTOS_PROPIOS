package biz.belcorp.ssicc.dao.spusicc.lec.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;



public class AmbitoGeografico extends AuditableBaseObject {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPrograma;
	private String pais;
	private String region;
	private String zona;
	private String codigoAmbGeo;
	private String codigoRegion;
	private String codigoZona;
	private String secuencia;
	private String indPais;
	private String campanaProceso;
	private String correlativo;
	private String indicadorExclu;
	private String codTipoUso;
	private String accion;
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	public String getPais() {
		return pais;
	}
	public String getRegion() {
		return region;
	}
	public String getZona() {
		return zona;
	}
	public String getCodigoAmbGeo() {
		return codigoAmbGeo;
	}
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public void setCodigoAmbGeo(String codigoAmbGeo) {
		this.codigoAmbGeo = codigoAmbGeo;
	}
	public String getCodigoRegion() {
		return codigoRegion;
	}
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	public String getCodigoZona() {
		return codigoZona;
	}
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoAmbGeo == null) ? 0 : codigoAmbGeo.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime * result
				+ ((codigoRegion == null) ? 0 : codigoRegion.hashCode());
		result = prime * result
				+ ((codigoZona == null) ? 0 : codigoZona.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + ((zona == null) ? 0 : zona.hashCode());
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
		AmbitoGeografico other = (AmbitoGeografico) obj;
		if (codigoAmbGeo == null) {
			if (other.codigoAmbGeo != null)
				return false;
		} else if (!codigoAmbGeo.equals(other.codigoAmbGeo))
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
		if (codigoRegion == null) {
			if (other.codigoRegion != null)
				return false;
		} else if (!codigoRegion.equals(other.codigoRegion))
			return false;
		if (codigoZona == null) {
			if (other.codigoZona != null)
				return false;
		} else if (!codigoZona.equals(other.codigoZona))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (zona == null) {
			if (other.zona != null)
				return false;
		} else if (!zona.equals(other.zona))
			return false;
		return true;
	}
	
	
	
	@Override
	public String toString() {
		return "AmbitoGeografico [codigoPais=" + codigoPais
				+ ", codigoPrograma=" + codigoPrograma + ", pais=" + pais
				+ ", region=" + region + ", zona=" + zona + ", codigoAmbGeo="
				+ codigoAmbGeo + ", codigoRegion=" + codigoRegion
				+ ", codigoZona=" + codigoZona + ", secuencia=" + secuencia
				+ ", indPais=" + indPais + ", campanaProceso=" + campanaProceso
				+ ", correlativo=" + correlativo + ", indicadorExclu="
				+ indicadorExclu + ", codTipoUso=" + codTipoUso + "]";
	}
	public String getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}
	public String getCampanaProceso() {
		return campanaProceso;
	}
	public void setCampanaProceso(String campanaProceso) {
		this.campanaProceso = campanaProceso;
	}
	public String getIndPais() {
		return indPais;
	}
	public void setIndPais(String indPais) {
		this.indPais = indPais;
	}
	public String getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	public String getIndicadorExclu() {
		return indicadorExclu;
	}
	public void setIndicadorExclu(String indicadorExclu) {
		this.indicadorExclu = indicadorExclu;
	}
	public String getCodTipoUso() {
		return codTipoUso;
	}
	public void setCodTipoUso(String codTipoUso) {
		this.codTipoUso = codTipoUso;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}

}
