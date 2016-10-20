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
 * <a href="ACOProcesoMAVFacturacionGerentesWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public interface ACOProcesoMAVFacturacionGerentesWebService {

	/**
	 * 
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @param codigoActividad
	 * @param codigoUsuario
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProcesoMAVFacturacionGerentes(String codigoPais, String codigoPeriodo, String codigoActividad, String codigoUsuario) throws RemoteException;
	
}
