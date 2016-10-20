package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ClienteHistoricoEstatus extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long oid;
	private Long oidPeriodo;
	private Long oidCliente;
	private Long oidPeriodoFin;
	private Long oidEstatus;
	
	/* INI SA PER-SiCC-2013-0586 */
	private String codigoUsuario;
	/* FIN SA PER-SiCC-2013-0586 */
	
	public ClienteHistoricoEstatus() {
		
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
	 * @return Returns the oidCliente.
	 */
	public Long getOidCliente() {
		return oidCliente;
	}

	/**
	 * @param oidCliente The oidCliente to set.
	 */
	public void setOidCliente(Long oidCliente) {
		this.oidCliente = oidCliente;
	}

	/**
	 * @return Returns the oidEstatus.
	 */
	public Long getOidEstatus() {
		return oidEstatus;
	}

	/**
	 * @param oidEstatus The oidEstatus to set.
	 */
	public void setOidEstatus(Long oidEstatus) {
		this.oidEstatus = oidEstatus;
	}

	/**
	 * @return Returns the oidPeriodo.
	 */
	public Long getOidPeriodo() {
		return oidPeriodo;
	}

	/**
	 * @param oidPeriodo The oidPeriodo to set.
	 */
	public void setOidPeriodo(Long oidPeriodo) {
		this.oidPeriodo = oidPeriodo;
	}

	/**
	 * @return Returns the oidPeriodoFin.
	 */
	public Long getOidPeriodoFin() {
		return oidPeriodoFin;
	}

	/**
	 * @param oidPeriodoFin The oidPeriodoFin to set.
	 */
	public void setOidPeriodoFin(Long oidPeriodoFin) {
		this.oidPeriodoFin = oidPeriodoFin;
	}

	/**
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
}
