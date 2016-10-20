/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.flx.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author csoberon
 *
 */
public class ConsultoraFlexipagoObjetada extends AuditableBaseObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4597589259231397465L;
	private String codigoPais;
	private String codigoCliente;
	private String codigoCampanyaFacturacion;
	private String nombreConsultora;
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
	 * @return the codigoCampanyaFacturacion
	 */
	public String getCodigoCampanyaFacturacion() {
		return codigoCampanyaFacturacion;
	}
	/**
	 * @param codigoCampanyaFacturacion the codigoCampanyaFacturacion to set
	 */
	public void setCodigoCampanyaFacturacion(String codigoCampanyaFacturacion) {
		this.codigoCampanyaFacturacion = codigoCampanyaFacturacion;
	}
	/**
	 * @return the nombreConsultora
	 */
	public String getNombreConsultora() {
		return nombreConsultora;
	}
	/**
	 * @param nombreConsultora the nombreConsultora to set
	 */
	public void setNombreConsultora(String nombreConsultora) {
		this.nombreConsultora = nombreConsultora;
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
		ConsultoraFlexipagoObjetada other = (ConsultoraFlexipagoObjetada) obj;
		if (codigoCampanyaFacturacion == null) {
			if (other.codigoCampanyaFacturacion != null)
				return false;
		} else if (!codigoCampanyaFacturacion
				.equals(other.codigoCampanyaFacturacion))
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
		if (nombreConsultora == null) {
			if (other.nombreConsultora != null)
				return false;
		} else if (!nombreConsultora.equals(other.nombreConsultora))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 11;
		result = prime
				* result
				+ ((codigoCampanyaFacturacion == null) ? 0
						: codigoCampanyaFacturacion.hashCode());
		result = prime * result
				+ ((codigoCliente == null) ? 0 : codigoCliente.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime
				* result
				+ ((nombreConsultora == null) ? 0 : nombreConsultora.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConsultoraFlexipagoObjetada [codigoCampanyaFacturacion="
				+ codigoCampanyaFacturacion + ", codigoCliente="
				+ codigoCliente + ", codigoPais=" + codigoPais
				+ ", nombreConsultora=" + nombreConsultora + "]";
	}
}
