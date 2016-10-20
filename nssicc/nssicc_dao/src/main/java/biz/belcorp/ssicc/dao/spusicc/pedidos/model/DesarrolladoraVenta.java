/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;
import java.sql.Timestamp;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Danny Amaro
 *
 */
public class DesarrolladoraVenta extends AuditableBaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7552498221748474077L;
	
	private String codigoPeriodo;
	private String codigoCliente;
	private String monto;
	private String usuarioCreacion;
	private Timestamp fechaCreacion;
	private String nombreArchivo;
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
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
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}
	/**
	 * @param monto the monto to set
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}
	/**
	 * @return the usuarioCreacion
	 */
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	/**
	 * @param usuarioCreacion the usuarioCreacion to set
	 */
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	/**
	 * @return the fechaCreacion
	 */
	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	/**
	 * @param nombreArchivo the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int 
		result = prime 
				+ ((codigoCliente == null) ? 0 : codigoCliente.hashCode());
		result = prime * result
				+ ((codigoPeriodo == null) ? 0 : codigoPeriodo.hashCode());
		result = prime * result
				+ ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result + ((monto == null) ? 0 : monto.hashCode());
		result = prime * result
				+ ((nombreArchivo == null) ? 0 : nombreArchivo.hashCode());
		result = prime * result
				+ ((usuarioCreacion == null) ? 0 : usuarioCreacion.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}		
		if (!(obj instanceof DesarrolladoraVenta)) {
			return false;
		}
		DesarrolladoraVenta other = (DesarrolladoraVenta) obj;
		if (codigoCliente == null) {
			if (other.codigoCliente != null) {
				return false;
			}
		} else if (!codigoCliente.equals(other.codigoCliente)) {
			return false;
		}
		if (codigoPeriodo == null) {
			if (other.codigoPeriodo != null) {
				return false;
			}
		} else if (!codigoPeriodo.equals(other.codigoPeriodo)) {
			return false;
		}
		if (fechaCreacion == null) {
			if (other.fechaCreacion != null) {
				return false;
			}
		} else if (!fechaCreacion.equals(other.fechaCreacion)) {
			return false;
		}
		if (monto == null) {
			if (other.monto != null) {
				return false;
			}
		} else if (!monto.equals(other.monto)) {
			return false;
		}
		if (nombreArchivo == null) {
			if (other.nombreArchivo != null) {
				return false;
			}
		} else if (!nombreArchivo.equals(other.nombreArchivo)) {
			return false;
		}
		if (usuarioCreacion == null) {
			if (other.usuarioCreacion != null) {
				return false;
			}
		} else if (!usuarioCreacion.equals(other.usuarioCreacion)) {
			return false;
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DesarrolladoraVenta [codigoCliente=" + codigoCliente
				+ ", codigoPeriodo=" + codigoPeriodo + ", fechaCreacion="
				+ fechaCreacion + ", monto=" + monto + ", nombreArchivo="
				+ nombreArchivo + ", usuarioCreacion=" + usuarioCreacion + "]";
	}
	
}
