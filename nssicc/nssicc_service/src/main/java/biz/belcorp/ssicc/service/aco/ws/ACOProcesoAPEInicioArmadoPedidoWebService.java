package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoAPEInicioArmadoPedidoWebService {

	public ACOWebServiceResponse ejecutarProcesoAPEInicioArmadoPedido(
			String codigoPais,
			String codigoUsuario) throws RemoteException;
}
