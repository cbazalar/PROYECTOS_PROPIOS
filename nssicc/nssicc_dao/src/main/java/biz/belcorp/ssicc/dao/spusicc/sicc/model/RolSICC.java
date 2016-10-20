/*
 * Created on 23-03-2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.spusicc.sicc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="Base.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 */
public class RolSICC implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2279571540807703258L;

	private Long oid;

	private String descripcion;

	
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
