package biz.belcorp.ssicc.service.sisicc.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface InterfazPREWebService 
{
	/**
	 * @param codigoPais
	 * @param codigoUsuario
	 * @param numeroLote
	 * @param codigoPeriodo
	 * @param medioVenta
	 * @return
	 * @throws RemoteException
	 */
	public int ejecutarProceso(String codigoPais, String codigoUsuario, String numeroLote, String codigoPeriodo, String medioVenta) throws RemoteException;
}
