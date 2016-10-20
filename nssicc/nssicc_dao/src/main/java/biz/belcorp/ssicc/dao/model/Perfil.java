/*
 * Created on 10/11/2005 09:40:52 AM biz.belcorp.ssicc.model.Perfil
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
 * <a href="Perfil.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class Perfil extends AuditableBaseObject implements Serializable {

    
	/**
	 * 
	 */
	private static final long serialVersionUID = -935517948649227879L;
	
	/**
     * @uml.property name="codigoUsuario" multiplicity="(0 1)"
     */
    private String codigoUsuario;

    /**
     * @uml.property name="codigoUsuario"
     * @struts.form-field
     */
    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    /**
     * @uml.property name="codigoUsuario"
     */
    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    /**
     * @uml.property name="codigoPais" multiplicity="(0 1)"
     */
    private String codigoPais;

    /**
     * @uml.property name="codigoPais"
     * @struts.form-field
     */
    public String getCodigoPais() {
        return codigoPais;
    }

    /**
     * @uml.property name="codigoPais"
     */
    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    /**
     * @uml.property name="codigoRol" multiplicity="(0 1)"
     */
    private String codigoRol;

    /**
     * @uml.property name="codigoRol"
     * @struts.form-field
     */
    public String getCodigoRol() {
        return codigoRol;
    }

    /**
     * @uml.property name="codigoRol"
     */
    public void setCodigoRol(String codigoRol) {
        this.codigoRol = codigoRol;
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
     * @uml.property name="rol"
     * @uml.associationEnd inverse="user:biz.belcorp.ssicc.model.Rol"
     *                     multiplicity= "(0 1)"
     */
    private Rol rol = new Rol();

    /**
     * @uml.property name="rol"
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * @uml.property name="rol"
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * @uml.property name="usuario"
     * @uml.associationEnd inverse="user:biz.belcorp.ssicc.model.Rol"
     *                     multiplicity= "(0 1)"
     */
    private Usuario usuario = new Usuario();

    /**
     * @uml.property name="usuario"
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @uml.property name="usuario"
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Perfil)) {
            return false;
        }
        Perfil rhs = (Perfil) object;
        return new EqualsBuilder().append(this.codigoRol, rhs.codigoRol).append(this.codigoUsuario, rhs.codigoUsuario)
                .append(this.auditInfo, rhs.auditInfo).append(this.estado, rhs.estado).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1262579699, 202692323).append(this.codigoRol).append(this.codigoUsuario).append(
                this.auditInfo).append(this.estado).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("estado", this.estado).append(
                "codigoRol", this.codigoRol).append("auditInfo", this.auditInfo).append("codigoUsuario",
                this.codigoUsuario).toString();
    }
}
