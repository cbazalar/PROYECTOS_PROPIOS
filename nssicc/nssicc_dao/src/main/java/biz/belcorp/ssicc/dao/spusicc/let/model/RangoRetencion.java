package biz.belcorp.ssicc.dao.spusicc.let.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author aoviedo
 *
 */
public class RangoRetencion extends AuditableBaseObject {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPrograma;
	private String codigoRetencion;
	private String ingresosIniciales;
	private String ingresosFinales;
	private String ganancia22;
	private String ganancia33;
	private String ganancia44;
	private String retencion22;
	private String retencion33;
	private String retencion44;
	private String indicadorPremio;
	
	private String correlativo;
	private String estado;
	
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
	 * @return the codigoRetencion
	 */
	public String getCodigoRetencion() {
		return codigoRetencion;
	}
	/**
	 * @param codigoRetencion the codigoRetencion to set
	 */
	public void setCodigoRetencion(String codigoRetencion) {
		this.codigoRetencion = codigoRetencion;
	}
	/**
	 * @return the ingresosIniciales
	 */
	public String getIngresosIniciales() {
		return ingresosIniciales;
	}
	/**
	 * @param ingresosIniciales the ingresosIniciales to set
	 */
	public void setIngresosIniciales(String ingresosIniciales) {
		this.ingresosIniciales = ingresosIniciales;
	}
	/**
	 * @return the ingresosFinales
	 */
	public String getIngresosFinales() {
		return ingresosFinales;
	}
	/**
	 * @param ingresosFinales the ingresosFinales to set
	 */
	public void setIngresosFinales(String ingresosFinales) {
		this.ingresosFinales = ingresosFinales;
	}
	/**
	 * @return the ganancia22
	 */
	public String getGanancia22() {
		return ganancia22;
	}
	/**
	 * @param ganancia22 the ganancia22 to set
	 */
	public void setGanancia22(String ganancia22) {
		this.ganancia22 = ganancia22;
	}
	/**
	 * @return the ganancia33
	 */
	public String getGanancia33() {
		return ganancia33;
	}
	/**
	 * @param ganancia33 the ganancia33 to set
	 */
	public void setGanancia33(String ganancia33) {
		this.ganancia33 = ganancia33;
	}
	/**
	 * @return the ganancia44
	 */
	public String getGanancia44() {
		return ganancia44;
	}
	/**
	 * @param ganancia44 the ganancia44 to set
	 */
	public void setGanancia44(String ganancia44) {
		this.ganancia44 = ganancia44;
	}
	/**
	 * @return the retencion22
	 */
	public String getRetencion22() {
		return retencion22;
	}
	/**
	 * @param retencion22 the retencion22 to set
	 */
	public void setRetencion22(String retencion22) {
		this.retencion22 = retencion22;
	}
	/**
	 * @return the retencion33
	 */
	public String getRetencion33() {
		return retencion33;
	}
	/**
	 * @param retencion33 the retencion33 to set
	 */
	public void setRetencion33(String retencion33) {
		this.retencion33 = retencion33;
	}
	/**
	 * @return the retencion44
	 */
	public String getRetencion44() {
		return retencion44;
	}
	/**
	 * @param retencion44 the retencion44 to set
	 */
	public void setRetencion44(String retencion44) {
		this.retencion44 = retencion44;
	}
	/**
	 * @return the indicadorPremio
	 */
	public String getIndicadorPremio() {
		return indicadorPremio;
	}
	/**
	 * @param indicadorPremio the indicadorPremio to set
	 */
	public void setIndicadorPremio(String indicadorPremio) {
		this.indicadorPremio = indicadorPremio;
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
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime * result
				+ ((codigoRetencion == null) ? 0 : codigoRetencion.hashCode());
		result = prime * result
				+ ((correlativo == null) ? 0 : correlativo.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result
				+ ((ganancia22 == null) ? 0 : ganancia22.hashCode());
		result = prime * result
				+ ((ganancia33 == null) ? 0 : ganancia33.hashCode());
		result = prime * result
				+ ((ganancia44 == null) ? 0 : ganancia44.hashCode());
		result = prime * result
				+ ((indicadorPremio == null) ? 0 : indicadorPremio.hashCode());
		result = prime * result
				+ ((ingresosFinales == null) ? 0 : ingresosFinales.hashCode());
		result = prime
				* result
				+ ((ingresosIniciales == null) ? 0 : ingresosIniciales
						.hashCode());
		result = prime * result
				+ ((retencion22 == null) ? 0 : retencion22.hashCode());
		result = prime * result
				+ ((retencion33 == null) ? 0 : retencion33.hashCode());
		result = prime * result
				+ ((retencion44 == null) ? 0 : retencion44.hashCode());
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
		RangoRetencion other = (RangoRetencion) obj;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (codigoPrograma == null) {
			if (other.codigoPrograma != null)
				return false;
		} else if (!codigoPrograma.equals(other.codigoPrograma))
			return false;
		if (codigoRetencion == null) {
			if (other.codigoRetencion != null)
				return false;
		} else if (!codigoRetencion.equals(other.codigoRetencion))
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
		if (ganancia22 == null) {
			if (other.ganancia22 != null)
				return false;
		} else if (!ganancia22.equals(other.ganancia22))
			return false;
		if (ganancia33 == null) {
			if (other.ganancia33 != null)
				return false;
		} else if (!ganancia33.equals(other.ganancia33))
			return false;
		if (ganancia44 == null) {
			if (other.ganancia44 != null)
				return false;
		} else if (!ganancia44.equals(other.ganancia44))
			return false;
		if (indicadorPremio == null) {
			if (other.indicadorPremio != null)
				return false;
		} else if (!indicadorPremio.equals(other.indicadorPremio))
			return false;
		if (ingresosFinales == null) {
			if (other.ingresosFinales != null)
				return false;
		} else if (!ingresosFinales.equals(other.ingresosFinales))
			return false;
		if (ingresosIniciales == null) {
			if (other.ingresosIniciales != null)
				return false;
		} else if (!ingresosIniciales.equals(other.ingresosIniciales))
			return false;
		if (retencion22 == null) {
			if (other.retencion22 != null)
				return false;
		} else if (!retencion22.equals(other.retencion22))
			return false;
		if (retencion33 == null) {
			if (other.retencion33 != null)
				return false;
		} else if (!retencion33.equals(other.retencion33))
			return false;
		if (retencion44 == null) {
			if (other.retencion44 != null)
				return false;
		} else if (!retencion44.equals(other.retencion44))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RangoRetencion [codigoPais=" + codigoPais + ", codigoPrograma="
				+ codigoPrograma + ", codigoRetencion=" + codigoRetencion
				+ ", ingresosIniciales=" + ingresosIniciales
				+ ", ingresosFinales=" + ingresosFinales + ", ganancia22="
				+ ganancia22 + ", ganancia33=" + ganancia33 + ", ganancia44="
				+ ganancia44 + ", retencion22=" + retencion22
				+ ", retencion33=" + retencion33 + ", retencion44="
				+ retencion44 + ", indicadorPremio=" + indicadorPremio
				+ ", correlativo=" + correlativo + ", estado=" + estado + "]";
	}
}