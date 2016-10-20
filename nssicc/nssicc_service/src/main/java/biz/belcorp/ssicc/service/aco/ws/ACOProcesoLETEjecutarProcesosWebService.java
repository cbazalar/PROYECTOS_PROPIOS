package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoLETEjecutarProcesosWebService {

	public ACOWebServiceResponse ejecutarProcesoLETEjecutarProcesos(
			String codigoUsuario,
			String codigoPais,
			String codigoMarca,
			String codigoCanal,
			String codigoPeriodo) throws RemoteException;
}
