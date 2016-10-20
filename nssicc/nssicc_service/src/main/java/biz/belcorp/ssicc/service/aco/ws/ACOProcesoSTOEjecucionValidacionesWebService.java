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
 * <a href="ACOProcesoSTOEjecucionValidacionesWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public interface ACOProcesoSTOEjecucionValidacionesWebService {

	/**
	 * 
	 * @param codigoPais
	 * @param codigoTipoDocumento
	 * @param codigoPeriodo
	 * @param fechaProceso
	 * @param codigoUsuario
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProcesoSTOEjecucionValidaciones(String codigoPais, String codigoTipoDocumento, String codigoPeriodo, String fechaProceso, String codigoUsuario) throws RemoteException;
	
}
