package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoIMPGeneracionSpoolLaserMultihiloMavWebService {

	public ACOWebServiceResponse ejecutarProcesoIMPGeneracionSpoolLaserMultihiloMav(
			String codigoPais,			
			String codigoMarca,
			String codigoCanal,
			String codigoPeriodo,
			String fechaFacturacion,
			String codigoUsuario) throws RemoteException;
}
