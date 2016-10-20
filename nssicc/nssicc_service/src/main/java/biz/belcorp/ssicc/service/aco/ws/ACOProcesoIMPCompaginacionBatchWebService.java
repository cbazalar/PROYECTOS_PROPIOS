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
 * <a href="ACOProcesoIMPCompaginacionBatchWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public interface ACOProcesoIMPCompaginacionBatchWebService {

	/**
	 * 
	 * @param codigoPais
	 * @param codigoProcesoImpresion
	 * @param codigoPeriodo
	 * @param fechaFacturacion
	 * @param codigoUsuario
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProcesoIMPCompaginacionBatch(String codigoPais, String codigoProcesoImpresion, String codigoPeriodo, String fechaFacturacion, String codigoUsuario) throws RemoteException;
	
}
