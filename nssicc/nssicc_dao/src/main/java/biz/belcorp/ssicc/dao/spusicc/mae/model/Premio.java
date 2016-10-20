package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextdoliva
 *
 */
public class Premio extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oidPremio;	
	private String tipoPremio;
	private String nivel;
	private String descripcion;	
	private Integer numeroPremio;
	
	private Long oidConcurso;
	
	public Premio() {
		
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
	 * @return
	 */
	public Long getOidPremio() {
		return oidPremio;
	}

	/**
	 * @param oidPremio
	 */
	public void setOidPremio(Long oidPremio) {
		this.oidPremio = oidPremio;
	}

	/**
	 * @return
	 */
	public String getTipoPremio() {
		return tipoPremio;
	}

	/**
	 * @param tipoPremio
	 */
	public void setTipoPremio(String tipoPremio) {
		this.tipoPremio = tipoPremio;
	}

	/**
	 * @return
	 */
	public String getNivel() {
		return nivel;
	}

	/**
	 * @param nivel
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	/**
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return Returns the numeroPremio.
	 */
	public Integer getNumeroPremio() {
		return numeroPremio;
	}

	/**
	 * @param numeroPremio The numeroPremio to set.
	 */
	public void setNumeroPremio(Integer numeroPremio) {
		this.numeroPremio = numeroPremio;
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
	
}
