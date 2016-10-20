package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoBELEnviarInterfaceDiariaWebService {

	public ACOWebServiceResponse ejecutarProcesoBELEnviarInterfaceDiaria(			
			String codigoPais,
			String codigoMarca,
			String codigoCanal,
			String codigoAcceso,
			String codigoPeriodo,
			String fechaFacturacion,
			String codigoTipoCliente,
			String codigoUsuario) throws RemoteException;
}
