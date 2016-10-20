/*
 * Created on 10/02/2005 03:21:02 PM biz.belcorp.ssicc.model.OpcionPK
 */
package biz.belcorp.ssicc.dao.scdf.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * TODO Include class description here.
 * <p>
 * <a href="OpcionPK.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */

public class RegionPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5784871008738508038L;
	/**
     * @uml.property name="codigoMenu" multiplicity="(0 1)"
     */
    private String codigoMenu;

    /**
     * @uml.property name="codigoMenu"
     * @hibernate.property column="MENU_COD_MENU"
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
     * @uml.property name="codigoRol" multiplicity="(0 1)"
     */
    private String codigoRol;

    /**
     * @uml.property name="codigoRol"
     * @hibernate.property column="ROL_COD_ROL"
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
     *  
     */
    public RegionPK() {
    }

    /**
     * @param codigoMenu
     * @param codigoRol
     */
    public RegionPK(String codigoMenu, String codigoRol, String codigo) {
        this.codigoMenu = codigoMenu;
        this.codigoRol = codigoRol;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof RegionPK)) {
            return false;
        }
        RegionPK rhs = (RegionPK) object;
        return new EqualsBuilder().append(this.codigoRol, rhs.codigoRol)
                .append(this.codigoMenu, rhs.codigoMenu).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-515242531, 564779073)
                .append(this.codigoRol).append(this.codigoMenu).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("codigoRol", this.codigoRol).append("codigoMenu",
                        this.codigoMenu).toString();
    }
}