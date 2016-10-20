/*
 * Created on 23/12/2015 11:23:15 AM
 * biz.belcorp.ssicc.service.spusicc.retail.ws.beans.ProcesoRMSDatosConsultoraWebService
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.DatosConsultoraRMSWebServiceResultado;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.DatosConsultoraWebServiceParameter;


/**
 * <p>
 * <a href="ProcesoRMSDatosConsultoraWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Richar Cruzado
 */
public interface ProcesoRMSDatosConsultoraWebService {
 	
    
	/**
	 * RCR PER-SiCC-2015-0823: Desarrollo de WebService Información de Datos de Consultora
	 * Permite obtener el objeto DatosConsultora, el cual devolverá datos de la consultora.
	 * 
	 */
	public DatosConsultoraRMSWebServiceResultado getDatosConsultora(DatosConsultoraWebServiceParameter beanConsultora) throws RemoteException;
	

	
}
