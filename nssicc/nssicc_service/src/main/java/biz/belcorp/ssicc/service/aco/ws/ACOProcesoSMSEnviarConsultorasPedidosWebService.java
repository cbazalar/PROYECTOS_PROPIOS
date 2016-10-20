package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoSMSEnviarConsultorasPedidosWebService {

	public ACOWebServiceResponse ejecutarProcesoSMSEnviarConsultorasPedidos(
			String codigoPais,
			String tipoInterfaz,
			String codigoPeriodo,
			String fechaFacturacion,			
			String codigoUsuario) throws RemoteException;
}
