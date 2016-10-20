package biz.belcorp.ssicc.dao.spusicc.lec.model;

import java.math.BigDecimal;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Yahir Rivas L.
 *
 */
public class TramoObjetivoCobranza extends AuditableBaseObject {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPrograma;
	private String nroTramo;
	private String nroDias;
	private String nroDiasExtras;
	private String porcCob;
	private String procMinCob;
	private String codTipoPrem;
	private String montPrem;
	private String estado;
	private String secuencia;
	private String codTipoMedi;
	private String codTramo;
	private String region;
	private String zona;
	private String codRegion;
	private String codZona;
	private String correlativo;
	private String accion;
	
	private BigDecimal dporcCob;
	private BigDecimal dprocMinCob;
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
	 * @return the nroDias
	 */
	public String getNroDias() {
		return nroDias;
	}
	/**
	 * @param nroDias the nroDias to set
	 */
	public void setNroDias(String nroDias) {
		this.nroDias = nroDias;
	}
	/**
	 * @return the porcCob
	 */
	public String getPorcCob() {
		return porcCob;
	}
	/**
	 * @param porcCob the porcCob to set
	 */
	public void setPorcCob(String porcCob) {
		this.porcCob = porcCob;
	}
	/**
	 * @return the procMinCob
	 */
	public String getProcMinCob() {
		return procMinCob;
	}
	/**
	 * @param procMinCob the procMinCob to set
	 */
	public void setProcMinCob(String procMinCob) {
		this.procMinCob = procMinCob;
	}
	/**
	 * @return the codTipoPrem
	 */
	public String getCodTipoPrem() {
		return codTipoPrem;
	}
	/**
	 * @param codTipoPrem the codTipoPrem to set
	 */
	public void setCodTipoPrem(String codTipoPrem) {
		this.codTipoPrem = codTipoPrem;
	}
	/**
	 * @return the montPrem
	 */
	public String getMontPrem() {
		return montPrem;
	}
	/**
	 * @param montPrem the montPrem to set
	 */
	public void setMontPrem(String montPrem) {
		this.montPrem = montPrem;
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
		result = prime * result + ((codTipoPrem == null) ? 0 : codTipoPrem.hashCode());
		result = prime * result + ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result + ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((montPrem == null) ? 0 : montPrem.hashCode());
		result = prime * result + ((nroDias == null) ? 0 : nroDias.hashCode());
		result = prime * result + ((nroTramo == null) ? 0 : nroTramo.hashCode());
		result = prime * result + ((porcCob == null) ? 0 : porcCob.hashCode());
		result = prime * result + ((procMinCob == null) ? 0 : procMinCob.hashCode());
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
		TramoObjetivoCobranza other = (TramoObjetivoCobranza) obj;
		if (codTipoPrem == null) {
			if (other.codTipoPrem != null)
				return false;
		} else if (!codTipoPrem.equals(other.codTipoPrem))
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
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (montPrem == null) {
			if (other.montPrem != null)
				return false;
		} else if (!montPrem.equals(other.montPrem))
			return false;
		if (nroDias == null) {
			if (other.nroDias != null)
				return false;
		} else if (!nroDias.equals(other.nroDias))
			return false;
		if (nroTramo == null) {
			if (other.nroTramo != null)
				return false;
		} else if (!nroTramo.equals(other.nroTramo))
			return false;
		if (porcCob == null) {
			if (other.porcCob != null)
				return false;
		} else if (!porcCob.equals(other.porcCob))
			return false;
		if (procMinCob == null) {
			if (other.procMinCob != null)
				return false;
		} else if (!procMinCob.equals(other.procMinCob))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TramoObjetivoCobranza [codigoPais=" + codigoPais + ", codigoPrograma=" + codigoPrograma + ", nroTramo=" + nroTramo + ", nroDias=" + nroDias + ", porcCob=" + porcCob + ", procMinCob=" + procMinCob + ", codTipoPrem=" + codTipoPrem + ", montPrem=" + montPrem + ", estado=" + estado + "]";
	}
	public String getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}
	public String getCodTipoMedi() {
		return codTipoMedi;
	}
	public void setCodTipoMedi(String codTipoMedi) {
		this.codTipoMedi = codTipoMedi;
	}
	public String getCodTramo() {
		return codTramo;
	}
	public void setCodTramo(String codTramo) {
		this.codTramo = codTramo;
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
	
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return the zona
	 */
	public String getZona() {
		return zona;
	}
	/**
	 * @param zona the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}
	/**
	 * @return the codRegion
	 */
	public String getCodRegion() {
		return codRegion;
	}
	/**
	 * @param codRegion the codRegion to set
	 */
	public void setCodRegion(String codRegion) {
		this.codRegion = codRegion;
	}
	/**
	 * @return the codZona
	 */
	public String getCodZona() {
		return codZona;
	}
	/**
	 * @param codZona the codZona to set
	 */
	public void setCodZona(String codZona) {
		this.codZona = codZona;
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
	 * @return the dporcCob
	 */
	public BigDecimal getDporcCob() {
		return dporcCob;
	}
	/**
	 * @param dporcCob the dporcCob to set
	 */
	public void setDporcCob(BigDecimal dporcCob) {
		this.dporcCob = dporcCob;
	}
	/**
	 * @return the dprocMinCob
	 */
	public BigDecimal getDprocMinCob() {
		return dprocMinCob;
	}
	/**
	 * @param dprocMinCob the dprocMinCob to set
	 */
	public void setDprocMinCob(BigDecimal dprocMinCob) {
		this.dprocMinCob = dprocMinCob;
	}
	
	
	
	
    

}