package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
/**
 * <p>
 * Servicio web para los procesos invocados desde ACO <a
 * href="ProcesoEDUWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public interface ACOProcesoGENProcesarGP3WebService {


	/**
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @param fechaFacturacion
	 * @param codigoUsuario
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProcesoGENProcesarGP3(
			String codigoPais, String codigoPeriodo, String fechaFacturacion,
			String codigoUsuario) throws RemoteException;
	
	
	
}
