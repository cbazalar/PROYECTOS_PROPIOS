package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoCOBEnviarRecuCobranzaFFVVDatamartWebService {

	public ACOWebServiceResponse ejecutarProcesoCOBEnviarRecuCobranzaFFVVDatamart(
			String codigoUsuario,
			String codigoPais,
			String codigoPeriodo) throws RemoteException;
}
