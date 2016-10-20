package biz.belcorp.ssicc.dao.spisicc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ClasificacionVIP.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:ghuertas@sigcomt.com">Gonzalo Huertas</a>
 * 
 *                      
 */
public class ClasificacionVIP extends AuditableBaseObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private String oid;
	private String oidTipoClasificacion;
	private String oidClasificacion;
	private String valorLAR;
	private String numeroOCS;
    
	
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
	 * @return the oidTipoClasificacion
	 */
	public String getOidTipoClasificacion() {
		return oidTipoClasificacion;
	}

	/**
	 * @param oidTipoClasificacion the oidTipoClasificacion to set
	 * 
	 *  
	 */
	public void setOidTipoClasificacion(String oidTipoClasificacion) {
		this.oidTipoClasificacion = oidTipoClasificacion;
	}
	
	/**
	 * @return the oidClasificacion
	 */
	public String getOidClasificacion() {
		return oidClasificacion;
	}

	/**
	 * @param oidClasificacion the oidClasificacion to set
	 * 
	 *  
	 */
	public void setOidClasificacion(String oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
	}	
	
	/**
	 * @return the valorLAR
	 */
	public String getValorLAR() {
		return valorLAR;
	}

	/**
	 * @param valorLAR the valorLAR to set
	 * 
	 *  
	 */
	public void setValorLAR(String valorLAR) {
		this.valorLAR = valorLAR;
	}
	
	/**
	 * @return the numeroOCS
	 */
	public String getNumeroOCS() {
		return numeroOCS;
	}

	/**
	 * @param numeroOCS the numeroOCS to set
	 * 
	 *  
	 */
	public void setNumeroOCS(String numeroOCS) {
		this.numeroOCS = numeroOCS;
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