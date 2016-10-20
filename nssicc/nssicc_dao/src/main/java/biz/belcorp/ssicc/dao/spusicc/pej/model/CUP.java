package biz.belcorp.ssicc.dao.spusicc.pej.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author aoviedo
 *
 */
public class CUP extends AuditableBaseObject {
	
	private String codigoPrograma;
	
	private String numeroProducto;
	private String campanyaProceso;
	private String codigo;
	private String nivel;
	private String correlativo;
	
	private String estadoRegistro;
	
	private String descNivel;
	
	/**
	 * @return the numeroProducto
	 */
	public String getNumeroProducto() {
		return numeroProducto;
	}

	/**
	 * @param numeroProducto the numeroProducto to set
	 */
	public void setNumeroProducto(String numeroProducto) {
		this.numeroProducto = numeroProducto;
	}

	/**
	 * @return the campanyaProceso
	 */
	public String getCampanyaProceso() {
		return campanyaProceso;
	}

	/**
	 * @param campanyaProceso the campanyaProceso to set
	 */
	public void setCampanyaProceso(String campanyaProceso) {
		this.campanyaProceso = campanyaProceso;
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
	 * @return the nivel
	 */
	public String getNivel() {
		return nivel;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
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

	/**
	 * @return the descNivel
	 */
	public String getDescNivel() {
		return descNivel;
	}

	/**
	 * @param descNivel the descNivel to set
	 */
	public void setDescNivel(String descNivel) {
		this.descNivel = descNivel;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CUP [codigoPrograma=" + codigoPrograma + ", campanyaProceso="
				+ campanyaProceso + ", codigo=" + codigo + ", nivel=" + nivel
				+ ", correlativo=" + correlativo + "]";
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
		CUP other = (CUP) obj;
		if (campanyaProceso == null) {
			if (other.campanyaProceso != null)
				return false;
		} else if (!campanyaProceso.equals(other.campanyaProceso))
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
		if (nivel == null) {
			if (other.nivel != null)
				return false;
		} else if (!nivel.equals(other.nivel))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((campanyaProceso == null) ? 0 : campanyaProceso.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime * result
				+ ((correlativo == null) ? 0 : correlativo.hashCode());
		result = prime * result + ((nivel == null) ? 0 : nivel.hashCode());
		return result;
	}
	
}