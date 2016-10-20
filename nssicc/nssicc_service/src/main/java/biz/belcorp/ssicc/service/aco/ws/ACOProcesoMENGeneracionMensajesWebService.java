package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoMENGeneracionMensajesWebService {

	/**
	 * @param codigoPais
	 * @param codigoCampanha
	 * @param fechaFacturacion
	 * @param codigoTipoProceso
	 * @param codigoUsuario
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProcesoMENGeneracionMensajesWevService(
			String codigoPais, String codigoCampanha, String fechaFacturacion,
			String codigoTipoProceso, String codigoUsuario) throws RemoteException;
}
