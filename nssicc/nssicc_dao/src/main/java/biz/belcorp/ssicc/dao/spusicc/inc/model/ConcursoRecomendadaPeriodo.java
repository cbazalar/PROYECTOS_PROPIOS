package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ConcursoRecomendadaPeriodo extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String numeroConcurso;
	private String indicadorTipo;
	private String secuencia;
	private java.math.BigDecimal monto;
	
	private String descripcionIndicadorTipo;

	public ConcursoRecomendadaPeriodo() {
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
		return "ConcursoRecomendadaPeriodo [codigoPais=" + codigoPais
				+ ", numeroConcurso=" + numeroConcurso + ", indicadorTipo="
				+ indicadorTipo + ", secuencia=" + secuencia + ", monto="
				+ monto + ", descripcionIndicadorTipo="
				+ descripcionIndicadorTipo + "]";
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
	 * @return the indicadorTipo
	 */
	public String getIndicadorTipo() {
		return indicadorTipo;
	}

	/**
	 * @param indicadorTipo the indicadorTipo to set
	 */
	public void setIndicadorTipo(String indicadorTipo) {
		this.indicadorTipo = indicadorTipo;
	}

	/**
	 * @return the secuencia
	 */
	public String getSecuencia() {
		return secuencia;
	}

	/**
	 * @param secuencia the secuencia to set
	 */
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	/**
	 * @return the monto
	 */
	public java.math.BigDecimal getMonto() {
		return monto;
	}

	/**
	 * @param monto the monto to set
	 */
	public void setMonto(java.math.BigDecimal monto) {
		this.monto = monto;
	}

	/**
	 * @return the descripcionIndicadorTipo
	 */
	public String getDescripcionIndicadorTipo() {
		return descripcionIndicadorTipo;
	}

	/**
	 * @param descripcionIndicadorTipo the descripcionIndicadorTipo to set
	 */
	public void setDescripcionIndicadorTipo(String descripcionIndicadorTipo) {
		this.descripcionIndicadorTipo = descripcionIndicadorTipo;
	}
	
}
