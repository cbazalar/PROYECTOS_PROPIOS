package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoCOBEnvArchTransCobWebService {

	public ACOWebServiceResponse ejecutarProcesoCOBEnvArchTransCob(
			String codigoUsuario,
			String codigoPais,
			String codigoSociedad,
			String anho,
			String mes) throws RemoteException;
}
