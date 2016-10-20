package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ConcursoAmbitoGeografico extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private Long oidSeccion;
	private Long oidRegion;
	private Long oidSubgerencia;
	private Long oidTerritorio;
	private Long oidZona;
	private Long oidConcurso;
	
	private String descripcionSubGerencia;
	private String descripcionRegion;
	private String descripcionZona;
	

	public ConcursoAmbitoGeografico() {
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
		return "ConcursoAmbitoGeografico [oid=" + oid + ", oidSeccion="
				+ oidSeccion + ", oidRegion=" + oidRegion + ", oidSubgerencia="
				+ oidSubgerencia + ", oidTerritorio=" + oidTerritorio
				+ ", oidZona=" + oidZona + ", oidConcurso=" + oidConcurso
				+ ", descripcionSubGerencia=" + descripcionSubGerencia
				+ ", descripcionRegion=" + descripcionRegion
				+ ", descripcionZona=" + descripcionZona + "]";
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
	 * @return the oidSeccion
	 */
	public Long getOidSeccion() {
		return oidSeccion;
	}

	/**
	 * @param oidSeccion the oidSeccion to set
	 */
	public void setOidSeccion(Long oidSeccion) {
		this.oidSeccion = oidSeccion;
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
	 * @return the oidSubgerencia
	 */
	public Long getOidSubgerencia() {
		return oidSubgerencia;
	}

	/**
	 * @param oidSubgerencia the oidSubgerencia to set
	 */
	public void setOidSubgerencia(Long oidSubgerencia) {
		this.oidSubgerencia = oidSubgerencia;
	}

	/**
	 * @return the oidTerritorio
	 */
	public Long getOidTerritorio() {
		return oidTerritorio;
	}

	/**
	 * @param oidTerritorio the oidTerritorio to set
	 */
	public void setOidTerritorio(Long oidTerritorio) {
		this.oidTerritorio = oidTerritorio;
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
	 * @return the oidConcurso
	 */
	public Long getOidConcurso() {
		return oidConcurso;
	}

	/**
	 * @param oidConcurso the oidConcurso to set
	 */
	public void setOidConcurso(Long oidConcurso) {
		this.oidConcurso = oidConcurso;
	}

	/**
	 * @return the descripcionSubGerencia
	 */
	public String getDescripcionSubGerencia() {
		return descripcionSubGerencia;
	}

	/**
	 * @param descripcionSubGerencia the descripcionSubGerencia to set
	 */
	public void setDescripcionSubGerencia(String descripcionSubGerencia) {
		this.descripcionSubGerencia = descripcionSubGerencia;
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
}
