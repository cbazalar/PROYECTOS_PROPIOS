/*
 * Created on 10/11/2005 12:13:26 PM biz.belcorp.ssicc.model.DescripcionMenuPK
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
 * <a href="DescripcionMenuPK.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class DescripcionMenuPK extends BaseObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1878492309643093045L;
	
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
     * @uml.property name="codigoIdioma" multiplicity="(0 1)"
     */
    private String codigoIdioma;

    /**
     * @uml.property name="codigoIdioma"
     * @struts.form-field
     */
    public String getCodigoIdioma() {
        return codigoIdioma;
    }

    /**
     * @uml.property name="codigoIdioma"
     */
    public void setCodigoIdioma(String codigoIdioma) {
        this.codigoIdioma = codigoIdioma;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof DescripcionMenuPK)) {
            return false;
        }
        DescripcionMenuPK rhs = (DescripcionMenuPK) object;
        return new EqualsBuilder().append(this.codigoIdioma, rhs.codigoIdioma).append(this.codigoMenu, rhs.codigoMenu)
                .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1051537615, -1294161117).append(this.codigoIdioma).append(this.codigoMenu)
                .toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("codigoMenu", this.codigoMenu).append(
                "codigoIdioma", this.codigoIdioma).toString();
    }
}
