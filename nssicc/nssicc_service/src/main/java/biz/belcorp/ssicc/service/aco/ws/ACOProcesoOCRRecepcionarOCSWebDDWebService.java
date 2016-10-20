/**
 * 
 */
package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

/**
 * 
 * TODO Include class description here. 
 * 
 * <p>
 * <a href="ACOProcesoOCRRecepcionarOCSWebDDWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public interface ACOProcesoOCRRecepcionarOCSWebDDWebService {

	/**
	 * 
	 * @param codigoPais
	 * @param indValidacionSTO
	 * @param codigoUsuario
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProceso(String codigoPais, String indValidacionSTO, String codigoUsuario) throws RemoteException;
}
