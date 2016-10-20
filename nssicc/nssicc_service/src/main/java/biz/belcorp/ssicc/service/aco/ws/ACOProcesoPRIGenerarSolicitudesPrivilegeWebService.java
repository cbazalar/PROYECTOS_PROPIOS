package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

/**
 * <p>
 * <a href="ProcesoEDUWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public interface ACOProcesoPRIGenerarSolicitudesPrivilegeWebService {

	
	/**
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @param fechaProceso
	 * @param codigoUsuario
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProcesoPRIGenerarSolicitudesPrivilege(
			String codigoPais, String codigoPeriodo, String fechaProceso,
			String codigoUsuario) throws RemoteException;


	

}
