/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.zon.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.BaseObject;

/**
 * @author itocto
 *
 */
public class EstructuraGeopolitica extends BaseObject implements Serializable {

	private String oid;
	private String codigoOrden;
	private String indicadorBorrado;
	private String oidPais;
	private String oidDivisionPolitica;
	private String fechaActualizacion;
	private String descripcion;
	
	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}

	/**
	 * @return the codigoOrden
	 */
	public String getCodigoOrden() {
		return codigoOrden;
	}

	/**
	 * @param codigoOrden the codigoOrden to set
	 */
	public void setCodigoOrden(String codigoOrden) {
		this.codigoOrden = codigoOrden;
	}

	/**
	 * @return the indicadorBorrado
	 */
	public String getIndicadorBorrado() {
		return indicadorBorrado;
	}

	/**
	 * @param indicadorBorrado the indicadorBorrado to set
	 */
	public void setIndicadorBorrado(String indicadorBorrado) {
		this.indicadorBorrado = indicadorBorrado;
	}

	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

	/**
	 * @return the oidDivisionPolitica
	 */
	public String getOidDivisionPolitica() {
		return oidDivisionPolitica;
	}

	/**
	 * @param oidDivisionPolitica the oidDivisionPolitica to set
	 */
	public void setOidDivisionPolitica(String oidDivisionPolitica) {
		this.oidDivisionPolitica = oidDivisionPolitica;
	}

	/**
	 * @return the fechaActualizacion
	 */
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}

	/**
	 * @param fechaActualizacion the fechaActualizacion to set
	 */
	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		EstructuraGeopolitica other = (EstructuraGeopolitica) obj;
		if (codigoOrden == null) {
			if (other.codigoOrden != null)
				return false;
		} else if (!codigoOrden.equals(other.codigoOrden))
			return false;
		if (fechaActualizacion == null) {
			if (other.fechaActualizacion != null)
				return false;
		} else if (!fechaActualizacion.equals(other.fechaActualizacion))
			return false;
		if (indicadorBorrado == null) {
			if (other.indicadorBorrado != null)
				return false;
		} else if (!indicadorBorrado.equals(other.indicadorBorrado))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (oidDivisionPolitica == null) {
			if (other.oidDivisionPolitica != null)
				return false;
		} else if (!oidDivisionPolitica.equals(other.oidDivisionPolitica))
			return false;
		if (oidPais == null) {
			if (other.oidPais != null)
				return false;
		} else if (!oidPais.equals(other.oidPais))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 13;
		result = prime * result
				+ ((codigoOrden == null) ? 0 : codigoOrden.hashCode());
		result = prime
				* result
				+ ((fechaActualizacion == null) ? 0 : fechaActualizacion
						.hashCode());
		result = prime
				* result
				+ ((indicadorBorrado == null) ? 0 : indicadorBorrado.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime
				* result
				+ ((oidDivisionPolitica == null) ? 0 : oidDivisionPolitica
						.hashCode());
		result = prime * result + ((oidPais == null) ? 0 : oidPais.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EstructuraGeopolitica [codigoOrden=" + codigoOrden
				+ ", fechaActualizacion=" + fechaActualizacion
				+ ", indicadorBorrado=" + indicadorBorrado + ", oid=" + oid
				+ ", oidDivisionPolitica=" + oidDivisionPolitica + ", oidPais="
				+ oidPais + "]";
	}

}
