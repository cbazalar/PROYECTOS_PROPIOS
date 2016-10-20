package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="itocto@sigcomt.com">Ivan Tocto</a>
 *
 */
public class TipoBloqueo extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String oid;
	private String codigoPais;
	private String codigo;
	private String descripcion;
	private String indicadorBloqueoFinanciero;
	private String oidMotivoRechazo;
	private String oidFormaBloqueo;
	private String oidFormaDesbloqueo;
	private String nivelGravedad;
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
	 * @return the indicadorBloqueoFinanciero
	 */
	public String getIndicadorBloqueoFinanciero() {
		return indicadorBloqueoFinanciero;
	}
	/**
	 * @param indicadorBloqueoFinanciero the indicadorBloqueoFinanciero to set
	 */
	public void setIndicadorBloqueoFinanciero(String indicadorBloqueoFinanciero) {
		this.indicadorBloqueoFinanciero = indicadorBloqueoFinanciero;
	}
	/**
	 * @return the oidMotivoRechazo
	 */
	public String getOidMotivoRechazo() {
		return oidMotivoRechazo;
	}
	/**
	 * @param oidMotivoRechazo the oidMotivoRechazo to set
	 */
	public void setOidMotivoRechazo(String oidMotivoRechazo) {
		this.oidMotivoRechazo = oidMotivoRechazo;
	}
	/**
	 * @return the oidFormaBloqueo
	 */
	public String getOidFormaBloqueo() {
		return oidFormaBloqueo;
	}
	/**
	 * @param oidFormaBloqueo the oidFormaBloqueo to set
	 */
	public void setOidFormaBloqueo(String oidFormaBloqueo) {
		this.oidFormaBloqueo = oidFormaBloqueo;
	}
	/**
	 * @return the oidFormaDesbloqueo
	 */
	public String getOidFormaDesbloqueo() {
		return oidFormaDesbloqueo;
	}
	/**
	 * @param oidFormaDesbloqueo the oidFormaDesbloqueo to set
	 */
	public void setOidFormaDesbloqueo(String oidFormaDesbloqueo) {
		this.oidFormaDesbloqueo = oidFormaDesbloqueo;
	}
	/**
	 * @return the nivelGravedad
	 */
	public String getNivelGravedad() {
		return nivelGravedad;
	}
	/**
	 * @param nivelGravedad the nivelGravedad to set
	 */
	public void setNivelGravedad(String nivelGravedad) {
		this.nivelGravedad = nivelGravedad;
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
				+ ((indicadorBloqueoFinanciero == null) ? 0
						: indicadorBloqueoFinanciero.hashCode());
		result = prime * result
				+ ((indicadorEstado == null) ? 0 : indicadorEstado.hashCode());
		result = prime * result
				+ ((nivelGravedad == null) ? 0 : nivelGravedad.hashCode());
		result = prime * result
				+ ((oidFormaBloqueo == null) ? 0 : oidFormaBloqueo.hashCode());
		result = prime
				* result
				+ ((oidFormaDesbloqueo == null) ? 0 : oidFormaDesbloqueo
						.hashCode());
		result = prime
				* result
				+ ((oidMotivoRechazo == null) ? 0 : oidMotivoRechazo.hashCode());
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
		TipoBloqueo other = (TipoBloqueo) obj;
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
		if (indicadorBloqueoFinanciero == null) {
			if (other.indicadorBloqueoFinanciero != null)
				return false;
		} else if (!indicadorBloqueoFinanciero
				.equals(other.indicadorBloqueoFinanciero))
			return false;
		if (indicadorEstado == null) {
			if (other.indicadorEstado != null)
				return false;
		} else if (!indicadorEstado.equals(other.indicadorEstado))
			return false;
		if (nivelGravedad == null) {
			if (other.nivelGravedad != null)
				return false;
		} else if (!nivelGravedad.equals(other.nivelGravedad))
			return false;
		if (oidFormaBloqueo == null) {
			if (other.oidFormaBloqueo != null)
				return false;
		} else if (!oidFormaBloqueo.equals(other.oidFormaBloqueo))
			return false;
		if (oidFormaDesbloqueo == null) {
			if (other.oidFormaDesbloqueo != null)
				return false;
		} else if (!oidFormaDesbloqueo.equals(other.oidFormaDesbloqueo))
			return false;
		if (oidMotivoRechazo == null) {
			if (other.oidMotivoRechazo != null)
				return false;
		} else if (!oidMotivoRechazo.equals(other.oidMotivoRechazo))
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
				+ ", indicadorBloqueoFinanciero=" + indicadorBloqueoFinanciero
				+ ", oidMotivoRechazo=" + oidMotivoRechazo
				+ ", oidFormaBloqueo=" + oidFormaBloqueo
				+ ", oidFormaDesbloqueo=" + oidFormaDesbloqueo
				+ ", nivelGravedad=" + nivelGravedad + ", indicadorEstado="
				+ indicadorEstado + "]";
	}	
	
}
