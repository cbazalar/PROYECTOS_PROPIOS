package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ConcursoEstatusVenta extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long oid;
	private Long oidConcurso;
	private Long oidEstatusCliente;
	private Long oidPeriodoDesde;
	private Long oidPeriodoHasta;
	
	private String codigoPeriodoDesde;
	private String codigoPeriodoHasta;
	private String descripcionEstatusCliente;
	
	public ConcursoEstatusVenta() {
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConcursoEstatusVenta [oid=" + oid + ", oidConcurso="
				+ oidConcurso + ", oidEstatusCliente=" + oidEstatusCliente
				+ ", oidPeriodoDesde=" + oidPeriodoDesde + ", oidPeriodoHasta="
				+ oidPeriodoHasta + ", codigoPeriodoDesde="
				+ codigoPeriodoDesde + ", codigoPeriodoHasta="
				+ codigoPeriodoHasta + ", descripcionEstatusCliente="
				+ descripcionEstatusCliente + "]";
	}

	/**
	 * @return the oid
	 */
	public Long getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(Long oid) {
		this.oid = oid;
	}

	/**
	 * @return the oidConcurso
	 */
	public Long getOidConcurso() {
		return oidConcurso;
	}

	/**
	 * @param oidConcurso the oidConcurso to set
	 */
	public void setOidConcurso(Long oidConcurso) {
		this.oidConcurso = oidConcurso;
	}

	/**
	 * @return the oidPeriodoDesde
	 */
	public Long getOidPeriodoDesde() {
		return oidPeriodoDesde;
	}

	/**
	 * @param oidPeriodoDesde the oidPeriodoDesde to set
	 */
	public void setOidPeriodoDesde(Long oidPeriodoDesde) {
		this.oidPeriodoDesde = oidPeriodoDesde;
	}

	/**
	 * @return the oidPeriodoHasta
	 */
	public Long getOidPeriodoHasta() {
		return oidPeriodoHasta;
	}

	/**
	 * @param oidPeriodoHasta the oidPeriodoHasta to set
	 */
	public void setOidPeriodoHasta(Long oidPeriodoHasta) {
		this.oidPeriodoHasta = oidPeriodoHasta;
	}

	/**
	 * @return the codigoPeriodoDesde
	 */
	public String getCodigoPeriodoDesde() {
		return codigoPeriodoDesde;
	}

	/**
	 * @param codigoPeriodoDesde the codigoPeriodoDesde to set
	 */
	public void setCodigoPeriodoDesde(String codigoPeriodoDesde) {
		this.codigoPeriodoDesde = codigoPeriodoDesde;
	}

	/**
	 * @return the codigoPeriodoHasta
	 */
	public String getCodigoPeriodoHasta() {
		return codigoPeriodoHasta;
	}

	/**
	 * @param codigoPeriodoHasta the codigoPeriodoHasta to set
	 */
	public void setCodigoPeriodoHasta(String codigoPeriodoHasta) {
		this.codigoPeriodoHasta = codigoPeriodoHasta;
	}

	/**
	 * @return the oidEstatusCliente
	 */
	public Long getOidEstatusCliente() {
		return oidEstatusCliente;
	}

	/**
	 * @param oidEstatusCliente the oidEstatusCliente to set
	 */
	public void setOidEstatusCliente(Long oidEstatusCliente) {
		this.oidEstatusCliente = oidEstatusCliente;
	}

	/**
	 * @return the descripcionEstatusCliente
	 */
	public String getDescripcionEstatusCliente() {
		return descripcionEstatusCliente;
	}

	/**
	 * @param descripcionEstatusCliente the descripcionEstatusCliente to set
	 */
	public void setDescripcionEstatusCliente(String descripcionEstatusCliente) {
		this.descripcionEstatusCliente = descripcionEstatusCliente;
	}

}
