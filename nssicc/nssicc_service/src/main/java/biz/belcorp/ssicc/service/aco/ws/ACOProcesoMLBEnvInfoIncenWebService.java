package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoMLBEnvInfoIncenWebService {

	public ACOWebServiceResponse ejecutarProcesoMLBEnvInfoIncen(
			String codigoUsuario,
			String codigoPais,
			String codigoPeriodo,
			String fechaFacturacion) throws RemoteException;
}
