/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="LocalDetalRETWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 * 
 * 
 */
public class LocalDetalRETWebService implements Serializable {

	private static final long serialVersionUID = 1L;
	private String campanha;
	private String fecha;
	private String codigoLocal;
	private String totalTrans;
	
	/**
	 * @return the campanha
	 */
	public String getCampanha() {
		return campanha;
	}
	/**
	 * @param campanha the campanha to set
	 */
	public void setCampanha(String campanha) {
		this.campanha = campanha;
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
	 * @return the codigoLocal
	 */
	public String getCodigoLocal() {
		return codigoLocal;
	}
	/**
	 * @param codigoLocal the codigoLocal to set
	 */
	public void setCodigoLocal(String codigoLocal) {
		this.codigoLocal = codigoLocal;
	}
	/**
	 * @return the totalTrans
	 */
	public String getTotalTrans() {
		return totalTrans;
	}
	/**
	 * @param totalTrans the totalTrans to set
	 */
	public void setTotalTrans(String totalTrans) {
		this.totalTrans = totalTrans;
	}
}