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
 * <a href="ACOProcesoSAMEnviarMovimientosAlmacenSiccWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public interface ACOProcesoSAMEnviarMovimientosAlmacenSiccWebService {
	/**
	 * 
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @param fechaProceso
	 * @param codigoUsuario
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProceso(String codigoPais, String codigoPeriodo, String fechaProceso, String codigoUsuario) throws RemoteException;
}
