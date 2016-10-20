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
public class GraficoBar extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4231919539749142586L;

	private String leyendaSerie;
	
	private String leyendaCategoria;
	
	private Double serie;


	public GraficoBar() {
		leyendaSerie = "";
	}
	
	/**
	 * @return Returns the leyendaCategoria.
	 */
	public String getLeyendaCategoria() {
		return leyendaCategoria;
	}

	/**
	 * @param leyendaCategoria The leyendaCategoria to set.
	 */
	public void setLeyendaCategoria(String leyendaCategoria) {
		this.leyendaCategoria = leyendaCategoria;
	}


	/**
	 * @return Returns the leyendaSerie.
	 */
	public String getLeyendaSerie() {
		return leyendaSerie;
	}


	/**
	 * @param leyendaSerie The leyendaSerie to set.
	 */
	public void setLeyendaSerie(String leyendaSerie) {
		this.leyendaSerie = leyendaSerie;
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
