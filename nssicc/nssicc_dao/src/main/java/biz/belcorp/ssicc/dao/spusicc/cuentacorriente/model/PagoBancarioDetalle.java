/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author pejflorencio
 * 
 */
public class PagoBancarioDetalle extends AuditableBaseObject implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoConsultora;
	private String nombreConsultora;
	private String consecutivoTransaccion;
	private String fechaPago;
	private String importePago;
	private int numeroLinea;

	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora
	 *            the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	/**
	 * @return the nombreConsultora
	 */
	public String getNombreConsultora() {
		return nombreConsultora;
	}

	/**
	 * @param nombreConsultora
	 *            the nombreConsultora to set
	 */
	public void setNombreConsultora(String nombreConsultora) {
		this.nombreConsultora = nombreConsultora;
	}

	/**
	 * @return the consecutivoTransaccion
	 */
	public String getConsecutivoTransaccion() {
		return consecutivoTransaccion;
	}

	/**
	 * @param consecutivoTransaccion the consecutivoTransaccion to set
	 */
	public void setConsecutivoTransaccion(String consecutivoTransaccion) {
		this.consecutivoTransaccion = consecutivoTransaccion;
	}
	
	

	/**
	 * @return the fechaPago
	 */
	public String getFechaPago() {
		return fechaPago;
	}

	/**
	 * @param fechaPago the fechaPago to set
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * @return the importePago
	 */
	public String getImportePago() {
		return importePago;
	}

	/**
	 * @param importePago
	 *            the importePago to set
	 */
	public void setImportePago(String importePago) {
		this.importePago = importePago;
	}

	/**
	 * @return the numeroLinea
	 */
	public int getNumeroLinea() {
		return numeroLinea;
	}

	/**
	 * @param numeroLinea
	 *            the numeroLinea to set
	 */
	public void setNumeroLinea(int numeroLinea) {
		this.numeroLinea = numeroLinea;
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
				+ ((codigoConsultora == null) ? 0 : codigoConsultora.hashCode());
		result = prime
				* result
				+ ((consecutivoTransaccion == null) ? 0
						: consecutivoTransaccion.hashCode());
		result = prime * result
				+ ((fechaPago == null) ? 0 : fechaPago.hashCode());
		result = prime * result
				+ ((importePago == null) ? 0 : importePago.hashCode());
		result = prime
				* result
				+ ((nombreConsultora == null) ? 0 : nombreConsultora.hashCode());
		result = prime * result + numeroLinea;
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
		PagoBancarioDetalle other = (PagoBancarioDetalle) obj;
		if (codigoConsultora == null) {
			if (other.codigoConsultora != null)
				return false;
		} else if (!codigoConsultora.equals(other.codigoConsultora))
			return false;
		if (consecutivoTransaccion == null) {
			if (other.consecutivoTransaccion != null)
				return false;
		} else if (!consecutivoTransaccion.equals(other.consecutivoTransaccion))
			return false;
		if (fechaPago == null) {
			if (other.fechaPago != null)
				return false;
		} else if (!fechaPago.equals(other.fechaPago))
			return false;
		if (importePago == null) {
			if (other.importePago != null)
				return false;
		} else if (!importePago.equals(other.importePago))
			return false;
		if (nombreConsultora == null) {
			if (other.nombreConsultora != null)
				return false;
		} else if (!nombreConsultora.equals(other.nombreConsultora))
			return false;
		if (numeroLinea != other.numeroLinea)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PagoBancarioDetalle [codigoConsultora=" + codigoConsultora
				+ ", nombreConsultora=" + nombreConsultora
				+ ", consecutivoTransaccion=" + consecutivoTransaccion
				+ ", fechaPago=" + fechaPago + ", importePago=" + importePago
				+ ", numeroLinea=" + numeroLinea + "]";
	}

	

}
