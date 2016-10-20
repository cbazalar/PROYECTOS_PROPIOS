/**
 * 
 */
package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ControlACOWebService;



/**
 * 
 * TODO Include class description here. 
 * 
 * <p>
 * <a href="ACOProcesoGENEjecutarInterfazWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author sb
 */

public interface ACOProcesoGENControlWebService {

	/**
	 * 
	 * @param codigoPais
	 * @param codigoMarca
	 * @param codigoCanal
	 * @param codigoInterfaz
	 * @param codigoUsuario
	 * @return
	 * @throws RemoteException
	 */
	public ControlACOWebService getControlACO() throws RemoteException;
}
