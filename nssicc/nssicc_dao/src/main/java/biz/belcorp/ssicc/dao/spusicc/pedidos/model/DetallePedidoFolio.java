/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import biz.belcorp.ssicc.dao.framework.model.BaseObject;



/**
 * @author tokkto
 *
 */
public class DetallePedidoFolio extends BaseObject {
	
	private String codigoCliente;
	private String codigoPeri;
	private String valorNumericoSolicitud;
	private String fechaProc;
	private String valorCodigoVenta;
	private String valorDescripcionProducto;
	private String numeroUnidad;
	private String valorPrecioCatalogo;
	private String valorFolio;
	private String indiceReclamo;
	

	
	
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
	 * @return the codigoPeri
	 */
	public String getCodigoPeri() {
		return codigoPeri;
	}

	/**
	 * @param codigoPeri the codigoPeri to set
	 */
	public void setCodigoPeri(String codigoPeri) {
		this.codigoPeri = codigoPeri;
	}

	/**
	 * @return the valorNumericoSolicitud
	 */
	public String getValorNumericoSolicitud() {
		return valorNumericoSolicitud;
	}

	/**
	 * @param valorNumericoSolicitud the valorNumericoSolicitud to set
	 */
	public void setValorNumericoSolicitud(String valorNumericoSolicitud) {
		this.valorNumericoSolicitud = valorNumericoSolicitud;
	}

	/**
	 * @return the fechaProc
	 */
	public String getFechaProc() {
		return fechaProc;
	}

	/**
	 * @param fechaProc the fechaProc to set
	 */
	public void setFechaProc(String fechaProc) {
		this.fechaProc = fechaProc;
	}

	/**
	 * @return the valorCodigoVenta
	 */
	public String getValorCodigoVenta() {
		return valorCodigoVenta;
	}

	/**
	 * @param valorCodigoVenta the valorCodigoVenta to set
	 */
	public void setValorCodigoVenta(String valorCodigoVenta) {
		this.valorCodigoVenta = valorCodigoVenta;
	}

	/**
	 * @return the valorDescripcionProducto
	 */
	public String getValorDescripcionProducto() {
		return valorDescripcionProducto;
	}

	/**
	 * @param valorDescripcionProducto the valorDescripcionProducto to set
	 */
	public void setValorDescripcionProducto(String valorDescripcionProducto) {
		this.valorDescripcionProducto = valorDescripcionProducto;
	}

	/**
	 * @return the numeroUnidad
	 */
	public String getNumeroUnidad() {
		return numeroUnidad;
	}

	/**
	 * @param numeroUnidad the numeroUnidad to set
	 */
	public void setNumeroUnidad(String numeroUnidad) {
		this.numeroUnidad = numeroUnidad;
	}

	/**
	 * @return the valorPrecioCatalogo
	 */
	public String getValorPrecioCatalogo() {
		return valorPrecioCatalogo;
	}

	/**
	 * @param valorPrecioCatalogo the valorPrecioCatalogo to set
	 */
	public void setValorPrecioCatalogo(String valorPrecioCatalogo) {
		this.valorPrecioCatalogo = valorPrecioCatalogo;
	}

	/**
	 * @return the valorFolio
	 */
	public String getValorFolio() {
		return valorFolio;
	}

	/**
	 * @param valorFolio the valorFolio to set
	 */
	public void setValorFolio(String valorFolio) {
		this.valorFolio = valorFolio;
	}

	/**
	 * @return the indiceReclamo
	 */
	public String getIndiceReclamo() {
		return indiceReclamo;
	}

	/**
	 * @param indiceReclamo the indiceReclamo to set
	 */
	public void setIndiceReclamo(String indiceReclamo) {
		this.indiceReclamo = indiceReclamo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 11;
		result = prime * result
				+ ((codigoCliente == null) ? 0 : codigoCliente.hashCode());
		result = prime * result
				+ ((codigoPeri == null) ? 0 : codigoPeri.hashCode());
		result = prime * result
				+ ((fechaProc == null) ? 0 : fechaProc.hashCode());
		result = prime * result
				+ ((indiceReclamo == null) ? 0 : indiceReclamo.hashCode());
		result = prime * result
				+ ((numeroUnidad == null) ? 0 : numeroUnidad.hashCode());
		result = prime
				* result
				+ ((valorCodigoVenta == null) ? 0 : valorCodigoVenta.hashCode());
		result = prime
				* result
				+ ((valorDescripcionProducto == null) ? 0
						: valorDescripcionProducto.hashCode());
		result = prime * result
				+ ((valorFolio == null) ? 0 : valorFolio.hashCode());
		result = prime
				* result
				+ ((valorNumericoSolicitud == null) ? 0
						: valorNumericoSolicitud.hashCode());
		result = prime
				* result
				+ ((valorPrecioCatalogo == null) ? 0 : valorPrecioCatalogo
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
		
		if (getClass() != obj.getClass())
			return false;
		DetallePedidoFolio other = (DetallePedidoFolio) obj;
		if (codigoCliente == null) {
			if (other.codigoCliente != null)
				return false;
		} else if (!codigoCliente.equals(other.codigoCliente))
			return false;
		if (codigoPeri == null) {
			if (other.codigoPeri != null)
				return false;
		} else if (!codigoPeri.equals(other.codigoPeri))
			return false;
		if (fechaProc == null) {
			if (other.fechaProc != null)
				return false;
		} else if (!fechaProc.equals(other.fechaProc))
			return false;
		if (indiceReclamo == null) {
			if (other.indiceReclamo != null)
				return false;
		} else if (!indiceReclamo.equals(other.indiceReclamo))
			return false;
		if (numeroUnidad == null) {
			if (other.numeroUnidad != null)
				return false;
		} else if (!numeroUnidad.equals(other.numeroUnidad))
			return false;
		if (valorCodigoVenta == null) {
			if (other.valorCodigoVenta != null)
				return false;
		} else if (!valorCodigoVenta.equals(other.valorCodigoVenta))
			return false;
		if (valorDescripcionProducto == null) {
			if (other.valorDescripcionProducto != null)
				return false;
		} else if (!valorDescripcionProducto
				.equals(other.valorDescripcionProducto))
			return false;
		if (valorFolio == null) {
			if (other.valorFolio != null)
				return false;
		} else if (!valorFolio.equals(other.valorFolio))
			return false;
		if (valorNumericoSolicitud == null) {
			if (other.valorNumericoSolicitud != null)
				return false;
		} else if (!valorNumericoSolicitud.equals(other.valorNumericoSolicitud))
			return false;
		if (valorPrecioCatalogo == null) {
			if (other.valorPrecioCatalogo != null)
				return false;
		} else if (!valorPrecioCatalogo.equals(other.valorPrecioCatalogo))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DetallePedidoFolio [codigoCliente=" + codigoCliente
				+ ", codigoPeri=" + codigoPeri + ", fechaProc=" + fechaProc
				+ ", indiceReclamo=" + indiceReclamo + ", numeroUnidad="
				+ numeroUnidad + ", valorCodigoVenta=" + valorCodigoVenta
				+ ", valorDescripcionProducto=" + valorDescripcionProducto
				+ ", valorFolio=" + valorFolio + ", valorNumericoSolicitud="
				+ valorNumericoSolicitud + ", valorPrecioCatalogo="
				+ valorPrecioCatalogo + "]";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	
	

}
