package biz.belcorp.ssicc.dao.spusicc.flexipago.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *
 */
public class ParametroFLX extends AuditableBaseObject implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String codigoParametro;
	private String descripcionParametro;
	private String valorParametro;
	private String codigoGrupo;
	private String descripcionGrupo;
	
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
	 * @return the codigoParametro
	 */
	public String getCodigoParametro() {
		return codigoParametro;
	}
	/**
	 * @param codigoParametro the codigoParametro to set
	 */
	public void setCodigoParametro(String codigoParametro) {
		this.codigoParametro = codigoParametro;
	}
	/**
	 * @return the descripcionParametro
	 */
	public String getDescripcionParametro() {
		return descripcionParametro;
	}
	/**
	 * @param descripcionParametro the descripcionParametro to set
	 */
	public void setDescripcionParametro(String descripcionParametro) {
		this.descripcionParametro = descripcionParametro;
	}
	/**
	 * @return the valorParametro
	 */
	public String getValorParametro() {
		return valorParametro;
	}
	/**
	 * @param valorParametro the valorParametro to set
	 */
	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}
	/**
	 * @return the codigoGrupo
	 */
	public String getCodigoGrupo() {
		return codigoGrupo;
	}
	/**
	 * @param codigoGrupo the codigoGrupo to set
	 */
	public void setCodigoGrupo(String codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}
	/**
	 * @return the descripcionGrupo
	 */
	public String getDescripcionGrupo() {
		return descripcionGrupo;
	}
	/**
	 * @param descripcionGrupo the descripcionGrupo to set
	 */
	public void setDescripcionGrupo(String descripcionGrupo) {
		this.descripcionGrupo = descripcionGrupo;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((codigoGrupo == null) ? 0 : codigoGrupo.hashCode());
		result = prime * result
				+ ((codigoParametro == null) ? 0 : codigoParametro.hashCode());
		result = prime
				* result
				+ ((descripcionGrupo == null) ? 0 : descripcionGrupo.hashCode());
		result = prime
				* result
				+ ((descripcionParametro == null) ? 0 : descripcionParametro
						.hashCode());
		result = prime * result
				+ ((valorParametro == null) ? 0 : valorParametro.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParametroFLX other = (ParametroFLX) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (codigoGrupo == null) {
			if (other.codigoGrupo != null)
				return false;
		} else if (!codigoGrupo.equals(other.codigoGrupo))
			return false;
		if (codigoParametro == null) {
			if (other.codigoParametro != null)
				return false;
		} else if (!codigoParametro.equals(other.codigoParametro))
			return false;
		if (descripcionGrupo == null) {
			if (other.descripcionGrupo != null)
				return false;
		} else if (!descripcionGrupo.equals(other.descripcionGrupo))
			return false;
		if (descripcionParametro == null) {
			if (other.descripcionParametro != null)
				return false;
		} else if (!descripcionParametro.equals(other.descripcionParametro))
			return false;
		if (valorParametro == null) {
			if (other.valorParametro != null)
				return false;
		} else if (!valorParametro.equals(other.valorParametro))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "ParametroFLX [codigo=" + codigo + ", codigoParametro="
				+ codigoParametro + ", descripcionParametro="
				+ descripcionParametro + ", valorParametro=" + valorParametro
				+ ", codigoGrupo=" + codigoGrupo + ", descripcionGrupo="
				+ descripcionGrupo + "]";
	}
}