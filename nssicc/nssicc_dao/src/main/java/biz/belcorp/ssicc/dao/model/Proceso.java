/*
 * Created on 10/11/2005 09:46:47 AM biz.belcorp.ssicc.model.Proceso
 */
package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="Proceso.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class Proceso extends AuditableBaseObject implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3684505871220903678L;
	/**
     * @uml.property name="codigo" multiplicity="(0 1)"
     */
    private String codigo;

    /**
     * @uml.property name="codigo"
     * @struts.form-field
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @uml.property name="codigo"
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @uml.property name="descripcion" multiplicity="(0 1)"
     */
    private String descripcion;

    /**
     * @uml.property name="descripcion"
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @uml.property name="descripcion"
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @uml.property name="estado" multiplicity="(0 1)"
     */
    private String estado;

    /**
     * @uml.property name="estado"
     * @struts.form-field
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @uml.property name="estado"
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Proceso)) {
            return false;
        }
        Proceso rhs = (Proceso) object;
        return new EqualsBuilder().append(this.descripcion, rhs.descripcion).append(this.auditInfo, rhs.auditInfo)
                .append(this.estado, rhs.estado).append(this.codigo, rhs.codigo).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(729290315, 363340395).append(this.descripcion).append(this.auditInfo).append(
                this.estado).append(this.codigo).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("estado", this.estado).append(
                "auditInfo", this.auditInfo).append("codigo", this.codigo).append("descripcion", this.descripcion)
                .toString();
    }
}
