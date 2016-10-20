package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoIMPGenSpoolLasMultiWebService {

	public ACOWebServiceResponse ejecutarProcesoIMPGeneracionSpoolLaserMultihilo(
			String codigoUsuario,
			String codigoPais,			
			String codigoMarca,
			String codigoCanal,
			String codigoPeriodo,
			String fechaFacturacion) throws RemoteException;
}
