package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoOCREnvArchProlWebService {

	public ACOWebServiceResponse ejecutarProcesoOCREnvArchProl(
			String codigoUsuario,
			String codigoPais) throws RemoteException;
}
