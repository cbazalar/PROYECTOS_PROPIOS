package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoIMPGenProcNoDepFactWebService {

	public ACOWebServiceResponse ejecutarProcesoIMPGenProcNoDepFact(
			String codigoUsuario,
			String codigoPais,
			String codigoMarca,
			String codigoCanal,
			String codigoPeriodo,
			String fechaFacturacion) throws RemoteException;
}
