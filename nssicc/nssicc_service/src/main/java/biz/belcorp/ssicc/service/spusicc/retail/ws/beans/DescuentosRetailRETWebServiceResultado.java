/*
 * Created on 23/12/2015 11:20:15 AM
 * biz.belcorp.ssicc.service.spusicc.retail.ws.beans.DescuentosRetailRETWebServiceResultado
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws.beans;

import java.io.Serializable;


/**
 * 
 * @author Richar Cruzado
 *
 */
public class DescuentosRetailRETWebServiceResultado implements Serializable {

	private static final long serialVersionUID = 1L;
		
	private DescuentoRMSWebService[] descuentoRMSWebServices = new DescuentoRMSWebService[0];

	
	/**
	 * @return the descuentoRMSWebServices
	 */
	public DescuentoRMSWebService[] getDescuentoRMSWebServices() {
		return descuentoRMSWebServices;
	}

	/**
	 * @param descuentoRMSWebServices the descuentoRMSWebServices to set
	 */
	public void setDescuentoRMSWebServices(
			DescuentoRMSWebService[] descuentoRMSWebServices) {
		this.descuentoRMSWebServices = descuentoRMSWebServices;
	}	
	
	
}
