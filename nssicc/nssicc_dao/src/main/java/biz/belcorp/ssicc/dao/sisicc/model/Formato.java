/*
 * Created on 18-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="Formato.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class Formato extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2236028199170713820L;

	private String codigo;

	private String descripcion;

	private String valor;

	private String estado;

	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            The codigo to set.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	 * @return Returns the estado.
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            The estado to set.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return Returns the valor.
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            The valor to set.
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Formato)) {
			return false;
		}
		Formato rhs = (Formato) object;
		return new EqualsBuilder().append(this.descripcion, rhs.descripcion)
				.append(this.auditInfo, rhs.auditInfo).append(this.valor,
						rhs.valor).append(this.estado, rhs.estado).append(
						this.codigo, rhs.codigo).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-252938609, 334293761).append(
				this.descripcion).append(this.auditInfo).append(this.valor)
				.append(this.estado).append(this.codigo).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("codigo", codigo).append(
				"descripcion", descripcion).append("estado", estado).append(
				"valor", valor).toString();
	}
}