package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

/**
 * <p>
 * Servicio web para los procesos Proceso Cyzone de Actualizacion de Clasificaciones
 * <a href="ProcesoEDUWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public interface ACOProcesoPEDAsignacionStockWebService {

	
	/**
	 * Metodo que se encarga de envir el objeto de respuesta
	 * ACOWebServiceResponse a la interfaz que consuma este servicio.
	 * 
	 * @param codigoPais
	 * @param usuarioLogin
	 * @return
	 */
	public ACOWebServiceResponse ejecutarProcesoPEDAsignacionStock(
			String codigoPais, String usuarioLogin, String fechaFacturacion) throws RemoteException;

	

}
