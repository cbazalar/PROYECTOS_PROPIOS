package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoHIPRecepcionarRegistroVentasWebService {

	public ACOWebServiceResponse ejecutarProcesoHIPRecepcionarRegistroVentas(
			String codigoPais,
			String codigoSociedad,
			String codigoUsuario) throws RemoteException;
}
