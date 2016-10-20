package org.sistema.framework.web.base.form;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Base ActionForm bean. Used to give child classes readable representation of
 * their properties using toString() method.
 * <p>
 * <a href="BaseForm.java.html"> <i>View Source </i> </a>
 * </p>
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a>
 * @version $Revision: 1.3 $ $Date: 2009/04/15 15:32:57 $
 */
public abstract class BaseForm  implements Serializable {

    private static final long serialVersionUID = -8727684903417295497L;
	protected transient final Log log = LogFactory.getLog(getClass());
	
	protected Map parametrosPantalla = new HashMap();

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

	/**
	 * @return the parametrosPantalla
	 */
	public Map getParametrosPantalla() {
		return parametrosPantalla;
	}

	/**
	 * @param parametrosPantalla the parametrosPantalla to set
	 */
	public void setParametrosPantalla(Map parametrosPantalla) {
		this.parametrosPantalla = parametrosPantalla;
	}
    
    
    
}