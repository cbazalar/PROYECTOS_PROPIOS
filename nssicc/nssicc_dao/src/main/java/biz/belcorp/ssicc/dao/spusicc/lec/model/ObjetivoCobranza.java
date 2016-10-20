package biz.belcorp.ssicc.dao.spusicc.lec.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Yahir Rivas L.
 *
 */
public class ObjetivoCobranza extends AuditableBaseObject {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPrograma;
	private String ambitoGeogra;
	private String descTipoMedi;
	private String codTipoMedi;
	private String nroDiasExtras;
	private String nroTramo;
	private String estado;
	private String nroDias;
	private String porcCob;
	private String procMinCob;
	private String numAmbGeo;
	private String secuencia;
	private String region;
	private String zona;
	private String codRegion;
	private String codZona;
	private String indPais;
	private String campanaProceso;
	private String correlativo;
	private String accion;
	public String getRegion() {
		return region;
	}
	public String getZona() {
		return zona;
	}
	public String getCodRegion() {
		return codRegion;
	}
	public String getCodZona() {
		return codZona;
	}
	public String getIndPais() {
		return indPais;
	}
	public String getCampanaProceso() {
		return campanaProceso;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public void setCodRegion(String codRegion) {
		this.codRegion = codRegion;
	}
	public void setCodZona(String codZona) {
		this.codZona = codZona;
	}
	public void setIndPais(String indPais) {
		this.indPais = indPais;
	}
	public void setCampanaProceso(String campanaProceso) {
		this.campanaProceso = campanaProceso;
	}
	
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoPrograma
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	/**
	 * @param codigoPrograma the codigoPrograma to set
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	/**
	 * @return the ambitoGeogra
	 */
	public String getAmbitoGeogra() {
		return ambitoGeogra;
	}
	/**
	 * @param ambitoGeogra the ambitoGeogra to set
	 */
	public void setAmbitoGeogra(String ambitoGeogra) {
		this.ambitoGeogra = ambitoGeogra;
	}
	/**
	 * @return the descTipoMedi
	 */
	public String getDescTipoMedi() {
		return descTipoMedi;
	}
	/**
	 * @param descTipoMedi the descTipoMedi to set
	 */
	public void setDescTipoMedi(String descTipoMedi) {
		this.descTipoMedi = descTipoMedi;
	}
	/**
	 * @return the nroDiasExtras
	 */
	public String getNroDiasExtras() {
		return nroDiasExtras;
	}
	/**
	 * @param nroDiasExtras the nroDiasExtras to set
	 */
	public void setNroDiasExtras(String nroDiasExtras) {
		this.nroDiasExtras = nroDiasExtras;
	}
	/**
	 * @return the nroTramo
	 */
	public String getNroTramo() {
		return nroTramo;
	}
	/**
	 * @param nroTramo the nroTramo to set
	 */
	public void setNroTramo(String nroTramo) {
		this.nroTramo = nroTramo;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ambitoGeogra == null) ? 0 : ambitoGeogra.hashCode());
		result = prime * result + ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result + ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime * result + ((descTipoMedi == null) ? 0 : descTipoMedi.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((nroDiasExtras == null) ? 0 : nroDiasExtras.hashCode());
		result = prime * result + ((nroTramo == null) ? 0 : nroTramo.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjetivoCobranza other = (ObjetivoCobranza) obj;
		if (ambitoGeogra == null) {
			if (other.ambitoGeogra != null)
				return false;
		} else if (!ambitoGeogra.equals(other.ambitoGeogra))
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
		if (descTipoMedi == null) {
			if (other.descTipoMedi != null)
				return false;
		} else if (!descTipoMedi.equals(other.descTipoMedi))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (nroDiasExtras == null) {
			if (other.nroDiasExtras != null)
				return false;
		} else if (!nroDiasExtras.equals(other.nroDiasExtras))
			return false;
		if (nroTramo == null) {
			if (other.nroTramo != null)
				return false;
		} else if (!nroTramo.equals(other.nroTramo))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "ObjetivoCobranza [codigoPais=" + codigoPais
				+ ", codigoPrograma=" + codigoPrograma + ", ambitoGeogra="
				+ ambitoGeogra + ", descTipoMedi=" + descTipoMedi
				+ ", codTipoMedi=" + codTipoMedi + ", nroDiasExtras="
				+ nroDiasExtras + ", nroTramo=" + nroTramo + ", estado="
				+ estado + ", nroDias=" + nroDias + ", porcCob=" + porcCob
				+ ", procMinCob=" + procMinCob + ", numAmbGeo=" + numAmbGeo
				+ ", secuencia=" + secuencia + ", region=" + region + ", zona="
				+ zona + ", codRegion=" + codRegion + ", codZona=" + codZona
				+ ", indPais=" + indPais + ", campanaProceso=" + campanaProceso
				+ "]";
	}
	public String getNroDias() {
		return nroDias;
	}
	public void setNroDias(String nroDias) {
		this.nroDias = nroDias;
	}
	public String getPorcCob() {
		return porcCob;
	}
	public void setPorcCob(String porcCob) {
		this.porcCob = porcCob;
	}
	public String getProcMinCob() {
		return procMinCob;
	}
	public void setProcMinCob(String procMinCob) {
		this.procMinCob = procMinCob;
	}
	public String getNumAmbGeo() {
		return numAmbGeo;
	}
	public void setNumAmbGeo(String numAmbGeo) {
		this.numAmbGeo = numAmbGeo;
	}
	public String getCodTipoMedi() {
		return codTipoMedi;
	}
	public void setCodTipoMedi(String codTipoMedi) {
		this.codTipoMedi = codTipoMedi;
	}
	public String getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
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