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
 * <a href="ACOProcesoRECEnviarProductosReclamadosWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public interface ACOProcesoRECEnviarProductosReclamadosWebService {

	/**
	 * 
	 * @param codigoPais
	 * @param operacion
	 * @param fechaInicio
	 * @param fechaFin
	 * @param codigoUsuario
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProceso(String codigoPais, String operacion, String fechaInicio, String fechaFin, String codigoUsuario) throws RemoteException;
}
