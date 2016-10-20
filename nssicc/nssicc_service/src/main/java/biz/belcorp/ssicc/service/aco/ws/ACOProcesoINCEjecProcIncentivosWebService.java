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
public interface ACOProcesoINCEjecProcIncentivosWebService {


	/**
	 * @param codigoPais
	 * @param usuarioLogin
	 * @param marca
	 * @param canal
	 * @param codigoPeriodo
	 * @param fechaFacturacion
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProcesoINCEjecProcIncentivos(
			String codigoPais, 
			String codigoUsuario, 
			String marca, 
			String canal,
			String codigoPeriodo, 
			String fechaFacturacion)
			throws RemoteException ;
	
	
	
}
