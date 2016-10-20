/*
 * Created on 19-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.scsicc.model;

import java.io.Serializable;

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
public class GraficoPie extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2164717401467860767L;

	private String leyenda;
	
	private Double serie;


	public GraficoPie() {
		leyenda = "";
	}

	
	/**
	 * @return Returns the leyenda.
	 */
	public String getLeyenda() {
		return leyenda;
	}




	/**
	 * @param leyenda The leyenda to set.
	 */
	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}




	/**
	 * @return Returns the serie.
	 */
	public Double getSerie() {
		return serie;
	}




	/**
	 * @param serie The serie to set.
	 */
	public void setSerie(Double serie) {
		this.serie = serie;
	}




	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


	

}
