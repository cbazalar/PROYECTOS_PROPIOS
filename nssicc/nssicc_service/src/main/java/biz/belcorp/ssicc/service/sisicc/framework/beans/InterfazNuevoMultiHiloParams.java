package biz.belcorp.ssicc.service.sisicc.framework.beans;

import java.util.ArrayList;
import java.util.List;

import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;

/* NUEVA REESTRUCTURACION NSSICC */
/**
 * Clase que encapsula los parametros para la ejecucion de las Interfaces SSiCC en formato MultiHilo.
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 */
public class InterfazNuevoMultiHiloParams implements Cloneable {
	
	Long nroNivel;
	Long nroHilo;
	List<Interfaz> listaInterfacesEmpaquetadas;
	
	/**
	 * Constructor Base 
	 */
	public InterfazNuevoMultiHiloParams() {
		this.nroNivel = new Long(0);
		this.nroHilo = new Long(0);
		this.listaInterfacesEmpaquetadas = new ArrayList<Interfaz>();
	}
	
	
	/**
	 * @param nroNivel
	 * @param nroHilo
	 * @param listaInterfacesEmpaquetadas
	 */
	public InterfazNuevoMultiHiloParams(Long nroNivel, Long nroHilo, List<Interfaz> listaInterfacesEmpaquetadas) {
		this.nroNivel = nroNivel;
		this.nroHilo = nroHilo;
		this.listaInterfacesEmpaquetadas = listaInterfacesEmpaquetadas;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Object clone() throws CloneNotSupportedException{
		InterfazNuevoMultiHiloParams obj = null;
	    obj = (InterfazNuevoMultiHiloParams)super.clone();
	    return obj;
    }


	/**
	 * @return the nroNivel
	 */
	public Long getNroNivel() {
		return nroNivel;
	}


	/**
	 * @param nroNivel the nroNivel to set
	 */
	public void setNroNivel(Long nroNivel) {
		this.nroNivel = nroNivel;
	}


	/**
	 * @return the nroHilo
	 */
	public Long getNroHilo() {
		return nroHilo;
	}


	/**
	 * @param nroHilo the nroHilo to set
	 */
	public void setNroHilo(Long nroHilo) {
		this.nroHilo = nroHilo;
	}


	/**
	 * @return the listaInterfacesEmpaquetadas
	 */
	public List<Interfaz> getListaInterfacesEmpaquetadas() {
		return listaInterfacesEmpaquetadas;
	}


	/**
	 * @param listaInterfacesEmpaquetadas the listaInterfacesEmpaquetadas to set
	 */
	public void setListaInterfacesEmpaquetadas(
			List<Interfaz> listaInterfacesEmpaquetadas) {
		this.listaInterfacesEmpaquetadas = listaInterfacesEmpaquetadas;
	}


	
	
		
}
