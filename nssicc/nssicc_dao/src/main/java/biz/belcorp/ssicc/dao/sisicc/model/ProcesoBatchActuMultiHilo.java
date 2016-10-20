/*
 * Created on 19-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 *  
 * <p>
 * <a href="Interfaz.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 */
public class ProcesoBatchActuMultiHilo extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = -223598286351432341L;
	private ProcesoBatchActu procesoBatchActu;
	private List<NroLoteMultiHilo> listaNroLoteMultiHilo;
	 

	public ProcesoBatchActuMultiHilo() {
		procesoBatchActu = new ProcesoBatchActu();
		listaNroLoteMultiHilo = new ArrayList<NroLoteMultiHilo>();
	}

	
	
	/**
	 * @return the procesoBatchActu
	 */
	public ProcesoBatchActu getProcesoBatchActu() {
		return procesoBatchActu;
	}



	/**
	 * @param procesoBatchActu the procesoBatchActu to set
	 */
	public void setProcesoBatchActu(ProcesoBatchActu procesoBatchActu) {
		this.procesoBatchActu = procesoBatchActu;
	}



	/**
	 * @return the listaNroLoteMultiHilo
	 */
	public List<NroLoteMultiHilo> getListaNroLoteMultiHilo() {
		return listaNroLoteMultiHilo;
	}



	/**
	 * @param listaNroLoteMultiHilo the listaNroLoteMultiHilo to set
	 */
	public void setListaNroLoteMultiHilo(
			List<NroLoteMultiHilo> listaNroLoteMultiHilo) {
		this.listaNroLoteMultiHilo = listaNroLoteMultiHilo;
	}



	/*
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
