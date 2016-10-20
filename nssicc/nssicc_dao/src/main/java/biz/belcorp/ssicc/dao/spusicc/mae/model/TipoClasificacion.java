package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="kgomez@sigcomt.com">Karina Gomez</a>
 *
 */
public class TipoClasificacion extends AuditableBaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoTipoClienteCL;
	private String codigoTipoClasificacion;
	private String codigoTipoSubCliente;
	private String descripcionTipoClasificacion;
	private String indicadorRegistro;
	private String nombreEntidad;
	private String oidTipoClasificacion;
	private String tipoClasificacionPais;
	
	
	/**
	 * @return the tipoClasificacionPais
	 */
	public String getTipoClasificacionPais() {
		return tipoClasificacionPais;
	}

	/**
	 * @param tipoClasificacionPais the tipoClasificacionPais to set
	 */
	public void setTipoClasificacionPais(String tipoClasificacionPais) {
		this.tipoClasificacionPais = tipoClasificacionPais;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoTipoClienteCL
	 */
	public String getCodigoTipoClienteCL() {
		return codigoTipoClienteCL;
	}

	/**
	 * @param codigoTipoClienteCL the codigoTipoClienteCL to set
	 */
	public void setCodigoTipoClienteCL(String codigoTipoClienteCL) {
		this.codigoTipoClienteCL = codigoTipoClienteCL;
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
	 * @return the codigoTipoSubCliente
	 */
	public String getCodigoTipoSubCliente() {
		return codigoTipoSubCliente;
	}

	/**
	 * @param codigoTipoSubCliente the codigoTipoSubCliente to set
	 */
	public void setCodigoTipoSubCliente(String codigoTipoSubCliente) {
		this.codigoTipoSubCliente = codigoTipoSubCliente;
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
	 * @return the indicadorRegistro
	 */
	public String getIndicadorRegistro() {
		return indicadorRegistro;
	}

	/**
	 * @param indicadorRegistro the indicadorRegistro to set
	 */
	public void setIndicadorRegistro(String indicadorRegistro) {
		this.indicadorRegistro = indicadorRegistro;
	}

	/**
	 * @return the nombreEntidad
	 */
	public String getNombreEntidad() {
		return nombreEntidad;
	}

	/**
	 * @param nombreEntidad the nombreEntidad to set
	 */
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "TipoClasificacion [codigoPais=" + codigoPais + ", codigoTipoClienteCL=" + codigoTipoClienteCL
				+ ", codigoTipoClasificacion=" + codigoTipoClasificacion
				+ ", descripcionTipoClasificacion=" + descripcionTipoClasificacion
				+ ", indicadorRegistro=" + indicadorRegistro
				+ ", oidTipoClasificacion=" + oidTipoClasificacion
				+ ", nombreEntidad=" + nombreEntidad
				+ ", tipoClasificacionPais=" + tipoClasificacionPais
				+ ", codigoTipoSubCliente=" + codigoTipoSubCliente  + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoClasificacion other = (TipoClasificacion) obj;
		if (codigoTipoClienteCL == null) {
			if (other.codigoTipoClienteCL != null)
				return false;
		} else if (!codigoTipoClienteCL.equals(other.codigoTipoClienteCL))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (codigoTipoClasificacion == null) {
			if (other.codigoTipoClasificacion != null)
				return false;
		} else if (!codigoTipoClasificacion.equals(other.codigoTipoClasificacion))
			return false;
		if (descripcionTipoClasificacion == null) {
			if (other.descripcionTipoClasificacion != null)
				return false;
		} else if (!descripcionTipoClasificacion.equals(other.descripcionTipoClasificacion))
			return false;
		if (codigoTipoSubCliente == null) {
			if (other.codigoTipoSubCliente != null)
				return false;
		} else if (!codigoTipoSubCliente.equals(other.codigoTipoSubCliente))
			return false;
		if (indicadorRegistro == null) {
			if (other.indicadorRegistro != null)
				return false;
		} else if (!indicadorRegistro
				.equals(other.indicadorRegistro))
			return false;
		if (oidTipoClasificacion == null) {
			if (other.oidTipoClasificacion != null)
				return false;
		} else if (!oidTipoClasificacion
				.equals(other.oidTipoClasificacion))
			return false;
		if (nombreEntidad == null) {
			if (other.nombreEntidad != null)
				return false;
		} else if (!nombreEntidad
				.equals(other.nombreEntidad))
			return false;
		if (tipoClasificacionPais == null) {
			if (other.tipoClasificacionPais != null)
				return false;
		} else if (!tipoClasificacionPais
				.equals(other.tipoClasificacionPais))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoTipoClienteCL == null) ? 0 : codigoTipoClienteCL.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime
				* result
				+ ((indicadorRegistro == null) ? 0
						: indicadorRegistro.hashCode());
		result = prime * result
				+ ((codigoTipoSubCliente == null) ? 0 : codigoTipoSubCliente.hashCode());
		result = prime * result
				+ ((codigoTipoClasificacion == null) ? 0 : codigoTipoClasificacion.hashCode());
		result = prime * result
				+ ((descripcionTipoClasificacion == null) ? 0 : descripcionTipoClasificacion.hashCode());
		result = prime * result
				+ ((oidTipoClasificacion == null) ? 0 : oidTipoClasificacion.hashCode());
		result = prime * result
				+ ((nombreEntidad == null) ? 0 : nombreEntidad.hashCode());
		result = prime * result
				+ ((tipoClasificacionPais == null) ? 0 : tipoClasificacionPais.hashCode());
		return result;
	}

}
