package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;


/**
 * @author <a href="kgomez@sigcomt.com">Karina Gomez</a>
 *
 */

public class EstadoCivil extends AuditableBaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String codigoPais;
	private String nombreEntidad;
	private String oidEstadoCivil;
	private String codigo;
	private String estado;
	private String descripcion;
	
	
	
	
	/**
	 * @return the oidEstadoCivil
	 */
	public String getOidEstadoCivil() {
		return oidEstadoCivil;
	}

	/**
	 * @param oidEstadoCivil the oidEstadoCivil to set
	 */
	public void setOidEstadoCivil(String oidEstadoCivil) {
		this.oidEstadoCivil = oidEstadoCivil;
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
	 * @return the nombreEntidad
	 */
	public String getNombreEntidad() {
		return nombreEntidad;
	}

	/**
	 * @param nombreEntidad the nombreEntidad to set
	 */
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
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

	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "EstadoCivil [codigoPais=" + codigoPais + ", codigo=" + codigo
				+ ", nombreEntidad=" + nombreEntidad
				+ ", oidEstadoCivil=" + oidEstadoCivil  
				+ ", estado=" + estado
				+ ", descripcion=" + descripcion  + "]";
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
		EstadoCivil other = (EstadoCivil) obj;
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
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (oidEstadoCivil == null) {
			if (other.oidEstadoCivil != null)
				return false;
		} else if (!oidEstadoCivil.equals(other.oidEstadoCivil))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
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
				+ ((nombreEntidad == null) ? 0
						: nombreEntidad.hashCode());
		result = prime * result
				+ ((oidEstadoCivil == null) ? 0 : oidEstadoCivil.hashCode());
		result = prime * result
				+ ((estado  == null) ? 0 : estado.hashCode());
		result = prime * result
				+ ((descripcion  == null) ? 0 : descripcion.hashCode());
		return result;
	}

}
