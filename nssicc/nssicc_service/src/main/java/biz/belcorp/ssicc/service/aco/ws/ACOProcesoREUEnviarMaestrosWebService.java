package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoREUEnviarMaestrosWebService {

	public ACOWebServiceResponse ejecutarProcesoREUEnviarMaestros(
			String codigoUsuario, 
			String codigoPais,
			String codigoMarca,
			String codigoCanal,
			String periodoDesde,
			String periodoHasta,
			String codigoActividad,
			String codigoTipoCliente,
			String codigoAcceso,
			String fechaFacturacion,
			String codigoPeriodo) throws RemoteException;
}
