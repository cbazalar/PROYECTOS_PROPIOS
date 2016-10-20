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
 * <a href="ACOProcesoEDUGenerarPlanillaProgramacionWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public interface ACOProcesoEDUGenerarPlanillaProgramacionWebService {

	/**
	 * 
	 * @param codigoPais
	 * @param codigoEmpresa
	 * @param regiones
	 * @param tipoProceso
	 * @param codigoPeriodo
	 * @param codigoUsuario
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProcesoEDUGenerarPlanillaProgramacion(
				String codigoPais,
				String codigoUsuario,
				String codigoPeriodo,				 
				String codigoEmpresa, 
				String []regiones, 
				String tipoProceso) throws RemoteException;
}
