/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.zon.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="itoto@sigcomt.com">Ivan Tocto</a>
 * 
 */
public class RolDirectorio extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String descripcion;
	private String codigoTipoUniAdmi;
	private String indicadorActivo;
	private String estado;

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the codigoTipoUniAdmi
	 */
	public String getCodigoTipoUniAdmi() {
		return codigoTipoUniAdmi;
	}

	/**
	 * @param codigoTipoUniAdmi
	 *            the codigoTipoUniAdmi to set
	 */
	public void setCodigoTipoUniAdmi(String codigoTipoUniAdmi) {
		this.codigoTipoUniAdmi = codigoTipoUniAdmi;
	}

	/**
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}

	/**
	 * @param indicadorActivo
	 *            the indicadorActivo to set
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		RolDirectorio other = (RolDirectorio) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
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
		if (codigoTipoUniAdmi == null) {
			if (other.codigoTipoUniAdmi != null)
				return false;
		} else if (!codigoTipoUniAdmi.equals(other.codigoTipoUniAdmi))
			return false;
		if (indicadorActivo == null) {
			if (other.indicadorActivo != null)
				return false;
		} else if (!indicadorActivo.equals(other.indicadorActivo))
			return false;

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 13;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((codigoTipoUniAdmi == null) ? 0 : codigoTipoUniAdmi.hashCode());
		result = prime * result + ((indicadorActivo == null) ? 0 : indicadorActivo.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RolDirectorio [codigo=" + codigo + ", descripcion=" + descripcion + ", estado=" + estado 
				+ ", codigoTipoUniAdmi="+ codigoTipoUniAdmi+ ", indicadorActivo=" + indicadorActivo + "]";
	}

}
