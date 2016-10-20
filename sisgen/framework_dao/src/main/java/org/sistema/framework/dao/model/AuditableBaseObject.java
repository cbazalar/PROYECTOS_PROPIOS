/*
 * Created on 08/02/2005 06:05:30 PM
 *
 * org.sistema.framework.model.AuditableBaseObject
 */
package org.sistema.framework.dao.model;

import java.io.Serializable;
import org.sistema.framework.dao.Auditable;

/**
 * This class is used to represent an AuditableBaseObject.
 * 
 * <p>
 * <a href="AuditableBaseObject.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 */

public abstract class AuditableBaseObject extends BaseObject implements
        Serializable, Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 11448503514715068L;
	
	
	protected AuditInfo auditInfo;

    /* (non-Javadoc)
     * @see org.sistema.framework.dao.framework.Auditable#getAuditInfo()
     */
    public AuditInfo getAuditInfo() {
        if (auditInfo == null) {
            auditInfo = new AuditInfo();
        }

        return auditInfo;
    }

    /**
     * @param auditInfo
     */
    public void setAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }

}
