package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoMYEEnviarMaestrosWebService {

	public ACOWebServiceResponse ejecutarProcesoMYEEnviarMaestros(
			String codigoUsuario, 
			String codigoPais,
			String codigoMarca,
			String codigoCanal,					
			String codigoTipoCliente,
			String codigoAcceso,
			String codigoSubacceso,
			String fechaFacturacion,
			String codigoPeriodo) throws RemoteException;
}
