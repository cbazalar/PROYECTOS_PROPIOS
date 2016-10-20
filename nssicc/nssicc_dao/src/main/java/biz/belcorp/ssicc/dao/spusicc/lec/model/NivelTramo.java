package biz.belcorp.ssicc.dao.spusicc.lec.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class NivelTramo extends AuditableBaseObject {
	
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoPrograma;
	private String codigoNivel;
	private String nivel;
	private String codigoTramo;
	private String tramo;
	private String valPorComiPediCons;
	private String valPorComiPediNCon;
	private String valPorComiTole;
	private String estado;
	private String accion;
	private String codigoRangoComision;
	private String descriRangoComision;
	private String codigoNivelRango;
	private String campannaVigencia;
	    
	public String getCodigoPais() {
		return codigoPais;
	}
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	public String getCodigoNivel() {
		return codigoNivel;
	}
	public String getNivel() {
		return nivel;
	}
	public String getCodigoTramo() {
		return codigoTramo;
	}
	public String getTramo() {
		return tramo;
	}
	public String getValPorComiPediCons() {
		return valPorComiPediCons;
	}
	public String getValPorComiPediNCon() {
		return valPorComiPediNCon;
	}
	public String getValPorComiTole() {
		return valPorComiTole;
	}
	public String getEstado() {
		return estado;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	public void setCodigoNivel(String codigoNivel) {
		this.codigoNivel = codigoNivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public void setCodigoTramo(String codigoTramo) {
		this.codigoTramo = codigoTramo;
	}
	public void setTramo(String tramo) {
		this.tramo = tramo;
	}
	public void setValPorComiPediCons(String valPorComiPediCons) {
		this.valPorComiPediCons = valPorComiPediCons;
	}
	public void setValPorComiPediNCon(String valPorComiPediNCon) {
		this.valPorComiPediNCon = valPorComiPediNCon;
	}
	public void setValPorComiTole(String valPorComiTole) {
		this.valPorComiTole = valPorComiTole;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	/**
	 * @return the codigoRangoComision
	 */
	public String getCodigoRangoComision() {
		return codigoRangoComision;
	}
	/**
	 * @param codigoRangoComision the codigoRangoComision to set
	 */
	public void setCodigoRangoComision(String codigoRangoComision) {
		this.codigoRangoComision = codigoRangoComision;
	}
	/**
	 * @return the descriRangoComision
	 */
	public String getDescriRangoComision() {
		return descriRangoComision;
	}
	/**
	 * @param descriRangoComision the descriRangoComision to set
	 */
	public void setDescriRangoComision(String descriRangoComision) {
		this.descriRangoComision = descriRangoComision;
	}
	
	/**
	 * @return the codigoNivelRango
	 */
	public String getCodigoNivelRango() {
		return codigoNivelRango;
	}
	/**
	 * @param codigoNivelRango the codigoNivelRango to set
	 */
	public void setCodigoNivelRango(String codigoNivelRango) {
		this.codigoNivelRango = codigoNivelRango;
	}
	/**
	 * @return the campannaVigencia
	 */
	public String getCampannaVigencia() {
		return campannaVigencia;
	}
	/**
	 * @param campannaVigencia the campannaVigencia to set
	 */
	public void setCampannaVigencia(String campannaVigencia) {
		this.campannaVigencia = campannaVigencia;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accion == null) ? 0 : accion.hashCode());
		result = prime
				* result
				+ ((campannaVigencia == null) ? 0 : campannaVigencia.hashCode());
		result = prime * result
				+ ((codigoNivel == null) ? 0 : codigoNivel.hashCode());
		result = prime
				* result
				+ ((codigoNivelRango == null) ? 0 : codigoNivelRango.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime
				* result
				+ ((codigoRangoComision == null) ? 0 : codigoRangoComision
						.hashCode());
		result = prime * result
				+ ((codigoTramo == null) ? 0 : codigoTramo.hashCode());
		result = prime
				* result
				+ ((descriRangoComision == null) ? 0 : descriRangoComision
						.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((nivel == null) ? 0 : nivel.hashCode());
		result = prime * result + ((tramo == null) ? 0 : tramo.hashCode());
		result = prime
				* result
				+ ((valPorComiPediCons == null) ? 0 : valPorComiPediCons
						.hashCode());
		result = prime
				* result
				+ ((valPorComiPediNCon == null) ? 0 : valPorComiPediNCon
						.hashCode());
		result = prime * result
				+ ((valPorComiTole == null) ? 0 : valPorComiTole.hashCode());
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
		NivelTramo other = (NivelTramo) obj;
		if (accion == null) {
			if (other.accion != null)
				return false;
		} else if (!accion.equals(other.accion))
			return false;
		if (campannaVigencia == null) {
			if (other.campannaVigencia != null)
				return false;
		} else if (!campannaVigencia.equals(other.campannaVigencia))
			return false;
		if (codigoNivel == null) {
			if (other.codigoNivel != null)
				return false;
		} else if (!codigoNivel.equals(other.codigoNivel))
			return false;
		if (codigoNivelRango == null) {
			if (other.codigoNivelRango != null)
				return false;
		} else if (!codigoNivelRango.equals(other.codigoNivelRango))
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
		if (codigoRangoComision == null) {
			if (other.codigoRangoComision != null)
				return false;
		} else if (!codigoRangoComision.equals(other.codigoRangoComision))
			return false;
		if (codigoTramo == null) {
			if (other.codigoTramo != null)
				return false;
		} else if (!codigoTramo.equals(other.codigoTramo))
			return false;
		if (descriRangoComision == null) {
			if (other.descriRangoComision != null)
				return false;
		} else if (!descriRangoComision.equals(other.descriRangoComision))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (nivel == null) {
			if (other.nivel != null)
				return false;
		} else if (!nivel.equals(other.nivel))
			return false;
		if (tramo == null) {
			if (other.tramo != null)
				return false;
		} else if (!tramo.equals(other.tramo))
			return false;
		if (valPorComiPediCons == null) {
			if (other.valPorComiPediCons != null)
				return false;
		} else if (!valPorComiPediCons.equals(other.valPorComiPediCons))
			return false;
		if (valPorComiPediNCon == null) {
			if (other.valPorComiPediNCon != null)
				return false;
		} else if (!valPorComiPediNCon.equals(other.valPorComiPediNCon))
			return false;
		if (valPorComiTole == null) {
			if (other.valPorComiTole != null)
				return false;
		} else if (!valPorComiTole.equals(other.valPorComiTole))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NivelTramo [codigoPais=" + codigoPais + ", codigoPrograma="
				+ codigoPrograma + ", codigoNivel=" + codigoNivel + ", nivel="
				+ nivel + ", codigoTramo=" + codigoTramo + ", tramo=" + tramo
				+ ", valPorComiPediCons=" + valPorComiPediCons
				+ ", valPorComiPediNCon=" + valPorComiPediNCon
				+ ", valPorComiTole=" + valPorComiTole + ", estado=" + estado
				+ ", accion=" + accion + ", codigoRangoComision="
				+ codigoRangoComision + ", descriRangoComision="
				+ descriRangoComision + ", codigoNivelRango="
				+ codigoNivelRango + ", campannaVigencia=" + campannaVigencia
				+ "]";
	}
	
	
	
	
}
