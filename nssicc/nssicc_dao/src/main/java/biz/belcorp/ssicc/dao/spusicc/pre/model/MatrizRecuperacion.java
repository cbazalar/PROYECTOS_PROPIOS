/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pre.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Sigcomt
 *
 */
public class MatrizRecuperacion extends AuditableBaseObject implements Serializable {

	private String oid;
	private String indicadorActivo;
	
	private String oidMatrizRecuperacion;
	private String oidMatrizReemplazo;
	private String indicadorMensaje;
	
	private String codigoRegion;
	private String codigoZona;
	
	private String oidTipoCliente;
	private String oidSubTipoCliente;
	private String oidTipoClasificacion;
	private String oidClasificacion;
	private String numeroUnidadRecuperacion;
	
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
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}

	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}

	/**
	 * @return the oidMatrizRecuperacion
	 */
	public String getOidMatrizRecuperacion() {
		return oidMatrizRecuperacion;
	}

	/**
	 * @param oidMatrizRecuperacion the oidMatrizRecuperacion to set
	 */
	public void setOidMatrizRecuperacion(String oidMatrizRecuperacion) {
		this.oidMatrizRecuperacion = oidMatrizRecuperacion;
	}

	/**
	 * @return the oidMatrizReemplazo
	 */
	public String getOidMatrizReemplazo() {
		return oidMatrizReemplazo;
	}

	/**
	 * @param oidMatrizReemplazo the oidMatrizReemplazo to set
	 */
	public void setOidMatrizReemplazo(String oidMatrizReemplazo) {
		this.oidMatrizReemplazo = oidMatrizReemplazo;
	}

	/**
	 * @return the indicadorMensaje
	 */
	public String getIndicadorMensaje() {
		return indicadorMensaje;
	}

	/**
	 * @param indicadorMensaje the indicadorMensaje to set
	 */
	public void setIndicadorMensaje(String indicadorMensaje) {
		this.indicadorMensaje = indicadorMensaje;
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
	


	public String getNumeroUnidadRecuperacion() {
		return numeroUnidadRecuperacion;
	}

	public void setNumeroUnidadRecuperacion(String numeroUnidadRecuperacion) {
		this.numeroUnidadRecuperacion = numeroUnidadRecuperacion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "MatrizRecuperacion [oid=" + oid + ", indicadorActivo="
				+ indicadorActivo + ", oidMatrizRecuperacion="
				+ oidMatrizRecuperacion + ", oidMatrizReemplazo="
				+ oidMatrizReemplazo + ", indicadorMensaje=" + indicadorMensaje
				+ ", codigoRegion=" + codigoRegion + ", codigoZona="
				+ codigoZona + ", oidTipoCliente=" + oidTipoCliente
				+ ", oidSubTipoCliente=" + oidSubTipoCliente
				+ ", oidTipoClasificacion=" + oidTipoClasificacion
				+ ", oidClasificacion=" + oidClasificacion
				+ ", numeroUnidadRecuperacion=" + numeroUnidadRecuperacion + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatrizRecuperacion other = (MatrizRecuperacion) obj;
		if (codigoRegion == null) {
			if (other.codigoRegion != null)
				return false;
		} else if (!codigoRegion.equals(other.codigoRegion))
			return false;
		if (codigoZona == null) {
			if (other.codigoZona != null)
				return false;
		} else if (!codigoZona.equals(other.codigoZona))
			return false;
		if (indicadorActivo == null) {
			if (other.indicadorActivo != null)
				return false;
		} else if (!indicadorActivo.equals(other.indicadorActivo))
			return false;
		if (indicadorMensaje == null) {
			if (other.indicadorMensaje != null)
				return false;
		} else if (!indicadorMensaje.equals(other.indicadorMensaje))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (oidClasificacion == null) {
			if (other.oidClasificacion != null)
				return false;
		} else if (!oidClasificacion.equals(other.oidClasificacion))
			return false;
		if (oidMatrizRecuperacion == null) {
			if (other.oidMatrizRecuperacion != null)
				return false;
		} else if (!oidMatrizRecuperacion.equals(other.oidMatrizRecuperacion))
			return false;
		if (oidMatrizReemplazo == null) {
			if (other.oidMatrizReemplazo != null)
				return false;
		} else if (!oidMatrizReemplazo.equals(other.oidMatrizReemplazo))
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
		if (numeroUnidadRecuperacion == null) {
			if (other.numeroUnidadRecuperacion != null)
				return false;
		} else if (!numeroUnidadRecuperacion.equals(other.numeroUnidadRecuperacion))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoRegion == null) ? 0 : codigoRegion.hashCode());
		result = prime * result
				+ ((codigoZona == null) ? 0 : codigoZona.hashCode());
		result = prime * result
				+ ((indicadorActivo == null) ? 0 : indicadorActivo.hashCode());
		result = prime
				* result
				+ ((indicadorMensaje == null) ? 0 : indicadorMensaje.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime
				* result
				+ ((oidClasificacion == null) ? 0 : oidClasificacion.hashCode());
		result = prime
				* result
				+ ((oidMatrizRecuperacion == null) ? 0 : oidMatrizRecuperacion
						.hashCode());
		result = prime
				* result
				+ ((oidMatrizReemplazo == null) ? 0 : oidMatrizReemplazo
						.hashCode());
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
		
		result = prime * result
				+ ((numeroUnidadRecuperacion == null) ? 0 : numeroUnidadRecuperacion.hashCode());
		return result;
	}

}
