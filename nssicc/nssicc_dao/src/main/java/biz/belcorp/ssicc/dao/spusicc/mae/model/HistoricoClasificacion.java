package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class HistoricoClasificacion extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private Long oidClienteHistoricoEstatus;
	private Long oidClasificacion;

	public HistoricoClasificacion() {
		
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
	 * @return Returns the oid.
	 */
	public Long getOid() {
		return oid;
	}

	/**
	 * @param oid The oid to set.
	 */
	public void setOid(Long oid) {
		this.oid = oid;
	}

	/**
	 * @return Returns the oidClasificacion.
	 */
	public Long getOidClasificacion() {
		return oidClasificacion;
	}

	/**
	 * @param oidClasificacion The oidClasificacion to set.
	 */
	public void setOidClasificacion(Long oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
	}

	/**
	 * @return Returns the oidClienteHistoricoEstatus.
	 */
	public Long getOidClienteHistoricoEstatus() {
		return oidClienteHistoricoEstatus;
	}

	/**
	 * @param oidClienteHistoricoEstatus The oidClienteHistoricoEstatus to set.
	 */
	public void setOidClienteHistoricoEstatus(Long oidClienteHistoricoEstatus) {
		this.oidClienteHistoricoEstatus = oidClienteHistoricoEstatus;
	}
	
	
}
