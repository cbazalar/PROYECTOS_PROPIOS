/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.mae.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="itocto@sigcomt.com">Ivan Tocto</a>
 *
 */
public class ExcencionFlete extends AuditableBaseObject {

	private String oidPais;
	private String oidExcencion;
	private String oidTipoCliente;
	private String oidSubTipoCliente;
	private String oidTipoClasificacion;
	private String oidClasificacion;
	private String indicadorExcepcionFlete;
	
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
	 * @return the oidExcencion
	 */
	public String getOidExcencion() {
		return oidExcencion;
	}

	/**
	 * @param oidExcencion the oidExcencion to set
	 */
	public void setOidExcencion(String oidExcencion) {
		this.oidExcencion = oidExcencion;
	}

	/**
	 * @return the oidTipoCliente
	 */
	public String getOidTipoCliente() {
		return oidTipoCliente;
	}

	/**
	 * @param oidTipoCliente the oidTipoCliente to set
	 */
	public void setOidTipoCliente(String oidTipoCliente) {
		this.oidTipoCliente = oidTipoCliente;
	}

	/**
	 * @return the oidSubTipoCliente
	 */
	public String getOidSubTipoCliente() {
		return oidSubTipoCliente;
	}

	/**
	 * @param oidSubTipoCliente the oidSubTipoCliente to set
	 */
	public void setOidSubTipoCliente(String oidSubTipoCliente) {
		this.oidSubTipoCliente = oidSubTipoCliente;
	}

	/**
	 * @return the oidTipoClasificacion
	 */
	public String getOidTipoClasificacion() {
		return oidTipoClasificacion;
	}

	/**
	 * @param oidTipoClasificacion the oidTipoClasificacion to set
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
	 */
	public void setOidClasificacion(String oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
	}

	/**
	 * @return the indicadorExcepcionFlete
	 */
	public String getIndicadorExcepcionFlete() {
		return indicadorExcepcionFlete;
	}

	/**
	 * @param indicadorExcepcionFlete the indicadorExcepcionFlete to set
	 */
	public void setIndicadorExcepcionFlete(String indicadorExcepcionFlete) {
		this.indicadorExcepcionFlete = indicadorExcepcionFlete;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExcencionFlete [oidPais=" + oidPais + ", oidExcencion="
				+ oidExcencion + ", oidTipoCliente=" + oidTipoCliente
				+ ", oidSubTipoCliente=" + oidSubTipoCliente
				+ ", oidTipoClasificacion=" + oidTipoClasificacion
				+ ", oidClasificacion=" + oidClasificacion
				+ ", indicadorExcepcionFlete=" + indicadorExcepcionFlete + "]";
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
		ExcencionFlete other = (ExcencionFlete) obj;
		if (indicadorExcepcionFlete == null) {
			if (other.indicadorExcepcionFlete != null)
				return false;
		} else if (!indicadorExcepcionFlete
				.equals(other.indicadorExcepcionFlete))
			return false;
		if (oidClasificacion == null) {
			if (other.oidClasificacion != null)
				return false;
		} else if (!oidClasificacion.equals(other.oidClasificacion))
			return false;
		if (oidExcencion == null) {
			if (other.oidExcencion != null)
				return false;
		} else if (!oidExcencion.equals(other.oidExcencion))
			return false;
		if (oidPais == null) {
			if (other.oidPais != null)
				return false;
		} else if (!oidPais.equals(other.oidPais))
			return false;
		if (oidSubTipoCliente == null) {
			if (other.oidSubTipoCliente != null)
				return false;
		} else if (!oidSubTipoCliente.equals(other.oidSubTipoCliente))
			return false;
		if (oidTipoClasificacion == null) {
			if (other.oidTipoClasificacion != null)
				return false;
		} else if (!oidTipoClasificacion.equals(other.oidTipoClasificacion))
			return false;
		if (oidTipoCliente == null) {
			if (other.oidTipoCliente != null)
				return false;
		} else if (!oidTipoCliente.equals(other.oidTipoCliente))
			return false;
		return true;
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
				+ ((indicadorExcepcionFlete == null) ? 0
						: indicadorExcepcionFlete.hashCode());
		result = prime
				* result
				+ ((oidClasificacion == null) ? 0 : oidClasificacion.hashCode());
		result = prime * result
				+ ((oidExcencion == null) ? 0 : oidExcencion.hashCode());
		result = prime * result + ((oidPais == null) ? 0 : oidPais.hashCode());
		result = prime
				* result
				+ ((oidSubTipoCliente == null) ? 0 : oidSubTipoCliente
						.hashCode());
		result = prime
				* result
				+ ((oidTipoClasificacion == null) ? 0 : oidTipoClasificacion
						.hashCode());
		result = prime * result
				+ ((oidTipoCliente == null) ? 0 : oidTipoCliente.hashCode());
		return result;
	}

}
