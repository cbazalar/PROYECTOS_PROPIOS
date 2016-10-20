package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ConcursoDespachoPremios extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private Integer indicadorComunicacionAutomatico;
	private Integer indicadorComunicacionManual;
	private Long oidConcurso;
	private Long oidMensajeAutomatico;
	private Long oidMensajeManual;
	
	private String codigoMensajeAutomatico;
	private String codigoMensajeManual;
	
	public ConcursoDespachoPremios() {
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
	 * @return the indicadorComunicacionAutomatico
	 */
	public Integer getIndicadorComunicacionAutomatico() {
		return indicadorComunicacionAutomatico;
	}

	/**
	 * @param indicadorComunicacionAutomatico the indicadorComunicacionAutomatico to set
	 */
	public void setIndicadorComunicacionAutomatico(
			Integer indicadorComunicacionAutomatico) {
		this.indicadorComunicacionAutomatico = indicadorComunicacionAutomatico;
	}

	/**
	 * @return the indicadorComunicacionManual
	 */
	public Integer getIndicadorComunicacionManual() {
		return indicadorComunicacionManual;
	}

	/**
	 * @param indicadorComunicacionManual the indicadorComunicacionManual to set
	 */
	public void setIndicadorComunicacionManual(Integer indicadorComunicacionManual) {
		this.indicadorComunicacionManual = indicadorComunicacionManual;
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
	 * @return the oidMensajeAutomatico
	 */
	public Long getOidMensajeAutomatico() {
		return oidMensajeAutomatico;
	}

	/**
	 * @param oidMensajeAutomatico the oidMensajeAutomatico to set
	 */
	public void setOidMensajeAutomatico(Long oidMensajeAutomatico) {
		this.oidMensajeAutomatico = oidMensajeAutomatico;
	}

	/**
	 * @return the oidMensajeManual
	 */
	public Long getOidMensajeManual() {
		return oidMensajeManual;
	}

	/**
	 * @param oidMensajeManual the oidMensajeManual to set
	 */
	public void setOidMensajeManual(Long oidMensajeManual) {
		this.oidMensajeManual = oidMensajeManual;
	}

	/**
	 * @return the codigoMensajeAutomatico
	 */
	public String getCodigoMensajeAutomatico() {
		return codigoMensajeAutomatico;
	}

	/**
	 * @param codigoMensajeAutomatico the codigoMensajeAutomatico to set
	 */
	public void setCodigoMensajeAutomatico(String codigoMensajeAutomatico) {
		this.codigoMensajeAutomatico = codigoMensajeAutomatico;
	}

	/**
	 * @return the codigoMensajeManual
	 */
	public String getCodigoMensajeManual() {
		return codigoMensajeManual;
	}

	/**
	 * @param codigoMensajeManual the codigoMensajeManual to set
	 */
	public void setCodigoMensajeManual(String codigoMensajeManual) {
		this.codigoMensajeManual = codigoMensajeManual;
	}

}
