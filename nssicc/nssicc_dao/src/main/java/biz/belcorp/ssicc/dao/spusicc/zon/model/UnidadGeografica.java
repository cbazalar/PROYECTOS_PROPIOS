package biz.belcorp.ssicc.dao.spusicc.zon.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class UnidadGeografica extends AuditableBaseObject implements Serializable {

	private String oidEstruGeopo;
	private String codigoOrdenEstruGeopo;
	private String descripcionEstruGeopo;
	private String codigoSubEstruGeopo;
	private String descripcionSubEstruGeopo;
	
	private String codigoColArchivo;
	private String descripcionColArchivo;
	private String codigoSubEstruGeopoColArchivo;
	
	private String oidValorEstructuraGeo;
	private String descripcion;
	private String nivel1;
	private String nivel2;
	private String nivel3;
	private String nivel4;
	private String estado;
	private String indicadorGeoreferencia;
	private String indicadorGeoreferenciaColArchivo;
	
	private String descripcionActual;
	
	private String codigoPais;
	
	private String indicadorActualizar;
	
	public String getCodigoColArchivo() {
		return codigoColArchivo;
	}
	
	public void setCodigoColArchivo(String codigoColArchivo) {
		this.codigoColArchivo = codigoColArchivo;
	}
	
	public String getCodigoOrdenEstruGeopo() {
		return codigoOrdenEstruGeopo;
	}
	
	public void setCodigoOrdenEstruGeopo(String codigoOrdenEstruGeopo) {
		this.codigoOrdenEstruGeopo = codigoOrdenEstruGeopo;
	}
	
	public String getCodigoSubEstruGeopo() {
		return codigoSubEstruGeopo;
	}
	
	public void setCodigoSubEstruGeopo(String codigoSubEstruGeopo) {
		this.codigoSubEstruGeopo = codigoSubEstruGeopo;
	}
	
	public String getCodigoSubEstruGeopoColArchivo() {
		return codigoSubEstruGeopoColArchivo;
	}
	
	public void setCodigoSubEstruGeopoColArchivo(String codigoSubEstruGeopoColArchivo) {
		this.codigoSubEstruGeopoColArchivo = codigoSubEstruGeopoColArchivo;
	}
	
	public String getDescripcionColArchivo() {
		return descripcionColArchivo;
	}
	
	public void setDescripcionColArchivo(String descripcionColArchivo) {
		this.descripcionColArchivo = descripcionColArchivo;
	}
	
	public String getDescripcionEstruGeopo() {
		return descripcionEstruGeopo;
	}
	
	public void setDescripcionEstruGeopo(String descripcionEstruGeopo) {
		this.descripcionEstruGeopo = descripcionEstruGeopo;
	}
	
	public String getDescripcionSubEstruGeopo() {
		return descripcionSubEstruGeopo;
	}
	
	public void setDescripcionSubEstruGeopo(String descripcionSubEstruGeopo) {
		this.descripcionSubEstruGeopo = descripcionSubEstruGeopo;
	}
	
	public String getOidEstruGeopo() {
		return oidEstruGeopo;
	}
	
	public void setOidEstruGeopo(String oidEstruGeopo) {
		this.oidEstruGeopo = oidEstruGeopo;
	}

	/**
	 * @return the oidValorEstructuraGeo
	 */
	public String getOidValorEstructuraGeo() {
		return oidValorEstructuraGeo;
	}

	/**
	 * @param oidValorEstructuraGeo the oidValorEstructuraGeo to set
	 */
	public void setOidValorEstructuraGeo(String oidValorEstructuraGeo) {
		this.oidValorEstructuraGeo = oidValorEstructuraGeo;
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
	 * @return the nivel1
	 */
	public String getNivel1() {
		return nivel1;
	}

	/**
	 * @param nivel1 the nivel1 to set
	 */
	public void setNivel1(String nivel1) {
		this.nivel1 = nivel1;
	}

	/**
	 * @return the nivel2
	 */
	public String getNivel2() {
		return nivel2;
	}

	/**
	 * @param nivel2 the nivel2 to set
	 */
	public void setNivel2(String nivel2) {
		this.nivel2 = nivel2;
	}

	/**
	 * @return the nivel3
	 */
	public String getNivel3() {
		return nivel3;
	}

	/**
	 * @param nivel3 the nivel3 to set
	 */
	public void setNivel3(String nivel3) {
		this.nivel3 = nivel3;
	}

	/**
	 * @return the nivel4
	 */
	public String getNivel4() {
		return nivel4;
	}

	/**
	 * @param nivel4 the nivel4 to set
	 */
	public void setNivel4(String nivel4) {
		this.nivel4 = nivel4;
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
	 * @return the indicadorGeoreferencia
	 */
	public String getIndicadorGeoreferencia() {
		return indicadorGeoreferencia;
	}

	/**
	 * @param indicadorGeoreferencia the indicadorGeoreferencia to set
	 */
	public void setIndicadorGeoreferencia(String indicadorGeoreferencia) {
		this.indicadorGeoreferencia = indicadorGeoreferencia;
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
	 * @return the indicadorGeoreferenciaColArchivo
	 */
	public String getIndicadorGeoreferenciaColArchivo() {
		return indicadorGeoreferenciaColArchivo;
	}

	/**
	 * @param indicadorGeoreferenciaColArchivo the indicadorGeoreferenciaColArchivo to set
	 */
	public void setIndicadorGeoreferenciaColArchivo(
			String indicadorGeoreferenciaColArchivo) {
		this.indicadorGeoreferenciaColArchivo = indicadorGeoreferenciaColArchivo;
	}

	public String getIndicadorActualizar() {
		return indicadorActualizar;
	}

	public void setIndicadorActualizar(String indicadorActualizar) {
		this.indicadorActualizar = indicadorActualizar;
	}


	/**
	 * @return the descripcionActual
	 */
	public String getDescripcionActual() {
		return descripcionActual;
	}

	/**
	 * @param descripcionActual the descripcionActual to set
	 */
	public void setDescripcionActual(String descripcionActual) {
		this.descripcionActual = descripcionActual;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 13;
		result = prime
				* result
				+ ((codigoColArchivo == null) ? 0 : codigoColArchivo.hashCode());
		result = prime
				* result
				+ ((codigoOrdenEstruGeopo == null) ? 0 : codigoOrdenEstruGeopo
						.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime
				* result
				+ ((codigoSubEstruGeopo == null) ? 0 : codigoSubEstruGeopo
						.hashCode());
		result = prime
				* result
				+ ((codigoSubEstruGeopoColArchivo == null) ? 0
						: codigoSubEstruGeopoColArchivo.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime
				* result
				+ ((descripcionColArchivo == null) ? 0 : descripcionColArchivo
						.hashCode());
		result = prime
				* result
				+ ((descripcionEstruGeopo == null) ? 0 : descripcionEstruGeopo
						.hashCode());
		result = prime
				* result
				+ ((descripcionSubEstruGeopo == null) ? 0
						: descripcionSubEstruGeopo.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime
				* result
				+ ((indicadorGeoreferencia == null) ? 0
						: indicadorGeoreferencia.hashCode());
		result = prime
				* result
				+ ((indicadorGeoreferenciaColArchivo == null) ? 0
						: indicadorGeoreferenciaColArchivo.hashCode());
		result = prime * result + ((nivel1 == null) ? 0 : nivel1.hashCode());
		result = prime * result + ((nivel2 == null) ? 0 : nivel2.hashCode());
		result = prime * result + ((nivel3 == null) ? 0 : nivel3.hashCode());
		result = prime * result + ((nivel4 == null) ? 0 : nivel4.hashCode());
		result = prime * result
				+ ((oidEstruGeopo == null) ? 0 : oidEstruGeopo.hashCode());
		result = prime
				* result
				+ ((oidValorEstructuraGeo == null) ? 0 : oidValorEstructuraGeo
						.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		UnidadGeografica other = (UnidadGeografica) obj;
		if (codigoColArchivo == null) {
			if (other.codigoColArchivo != null)
				return false;
		} else if (!codigoColArchivo.equals(other.codigoColArchivo))
			return false;
		if (codigoOrdenEstruGeopo == null) {
			if (other.codigoOrdenEstruGeopo != null)
				return false;
		} else if (!codigoOrdenEstruGeopo.equals(other.codigoOrdenEstruGeopo))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (codigoSubEstruGeopo == null) {
			if (other.codigoSubEstruGeopo != null)
				return false;
		} else if (!codigoSubEstruGeopo.equals(other.codigoSubEstruGeopo))
			return false;
		if (codigoSubEstruGeopoColArchivo == null) {
			if (other.codigoSubEstruGeopoColArchivo != null)
				return false;
		} else if (!codigoSubEstruGeopoColArchivo
				.equals(other.codigoSubEstruGeopoColArchivo))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (descripcionColArchivo == null) {
			if (other.descripcionColArchivo != null)
				return false;
		} else if (!descripcionColArchivo.equals(other.descripcionColArchivo))
			return false;
		if (descripcionEstruGeopo == null) {
			if (other.descripcionEstruGeopo != null)
				return false;
		} else if (!descripcionEstruGeopo.equals(other.descripcionEstruGeopo))
			return false;
		if (descripcionSubEstruGeopo == null) {
			if (other.descripcionSubEstruGeopo != null)
				return false;
		} else if (!descripcionSubEstruGeopo
				.equals(other.descripcionSubEstruGeopo))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (indicadorGeoreferencia == null) {
			if (other.indicadorGeoreferencia != null)
				return false;
		} else if (!indicadorGeoreferencia.equals(other.indicadorGeoreferencia))
			return false;
		if (indicadorGeoreferenciaColArchivo == null) {
			if (other.indicadorGeoreferenciaColArchivo != null)
				return false;
		} else if (!indicadorGeoreferenciaColArchivo
				.equals(other.indicadorGeoreferenciaColArchivo))
			return false;
		if (nivel1 == null) {
			if (other.nivel1 != null)
				return false;
		} else if (!nivel1.equals(other.nivel1))
			return false;
		if (nivel2 == null) {
			if (other.nivel2 != null)
				return false;
		} else if (!nivel2.equals(other.nivel2))
			return false;
		if (nivel3 == null) {
			if (other.nivel3 != null)
				return false;
		} else if (!nivel3.equals(other.nivel3))
			return false;
		if (nivel4 == null) {
			if (other.nivel4 != null)
				return false;
		} else if (!nivel4.equals(other.nivel4))
			return false;
		if (oidEstruGeopo == null) {
			if (other.oidEstruGeopo != null)
				return false;
		} else if (!oidEstruGeopo.equals(other.oidEstruGeopo))
			return false;
		if (oidValorEstructuraGeo == null) {
			if (other.oidValorEstructuraGeo != null)
				return false;
		} else if (!oidValorEstructuraGeo.equals(other.oidValorEstructuraGeo))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UnidadGeografica [codigoColArchivo=" + codigoColArchivo
				+ ", codigoOrdenEstruGeopo=" + codigoOrdenEstruGeopo
				+ ", codigoPais=" + codigoPais + ", codigoSubEstruGeopo="
				+ codigoSubEstruGeopo + ", codigoSubEstruGeopoColArchivo="
				+ codigoSubEstruGeopoColArchivo + ", descripcion="
				+ descripcion + ", descripcionColArchivo="
				+ descripcionColArchivo + ", descripcionEstruGeopo="
				+ descripcionEstruGeopo + ", descripcionSubEstruGeopo="
				+ descripcionSubEstruGeopo + ", estado=" + estado
				+ ", indicadorGeoreferencia=" + indicadorGeoreferencia
				+ ", indicadorGeoreferenciaColArchivo="
				+ indicadorGeoreferenciaColArchivo + ", nivel1=" + nivel1
				+ ", nivel2=" + nivel2 + ", nivel3=" + nivel3 + ", nivel4="
				+ nivel4 + ", oidEstruGeopo=" + oidEstruGeopo
				+ ", oidValorEstructuraGeo=" + oidValorEstructuraGeo + "]";
	}
}