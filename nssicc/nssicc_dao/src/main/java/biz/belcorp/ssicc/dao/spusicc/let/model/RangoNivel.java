package biz.belcorp.ssicc.dao.spusicc.let.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author aoviedo
 *
 */
public class RangoNivel extends AuditableBaseObject {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPrograma;
	private String codigoNivel;
	//private String nivel;
	private String descripcionNivel;
	private String pedidosIniciales;
	private String pedidosFinales;
	private String tolerancia;
	private String percentil;
	private String gananciaCumplimiento;
	private String gananciaSobrecumplimiento;
	
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
	 * @return the codigoNivel
	 */
	public String getCodigoNivel() {
		return codigoNivel;
	}
	/**
	 * @param codigoNivel the codigoNivel to set
	 */
	public void setCodigoNivel(String codigoNivel) {
		this.codigoNivel = codigoNivel;
	}
	/**
	 * @return the descripcionNivel
	 */
	public String getDescripcionNivel() {
		return descripcionNivel;
	}
	/**
	 * @param descripcionNivel the descripcionNivel to set
	 */
	public void setDescripcionNivel(String descripcionNivel) {
		this.descripcionNivel = descripcionNivel;
	}
	/**
	 * @return the pedidosIniciales
	 */
	public String getPedidosIniciales() {
		return pedidosIniciales;
	}
	/**
	 * @param pedidosIniciales the pedidosIniciales to set
	 */
	public void setPedidosIniciales(String pedidosIniciales) {
		this.pedidosIniciales = pedidosIniciales;
	}
	/**
	 * @return the pedidosFinales
	 */
	public String getPedidosFinales() {
		return pedidosFinales;
	}
	/**
	 * @param pedidosFinales the pedidosFinales to set
	 */
	public void setPedidosFinales(String pedidosFinales) {
		this.pedidosFinales = pedidosFinales;
	}
	/**
	 * @return the tolerancia
	 */
	public String getTolerancia() {
		return tolerancia;
	}
	/**
	 * @param tolerancia the tolerancia to set
	 */
	public void setTolerancia(String tolerancia) {
		this.tolerancia = tolerancia;
	}
	/**
	 * @return the percentil
	 */
	public String getPercentil() {
		return percentil;
	}
	/**
	 * @param percentil the percentil to set
	 */
	public void setPercentil(String percentil) {
		this.percentil = percentil;
	}
	/**
	 * @return the gananciaCumplimiento
	 */
	public String getGananciaCumplimiento() {
		return gananciaCumplimiento;
	}
	/**
	 * @param gananciaCumplimiento the gananciaCumplimiento to set
	 */
	public void setGananciaCumplimiento(String gananciaCumplimiento) {
		this.gananciaCumplimiento = gananciaCumplimiento;
	}
	/**
	 * @return the gananciaSobrecumplimiento
	 */
	public String getGananciaSobrecumplimiento() {
		return gananciaSobrecumplimiento;
	}
	/**
	 * @param gananciaSobrecumplimiento the gananciaSobrecumplimiento to set
	 */
	public void setGananciaSobrecumplimiento(String gananciaSobrecumplimiento) {
		this.gananciaSobrecumplimiento = gananciaSobrecumplimiento;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RangoNivel [codigoNivel=" + codigoNivel + ", codigoPais="
				+ codigoPais + ", codigoPrograma=" + codigoPrograma
				+ ", gananciaCumplimiento=" + gananciaCumplimiento
				+ ", gananciaSobrecumplimiento=" + gananciaSobrecumplimiento
				+ ", pedidosFinales=" + pedidosFinales
				+ ", pedidosIniciales=" + pedidosIniciales + ", percentil="
				+ percentil + ", tolerancia=" + tolerancia + "]";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoNivel == null) ? 0 : codigoNivel.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime
				* result
				+ ((gananciaCumplimiento == null) ? 0 : gananciaCumplimiento
						.hashCode());
		result = prime
				* result
				+ ((gananciaSobrecumplimiento == null) ? 0
						: gananciaSobrecumplimiento.hashCode());
		result = prime * result
				+ ((pedidosFinales == null) ? 0 : pedidosFinales.hashCode());
		result = prime
				* result
				+ ((pedidosIniciales == null) ? 0 : pedidosIniciales.hashCode());
		result = prime * result
				+ ((percentil == null) ? 0 : percentil.hashCode());
		result = prime * result
				+ ((tolerancia == null) ? 0 : tolerancia.hashCode());
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
		RangoNivel other = (RangoNivel) obj;
		if (codigoNivel == null) {
			if (other.codigoNivel != null)
				return false;
		} else if (!codigoNivel.equals(other.codigoNivel))
			return false;
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
		if (gananciaCumplimiento == null) {
			if (other.gananciaCumplimiento != null)
				return false;
		} else if (!gananciaCumplimiento.equals(other.gananciaCumplimiento))
			return false;
		if (gananciaSobrecumplimiento == null) {
			if (other.gananciaSobrecumplimiento != null)
				return false;
		} else if (!gananciaSobrecumplimiento
				.equals(other.gananciaSobrecumplimiento))
			return false;
		if (pedidosFinales == null) {
			if (other.pedidosFinales != null)
				return false;
		} else if (!pedidosFinales.equals(other.pedidosFinales))
			return false;
		if (pedidosIniciales == null) {
			if (other.pedidosIniciales != null)
				return false;
		} else if (!pedidosIniciales.equals(other.pedidosIniciales))
			return false;
		if (percentil == null) {
			if (other.percentil != null)
				return false;
		} else if (!percentil.equals(other.percentil))
			return false;
		if (tolerancia == null) {
			if (other.tolerancia != null)
				return false;
		} else if (!tolerancia.equals(other.tolerancia))
			return false;
		return true;
	}
}