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
 * <a href="ACOProcesoSAMRecepcionarStockDiarioWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public interface ACOProcesoSAMRecepcionarStockDiarioWebService {

	/**
	 * 
	 * @param codigoPais
	 * @param tipoCarga
	 * 		1 = RECEPCIONAR_STOCK_DIARIO_BATCH
	 * 		2 = RECEPCIONAR_STOCK_DIARIO_PROL
	 * @param codigoUsuario
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProceso(String codigoPais, String tipoCarga, String codigoUsuario) throws RemoteException;
}
