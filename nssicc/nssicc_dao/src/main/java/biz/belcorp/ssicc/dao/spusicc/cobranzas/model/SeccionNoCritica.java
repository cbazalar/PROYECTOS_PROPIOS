package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class SeccionNoCritica extends AuditableBaseObject implements Serializable{

	
	private static final long serialVersionUID = -5497614393320232081L;
	
	private String codEtapaDeuda;
	private String codigoRegion;
	private String codigoZona;
	private String codigoSeccion;
	private int indicaActivo;
	private String observacion;
	
	public SeccionNoCritica(){
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codEtapaDeuda == null) ? 0 : codEtapaDeuda.hashCode());
		result = prime * result
				+ ((codigoRegion == null) ? 0 : codigoRegion.hashCode());
		result = prime * result
				+ ((codigoSeccion == null) ? 0 : codigoSeccion.hashCode());
		result = prime * result
				+ ((codigoZona == null) ? 0 : codigoZona.hashCode());
		result = prime * result + indicaActivo;
		result = prime * result
				+ ((observacion == null) ? 0 : observacion.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SeccionNoCritica other = (SeccionNoCritica) obj;
		if (codEtapaDeuda == null) {
			if (other.codEtapaDeuda != null)
				return false;
		} else if (!codEtapaDeuda.equals(other.codEtapaDeuda))
			return false;
		if (codigoRegion == null) {
			if (other.codigoRegion != null)
				return false;
		} else if (!codigoRegion.equals(other.codigoRegion))
			return false;
		if (codigoSeccion == null) {
			if (other.codigoSeccion != null)
				return false;
		} else if (!codigoSeccion.equals(other.codigoSeccion))
			return false;
		if (codigoZona == null) {
			if (other.codigoZona != null)
				return false;
		} else if (!codigoZona.equals(other.codigoZona))
			return false;
		if (indicaActivo != other.indicaActivo)
			return false;
		if (observacion == null) {
			if (other.observacion != null)
				return false;
		} else if (!observacion.equals(other.observacion))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SeccionNoCritica [codEtapaDeuda=" + codEtapaDeuda
				+ ", codigoRegion=" + codigoRegion + ", codigoZona="
				+ codigoZona + ", codigoSeccion=" + codigoSeccion
				+ ", indicaActivo=" + indicaActivo + ", observacion="
				+ observacion + "]";
	}
	/**
	 * @return the codEtapaDeuda
	 */
	public String getCodEtapaDeuda() {
		return codEtapaDeuda;
	}
	/**
	 * @param codEtapaDeuda the codEtapaDeuda to set
	 */
	public void setCodEtapaDeuda(String codEtapaDeuda) {
		this.codEtapaDeuda = codEtapaDeuda;
	}
	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return the codigoSeccion
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}
	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}
	/**
	 * @return the indicaActivo
	 */
	public int getIndicaActivo() {
		return indicaActivo;
	}
	/**
	 * @param indicaActivo the indicaActivo to set
	 */
	public void setIndicaActivo(int indicaActivo) {
		this.indicaActivo = indicaActivo;
	}
	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}
	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	

}
