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
public interface ACOProcesoCYZActualizaClasificacionProgramaWebService {

	
	/**
	 *  Ejecuta Proceso Cyzone de Actualizacion de Clasificaciones
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @param codigoUsuario
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProcesoCYZActualizaClasificacionPrograma(
			String codigoPais, String codigoPeriodo, String codigoUsuario) throws RemoteException ;

	

}
