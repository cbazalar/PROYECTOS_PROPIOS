/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Sigcomt
 *
 */
public class ClasificacionParaINCDetalle extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1997331827100286808L;
	private Integer secuencialDetalle;
	private Integer secuencialCabecera;
	private Integer oidSubtipocliente;
	private Integer oidTipocliente;
	private Integer oidTipoClasificacion;
	private Integer oidClasificacion;
	
	private String codigoSubtipocliente;
	private String codigoTipocliente;
	private String codigoTipoClasificacion;
	private String codigoClasificacion;
	
	private String descripcionSubtipocliente;
	private String descripcionTipocliente;
	private String descripcionTipoClasificacion;
	private String descripcionClasificacion;
	
	private String  descripcion;

	
	/**
	 * @return the secuencialDetalle
	 */
	public Integer getSecuencialDetalle() {
		return secuencialDetalle;
	}

	/**
	 * @param secuencialDetalle the secuencialDetalle to set
	 */
	public void setSecuencialDetalle(Integer secuencialDetalle) {
		this.secuencialDetalle = secuencialDetalle;
	}

	/**
	 * @return the secuencialCabecera
	 */
	public Integer getSecuencialCabecera() {
		return secuencialCabecera;
	}

	/**
	 * @param secuencialCabecera the secuencialCabecera to set
	 */
	public void setSecuencialCabecera(Integer secuencialCabecera) {
		this.secuencialCabecera = secuencialCabecera;
	}

	/**
	 * @return the oidSubtipocliente
	 */
	public Integer getOidSubtipocliente() {
		return oidSubtipocliente;
	}

	/**
	 * @param oidSubtipocliente the oidSubtipocliente to set
	 */
	public void setOidSubtipocliente(Integer oidSubtipocliente) {
		this.oidSubtipocliente = oidSubtipocliente;
	}

	/**
	 * @return the oidTipocliente
	 */
	public Integer getOidTipocliente() {
		return oidTipocliente;
	}

	/**
	 * @param oidTipocliente the oidTipocliente to set
	 */
	public void setOidTipocliente(Integer oidTipocliente) {
		this.oidTipocliente = oidTipocliente;
	}

	/**
	 * @return the oidTipoClasificacion
	 */
	public Integer getOidTipoClasificacion() {
		return oidTipoClasificacion;
	}

	/**
	 * @param oidTipoClasificacion the oidTipoClasificacion to set
	 */
	public void setOidTipoClasificacion(Integer oidTipoClasificacion) {
		this.oidTipoClasificacion = oidTipoClasificacion;
	}

	/**
	 * @return the oidClasificacion
	 */
	public Integer getOidClasificacion() {
		return oidClasificacion;
	}

	/**
	 * @param oidClasificacion the oidClasificacion to set
	 */
	public void setOidClasificacion(Integer oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
	}

	/**
	 * @return the codigoSubtipocliente
	 */
	public String getCodigoSubtipocliente() {
		return codigoSubtipocliente;
	}

	/**
	 * @param codigoSubtipocliente the codigoSubtipocliente to set
	 */
	public void setCodigoSubtipocliente(String codigoSubtipocliente) {
		this.codigoSubtipocliente = codigoSubtipocliente;
	}

	/**
	 * @return the codigoTipocliente
	 */
	public String getCodigoTipocliente() {
		return codigoTipocliente;
	}

	/**
	 * @param codigoTipocliente the codigoTipocliente to set
	 */
	public void setCodigoTipocliente(String codigoTipocliente) {
		this.codigoTipocliente = codigoTipocliente;
	}

	/**
	 * @return the codigoTipoClasificacion
	 */
	public String getCodigoTipoClasificacion() {
		return codigoTipoClasificacion;
	}

	/**
	 * @param codigoTipoClasificacion the codigoTipoClasificacion to set
	 */
	public void setCodigoTipoClasificacion(String codigoTipoClasificacion) {
		this.codigoTipoClasificacion = codigoTipoClasificacion;
	}

	/**
	 * @return the codigoClasificacion
	 */
	public String getCodigoClasificacion() {
		return codigoClasificacion;
	}

	/**
	 * @param codigoClasificacion the codigoClasificacion to set
	 */
	public void setCodigoClasificacion(String codigoClasificacion) {
		this.codigoClasificacion = codigoClasificacion;
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

	/**
	 * @return the descripcionSubtipocliente
	 */
	public String getDescripcionSubtipocliente() {
		return descripcionSubtipocliente;
	}

	/**
	 * @param descripcionSubtipocliente the descripcionSubtipocliente to set
	 */
	public void setDescripcionSubtipocliente(String descripcionSubtipocliente) {
		this.descripcionSubtipocliente = descripcionSubtipocliente;
	}

	/**
	 * @return the descripcionTipocliente
	 */
	public String getDescripcionTipocliente() {
		return descripcionTipocliente;
	}

	/**
	 * @param descripcionTipocliente the descripcionTipocliente to set
	 */
	public void setDescripcionTipocliente(String descripcionTipocliente) {
		this.descripcionTipocliente = descripcionTipocliente;
	}

	/**
	 * @return the descripcionTipoClasificacion
	 */
	public String getDescripcionTipoClasificacion() {
		return descripcionTipoClasificacion;
	}

	/**
	 * @param descripcionTipoClasificacion the descripcionTipoClasificacion to set
	 */
	public void setDescripcionTipoClasificacion(String descripcionTipoClasificacion) {
		this.descripcionTipoClasificacion = descripcionTipoClasificacion;
	}

	/**
	 * @return the descripcionClasificacion
	 */
	public String getDescripcionClasificacion() {
		return descripcionClasificacion;
	}

	/**
	 * @param descripcionClasificacion the descripcionClasificacion to set
	 */
	public void setDescripcionClasificacion(String descripcionClasificacion) {
		this.descripcionClasificacion = descripcionClasificacion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClasificacionParaINCDetalle [secuencialDetalle="
				+ secuencialDetalle + ", secuencialCabecera="
				+ secuencialCabecera + ", oidSubtipocliente="
				+ oidSubtipocliente + ", oidTipocliente=" + oidTipocliente
				+ ", oidTipoClasificacion=" + oidTipoClasificacion
				+ ", oidClasificacion=" + oidClasificacion
				+ ", codigoSubtipocliente=" + codigoSubtipocliente
				+ ", codigoTipocliente=" + codigoTipocliente
				+ ", codigoTipoClasificacion=" + codigoTipoClasificacion
				+ ", codigoClasificacion=" + codigoClasificacion
				+ ", descripcionSubtipocliente=" + descripcionSubtipocliente
				+ ", descripcionTipocliente=" + descripcionTipocliente
				+ ", descripcionTipoClasificacion="
				+ descripcionTipoClasificacion + ", descripcionClasificacion="
				+ descripcionClasificacion + ", descripcion=" + descripcion
				+ "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codigoClasificacion == null) ? 0 : codigoClasificacion
						.hashCode());
		result = prime
				* result
				+ ((codigoSubtipocliente == null) ? 0 : codigoSubtipocliente
						.hashCode());
		result = prime
				* result
				+ ((codigoTipoClasificacion == null) ? 0
						: codigoTipoClasificacion.hashCode());
		result = prime
				* result
				+ ((codigoTipocliente == null) ? 0 : codigoTipocliente
						.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime
				* result
				+ ((descripcionClasificacion == null) ? 0
						: descripcionClasificacion.hashCode());
		result = prime
				* result
				+ ((descripcionSubtipocliente == null) ? 0
						: descripcionSubtipocliente.hashCode());
		result = prime
				* result
				+ ((descripcionTipoClasificacion == null) ? 0
						: descripcionTipoClasificacion.hashCode());
		result = prime
				* result
				+ ((descripcionTipocliente == null) ? 0
						: descripcionTipocliente.hashCode());
		result = prime
				* result
				+ ((oidClasificacion == null) ? 0 : oidClasificacion.hashCode());
		result = prime
				* result
				+ ((oidSubtipocliente == null) ? 0 : oidSubtipocliente
						.hashCode());
		result = prime
				* result
				+ ((oidTipoClasificacion == null) ? 0 : oidTipoClasificacion
						.hashCode());
		result = prime * result
				+ ((oidTipocliente == null) ? 0 : oidTipocliente.hashCode());
		result = prime
				* result
				+ ((secuencialCabecera == null) ? 0 : secuencialCabecera
						.hashCode());
		result = prime
				* result
				+ ((secuencialDetalle == null) ? 0 : secuencialDetalle
						.hashCode());
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
		ClasificacionParaINCDetalle other = (ClasificacionParaINCDetalle) obj;
		if (codigoClasificacion == null) {
			if (other.codigoClasificacion != null)
				return false;
		} else if (!codigoClasificacion.equals(other.codigoClasificacion))
			return false;
		if (codigoSubtipocliente == null) {
			if (other.codigoSubtipocliente != null)
				return false;
		} else if (!codigoSubtipocliente.equals(other.codigoSubtipocliente))
			return false;
		if (codigoTipoClasificacion == null) {
			if (other.codigoTipoClasificacion != null)
				return false;
		} else if (!codigoTipoClasificacion
				.equals(other.codigoTipoClasificacion))
			return false;
		if (codigoTipocliente == null) {
			if (other.codigoTipocliente != null)
				return false;
		} else if (!codigoTipocliente.equals(other.codigoTipocliente))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (descripcionClasificacion == null) {
			if (other.descripcionClasificacion != null)
				return false;
		} else if (!descripcionClasificacion
				.equals(other.descripcionClasificacion))
			return false;
		if (descripcionSubtipocliente == null) {
			if (other.descripcionSubtipocliente != null)
				return false;
		} else if (!descripcionSubtipocliente
				.equals(other.descripcionSubtipocliente))
			return false;
		if (descripcionTipoClasificacion == null) {
			if (other.descripcionTipoClasificacion != null)
				return false;
		} else if (!descripcionTipoClasificacion
				.equals(other.descripcionTipoClasificacion))
			return false;
		if (descripcionTipocliente == null) {
			if (other.descripcionTipocliente != null)
				return false;
		} else if (!descripcionTipocliente.equals(other.descripcionTipocliente))
			return false;
		if (oidClasificacion == null) {
			if (other.oidClasificacion != null)
				return false;
		} else if (!oidClasificacion.equals(other.oidClasificacion))
			return false;
		if (oidSubtipocliente == null) {
			if (other.oidSubtipocliente != null)
				return false;
		} else if (!oidSubtipocliente.equals(other.oidSubtipocliente))
			return false;
		if (oidTipoClasificacion == null) {
			if (other.oidTipoClasificacion != null)
				return false;
		} else if (!oidTipoClasificacion.equals(other.oidTipoClasificacion))
			return false;
		if (oidTipocliente == null) {
			if (other.oidTipocliente != null)
				return false;
		} else if (!oidTipocliente.equals(other.oidTipocliente))
			return false;
		if (secuencialCabecera == null) {
			if (other.secuencialCabecera != null)
				return false;
		} else if (!secuencialCabecera.equals(other.secuencialCabecera))
			return false;
		if (secuencialDetalle == null) {
			if (other.secuencialDetalle != null)
				return false;
		} else if (!secuencialDetalle.equals(other.secuencialDetalle))
			return false;
		return true;
	}
	
	
	
	

}
