package biz.belcorp.ssicc.dao.spusicc.flexipago.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *
 */
public class GrupoVariableFLX extends AuditableBaseObject{
	
	private String codigo;
	private String codigoGrupo;
	private String codigoVariable;
	private String valorPeso;	
	private String estado;
	
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @return the codigoGrupo
	 */
	public String getCodigoGrupo() {
		return codigoGrupo;
	}
	/**
	 * @return the codigoVariable
	 */
	public String getCodigoVariable() {
		return codigoVariable;
	}
	/**
	 * @return the valorPeso
	 */
	public String getValorPeso() {
		return valorPeso;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @param codigoGrupo the codigoGrupo to set
	 */
	public void setCodigoGrupo(String codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}
	/**
	 * @param codigoVariable the codigoVariable to set
	 */
	public void setCodigoVariable(String codigoVariable) {
		this.codigoVariable = codigoVariable;
	}
	/**
	 * @param valorPeso the valorPeso to set
	 */
	public void setValorPeso(String valorPeso) {
		this.valorPeso = valorPeso;
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
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((codigoGrupo == null) ? 0 : codigoGrupo.hashCode());
		result = prime * result
				+ ((codigoVariable == null) ? 0 : codigoVariable.hashCode());
		result = prime * result
				+ ((valorPeso == null) ? 0 : valorPeso.hashCode());
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
		GrupoVariableFLX other = (GrupoVariableFLX) obj;
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
		if (codigoVariable == null) {
			if (other.codigoVariable != null)
				return false;
		} else if (!codigoVariable.equals(other.codigoVariable))
			return false;
		if (valorPeso == null) {
			if (other.valorPeso != null)
				return false;
		} else if (!valorPeso.equals(other.valorPeso))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GrupoVariableFLX [codigo=" + codigo + ", codigoGrupo="
				+ codigoGrupo + ", codigoVariable=" + codigoVariable
				+ ", valorPeso=" + valorPeso + "]";
	}
	
}