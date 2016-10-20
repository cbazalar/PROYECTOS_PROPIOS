package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoSABEnvInfoVenDiaWebService {

	public ACOWebServiceResponse ejecutarProcesoSABEnvInfoVenDia(
			String codigoUsuario, 
			String codigoPais,
			String fechaFacturacion,
			String codigoPeriodo) throws RemoteException;
}
