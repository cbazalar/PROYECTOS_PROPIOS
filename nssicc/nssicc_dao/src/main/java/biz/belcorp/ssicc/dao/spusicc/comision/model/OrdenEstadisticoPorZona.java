/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.comision.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextrramirez
 *
 */
public class OrdenEstadisticoPorZona extends AuditableBaseObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	
	private String codigoRegion;
	
	private String codigoZona;
	
	private String ordenEstadistico;
	
	private String estatusRegistro;	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoRegion == null) ? 0 : codigoRegion.hashCode());
		result = prime * result
				+ ((codigoZona == null) ? 0 : codigoZona.hashCode());
		result = prime * result
				+ ((estatusRegistro == null) ? 0 : estatusRegistro.hashCode());
		result = prime
				* result
				+ ((ordenEstadistico == null) ? 0 : ordenEstadistico.hashCode());
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
		OrdenEstadisticoPorZona other = (OrdenEstadisticoPorZona) obj;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (codigoRegion == null) {
			if (other.codigoRegion != null)
				return false;
		} else if (!codigoRegion.equals(other.codigoRegion))
			return false;
		if (codigoZona == null) {
			if (other.codigoZona != null)
				return false;
		} else if (!codigoZona.equals(other.codigoZona))
			return false;
		if (estatusRegistro == null) {
			if (other.estatusRegistro != null)
				return false;
		} else if (!estatusRegistro.equals(other.estatusRegistro))
			return false;
		if (ordenEstadistico == null) {
			if (other.ordenEstadistico != null)
				return false;
		} else if (!ordenEstadistico.equals(other.ordenEstadistico))
			return false;
		return true;
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
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the ordenEstadistico
	 */
	public String getOrdenEstadistico() {
		return ordenEstadistico;
	}

	/**
	 * @param ordenEstadistico the ordenEstadistico to set
	 */
	public void setOrdenEstadistico(String ordenEstadistico) {
		this.ordenEstadistico = ordenEstadistico;
	}

	/**
	 * @return the estatusRegistro
	 */
	public String getEstatusRegistro() {
		return estatusRegistro;
	}

	/**
	 * @param estatusRegistro the estatusRegistro to set
	 */
	public void setEstatusRegistro(String estatusRegistro) {
		this.estatusRegistro = estatusRegistro;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrdenEstadisticoPorZona [codigoPais=" + codigoPais
				+ ", codigoRegion=" + codigoRegion + ", codigoZona="
				+ codigoZona + ", ordenEstadistico=" + ordenEstadistico
				+ ", estatusRegistro=" + estatusRegistro + "]";
	}
    
	

	

}
