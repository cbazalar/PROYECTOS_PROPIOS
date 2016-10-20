/*
 * Created on 10/11/2005 11:49:02 AM biz.belcorp.ssicc.model.AccesoPK
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
 * <a href="AccesoPK.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class AccesoPK extends BaseObject implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6247872782462706240L;
	/**
     * @uml.property name="codigoMenu" multiplicity="(0 1)"
     */
    private String codigoMenu;

    /**
     * @uml.property name="codigoMenu"
     * @struts.form-field
     */
    public String getCodigoMenu() {
        return codigoMenu;
    }

    /**
     * @uml.property name="codigoMenu"
     */
    public void setCodigoMenu(String codigoMenu) {
        this.codigoMenu = codigoMenu;
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
    public AccesoPK() {
        super();
    }

    /**
     * @param codigoMenu
     * @param codigoPais
     * @param codigoRol
     */
    public AccesoPK(String codigoMenu, String codigoPais, String codigoRol) {
        super();
        this.codigoMenu = codigoMenu;
        this.codigoPais = codigoPais;
        this.codigoRol = codigoRol;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof AccesoPK)) {
            return false;
        }
        AccesoPK rhs = (AccesoPK) object;
        return new EqualsBuilder().append(this.codigoPais, rhs.codigoPais).append(this.codigoRol, rhs.codigoRol)
                .append(this.codigoMenu, rhs.codigoMenu).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-872354265, 445274525).append(this.codigoPais).append(this.codigoRol).append(
                this.codigoMenu).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("codigoPais", this.codigoPais).append(
                "codigoRol", this.codigoRol).append("codigoMenu", this.codigoMenu).toString();
    }
}
