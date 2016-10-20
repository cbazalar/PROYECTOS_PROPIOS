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
public interface ACOProcesoGENProcesarCierreZonaWebService {


	
	/**
	 * @param codigoPais
	 * @param codigoUsuario
	 * @param marca
	 * @param canal
	 * @param fechaFacturacion
	 * @param codigoPeriodo
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProcesoGENProcesarCierreZona(String codigoPais,
			String codigoUsuario,
			String marca,
			String canal,
			String fechaFacturacion,
			String codigoPeriodo) throws RemoteException ;
	
	
	
}
