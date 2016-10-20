/*
 * Created on 17-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.sistema.framework.dao.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="Base.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 */
public class BaseOID implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6249529187066140985L;

	private Integer oid;

	private String descripcion;

	
	/**
	 * @return Returns the oid.
	 */
	public Integer getOid() {
		return oid;
	}

	/**
	 * @param oid The oid to set.
	 */
	public void setOid(Integer oid) {
		this.oid = oid;
	}

	/**
	 * @return Returns the descripcion.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            The descripcion to set.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("oid", this.oid).append(
				"descripcion", this.descripcion).toString();
	}
}
