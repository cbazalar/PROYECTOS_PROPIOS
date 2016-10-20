package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ConcursoBonificacionPeriodo extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String numeroConcurso;
	private String codigoPeriodo;
	private Integer puntos;
	
	public ConcursoBonificacionPeriodo() {
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
		return "ConcursoBonificacionPeriodo [codigoPais=" + codigoPais
				+ ", numeroConcurso=" + numeroConcurso + ", codigoPeriodo="
				+ codigoPeriodo + ", puntos=" + puntos + "]";
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
	 * @return the puntos
	 */
	public Integer getPuntos() {
		return puntos;
	}

	/**
	 * @param puntos the puntos to set
	 */
	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}
	
}
