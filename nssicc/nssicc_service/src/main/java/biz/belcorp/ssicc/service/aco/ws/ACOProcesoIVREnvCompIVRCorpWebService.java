package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoIVREnvCompIVRCorpWebService {

	public ACOWebServiceResponse ejecutarProcesoIVREnviarCompletoIVRCorporativo(
			String codigoUsuario,
			String codigoPais,
			String codigoMarca,
			String codigoCanal,
			String codigoAcceso,
			String codigoPeriodo) throws RemoteException;
}
