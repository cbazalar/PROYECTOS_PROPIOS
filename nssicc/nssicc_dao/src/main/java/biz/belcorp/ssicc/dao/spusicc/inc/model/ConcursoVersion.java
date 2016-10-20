package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ConcursoVersion extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private Integer versionNueva;
	private String numeroConcurso;
	private Long oidPeriodo;
	private Long oidConcurso;
	private Long oidConcursoOrigen;
	private Integer oidEstadoConcurso;
	private Integer oidVigenciaConcurso;
	private String codigoUsuario;
	
	private String codigoPeriodo;
	private String vigenciaConcurso;
	
	public ConcursoVersion() {
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
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
	 * @return the versionNueva
	 */
	public Integer getVersionNueva() {
		return versionNueva;
	}

	/**
	 * @param versionNueva the versionNueva to set
	 */
	public void setVersionNueva(Integer versionNueva) {
		this.versionNueva = versionNueva;
	}

	/**
	 * @return the numeroConcurso
	 */
	public String getNumeroConcurso() {
		return numeroConcurso;
	}

	/**
	 * @param numeroConcurso the numeroConcurso to set
	 */
	public void setNumeroConcurso(String numeroConcurso) {
		this.numeroConcurso = numeroConcurso;
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
	 * @return the oidConcursoOrigen
	 */
	public Long getOidConcursoOrigen() {
		return oidConcursoOrigen;
	}

	/**
	 * @param oidConcursoOrigen the oidConcursoOrigen to set
	 */
	public void setOidConcursoOrigen(Long oidConcursoOrigen) {
		this.oidConcursoOrigen = oidConcursoOrigen;
	}

	/**
	 * @return the oidEstadoConcurso
	 */
	public Integer getOidEstadoConcurso() {
		return oidEstadoConcurso;
	}

	/**
	 * @param oidEstadoConcurso the oidEstadoConcurso to set
	 */
	public void setOidEstadoConcurso(Integer oidEstadoConcurso) {
		this.oidEstadoConcurso = oidEstadoConcurso;
	}

	/**
	 * @return the oidVigenciaConcurso
	 */
	public Integer getOidVigenciaConcurso() {
		return oidVigenciaConcurso;
	}

	/**
	 * @param oidVigenciaConcurso the oidVigenciaConcurso to set
	 */
	public void setOidVigenciaConcurso(Integer oidVigenciaConcurso) {
		this.oidVigenciaConcurso = oidVigenciaConcurso;
	}

	/**
	 * @return the vigenciaConcurso
	 */
	public String getVigenciaConcurso() {
		return vigenciaConcurso;
	}

	/**
	 * @param vigenciaConcurso the vigenciaConcurso to set
	 */
	public void setVigenciaConcurso(String vigenciaConcurso) {
		this.vigenciaConcurso = vigenciaConcurso;
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
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
}
