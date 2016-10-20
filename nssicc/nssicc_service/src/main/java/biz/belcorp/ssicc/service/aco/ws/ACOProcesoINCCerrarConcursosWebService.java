package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

/**
 * <p>
 * Servicio web para los proceso SICC de Cerrar Concursos de Incentivos
 * <a href="ACOProcesoINCCerrarConcursosWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
public interface ACOProcesoINCCerrarConcursosWebService {

	
	/**
	 * Metodo que se encarga de envir el objeto de respuesta
	 * ACOWebServiceResponse a la interfaz que consuma este servicio.
	 * 
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @param codigoUsuario
	 * @return
	 */
	public ACOWebServiceResponse ejecutarProcesoINCCerrarConcursos(
			String codigoPais, String codigoPeriodo, String codigoUsuario) throws RemoteException;

	
}
