package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class DatosComisionEscalonada extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	//Datos Comisin Escalonada
	//Primera Lista
	private int oidComisionEscalonada;
	private Integer nroDiasRecuperacion;
	private Integer nroDiasComision;
	private double valPorcRecuperacionInicial;
	private double valPorcRecuperacionFinal;
	private double bono;
	private Integer nroTramo;
	private double valPorcComisionAdicional;
	private int codigoCobranza;
	//private int contador;
	private Integer nroEscala1;
	private Integer nroEscala2;
	private Integer nroEscala3;
	
	private int contador1;
	private int contador2;
	private int contador3;
	
	//Segunda Lista
	private double valPorcInicial;
	private double valPorcFinal;
	
	//Tercera Lista
	private double valPorcInicialVenta;
	private double valPorcFinalVenta;
	private double valPorcInicialRecuperacion;
	private double valPorcFinalRecuperacion;
	
	//Valor reutilizado para los 3 casos
	private double valPorcComision;
	
	/**
	 * @return the nroTramo
	 */
	public Integer getNroTramo() {
		return nroTramo;
	}

	/**
	 * @param nroTramo the nroTramo to set
	 */
	public void setNroTramo(Integer nroTramo) {
		this.nroTramo = nroTramo;
	}

	/**
	 * @return the nroDiasRecuperacion
	 */
	public Integer getNroDiasRecuperacion() {
		return nroDiasRecuperacion;
	}

	/**
	 * @param nroDiasRecuperacion the nroDiasRecuperacion to set
	 */
	public void setNroDiasRecuperacion(Integer nroDiasRecuperacion) {
		this.nroDiasRecuperacion = nroDiasRecuperacion;
	}

	/**
	 * @return the nroDiasComision
	 */
	public Integer getNroDiasComision() {
		return nroDiasComision;
	}

	/**
	 * @param nroDiasComision the nroDiasComision to set
	 */
	public void setNroDiasComision(Integer nroDiasComision) {
		this.nroDiasComision = nroDiasComision;
	}

	/**
	 * @return the valPorcRecuperacionInicial
	 */
	public double getValPorcRecuperacionInicial() {
		return valPorcRecuperacionInicial;
	}

	/**
	 * @param valPorcRecuperacionInicial the valPorcRecuperacionInicial to set
	 */
	public void setValPorcRecuperacionInicial(double valPorcRecuperacionInicial) {
		this.valPorcRecuperacionInicial = valPorcRecuperacionInicial;
	}

	/**
	 * @return the valPorcRecuperacionFinal
	 */
	public double getValPorcRecuperacionFinal() {
		return valPorcRecuperacionFinal;
	}

	/**
	 * @param valPorcRecuperacionFinal the valPorcRecuperacionFinal to set
	 */
	public void setValPorcRecuperacionFinal(double valPorcRecuperacionFinal) {
		this.valPorcRecuperacionFinal = valPorcRecuperacionFinal;
	}

	/**
	 * @return the valPorcComision
	 */
	public double getValPorcComision() {
		return valPorcComision;
	}

	/**
	 * @param valPorcComision the valPorcComision to set
	 */
	public void setValPorcComision(double valPorcComision) {
		this.valPorcComision = valPorcComision;
	}

	/**
	 * @return the valPorcComisionAdicional
	 */
	public double getValPorcComisionAdicional() {
		return valPorcComisionAdicional;
	}

	/**
	 * @param valPorcComisionAdicional the valPorcComisionAdicional to set
	 */
	public void setValPorcComisionAdicional(double valPorcComisionAdicional) {
		this.valPorcComisionAdicional = valPorcComisionAdicional;
	}

	/**
	 * @return the bono
	 */
	public double getBono() {
		return bono;
	}

	/**
	 * @param bono the bono to set
	 */
	public void setBono(double bono) {
		this.bono = bono;
	}

	/**
	 * @return the codigoCobranza
	 */
	public int getCodigoCobranza() {
		return codigoCobranza;
	}

	/**
	 * @param codigoCobranza the codigoCobranza to set
	 */
	public void setCodigoCobranza(int codigoCobranza) {
		this.codigoCobranza = codigoCobranza;
	}

	/**
	 * @return the oidComisionEscalonada
	 */
	public int getOidComisionEscalonada() {
		return oidComisionEscalonada;
	}

	/**
	 * @param oidComisionEscalonada the oidComisionEscalonada to set
	 */
	public void setOidComisionEscalonada(int oidComisionEscalonada) {
		this.oidComisionEscalonada = oidComisionEscalonada;
	}

	/**
	 * @return the nroEscala1
	 */
	public Integer getNroEscala1() {
		return nroEscala1;
	}

	/**
	 * @param nroEscala1 the nroEscala1 to set
	 */
	public void setNroEscala1(Integer nroEscala1) {
		this.nroEscala1 = nroEscala1;
	}

	/**
	 * @return the nroEscala2
	 */
	public Integer getNroEscala2() {
		return nroEscala2;
	}

	/**
	 * @param nroEscala2 the nroEscala2 to set
	 */
	public void setNroEscala2(Integer nroEscala2) {
		this.nroEscala2 = nroEscala2;
	}

	/**
	 * @return the nroEscala3
	 */
	public Integer getNroEscala3() {
		return nroEscala3;
	}

	/**
	 * @param nroEscala3 the nroEscala3 to set
	 */
	public void setNroEscala3(Integer nroEscala3) {
		this.nroEscala3 = nroEscala3;
	}

	/**
	 * @return the valPorcInicial
	 */
	public double getValPorcInicial() {
		return valPorcInicial;
	}

	/**
	 * @param valPorcInicial the valPorcInicial to set
	 */
	public void setValPorcInicial(double valPorcInicial) {
		this.valPorcInicial = valPorcInicial;
	}

	/**
	 * @return the valPorcFinal
	 */
	public double getValPorcFinal() {
		return valPorcFinal;
	}

	/**
	 * @param valPorcFinal the valPorcFinal to set
	 */
	public void setValPorcFinal(double valPorcFinal) {
		this.valPorcFinal = valPorcFinal;
	}

	/**
	 * @return the valPorcInicialVenta
	 */
	public double getValPorcInicialVenta() {
		return valPorcInicialVenta;
	}

	/**
	 * @param valPorcInicialVenta the valPorcInicialVenta to set
	 */
	public void setValPorcInicialVenta(double valPorcInicialVenta) {
		this.valPorcInicialVenta = valPorcInicialVenta;
	}

	/**
	 * @return the valPorcFinalVenta
	 */
	public double getValPorcFinalVenta() {
		return valPorcFinalVenta;
	}

	/**
	 * @param valPorcFinalVenta the valPorcFinalVenta to set
	 */
	public void setValPorcFinalVenta(double valPorcFinalVenta) {
		this.valPorcFinalVenta = valPorcFinalVenta;
	}

	/**
	 * @return the valPorcInicialRecuperacion
	 */
	public double getValPorcInicialRecuperacion() {
		return valPorcInicialRecuperacion;
	}

	/**
	 * @param valPorcInicialRecuperacion the valPorcInicialRecuperacion to set
	 */
	public void setValPorcInicialRecuperacion(double valPorcInicialRecuperacion) {
		this.valPorcInicialRecuperacion = valPorcInicialRecuperacion;
	}

	/**
	 * @return the valPorcFinalRecuperacion
	 */
	public double getValPorcFinalRecuperacion() {
		return valPorcFinalRecuperacion;
	}

	/**
	 * @param valPorcFinalRecuperacion the valPorcFinalRecuperacion to set
	 */
	public void setValPorcFinalRecuperacion(double valPorcFinalRecuperacion) {
		this.valPorcFinalRecuperacion = valPorcFinalRecuperacion;
	}

	/**
	 * @return the contador1
	 */
	public int getContador1() {
		return contador1;
	}

	/**
	 * @param contador1 the contador1 to set
	 */
	public void setContador1(int contador1) {
		this.contador1 = contador1;
	}

	/**
	 * @return the contador2
	 */
	public int getContador2() {
		return contador2;
	}

	/**
	 * @param contador2 the contador2 to set
	 */
	public void setContador2(int contador2) {
		this.contador2 = contador2;
	}

	/**
	 * @return the contador3
	 */
	public int getContador3() {
		return contador3;
	}

	/**
	 * @param contador3 the contador3 to set
	 */
	public void setContador3(int contador3) {
		this.contador3 = contador3;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
