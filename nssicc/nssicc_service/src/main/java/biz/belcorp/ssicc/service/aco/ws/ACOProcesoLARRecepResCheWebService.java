package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoLARRecepResCheWebService {

	public ACOWebServiceResponse ejecutarProcesoLARRecepResChe(
			String codigoUsuario,
			String codigoPais) throws RemoteException;
}
