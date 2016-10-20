package biz.belcorp.ssicc.dao.spusicc.flexipago.model;

import java.util.ArrayList;
import java.util.List;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *
 */
public class GrupoFLX extends AuditableBaseObject{
	
	private String codigo;
	private String descripcion;
	private String valorConstante;
	private String estado;
	
	private List variables = new ArrayList();
	private List regiones = new ArrayList();
	

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @return the valorConstante
	 */
	public String getValorConstante() {
		return valorConstante;
	}

	/**
	 * @return the variables
	 */
	public List getVariables() {
		return variables;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @param valorConstante the valorConstante to set
	 */
	public void setValorConstante(String valorConstante) {
		this.valorConstante = valorConstante;
	}

	/**
	 * @param variables the variables to set
	 */
	public void setVariables(List variables) {
		this.variables = variables;
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
	 * @return the regiones
	 */
	public List getRegiones() {
		return regiones;
	}

	/**
	 * @param regiones the regiones to set
	 */
	public void setRegiones(List regiones) {
		this.regiones = regiones;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result
				+ ((valorConstante == null) ? 0 : valorConstante.hashCode());
		result = prime * result
				+ ((variables == null) ? 0 : variables.hashCode());
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
		GrupoFLX other = (GrupoFLX) obj;
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
		if (valorConstante == null) {
			if (other.valorConstante != null)
				return false;
		} else if (!valorConstante.equals(other.valorConstante))
			return false;
		if (variables == null) {
			if (other.variables != null)
				return false;
		} else if (!variables.equals(other.variables))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GrupoFLX [codigo=" + codigo + ", descripcion=" + descripcion
				+ ", valorConstante=" + valorConstante + ", variables="
				+ variables + "]";
	}
	
}