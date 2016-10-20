/*
 * Created on 17-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;
import biz.belcorp.ssicc.dao.model.Pais;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="Sistema.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class Sistema extends AuditableBaseObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7049538885267985393L;
	private String codigo;	
	private String codigoPais;
	private String descripcion;
	private String estado;	
	private Pais pais = new Pais();
		
	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo The codigo to set.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return Returns the descripcion.
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion The descripcion to set.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return Returns the estado.
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado The estado to set.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return Returns the pais.
	 */
	public Pais getPais() {
		return pais;
	}
	/**
	 * @param pais The pais to set.
	 */
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Sistema)) {
			return false;
		}
		Sistema rhs = (Sistema) object;
		return new EqualsBuilder().append(this.codigoPais, rhs.codigoPais)
				.append(this.descripcion, rhs.descripcion).append(
						this.auditInfo, rhs.auditInfo).append(this.estado,
						rhs.estado).append(this.pais, rhs.pais).append(
						this.codigo, rhs.codigo).isEquals();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("codigoPais", this.codigoPais).append("estado",
						this.estado).append("auditInfo", this.auditInfo)
				.append("codigo", this.codigo).append("pais", this.pais)
				.append("descripcion", this.descripcion).toString();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(734611737, -11646577)
				.append(this.codigoPais).append(this.descripcion).append(
						this.auditInfo).append(this.estado).append(this.pais)
				.append(this.codigo).toHashCode();
	}

}