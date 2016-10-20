package biz.belcorp.ssicc.dao.spusicc.flexipago.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *
 */
public class EstatusRecomendacionFLX extends AuditableBaseObject{
	
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String codigoEstatus;
	private String descripcion;
	private String estado;
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the codigoEstatus
	 */
	public String getCodigoEstatus() {
		return codigoEstatus;
	}
	/**
	 * @param codigoEstatus the codigoEstatus to set
	 */
	public void setCodigoEstatus(String codigoEstatus) {
		this.codigoEstatus = codigoEstatus;
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
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 13;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((codigoEstatus == null) ? 0 : codigoEstatus.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
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
		EstatusRecomendacionFLX other = (EstatusRecomendacionFLX) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (codigoEstatus == null) {
			if (other.codigoEstatus != null)
				return false;
		} else if (!codigoEstatus.equals(other.codigoEstatus))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EstatusRecomendacionFLX [codigo=" + codigo + ", codigoEstatus="
				+ codigoEstatus + ", descripcion=" + descripcion + ", estado="
				+ estado + "]";
	}	
}