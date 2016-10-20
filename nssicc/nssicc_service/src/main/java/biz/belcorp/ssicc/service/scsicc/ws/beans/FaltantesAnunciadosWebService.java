/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.service.scsicc.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="FaltantesAnunciadosWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class FaltantesAnunciadosWebService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoVenta;
	private String descripcionProducto;
	private String limiteControlVenta;
	/**
	 * @return the codigoVenta
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}
	/**
	 * @param codigoVenta the codigoVenta to set
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	/**
	 * @return the descripcionProducto
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	/**
	 * @param descripcionProducto the descripcionProducto to set
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	/**
	 * @return the limiteControlVenta
	 */
	public String getLimiteControlVenta() {
		return limiteControlVenta;
	}
	/**
	 * @param limiteControlVenta the limiteControlVenta to set
	 */
	public void setLimiteControlVenta(String limiteControlVenta) {
		this.limiteControlVenta = limiteControlVenta;
	}

	
}
