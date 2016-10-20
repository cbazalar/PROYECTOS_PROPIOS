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
public interface ACOProcesoINCEjecutarProcesosWebService {


	/**
	 * @param codigoPais
	 * @param usuarioLogin
	 * @param marca
	 * @param canal
	 * @param codigoPeriodo
	 * @param fechaFacturacion
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProcesoINCEjecutarProcesos(
			String codigoPais, String codigoUsuario, String marca, String canal,
			String codigoPeriodo, String fechaFacturacion, String indicadorProceso)
			throws RemoteException ;
	
	
	
}
