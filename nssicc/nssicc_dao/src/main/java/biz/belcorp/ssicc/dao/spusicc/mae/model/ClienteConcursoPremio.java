package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ClienteConcursoPremio extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oidClienteRecomendante;
	private Long oidConcurso;
	private String codigoClienteRecomendante;
	
	private Long oidClienteRecomendado;
	private String codigoClienteRecomendado;
	private Long oidPeriodo;
	private Long oidNivelPremio;
	private Integer numeroPremio;
	
	private Long oidModulo;

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
	 * @return the oidClienteRecomendante
	 */
	public Long getOidClienteRecomendante() {
		return oidClienteRecomendante;
	}

	/**
	 * @param oidClienteRecomendante the oidClienteRecomendante to set
	 */
	public void setOidClienteRecomendante(Long oidClienteRecomendante) {
		this.oidClienteRecomendante = oidClienteRecomendante;
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
	 * @return the codigoClienteRecomendante
	 */
	public String getCodigoClienteRecomendante() {
		return codigoClienteRecomendante;
	}

	/**
	 * @param codigoClienteRecomendante the codigoClienteRecomendante to set
	 */
	public void setCodigoClienteRecomendante(String codigoClienteRecomendante) {
		this.codigoClienteRecomendante = codigoClienteRecomendante;
	}

	/**
	 * @return the oidClienteRecomendado
	 */
	public Long getOidClienteRecomendado() {
		return oidClienteRecomendado;
	}

	/**
	 * @param oidClienteRecomendado the oidClienteRecomendado to set
	 */
	public void setOidClienteRecomendado(Long oidClienteRecomendado) {
		this.oidClienteRecomendado = oidClienteRecomendado;
	}

	/**
	 * @return the codigoClienteRecomendado
	 */
	public String getCodigoClienteRecomendado() {
		return codigoClienteRecomendado;
	}

	/**
	 * @param codigoClienteRecomendado the codigoClienteRecomendado to set
	 */
	public void setCodigoClienteRecomendado(String codigoClienteRecomendado) {
		this.codigoClienteRecomendado = codigoClienteRecomendado;
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
	 * @return the oidNivelPremio
	 */
	public Long getOidNivelPremio() {
		return oidNivelPremio;
	}

	/**
	 * @param oidNivelPremio the oidNivelPremio to set
	 */
	public void setOidNivelPremio(Long oidNivelPremio) {
		this.oidNivelPremio = oidNivelPremio;
	}

	/**
	 * @return the numeroPremio
	 */
	public Integer getNumeroPremio() {
		return numeroPremio;
	}

	/**
	 * @param numeroPremio the numeroPremio to set
	 */
	public void setNumeroPremio(Integer numeroPremio) {
		this.numeroPremio = numeroPremio;
	}

	/**
	 * @return the oidModulo
	 */
	public Long getOidModulo() {
		return oidModulo;
	}

	/**
	 * @param oidModulo the oidModulo to set
	 */
	public void setOidModulo(Long oidModulo) {
		this.oidModulo = oidModulo;
	}

	
}
