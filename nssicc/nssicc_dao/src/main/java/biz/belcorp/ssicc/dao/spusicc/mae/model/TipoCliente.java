package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="itocto@sigcomt.com">Ivan Tocto</a>
 *
 */
public class TipoCliente extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String oid;
	private String codigoPais;
	private String codigo;
	private String descripcion;
	private String indicadorEvaluarStatus;
	private String indicadorEmpleado;
	private String indicadorEstado;
	
	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}
	/**
	 * @param oid the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
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
	 * @return the indicadorEvaluarStatus
	 */
	public String getIndicadorEvaluarStatus() {
		return indicadorEvaluarStatus;
	}
	/**
	 * @param indicadorEvaluarStatus the indicadorEvaluarStatus to set
	 */
	public void setIndicadorEvaluarStatus(String indicadorEvaluarStatus) {
		this.indicadorEvaluarStatus = indicadorEvaluarStatus;
	}
	/**
	 * @return the indicadorEmpleado
	 */
	public String getIndicadorEmpleado() {
		return indicadorEmpleado;
	}
	/**
	 * @param indicadorEmpleado the indicadorEmpleado to set
	 */
	public void setIndicadorEmpleado(String indicadorEmpleado) {
		this.indicadorEmpleado = indicadorEmpleado;
	}
	
	
	
	/**
	 * @return the indicadorEstado
	 */
	public String getIndicadorEstado() {
		return indicadorEstado;
	}
	/**
	 * @param indicadorEstado the indicadorEstado to set
	 */
	public void setIndicadorEstado(String indicadorEstado) {
		this.indicadorEstado = indicadorEstado;
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
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime
				* result
				+ ((indicadorEvaluarStatus == null) ? 0
						: indicadorEvaluarStatus.hashCode());
		result = prime * result
				+ ((indicadorEmpleado == null) ? 0 : indicadorEmpleado.hashCode());
		result = prime * result
		+ ((indicadorEstado == null) ? 0 : indicadorEstado.hashCode());
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
		TipoCliente other = (TipoCliente) obj;
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
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (indicadorEvaluarStatus == null) {
			if (other.indicadorEvaluarStatus != null)
				return false;
		} else if (!indicadorEvaluarStatus
				.equals(other.indicadorEvaluarStatus))
			return false;
		if (indicadorEmpleado == null) {
			if (other.indicadorEmpleado != null)
				return false;
		} else if (!indicadorEmpleado.equals(other.indicadorEmpleado))
			return false;
		
		if (indicadorEstado == null) {
			if (other.indicadorEstado != null)
				return false;
		} else if (!indicadorEstado.equals(other.indicadorEstado))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TipoBloqueo [codigoPais=" + codigoPais + ", codigo=" + codigo
				+ ", descripcion=" + descripcion
				+ ", indicadorEvaluarStatus=" + indicadorEvaluarStatus
				+ ", indicadorEstado=" + indicadorEstado
				+ ", indicadorEmpleado="
				+ indicadorEmpleado + "]";
	}	
	
}
