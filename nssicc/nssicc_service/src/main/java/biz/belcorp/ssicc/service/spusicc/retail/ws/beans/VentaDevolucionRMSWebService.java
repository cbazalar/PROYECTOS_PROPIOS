/*
 * Created on 30/12/2015 09:42:35 AM
 * biz.belcorp.ssicc.service.spusicc.retail.ws.beans.VentaDevolucionRMSWebService
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="VentaDevolucionRMSWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Richar Cruzado
 * 
 * 
 */
public class VentaDevolucionRMSWebService implements Serializable {
 
	private static final long serialVersionUID = 1L;
	private String status;
	private String mensaje;
	
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}