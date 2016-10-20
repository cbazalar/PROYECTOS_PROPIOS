package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoRETEnviarInformacionRetailWebService {

	public ACOWebServiceResponse ejecutarProcesoRETEnviarInformacionRetail(
			String codigoUsuario,
			String codigoPais,			
			String campanaFacturacion,
			String fechaFacturacion) throws RemoteException;
}
