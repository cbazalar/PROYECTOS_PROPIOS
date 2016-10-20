/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author jvelasquez
 *
 */
public class MinimoNuevas extends AuditableBaseObject implements
	Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String oidMinimoNueva;
	private String codigoRegion;
	private String codigoZona;
	private String cantidadMinima;
	private String descripcionRegion;
	
	public String getOidMinimoNueva() {
		return oidMinimoNueva;
	}

	public void setOidMinimoNueva(String oidMinimoNueva) {
		this.oidMinimoNueva = oidMinimoNueva;
	}

	public String getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	public String getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	public String getCantidadMinima() {
		return cantidadMinima;
	}

	public void setCantidadMinima(String cantidadMinima) {
		this.cantidadMinima = cantidadMinima;
	}

	public String getDescripcionRegion() {
		return descripcionRegion;
	}

	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
