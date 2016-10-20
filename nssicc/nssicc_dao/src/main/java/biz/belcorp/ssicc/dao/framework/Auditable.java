/*
 * Created on 08/02/2005 06:03:22 PM
 *
 * biz.belcorp.ssicc.model.Auditable
 */
package biz.belcorp.ssicc.dao.framework;

import biz.belcorp.ssicc.dao.framework.model.AuditInfo;



/**
 * This class is used to represent an Auditable.
 * <p> 
 * <a href="Auditable.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */

public interface Auditable { 
	
    /**
     * Instances must always return a non-null instance of AuditInfo   
     */
    public AuditInfo getAuditInfo();
}
