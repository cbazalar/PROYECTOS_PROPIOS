package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class ZonaDemandaAnticipada extends AuditableBaseObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoZona;
	private int numDiasTra1;
	private int numDiasTra2;
	private int numDiasCome;
	private int numDiasTra1Gzon;
	private int numDiasTra2Gzon;
	private int numDiasTra1Greg;
	private int numDiasTra2Greg;
	private int estRegi;
	
	
	public ZonaDemandaAnticipada() {
		// TODO Auto-generated constructor stub
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
	 * @return the numDiasTra1
	 */
	public int getNumDiasTra1() {
		return numDiasTra1;
	}

	/**
	 * @param numDiasTra1 the numDiasTra1 to set
	 */
	public void setNumDiasTra1(int numDiasTra1) {
		this.numDiasTra1 = numDiasTra1;
	}

	/**
	 * @return the numDiasTra2
	 */
	public int getNumDiasTra2() {
		return numDiasTra2;
	}

	/**
	 * @param numDiasTra2 the numDiasTra2 to set
	 */
	public void setNumDiasTra2(int numDiasTra2) {
		this.numDiasTra2 = numDiasTra2;
	}

	/**
	 * @return the numDiasCome
	 */
	public int getNumDiasCome() {
		return numDiasCome;
	}

	/**
	 * @param numDiasCome the numDiasCome to set
	 */
	public void setNumDiasCome(int numDiasCome) {
		this.numDiasCome = numDiasCome;
	}

	/**
	 * @return the numDiasTra1Gzon
	 */
	public int getNumDiasTra1Gzon() {
		return numDiasTra1Gzon;
	}

	/**
	 * @param numDiasTra1Gzon the numDiasTra1Gzon to set
	 */
	public void setNumDiasTra1Gzon(int numDiasTra1Gzon) {
		this.numDiasTra1Gzon = numDiasTra1Gzon;
	}

	/**
	 * @return the numDiasTra2Gzon
	 */
	public int getNumDiasTra2Gzon() {
		return numDiasTra2Gzon;
	}

	/**
	 * @param numDiasTra2Gzon the numDiasTra2Gzon to set
	 */
	public void setNumDiasTra2Gzon(int numDiasTra2Gzon) {
		this.numDiasTra2Gzon = numDiasTra2Gzon;
	}

	/**
	 * @return the numDiasTra1Greg
	 */
	public int getNumDiasTra1Greg() {
		return numDiasTra1Greg;
	}

	/**
	 * @param numDiasTra1Greg the numDiasTra1Greg to set
	 */
	public void setNumDiasTra1Greg(int numDiasTra1Greg) {
		this.numDiasTra1Greg = numDiasTra1Greg;
	}

	/**
	 * @return the numDiasTra2Greg
	 */
	public int getNumDiasTra2Greg() {
		return numDiasTra2Greg;
	}

	/**
	 * @param numDiasTra2Greg the numDiasTra2Greg to set
	 */
	public void setNumDiasTra2Greg(int numDiasTra2Greg) {
		this.numDiasTra2Greg = numDiasTra2Greg;
	}

	/**
	 * @return the estRegi
	 */
	public int getEstRegi() {
		return estRegi;
	}

	/**
	 * @param estRegi the estRegi to set
	 */
	public void setEstRegi(int estRegi) {
		this.estRegi = estRegi;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
