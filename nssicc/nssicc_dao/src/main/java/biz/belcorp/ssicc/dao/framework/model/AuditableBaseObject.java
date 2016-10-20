/*
 * Created on 08/02/2005 06:05:30 PM
 *
 * biz.belcorp.ssicc.model.AuditableBaseObject
 */
package biz.belcorp.ssicc.dao.framework.model;

import java.io.Serializable;
import biz.belcorp.ssicc.dao.framework.Auditable;

/**
 * This class is used to represent an AuditableBaseObject.
 * 
 * <p>
 * <a href="AuditableBaseObject.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */

public abstract class AuditableBaseObject extends BaseObject implements
        Serializable, Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 11448503514715068L;
	
	
	protected AuditInfo auditInfo;

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.framework.Auditable#getAuditInfo()
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
