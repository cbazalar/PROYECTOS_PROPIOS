/*
 * Created on 10/10/2005 02:10:21 PM biz.belcorp.ssicc.model.
 */
package biz.belcorp.ssicc.dao.scdf.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="Subgerencia.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */

public class Subgerencia extends AuditableBaseObject implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2267171364559460433L;

	private String codigo;

    private String codigoPais;

    private String estado;

    private String descripcion;

    /**
     * @uml.property name="primaryKey"
     * @uml.associationEnd inverse="optionAccess:biz.belcorp.ssicc.model.SubgerenciaPK"
     *                     multiplicity="(0 1)"
     */
    private SubgerenciaPK primaryKey;

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
     * @return Returns the codigoPais.
     */
    public String getCodigoPais() {
        return codigoPais;
    }

    /**
     * @param codigoPais
     *            The codigoPais to set.
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
     * @uml.property name="primaryKey"
     */
    public SubgerenciaPK getPrimaryKey() {
        return primaryKey;
    }

    /**
     * @uml.property name="primaryKey"
     */
    public void setPrimaryKey(SubgerenciaPK primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Subgerencia)) {
            return false;
        }
        Subgerencia rhs = (Subgerencia) object;
        return new EqualsBuilder().append(this.primaryKey, rhs.primaryKey)
                .append(this.codigoPais, rhs.codigoPais).append(
                        this.descripcion, rhs.descripcion).append(
                        this.auditInfo, rhs.auditInfo).append(this.estado,
                        rhs.estado).append(this.codigo, rhs.codigo).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1477610423, -587949827).append(
                this.primaryKey).append(this.codigoPais).append(
                this.descripcion).append(this.auditInfo).append(this.estado)
                .append(this.codigo).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("codigoPais", this.codigoPais).append("estado",
                        this.estado).append("auditInfo", this.auditInfo)
                .append("codigo", this.codigo).append("primaryKey",
                        this.primaryKey)
                .append("descripcion", this.descripcion).toString();
    }
}