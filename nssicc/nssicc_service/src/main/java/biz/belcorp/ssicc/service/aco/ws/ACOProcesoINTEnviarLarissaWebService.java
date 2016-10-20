package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

/**
 * <p>
 * Servicio web para enviar las interfaces Larissa de SICC
 * <a href="ACOProcesoINTEnviarLarissaWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
public interface ACOProcesoINTEnviarLarissaWebService {

	
	/**
	 * Metodo que se encarga de envir el objeto de respuesta
	 * ACOWebServiceResponse a la interfaz que consuma este servicio.
	 * 
	 * @param codigoPais
	 * @param codigoUsuario
	 * @param codigoPeriodo
	 * @return
	 */
	public ACOWebServiceResponse ejecutarProcesoINTEnviarLarissa(
			String codigoPais, String codigoUsuario, String codigoPeriodo) throws RemoteException;

}

