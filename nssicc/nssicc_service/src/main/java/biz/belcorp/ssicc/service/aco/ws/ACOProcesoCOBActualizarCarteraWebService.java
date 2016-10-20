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
 * <a href="ACOProcesoCOBActualizarCarteraWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public interface ACOProcesoCOBActualizarCarteraWebService {

	/**
	 * 
	 * @param codigoPais
	 * @param codigoSociedad
	 * @param codigoUsuario
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProcesoCOBActualizarCartera(String codigoPais, String codigoSociedad, String codigoUsuario) throws RemoteException;
	
}
