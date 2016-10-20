/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws.beans;

import java.io.Serializable;

/**
 * @author Richar Cruzado
 * @date   28/12/2015
 */
public class PrecioProductoRMSWebServiceResultado implements Serializable {

	private static final long serialVersionUID = 1L;
		
	private PrecioProductoRMSWebService[] precioProductoRMSWebServices = new PrecioProductoRMSWebService[0];

	/**
	 * @return the precioProductoRMSWebServices
	 */
	public PrecioProductoRMSWebService[] getPrecioProductoRMSWebServices() {
		return precioProductoRMSWebServices;
	}

	/**
	 * @param precioProductoRMSWebServices the precioProductoRMSWebServices to set
	 */
	public void setPrecioProductoRMSWebServices(
			PrecioProductoRMSWebService[] precioProductoRMSWebServices) {
		this.precioProductoRMSWebServices = precioProductoRMSWebServices;
	}
	
	
}
