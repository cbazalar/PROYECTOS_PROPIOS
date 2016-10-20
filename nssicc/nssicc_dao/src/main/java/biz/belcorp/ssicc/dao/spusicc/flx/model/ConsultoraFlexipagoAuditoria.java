/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.flx.model;

import java.sql.Timestamp;

import biz.belcorp.ssicc.dao.framework.model.BaseObject;

/**
 * @author Ivan Tocto Jaimes
 *
 */
public class ConsultoraFlexipagoAuditoria extends BaseObject {

	private String codigoPais;
	private String codigoCliente;
	private String codigoAccion;
	private String usuarioAccion;
	private Timestamp fechaAccion;
	private String campanyaFacturacion;
	private String codigoMotivo;
	
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
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the codigoAccion
	 */
	public String getCodigoAccion() {
		return codigoAccion;
	}

	/**
	 * @param codigoAccion the codigoAccion to set
	 */
	public void setCodigoAccion(String codigoAccion) {
		this.codigoAccion = codigoAccion;
	}

	/**
	 * @return the usuarioAccion
	 */
	public String getUsuarioAccion() {
		return usuarioAccion;
	}

	/**
	 * @param usuarioAccion the usuarioAccion to set
	 */
	public void setUsuarioAccion(String usuarioAccion) {
		this.usuarioAccion = usuarioAccion;
	}

	/**
	 * @return the fechaAccion
	 */
	public Timestamp getFechaAccion() {
		return fechaAccion;
	}

	public String getCampanyaFacturacion() {
		return campanyaFacturacion;
	}

	public void setCampanyaFacturacion(String campanyaFacturacion) {
		this.campanyaFacturacion = campanyaFacturacion;
	}

	/**
	 * @param fechaAccion the fechaAccion to set
	 */
	public void setFechaAccion(Timestamp fechaAccion) {
		this.fechaAccion = fechaAccion;
	}

	/**
	 * @return the codigoMotivo
	 */
	public String getCodigoMotivo() {
		return codigoMotivo;
	}

	/**
	 * @param codigoMotivo the codigoMotivo to set
	 */
	public void setCodigoMotivo(String codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		ConsultoraFlexipagoAuditoria other = (ConsultoraFlexipagoAuditoria) obj;
		if (campanyaFacturacion == null) {
			if (other.campanyaFacturacion != null)
				return false;
		} else if (!campanyaFacturacion.equals(other.campanyaFacturacion))
			return false;
		if (codigoAccion == null) {
			if (other.codigoAccion != null)
				return false;
		} else if (!codigoAccion.equals(other.codigoAccion))
			return false;
		if (codigoCliente == null) {
			if (other.codigoCliente != null)
				return false;
		} else if (!codigoCliente.equals(other.codigoCliente))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (fechaAccion == null) {
			if (other.fechaAccion != null)
				return false;
		} else if (!fechaAccion.equals(other.fechaAccion))
			return false;
		if (usuarioAccion == null) {
			if (other.usuarioAccion != null)
				return false;
		} else if (!usuarioAccion.equals(other.usuarioAccion))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 13;
		result = prime
				* result
				+ ((campanyaFacturacion == null) ? 0 : campanyaFacturacion
						.hashCode());
		result = prime * result
				+ ((codigoAccion == null) ? 0 : codigoAccion.hashCode());
		result = prime * result
				+ ((codigoCliente == null) ? 0 : codigoCliente.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((fechaAccion == null) ? 0 : fechaAccion.hashCode());
		result = prime * result
				+ ((usuarioAccion == null) ? 0 : usuarioAccion.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "ConsultoraFlexipagoAuditoria [campanyaFacturacion="
				+ campanyaFacturacion + ", codigoAccion=" + codigoAccion
				+ ", codigoCliente=" + codigoCliente + ", codigoPais="
				+ codigoPais + ", fechaAccion=" + fechaAccion
				+ ", usuarioAccion=" + usuarioAccion + "]";
	}

}
