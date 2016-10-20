/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsolidadoRetailRETWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class ConsolidadoRetailRETWebService implements Serializable {

	   private static final long serialVersionUID = 1L;
	   private String codigoPais;
	   private String fecha;//fecha de venta
	   private String tipoDocu;
	   private String monCata;
	   private String monDesc;
	   private String monDevu;
	   private String monImpu;
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
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the tipoDocu
	 */
	public String getTipoDocu() {
		return tipoDocu;
	}
	/**
	 * @param tipoDocu the tipoDocu to set
	 */
	public void setTipoDocu(String tipoDocu) {
		this.tipoDocu = tipoDocu;
	}
	/**
	 * @return the monCata
	 */
	public String getMonCata() {
		return monCata;
	}
	/**
	 * @param monCata the monCata to set
	 */
	public void setMonCata(String monCata) {
		this.monCata = monCata;
	}
	/**
	 * @return the monDesc
	 */
	public String getMonDesc() {
		return monDesc;
	}
	/**
	 * @param monDesc the monDesc to set
	 */
	public void setMonDesc(String monDesc) {
		this.monDesc = monDesc;
	}
	/**
	 * @return the monDevu
	 */
	public String getMonDevu() {
		return monDevu;
	}
	/**
	 * @param monDevu the monDevu to set
	 */
	public void setMonDevu(String monDevu) {
		this.monDevu = monDevu;
	}
	/**
	 * @return the monImpu
	 */
	public String getMonImpu() {
		return monImpu;
	}
	/**
	 * @param monImpu the monImpu to set
	 */
	public void setMonImpu(String monImpu) {
		this.monImpu = monImpu;
	}
	   
	   	   
}
