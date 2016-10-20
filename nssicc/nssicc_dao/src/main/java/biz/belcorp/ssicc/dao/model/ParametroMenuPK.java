/*
 * Created on 10/11/2005 12:00:13 PM biz.belcorp.ssicc.model.ParametroMenuPK
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
 * <a href="ParametroMenuPK.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class ParametroMenuPK extends BaseObject implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1600307003324004307L;
	/**
     * 
     */
    private String codigoMenu;

    /**
     * */
    public String getCodigoMenu() {
        return codigoMenu;
    }

    /**
     * 
     */
    public void setCodigoMenu(String codigoMenu) {
        this.codigoMenu = codigoMenu;
    }

    /**
     * 
     */
    private long numero;

    /**
     * 
     * */
    public long getNumero() {
        return numero;
    }

    /**
     * 
     */
    public void setNumero(long numero) {
        this.numero = numero;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof ParametroMenuPK)) {
            return false;
        }
        ParametroMenuPK rhs = (ParametroMenuPK) object;
        return new EqualsBuilder().append(this.numero, rhs.numero).append(this.codigoMenu, rhs.codigoMenu).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1698589611, -469595623).append(this.numero).append(this.codigoMenu).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("numero", this.numero).append(
                "codigoMenu", this.codigoMenu).toString();
    }
}
