package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoCCCCargasDeudasWebWebService {

	public ACOWebServiceResponse ejecutarProcesoCCCCargasDeudasWeb(			
			String codigoPais,
			String codigoUsuario) throws RemoteException;
}
