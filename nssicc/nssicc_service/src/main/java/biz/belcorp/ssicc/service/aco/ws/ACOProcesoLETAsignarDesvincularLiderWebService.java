package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

/**
 * <p>
 * Servicio web para los procesos EDU Calificacion Aptas Automatica invocados desde ACO <a
 * href="ProcesoEDUWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public interface ACOProcesoLETAsignarDesvincularLiderWebService {

	
	public ACOWebServiceResponse ejecutarProcesoLETAsignarDesvincularLider(			
			String codigoPais,
			String codigoPeriodo, 
			String codigoUsuario) throws RemoteException;
}
