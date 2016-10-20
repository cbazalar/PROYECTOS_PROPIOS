package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface  ACOProcesoRETEnvFactCompVentDirWebService {

	public ACOWebServiceResponse ejecutarProcesoRETEnvFactCompVentDir(
			String codigoUsuario, 
			String codigoPais,
			String codigoMarca,
			String codigoCanal,
			String codigoAcceso,
			String codigoPeriodo,
			String fechaFacturacion) throws RemoteException;
}
