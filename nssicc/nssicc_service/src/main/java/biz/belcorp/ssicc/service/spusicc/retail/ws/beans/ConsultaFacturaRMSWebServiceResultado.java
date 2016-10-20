/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws.beans;

import java.io.Serializable;

/**
 * @author Richar Cruzado
 * @date   29/12/2015
 */
public class ConsultaFacturaRMSWebServiceResultado implements Serializable {

	private static final long serialVersionUID = 1L;
		
	private ConsultaFacturaRMSWebService[] consultaFacturaRMSWebServices = new ConsultaFacturaRMSWebService[0];

	/**
	 * @return the consultaFacturaRMSWebServices
	 */
	public ConsultaFacturaRMSWebService[] getConsultaFacturaRMSWebServices() {
		return consultaFacturaRMSWebServices;
	}

	/**
	 * @param consultaFacturaRMSWebServices the consultaFacturaRMSWebServices to set
	 */
	public void setConsultaFacturaRMSWebServices(
			ConsultaFacturaRMSWebService[] consultaFacturaRMSWebServices) {
		this.consultaFacturaRMSWebServices = consultaFacturaRMSWebServices;
	}

}
