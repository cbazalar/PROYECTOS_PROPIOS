package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoLETModuloCaribeEnviarInformacionFOXWebService {

	public ACOWebServiceResponse ejecutarProcesoLETEnviarInformacionFOX(			
			String codigoPais,
			String codigoPeriodo,
			String fechaFacturacion,
			String codigoUsuario) throws RemoteException;
}
