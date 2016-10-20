package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoCUPGenerarCCCWebService {

	public ACOWebServiceResponse ejecutarProcesoCUPGenerarCCC(
			String codigoUsuario,
			String codigoPais,			
			String codigoPeriodo,
			String fechaFacturacion,
			String codigoPrograma) throws RemoteException;
}
