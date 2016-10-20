/*
 * Created on 10/11/2005 12:52:29 PM biz.belcorp.ssicc.model.Idioma
 */
package org.sistema.framework.dao.seguridad.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import org.sistema.framework.dao.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="Idioma.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class Idioma extends AuditableBaseObject implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -860559015169081775L;
	
	private String codigo;

    @NotNull
    public String getCodigo() {
        return codigo;
    }

    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    private String codigoISO;

    @NotNull
    public String getCodigoISO() {
        return codigoISO;
    }

    public void setCodigoISO(String codigoISO) {
        this.codigoISO = codigoISO;
    }

    private String codigoSiCC;

    @NotNull
    public String getCodigoSiCC() {
        return codigoSiCC;
    }

    public void setCodigoSiCC(String codigoSiCC) {
        this.codigoSiCC = codigoSiCC;
    }

    private String descripcion;

    @NotNull
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Idioma)) {
            return false;
        }
        Idioma rhs = (Idioma) object;
        return new EqualsBuilder().append(this.descripcion, rhs.descripcion).append(this.codigoISO, rhs.codigoISO)
                .append(this.codigo, rhs.codigo).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(696209119, -856739281).append(this.descripcion).append(this.codigoISO).append(
                this.codigo).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("codigoISO", this.codigoISO).append(
                "codigo", this.codigo).append("descripcion", this.descripcion).toString();
    }
}
