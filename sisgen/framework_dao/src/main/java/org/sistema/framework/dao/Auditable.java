/*
 * Created on 08/02/2005 06:03:22 PM
 *
 * org.sistema.framework.model.Auditable
 */
package org.sistema.framework.dao;

import org.sistema.framework.dao.model.AuditInfo;



/**
 * This class is used to represent an Auditable.
 * <p> 
 * <a href="Auditable.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 */

public interface Auditable { 
	
    /**
     * Instances must always return a non-null instance of AuditInfo   
     */
    public AuditInfo getAuditInfo();
}
