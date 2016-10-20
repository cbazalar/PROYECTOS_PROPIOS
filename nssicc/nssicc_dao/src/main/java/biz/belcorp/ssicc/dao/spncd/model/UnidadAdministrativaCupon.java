package biz.belcorp.ssicc.dao.spncd.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextjrodriguez
 *
 */
public class UnidadAdministrativaCupon extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private String codigoPais;
	private String codigoPrograma;
	private Long oidPeriodo;
	private String codigoPeriodo;
	private Long oidRegion;
	private String codigoRegion;
	private Long oidZona;
	private String codigoZona;
	private String usuario;
	private String descripcionPeriodo;
	private String descripcionRegion;
	private String descripcionZona;

	public UnidadAdministrativaCupon() {
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UnidadAdministrativaCupon [oid=" + oid + ", codigoPais="
				+ codigoPais + ", codigoPrograma=" + codigoPrograma
				+ ", oidPeriodo=" + oidPeriodo + ", codigoPeriodo="
				+ codigoPeriodo + ", oidRegion=" + oidRegion
				+ ", codigoRegion=" + codigoRegion + ", oidZona=" + oidZona
				+ ", codigoZona=" + codigoZona + ", usuario=" + usuario
				+ ", descripcionPeriodo=" + descripcionPeriodo
				+ ", descripcionRegion=" + descripcionRegion
				+ ", descripcionZona=" + descripcionZona + "]";
	}

	/**
	 * @return the oidRegion
	 */
	public Long getOidRegion() {
		return oidRegion;
	}

	/**
	 * @param oidRegion the oidRegion to set
	 */
	public void setOidRegion(Long oidRegion) {
		this.oidRegion = oidRegion;
	}

	/**
	 * @return the oidZona
	 */
	public Long getOidZona() {
		return oidZona;
	}

	/**
	 * @param oidZona the oidZona to set
	 */
	public void setOidZona(Long oidZona) {
		this.oidZona = oidZona;
	}

	/**
	 * @return the descripcionRegion
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}

	/**
	 * @param descripcionRegion the descripcionRegion to set
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}

	/**
	 * @return the descripcionZona
	 */
	public String getDescripcionZona() {
		return descripcionZona;
	}

	/**
	 * @param descripcionZona the descripcionZona to set
	 */
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}

	/**
	 * @return the descripcionPeriodo
	 */
	public String getDescripcionPeriodo() {
		return descripcionPeriodo;
	}

	/**
	 * @param descripcionPeriodo the descripcionPeriodo to set
	 */
	public void setDescripcionPeriodo(String descripcionPeriodo) {
		this.descripcionPeriodo = descripcionPeriodo;
	}

	/**
	 * @return the oid
	 */
	public Long getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(Long oid) {
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
	 * @return the oidPeriodo
	 */
	public Long getOidPeriodo() {
		return oidPeriodo;
	}

	/**
	 * @param oidPeriodo the oidPeriodo to set
	 */
	public void setOidPeriodo(Long oidPeriodo) {
		this.oidPeriodo = oidPeriodo;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
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
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}