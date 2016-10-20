package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

/**
 * <p>
 * Servicio web para los proceso SICC de Revertir Solicitudes de Servicio
 * <a href="ACOProcesoPEDRevertirSolicitudesServicioWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
public interface ACOProcesoPEDRevertirSolicitudesServicioWebService {

	
	/**
	 * Metodo que se encarga de envir el objeto de respuesta
	 * ACOWebServiceResponse a la interfaz que consuma este servicio.
	 * 
	 * @param codigoPais
	 * @param codigoUsuario
	 * @param fechaInicio
	 * @return
	 */
	public ACOWebServiceResponse ejecutarProcesoPEDRevertirSolicitudesServicio(
			String codigoPais, String codigoUsuario, String fechaInicio) throws RemoteException;

}

