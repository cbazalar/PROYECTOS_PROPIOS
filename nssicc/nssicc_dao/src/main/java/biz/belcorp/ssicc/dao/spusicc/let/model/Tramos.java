package biz.belcorp.ssicc.dao.spusicc.let.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author aoviedo
 *
 */
public class Tramos extends AuditableBaseObject {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPrograma;
	private String codigoTramo;
	private String periodoInicioTramo;
	private String periodoFinTramo;
	private String retencion22Tramo;
	private String retencion33Tramo;
	private String retencion44Tramo;
	
	private String numeroCampanasCambiarNivel;
	private String evaluacionNivelExito;
	
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
	 * @return the codigoTramo
	 */
	public String getCodigoTramo() {
		return codigoTramo;
	}
	/**
	 * @param codigoTramo the codigoTramo to set
	 */
	public void setCodigoTramo(String codigoTramo) {
		this.codigoTramo = codigoTramo;
	}
	/**
	 * @return the periodoInicioTramo
	 */
	public String getPeriodoInicioTramo() {
		return periodoInicioTramo;
	}
	/**
	 * @param periodoInicioTramo the periodoInicioTramo to set
	 */
	public void setPeriodoInicioTramo(String periodoInicioTramo) {
		this.periodoInicioTramo = periodoInicioTramo;
	}
	/**
	 * @return the periodoFinTramo
	 */
	public String getPeriodoFinTramo() {
		return periodoFinTramo;
	}
	/**
	 * @param periodoFinTramo the periodoFinTramo to set
	 */
	public void setPeriodoFinTramo(String periodoFinTramo) {
		this.periodoFinTramo = periodoFinTramo;
	}
	/**
	 * @return the retencion22Tramo
	 */
	public String getRetencion22Tramo() {
		return retencion22Tramo;
	}
	/**
	 * @param retencion22Tramo the retencion22Tramo to set
	 */
	public void setRetencion22Tramo(String retencion22Tramo) {
		this.retencion22Tramo = retencion22Tramo;
	}
	/**
	 * @return the retencion33Tramo
	 */
	public String getRetencion33Tramo() {
		return retencion33Tramo;
	}
	/**
	 * @param retencion33Tramo the retencion33Tramo to set
	 */
	public void setRetencion33Tramo(String retencion33Tramo) {
		this.retencion33Tramo = retencion33Tramo;
	}
	/**
	 * @return the retencion44Tramo
	 */
	public String getRetencion44Tramo() {
		return retencion44Tramo;
	}
	/**
	 * @param retencion44Tramo the retencion44Tramo to set
	 */
	public void setRetencion44Tramo(String retencion44Tramo) {
		this.retencion44Tramo = retencion44Tramo;
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
	
	/**
	 * @return the numeroCampanasCambiarNivel
	 */
	public String getNumeroCampanasCambiarNivel() {
		return numeroCampanasCambiarNivel;
	}
	/**
	 * @param numeroCampanasCambiarNivel the numeroCampanasCambiarNivel to set
	 */
	public void setNumeroCampanasCambiarNivel(String numeroCampanasCambiarNivel) {
		this.numeroCampanasCambiarNivel = numeroCampanasCambiarNivel;
	}
	/**
	 * @return the evaluacionNivelExito
	 */
	public String getEvaluacionNivelExito() {
		return evaluacionNivelExito;
	}
	/**
	 * @param evaluacionNivelExito the evaluacionNivelExito to set
	 */
	public void setEvaluacionNivelExito(String evaluacionNivelExito) {
		this.evaluacionNivelExito = evaluacionNivelExito;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tramos [codigoPais=" + codigoPais + ", codigoPrograma="
				+ codigoPrograma + ", codigoTramo=" + codigoTramo
				+ ", periodoFinTramo=" + periodoFinTramo
				+ ", periodoInicioTramo=" + periodoInicioTramo
				+ ", retencion22Tramo=" + retencion22Tramo
				+ ", retencion33Tramo=" + retencion33Tramo
				+ ", retencion44Tramo=" + retencion44Tramo
				+ ", evaluacionNivelExito=" + evaluacionNivelExito
				+ ", numeroCampanasCambiarNivel=" + numeroCampanasCambiarNivel + "]";
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
				+ ((codigoTramo == null) ? 0 : codigoTramo.hashCode());
		result = prime * result
				+ ((periodoFinTramo == null) ? 0 : periodoFinTramo.hashCode());
		result = prime
				* result
				+ ((periodoInicioTramo == null) ? 0 : periodoInicioTramo
						.hashCode());
		result = prime
				* result
				+ ((retencion22Tramo == null) ? 0 : retencion22Tramo.hashCode());
		result = prime
				* result
				+ ((retencion33Tramo == null) ? 0 : retencion33Tramo.hashCode());
		result = prime
				* result
				+ ((retencion44Tramo == null) ? 0 : retencion44Tramo.hashCode());
		result = prime
				* result
				+ ((evaluacionNivelExito == null) ? 0 : evaluacionNivelExito.hashCode());
		result = prime
				* result
				+ ((numeroCampanasCambiarNivel == null) ? 0 : numeroCampanasCambiarNivel.hashCode());
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
		Tramos other = (Tramos) obj;
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
		if (codigoTramo == null) {
			if (other.codigoTramo != null)
				return false;
		} else if (!codigoTramo.equals(other.codigoTramo))
			return false;
		if (periodoFinTramo == null) {
			if (other.periodoFinTramo != null)
				return false;
		} else if (!periodoFinTramo.equals(other.periodoFinTramo))
			return false;
		if (periodoInicioTramo == null) {
			if (other.periodoInicioTramo != null)
				return false;
		} else if (!periodoInicioTramo.equals(other.periodoInicioTramo))
			return false;
		if (retencion22Tramo == null) {
			if (other.retencion22Tramo != null)
				return false;
		} else if (!retencion22Tramo.equals(other.retencion22Tramo))
			return false;
		if (retencion33Tramo == null) {
			if (other.retencion33Tramo != null)
				return false;
		} else if (!retencion33Tramo.equals(other.retencion33Tramo))
			return false;
		if (retencion44Tramo == null) {
			if (other.retencion44Tramo != null)
				return false;
		} else if (!retencion44Tramo.equals(other.retencion44Tramo))
			return false;
		if (evaluacionNivelExito == null) {
			if (other.evaluacionNivelExito != null)
				return false;
		} else if (!evaluacionNivelExito.equals(other.evaluacionNivelExito))
			return false;
		if (numeroCampanasCambiarNivel == null) {
			if (other.numeroCampanasCambiarNivel != null)
				return false;
		} else if (!numeroCampanasCambiarNivel.equals(other.numeroCampanasCambiarNivel))
			return false;
		return true;
	}
}