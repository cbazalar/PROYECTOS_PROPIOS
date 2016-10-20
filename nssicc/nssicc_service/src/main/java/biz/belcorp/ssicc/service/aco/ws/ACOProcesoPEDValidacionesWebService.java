package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

/**
 * <p>
 * Servicio web para los proceso SICC de Validaciones de las Ordenes de Compra
 * <a href="ACOProcesoPEDValidacionesWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
public interface ACOProcesoPEDValidacionesWebService {

	
	/**
	 * Metodo que se encarga de envir el objeto de respuesta
	 * ACOWebServiceResponse a la interfaz que consuma este servicio.
	 * 
	 * @param codigoPais
	 * @param codigoUsuario
	 * @param codigoPeriodo
	 * @param fechaFacturacion
	 * @return
	 */
	public ACOWebServiceResponse ejecutarProcesoPEDValidaciones(
			String codigoPais, String codigoUsuario, String codigoPeriodo, String fechaFacturacion) throws RemoteException;

}
