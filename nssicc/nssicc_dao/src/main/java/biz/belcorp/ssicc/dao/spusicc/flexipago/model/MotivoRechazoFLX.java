package biz.belcorp.ssicc.dao.spusicc.flexipago.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *
 */
public class MotivoRechazoFLX extends AuditableBaseObject{
	
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String codigoMotivo;
	private String descripcion;
	private String estado;
	
	private EstatusRechazoFLX estatusRechazo;
	
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
	
	/**
	 * @return the estatusRechazo
	 */
	public EstatusRechazoFLX getEstatusRechazo() {
		return estatusRechazo;
	}
	/**
	 * @param estatusRechazo the estatusRechazo to set
	 */
	public void setEstatusRechazo(EstatusRechazoFLX estatusRechazo) {
		this.estatusRechazo = estatusRechazo;
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
				+ ((codigoMotivo == null) ? 0 : codigoMotivo.hashCode());
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
		MotivoRechazoFLX other = (MotivoRechazoFLX) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (codigoMotivo == null) {
			if (other.codigoMotivo != null)
				return false;
		} else if (!codigoMotivo.equals(other.codigoMotivo))
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
		return "MotivoRechazoFLX [codigo=" + codigo + ", codigoMotivo="
				+ codigoMotivo + ", descripcion=" + descripcion + ", estado="
				+ estado + "]";
	}	
}