package biz.belcorp.ssicc.service.sisicc.framework.beans;

import java.util.ArrayList;
import java.util.List;

import biz.belcorp.ssicc.service.sisicc.framework.BaseInterfazService;


/* POR REESTRUCTURAR NSSICC */
/**
 * Clase que encapsula los parametros para la ejecucion de las Interfaces SiCC en formato MultiHilo.
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 */
public class InterfazMultiHiloParams implements Cloneable {
	
	private List<BaseInterfazService> listaInterfazEmpaquetadaImpl = new ArrayList<BaseInterfazService>();
	private List<InterfazParams> listaInterfazParams = new ArrayList<InterfazParams>();
	private List<Integer> listaNumeroEjecucion = new ArrayList<Integer>();
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Object clone() throws CloneNotSupportedException{
		InterfazMultiHiloParams obj = null;
	    obj = (InterfazMultiHiloParams)super.clone();
	    return obj;
    }
	
	/**
	 * @return the listaInterfazEmpaquetadaImpl
	 */
	public List<BaseInterfazService> getListaInterfazEmpaquetadaImpl() {
		return listaInterfazEmpaquetadaImpl;
	}
	/**
	 * @param listaInterfazEmpaquetadaImpl the listaInterfazEmpaquetadaImpl to set
	 */
	public void setListaInterfazEmpaquetadaImpl(
			List<BaseInterfazService> listaInterfazEmpaquetadaImpl) {
		this.listaInterfazEmpaquetadaImpl = listaInterfazEmpaquetadaImpl;
	}
	/**
	 * @return the listaInterfazParams
	 */
	public List<InterfazParams> getListaInterfazParams() {
		return listaInterfazParams;
	}
	/**
	 * @param listaInterfazParams the listaInterfazParams to set
	 */
	public void setListaInterfazParams(List<InterfazParams> listaInterfazParams) {
		this.listaInterfazParams = listaInterfazParams;
	}
	/**
	 * @return the listaNumeroEjecucion
	 */
	public List<Integer> getListaNumeroEjecucion() {
		return listaNumeroEjecucion;
	}
	/**
	 * @param listaNumeroEjecucion the listaNumeroEjecucion to set
	 */
	public void setListaNumeroEjecucion(List<Integer> listaNumeroEjecucion) {
		this.listaNumeroEjecucion = listaNumeroEjecucion;
	}
	
		
}
