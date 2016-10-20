package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ConcursoPremioArticulo extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private Integer numeroUnidades;
	private Long oidNivelPremiacion;
	
	
	public ConcursoPremioArticulo() {
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
	 * @return the numeroUnidades
	 */
	public Integer getNumeroUnidades() {
		return numeroUnidades;
	}

	/**
	 * @param numeroUnidades the numeroUnidades to set
	 */
	public void setNumeroUnidades(Integer numeroUnidades) {
		this.numeroUnidades = numeroUnidades;
	}

	/**
	 * @return the oidNivelPremiacion
	 */
	public Long getOidNivelPremiacion() {
		return oidNivelPremiacion;
	}

	/**
	 * @param oidNivelPremiacion the oidNivelPremiacion to set
	 */
	public void setOidNivelPremiacion(Long oidNivelPremiacion) {
		this.oidNivelPremiacion = oidNivelPremiacion;
	}

}
