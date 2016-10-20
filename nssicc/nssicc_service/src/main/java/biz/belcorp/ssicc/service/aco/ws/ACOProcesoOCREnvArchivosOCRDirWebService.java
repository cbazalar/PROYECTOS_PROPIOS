package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoOCREnvArchivosOCRDirWebService {

	public ACOWebServiceResponse ejecutarProcesoOCREnvArchivosOCRDir(
			String codigoUsuario,
			String codigoPais,
			String codigoMarca,
			String codigoCanal,
			String codigoPeriodoDesde,
			String codigoPeriodoHasta) throws RemoteException;
}
