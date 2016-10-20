package biz.belcorp.ssicc.service.spusicc.retail.ws.beans;

import java.io.Serializable;


/**
 * 
 * @author Richar Cruzado
 * @date   23/12/2015
 *
 */
public class DatosConsultoraRMSWebServiceResultado implements Serializable {

	private static final long serialVersionUID = 1L;
		
	private DatosConsultoraRMSWebService[] datosConsultoraRMSWebServices = new DatosConsultoraRMSWebService[0];

	
	/**
	 * @return the datosConsultoraRMSWebServices
	 */
	public DatosConsultoraRMSWebService[] getDatosConsultoraRMSWebServices() {
		return datosConsultoraRMSWebServices;
	}

	/**
	 * @param datosConsultoraRMSWebServices the datosConsultoraRMSWebServices to set
	 */
	public void setDatosConsultoraRMSWebServices(
			DatosConsultoraRMSWebService[] datosConsultoraRMSWebServices) {
		this.datosConsultoraRMSWebServices = datosConsultoraRMSWebServices;
	}	
	
	
}
