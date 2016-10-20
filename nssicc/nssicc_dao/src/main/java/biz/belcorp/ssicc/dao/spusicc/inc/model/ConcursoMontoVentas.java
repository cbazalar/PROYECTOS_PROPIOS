package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ConcursoMontoVentas extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private java.math.BigDecimal cantidadMontoVenta;
	private Long oidConcurso;
	private Long oidTipoMontoVenta;
	
	public ConcursoMontoVentas() {
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
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
	 * @return the cantidadMontoVenta
	 */
	public java.math.BigDecimal getCantidadMontoVenta() {
		return cantidadMontoVenta;
	}

	/**
	 * @param cantidadMontoVenta the cantidadMontoVenta to set
	 */
	public void setCantidadMontoVenta(java.math.BigDecimal cantidadMontoVenta) {
		this.cantidadMontoVenta = cantidadMontoVenta;
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
	 * @return the oidTipoMontoVenta
	 */
	public Long getOidTipoMontoVenta() {
		return oidTipoMontoVenta;
	}

	/**
	 * @param oidTipoMontoVenta the oidTipoMontoVenta to set
	 */
	public void setOidTipoMontoVenta(Long oidTipoMontoVenta) {
		this.oidTipoMontoVenta = oidTipoMontoVenta;
	}

}
