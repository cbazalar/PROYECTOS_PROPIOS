package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoPRYEnvProyParcialWebService {

	public ACOWebServiceResponse ejecutarProcesoPRYEnvProyParcial(
			String codigoUsuario, 
			String codigoPais,
			String codigoMarca,
			String codigoCanal,
			String fechaFacturacion,
			String codigoPeriodo) throws RemoteException;
}
