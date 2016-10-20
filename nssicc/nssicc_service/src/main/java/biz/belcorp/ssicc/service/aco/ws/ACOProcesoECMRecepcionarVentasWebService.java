package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

/**
 * <p>
 * Servicio web para recepcionar las ventas ECM de SICC
 * <a href="ACOProcesoECMRecepcionarVentasWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
public interface ACOProcesoECMRecepcionarVentasWebService {

	/**
	 * Metodo que se encarga de envir el objeto de respuesta
	 * ACOWebServiceResponse a la interfaz que consuma este servicio.
	 * 
	 * @param codigoPais
	 * @param codigoUsuario
	 * @return
	 */
	public ACOWebServiceResponse ejecutarProcesoECMRecepcionarVentas(
			String codigoPais, String codigoUsuario) throws RemoteException;

}

