package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class DatosComisionCalculo extends AuditableBaseObject implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Datos  Comisin
	private int contador;
	private Integer nroTramo;
	private Integer nroDiasComision;
	private double valPorcRecuperacion;
	private double valPorcComision;
	private double valPorcActividad;
	private double valPorcComisionActividad;
	private int oidCobranza;
	
	/**
	 * @return the contador
	 */
	public int getContador() {
		return contador;
	}

	/**
	 * @param contador the contador to set
	 */
	public void setContador(int contador) {
		this.contador = contador;
	}
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
	 * @return the valPorcRecuperacion
	 */
	public double getValPorcRecuperacion() {
		return valPorcRecuperacion;
	}

	/**
	 * @param valPorcRecuperacion the valPorcRecuperacion to set
	 */
	public void setValPorcRecuperacion(double valPorcRecuperacion) {
		this.valPorcRecuperacion = valPorcRecuperacion;
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
	 * @return the oidCobranza
	 */
	public int getOidCobranza() {
		return oidCobranza;
	}

	/**
	 * @param oidCobranza the oidCobranza to set
	 */
	public void setOidCobranza(int oidCobranza) {
		this.oidCobranza = oidCobranza;
	}

	public double getValPorcActividad() {
		return valPorcActividad;
	}

	public void setValPorcActividad(double valPorcActividad) {
		this.valPorcActividad = valPorcActividad;
	}

	public double getValPorcComisionActividad() {
		return valPorcComisionActividad;
	}

	public void setValPorcComisionActividad(double valPorcComisionActividad) {
		this.valPorcComisionActividad = valPorcComisionActividad;
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
