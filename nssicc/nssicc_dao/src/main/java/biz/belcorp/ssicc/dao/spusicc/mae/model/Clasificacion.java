package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="kgomez@sigcomt.com">Karina Gomez</a>
 * 
 */
public class Clasificacion extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoTipoClienteCL;
	private String codigoTipoClasificacion;
	private String codigoTipoSubCliente;
	private String codigoClasificacion;
	private String descripcionClasificacion;
	private String indicadorRegistro;
	private String nombreEntidad;
	private String oidClasificacion;
	private String indicadorHiperconsulta;
	private String indicadorIVR;

	/**
	 * @return the indicadorIVR
	 */
	public String getIndicadorIVR() {
		return indicadorIVR;
	}

	/**
	 * @param indicadorIVR
	 *            the indicadorIVR to set
	 */
	public void setIndicadorIVR(String indicadorIVR) {
		this.indicadorIVR = indicadorIVR;
	}

	/**
	 * @return the indicadorHiperconsulta
	 */
	public String getIndicadorHiperconsulta() {
		return indicadorHiperconsulta;
	}

	/**
	 * @param indicadorHiperconsulta
	 *            the indicadorHiperconsulta to set
	 */
	public void setIndicadorHiperconsultas(String indicadorHiperconsulta) {
		this.indicadorHiperconsulta = indicadorHiperconsulta;
	}

	/**
	 * @return the oidClasificacion
	 */
	public String getOidClasificacion() {
		return oidClasificacion;
	}

	/**
	 * @param oidClasificacion
	 *            the oidClasificacion to set
	 */
	public void setOidClasificacion(String oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
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
	 * @param codigoTipoClienteCL
	 *            the codigoTipoClienteCL to set
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
	 * @param codigoTipoClasificacion
	 *            the codigoTipoClasificacion to set
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
	 * @param codigoTipoSubCliente
	 *            the codigoTipoSubCliente to set
	 */
	public void setCodigoTipoSubCliente(String codigoTipoSubCliente) {
		this.codigoTipoSubCliente = codigoTipoSubCliente;
	}

	/**
	 * @return the codigoClasificacion
	 */
	public String getCodigoClasificacion() {
		return codigoClasificacion;
	}

	/**
	 * @param codigoClasificacion
	 *            the codigoClasificacion to set
	 */
	public void setCodigoClasificacion(String codigoClasificacion) {
		this.codigoClasificacion = codigoClasificacion;
	}

	/**
	 * @return the descripcionClasificacion
	 */
	public String getDescripcionClasificacion() {
		return descripcionClasificacion;
	}

	/**
	 * @param descripcionClasificacion
	 *            the descripcionClasificacion to set
	 */
	public void setDescripcionClasificacion(String descripcionClasificacion) {
		this.descripcionClasificacion = descripcionClasificacion;
	}

	/**
	 * @return the indicadorRegistro
	 */
	public String getIndicadorRegistro() {
		return indicadorRegistro;
	}

	/**
	 * @param indicadorRegistro
	 *            the indicadorRegistro to set
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
	 * @param nombreEntidad
	 *            the nombreEntidad to set
	 */
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Clasificacion [codigoPais=" + codigoPais
				+ ", codigoTipoClienteCL=" + codigoTipoClienteCL
				+ ", codigoTipoClasificacion=" + codigoTipoClasificacion
				+ ", codigoClasificacion=" + codigoClasificacion
				+ ", descripcionClasificacion=" + descripcionClasificacion
				+ ", indicadorRegistro=" + indicadorRegistro
				+ ", oidClasificacion=" + oidClasificacion + ", nombreEntidad="
				+ nombreEntidad + ", codigoTipoSubCliente="
				+ codigoTipoSubCliente + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clasificacion other = (Clasificacion) obj;
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
		} else if (!codigoTipoClasificacion
				.equals(other.codigoTipoClasificacion))
			return false;
		if (codigoClasificacion == null) {
			if (other.codigoClasificacion != null)
				return false;
		} else if (!codigoClasificacion.equals(other.codigoClasificacion))
			return false;
		if (descripcionClasificacion == null) {
			if (other.descripcionClasificacion != null)
				return false;
		} else if (!descripcionClasificacion
				.equals(other.descripcionClasificacion))
			return false;
		if (codigoTipoSubCliente == null) {
			if (other.codigoTipoSubCliente != null)
				return false;
		} else if (!codigoTipoSubCliente.equals(other.codigoTipoSubCliente))
			return false;
		if (indicadorRegistro == null) {
			if (other.indicadorRegistro != null)
				return false;
		} else if (!indicadorRegistro.equals(other.indicadorRegistro))
			return false;
		if (oidClasificacion == null) {
			if (other.oidClasificacion != null)
				return false;
		} else if (!oidClasificacion.equals(other.oidClasificacion))
			return false;
		if (nombreEntidad == null) {
			if (other.nombreEntidad != null)
				return false;
		} else if (!nombreEntidad.equals(other.nombreEntidad))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codigoTipoClienteCL == null) ? 0 : codigoTipoClienteCL
						.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime
				* result
				+ ((indicadorRegistro == null) ? 0 : indicadorRegistro
						.hashCode());
		result = prime
				* result
				+ ((codigoTipoSubCliente == null) ? 0 : codigoTipoSubCliente
						.hashCode());
		result = prime
				* result
				+ ((codigoTipoClasificacion == null) ? 0
						: codigoTipoClasificacion.hashCode());
		result = prime
				* result
				+ ((codigoClasificacion == null) ? 0 : codigoClasificacion
						.hashCode());
		result = prime
				* result
				+ ((descripcionClasificacion == null) ? 0
						: descripcionClasificacion.hashCode());
		result = prime
				* result
				+ ((oidClasificacion == null) ? 0 : oidClasificacion.hashCode());
		result = prime * result
				+ ((nombreEntidad == null) ? 0 : nombreEntidad.hashCode());
		return result;
	}

}
