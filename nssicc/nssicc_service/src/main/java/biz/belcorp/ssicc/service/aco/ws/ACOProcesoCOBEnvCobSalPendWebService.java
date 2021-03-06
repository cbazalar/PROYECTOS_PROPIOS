package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoCOBEnvCobSalPendWebService {

	public ACOWebServiceResponse ejecutarProcesoACOProcesoCOBEnvCobSalPend(
			String codigoUsuario,
			String codigoPais,
			String codigoSociedad) throws RemoteException;
}
