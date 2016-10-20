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
 * <a href="ACOProcesoLARGenerarLARFacturacionElectronicaWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public interface ACOProcesoLARGenerarLARFacturacionElectronicaWebService {

	/**
	 * 
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @param fecha
	 * @param tipoDocumento
	 * @param regionList
	 * @param zonaList
	 * @param desde
	 * @param hasta
	 * @param codigoUsuario
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProcesoLARGenerarLARFacturacionElectronica(
				String codigoPais,
				String codigoPeriodo,
				String fecha,
				String desde,
				String hasta,
				String codigoUsuario) throws RemoteException;
}
