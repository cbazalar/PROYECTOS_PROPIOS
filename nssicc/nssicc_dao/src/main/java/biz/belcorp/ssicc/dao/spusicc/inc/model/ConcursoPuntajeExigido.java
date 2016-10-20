package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author cbazalar
 *
 */
public class ConcursoPuntajeExigido extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String numeroConcurso;
	private String nivel;
	private String codigoPeriodo;
	private String puntajeExigido;
	
	public ConcursoPuntajeExigido() {
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
		return "ConcursoPuntajeExigido [codigoPais=" + codigoPais
				+ ", numeroConcurso=" + numeroConcurso + ", nivel=" + nivel
				+ ", codigoPeriodo=" + codigoPeriodo + ", puntajeExigido="
				+ puntajeExigido + "]";
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
	 * @return the nivel
	 */
	public String getNivel() {
		return nivel;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
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
	 * @return the puntajeExigido
	 */
	public String getPuntajeExigido() {
		return puntajeExigido;
	}

	/**
	 * @param puntajeExigido the puntajeExigido to set
	 */
	public void setPuntajeExigido(String puntajeExigido) {
		this.puntajeExigido = puntajeExigido;
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
	
	

	
}
