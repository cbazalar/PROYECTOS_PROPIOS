/*
 * Created on 10/11/2005 10:23:42 AM biz.belcorp.ssicc.model.PerfilPK
 */
package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.BaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="PerfilPK.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class PerfilPK extends BaseObject implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4809695347488221301L;
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
     * Constructor por defecto
     */
    public PerfilPK() {
        super();
    }

    /**
     * @param codigoUsuario
     * @param codigoPais
     * @param codigoRol
     */
    public PerfilPK(String codigoUsuario, String codigoPais, String codigoRol) {
        super();
        this.codigoUsuario = codigoUsuario;
        this.codigoPais = codigoPais;
        this.codigoRol = codigoRol;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof PerfilPK)) {
            return false;
        }
        PerfilPK rhs = (PerfilPK) object;
        return new EqualsBuilder().append(this.codigoPais, rhs.codigoPais).append(this.codigoRol, rhs.codigoRol)
                .append(this.codigoUsuario, rhs.codigoUsuario).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-872354265, 445274525).append(this.codigoPais).append(this.codigoRol).append(
                this.codigoUsuario).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("codigoPais", this.codigoPais).append(
                "codigoRol", this.codigoRol).append("codigoUsuario", this.codigoUsuario).toString();
    }
}
