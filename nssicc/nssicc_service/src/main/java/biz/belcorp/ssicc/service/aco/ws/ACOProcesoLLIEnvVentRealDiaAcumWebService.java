package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoLLIEnvVentRealDiaAcumWebService {

	public ACOWebServiceResponse ejecutarProcesoLLIEnvVentRealDiaAcum(
			String codigoUsuario,
			String codigoPais,
			String codigoPeriodo,
			String codigoMarca,
			String codigoCanal,
			String fechaFacturacionInicial,
			String fechaFacturacionFin) throws RemoteException;
}
