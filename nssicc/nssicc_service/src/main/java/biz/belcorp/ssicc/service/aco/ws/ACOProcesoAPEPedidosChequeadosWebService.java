package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoAPEPedidosChequeadosWebService {

	public ACOWebServiceResponse ejecutarProcesoAPEPedidosChequeados(
			String codigoPais,
			String codigoUsuario) throws RemoteException;
}
