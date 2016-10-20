package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoDATEnviarAdministracionFlujosWebService {

	public ACOWebServiceResponse ejecutarProcesoDATEnviarAdministracionFlujos(
			String codigoUsuario,
			String codigoPais,
			String codigoMarca,
			String codigoCanal,
			String codigoAcceso,
			String codigoPeriodo,
			String fechaFacturacion) throws RemoteException;
}
