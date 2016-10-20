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
public interface ACOProcesoOCRRecepOCSDemWebDDWebService {


	/**
	 * @param codigoPais
	 * @param codigoUsuario	 
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProcesoOCRRecepOCSDemWebDD(
			String codigoUsuario,
			String codigoPais) throws RemoteException;
	
}
