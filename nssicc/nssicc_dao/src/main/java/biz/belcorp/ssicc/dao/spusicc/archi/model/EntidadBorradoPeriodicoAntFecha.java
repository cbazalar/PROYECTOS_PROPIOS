package biz.belcorp.ssicc.dao.spusicc.archi.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class EntidadBorradoPeriodicoAntFecha extends AuditableBaseObject implements Serializable {
	
	private static final long serialVersionUID = -7765158632298388535L;
	
	private String codigoModulo;
	private String entidad;
	private String fechaAntigua;
	private Integer numeroDias;
	private Integer indicadorActivo;
	
	
	public EntidadBorradoPeriodicoAntFecha(){
		
	}	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EntidadBorradoPeriodicoAntFecha [codigoModulo=" + codigoModulo
				+ ", entidad=" + entidad + ", fechaAntigua=" + fechaAntigua
				+ ", numeroDias=" + numeroDias + ", indicadorActivo="
				+ indicadorActivo + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoModulo == null) ? 0 : codigoModulo.hashCode());
		result = prime * result + ((entidad == null) ? 0 : entidad.hashCode());
		result = prime * result
				+ ((fechaAntigua == null) ? 0 : fechaAntigua.hashCode());
		result = prime * result
				+ ((indicadorActivo == null) ? 0 : indicadorActivo.hashCode());
		result = prime * result
				+ ((numeroDias == null) ? 0 : numeroDias.hashCode());
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
		EntidadBorradoPeriodicoAntFecha other = (EntidadBorradoPeriodicoAntFecha) obj;
		if (codigoModulo == null) {
			if (other.codigoModulo != null)
				return false;
		} else if (!codigoModulo.equals(other.codigoModulo))
			return false;
		if (entidad == null) {
			if (other.entidad != null)
				return false;
		} else if (!entidad.equals(other.entidad))
			return false;
		if (fechaAntigua == null) {
			if (other.fechaAntigua != null)
				return false;
		} else if (!fechaAntigua.equals(other.fechaAntigua))
			return false;
		if (indicadorActivo == null) {
			if (other.indicadorActivo != null)
				return false;
		} else if (!indicadorActivo.equals(other.indicadorActivo))
			return false;
		if (numeroDias == null) {
			if (other.numeroDias != null)
				return false;
		} else if (!numeroDias.equals(other.numeroDias))
			return false;
		return true;
	}



	/**
	 * @return the codigoModulo
	 */
	public String getCodigoModulo() {
		return codigoModulo;
	}


	/**
	 * @param codigoModulo the codigoModulo to set
	 */
	public void setCodigoModulo(String codigoModulo) {
		this.codigoModulo = codigoModulo;
	}


	/**
	 * @return the entidad
	 */
	public String getEntidad() {
		return entidad;
	}

	/**
	 * @param entidad the entidad to set
	 */
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	/**
	 * @return the fechaAntigua
	 */
	public String getFechaAntigua() {
		return fechaAntigua;
	}

	/**
	 * @param fechaAntigua the fechaAntigua to set
	 */
	public void setFechaAntigua(String fechaAntigua) {
		this.fechaAntigua = fechaAntigua;
	}

	/**
	 * @return the numeroDias
	 */
	public Integer getNumeroDias() {
		return numeroDias;
	}

	/**
	 * @param numeroDias the numeroDias to set
	 */
	public void setNumeroDias(Integer numeroDias) {
		this.numeroDias = numeroDias;
	}

	/**
	 * @return the indicadorActivo
	 */
	public Integer getIndicadorActivo() {
		return indicadorActivo;
	}

	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(Integer indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}
	
	

}
