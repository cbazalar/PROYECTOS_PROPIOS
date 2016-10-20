package biz.belcorp.ssicc.dao.spisicc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="EtiquetaEstatus.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:ghuertas@sigcomt.com">Gonzalo Huertas</a>
 * 
 *                      
 */
public class EtiquetaEstatus extends AuditableBaseObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private String oid;
	private String oidEstatus;
	private String oidEtiqueta;
    
	
	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 * 
	 *  
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}

	/**
	 * @return the oidEstatus
	 */
	public String getOidEstatus() {
		return oidEstatus;
	}

	/**
	 * @param oidEstatus the oidEstatus to set
	 * 
	 *  
	 */
	public void setOidEstatus(String oidEstatus) {
		this.oidEstatus = oidEstatus;
	}	
	
	/**
	 * @return the oidEtiqueta
	 */
	public String getOidEtiqueta() {
		return oidEtiqueta;
	}

	/**
	 * @param oidEtiqueta the oidEtiqueta to set
	 * 
	 *  
	 */
	public void setOidEtiqueta(String oidEtiqueta) {
		this.oidEtiqueta = oidEtiqueta;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}