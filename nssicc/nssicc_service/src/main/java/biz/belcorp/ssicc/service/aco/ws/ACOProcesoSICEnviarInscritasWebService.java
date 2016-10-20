package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoSICEnviarInscritasWebService {

	public ACOWebServiceResponse ejecutarProcesoSICEnviarInscritas(
			String codigoUsuario,
			String codigoPais,
			String codigoMarca,
			String codigoCanal,
			String codigoTipoCliente,
			String codigoPeriodo,
			String codigoTipoVinculo,
			String[] codigoSubTipoCliente,
			String[] codigoTipoClasificacion,
			String[] codigoClasificacion) throws RemoteException;
}
