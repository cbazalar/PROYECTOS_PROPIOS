package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ConcursoPeriodoDespacho extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String numeroConcurso;
	private String codigoPeriodo;
	private String codigoPeriodoFin;
	
	public ConcursoPeriodoDespacho() {
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
		return "ConcursoPeriodoDespacho [codigoPais=" + codigoPais
				+ ", numeroConcurso=" + numeroConcurso + ", codigoPeriodo="
				+ codigoPeriodo + ", codigoPeriodoFin=" + codigoPeriodoFin
				+ "]";
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
	 * @return the codigoPeriodoFin
	 */
	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}

	/**
	 * @param codigoPeriodoFin the codigoPeriodoFin to set
	 */
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}
}
