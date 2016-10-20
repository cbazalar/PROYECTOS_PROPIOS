package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="kgomez@sigcomt.com">Karina Gomez</a>
 *
 */
public class TipoEstatus extends AuditableBaseObject implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String oidEstatus;
	private String codigo;
	private String marca;
	private String descripcion;
	private String codigoEntidad;
	private String codigoPais;
	
	
	
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
	 * @return the oidEstatus
	 */
	public String getOidEstatus() {
		return oidEstatus;
	}

	/**
	 * @param oidEstatus the oidEstatus to set
	 */
	public void setOidEstatus(String oidEstatus) {
		this.oidEstatus = oidEstatus;
	}

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
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
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
	 * @return the codigoEntidad
	 */
	public String getCodigoEntidad() {
		return codigoEntidad;
	}

	/**
	 * @param codigoEntidad the codigoEntidad to set
	 */
	public void setCodigoEntidad(String codigoEntidad) {
		this.codigoEntidad = codigoEntidad;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "TipoEstatus [codigoPais=" + codigoPais + ", codigo=" + codigo
				+ ", descripcion=" + descripcion
				+ ", marca=" + marca
				+ ", oidEstatus=" + oidEstatus  + "]";
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
		TipoEstatus other = (TipoEstatus) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (oidEstatus == null) {
			if (other.oidEstatus != null)
				return false;
		} else if (!oidEstatus.equals(other.oidEstatus))
			return false;
		return true;

	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime
				* result
				+ ((marca == null) ? 0
						: marca.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result
				+ ((oidEstatus  == null) ? 0 : oidEstatus.hashCode());
		return result;
	}

}
