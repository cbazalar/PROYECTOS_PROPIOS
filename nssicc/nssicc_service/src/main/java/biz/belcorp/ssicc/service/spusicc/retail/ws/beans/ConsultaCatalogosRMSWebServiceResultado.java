/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws.beans;


/**
 * @author Richar Cruzado Vallejos
 *
 */
public class ConsultaCatalogosRMSWebServiceResultado {

	private static final long serialVersionUID = 1L;
	
	private ConsultaCatalogosRMSWebService[] consultaCatalogosRMSWebServices = new ConsultaCatalogosRMSWebService[0];

	/**
	 * @return the consultaCatalogosRMSWebServices
	 */
	public ConsultaCatalogosRMSWebService[] getConsultaCatalogosRMSWebServices() {
		return consultaCatalogosRMSWebServices;
	}

	/**
	 * @param consultaCatalogosRMSWebServices the consultaCatalogosRMSWebServices to set
	 */
	public void setConsultaCatalogosRMSWebServices(
			ConsultaCatalogosRMSWebService[] consultaCatalogosRMSWebServices) {
		this.consultaCatalogosRMSWebServices = consultaCatalogosRMSWebServices;
	}
	
}
