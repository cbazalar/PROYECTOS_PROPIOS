package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoACCEnvNovWebService {

	public ACOWebServiceResponse ejecutarProcesoACCEnvNov(
			String codigoUsuario,
			String codigoPais,
			String codigoPeriodo,
			String fechaFacturacion,
			String tipoEnvio) throws RemoteException;
}
