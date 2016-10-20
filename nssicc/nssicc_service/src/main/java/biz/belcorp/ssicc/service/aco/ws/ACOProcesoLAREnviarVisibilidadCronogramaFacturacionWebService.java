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
 * <a href="ACOProcesoLAREnviarVisibilidadCronogramaFacturacionWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public interface ACOProcesoLAREnviarVisibilidadCronogramaFacturacionWebService {

	/**
	 * 
	 * @param codigoPais
	 * @param codigoMarca
	 * @param codigoCanal
	 * @param codigoPeriodo
	 * @param codigoUsuario
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProceso(String codigoPais, String codigoMarca, String codigoCanal, String codigoPeriodo, String codigoUsuario) throws RemoteException;
	
}
