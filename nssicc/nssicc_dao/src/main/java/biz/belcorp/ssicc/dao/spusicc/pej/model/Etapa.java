/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pej.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;


/**
 * @author itocto
 *
 */
public class Etapa extends AuditableBaseObject{

	private String codigoPrograma;
	
	private String codigo;
	private String campanyaInicio;
	private String campanyaFinal;
	private String estado;
	private String correlativo;
	
	private String estadoRegistro;
	
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
	 * @return the campanyaInicio
	 */
	public String getCampanyaInicio() {
		return campanyaInicio;
	}
	/**
	 * @param campanyaInicio the campanyaInicio to set
	 */
	public void setCampanyaInicio(String campanyaInicio) {
		this.campanyaInicio = campanyaInicio;
	}
	/**
	 * @return the campanyaFinal
	 */
	public String getCampanyaFinal() {
		return campanyaFinal;
	}
	/**
	 * @param campanyaFinal the campanyaFinal to set
	 */
	public void setCampanyaFinal(String campanyaFinal) {
		this.campanyaFinal = campanyaFinal;
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
	 * @return the correlativo
	 */
	public String getCorrelativo() {
		return correlativo;
	}
	/**
	 * @param correlativo the correlativo to set
	 */
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	/**
	 * @return the codigoPrograma
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	/**
	 * @param codigoPrograma the codigoPrograma to set
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	
	/**
	 * @return the estadoRegistro
	 */
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	/**
	 * @param estadoRegistro the estadoRegistro to set
	 */
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((campanyaFinal == null) ? 0 : campanyaFinal.hashCode());
		result = prime * result
				+ ((campanyaInicio == null) ? 0 : campanyaInicio.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime * result
				+ ((correlativo == null) ? 0 : correlativo.hashCode());
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etapa other = (Etapa) obj;
		if (campanyaFinal == null) {
			if (other.campanyaFinal != null)
				return false;
		} else if (!campanyaFinal.equals(other.campanyaFinal))
			return false;
		if (campanyaInicio == null) {
			if (other.campanyaInicio != null)
				return false;
		} else if (!campanyaInicio.equals(other.campanyaInicio))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (codigoPrograma == null) {
			if (other.codigoPrograma != null)
				return false;
		} else if (!codigoPrograma.equals(other.codigoPrograma))
			return false;
		if (correlativo == null) {
			if (other.correlativo != null)
				return false;
		} else if (!correlativo.equals(other.correlativo))
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
		return "Etapa [codigoPrograma=" + codigoPrograma + ", codigo=" + codigo
				+ ", campanyaInicio=" + campanyaInicio + ", campanyaFinal="
				+ campanyaFinal + ", estado=" + estado + ", correlativo="
				+ correlativo + "]";
	}
}