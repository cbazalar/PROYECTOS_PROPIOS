package biz.belcorp.ssicc.dao.spisicc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="Etiqueta.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:ghuertas@sigcomt.com">Gonzalo Huertas</a>
 * 
 *                      
 */
public class Etiqueta extends AuditableBaseObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private String oid;
	private String valorEtiqueta;
	private String indicadorEstado;
    
	
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
	 * @return the valorEtiqueta
	 */
	public String getValorEtiqueta() {
		return valorEtiqueta;
	}

	/**
	 * @param valorEtiqueta the valorEtiqueta to set
	 * 
	 *  
	 */
	public void setValorEtiqueta(String valorEtiqueta) {
		this.valorEtiqueta = valorEtiqueta;
	}

	/**
	 * @return the indicadorEstado
	 */
	public String getIndicadorEstado() {
		return indicadorEstado;
	}

	/**
	 * @param indicadorEstado the indicadorEstado to set
	 * 
	 *  
	 */
	public void setIndicadorEstado(String indicadorEstado) {
		this.indicadorEstado = indicadorEstado;
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