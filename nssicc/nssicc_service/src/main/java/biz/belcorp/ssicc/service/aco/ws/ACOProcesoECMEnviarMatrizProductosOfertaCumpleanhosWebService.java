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
 * <a href="ACOProcesoECMEnviarMatrizProductosOfertaCumpleanhosWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public interface ACOProcesoECMEnviarMatrizProductosOfertaCumpleanhosWebService {

	/**
	 * 
	 * @param codigoPais
	 * @param codigoUsuario
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProcesoECMEnviarMatrizProductosOfertaCumpleanhos(String codigoPais, String codigoPeriodo, String codigoUsuario) throws RemoteException;
}