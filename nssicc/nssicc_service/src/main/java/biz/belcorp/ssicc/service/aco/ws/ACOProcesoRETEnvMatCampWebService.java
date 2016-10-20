package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface  ACOProcesoRETEnvMatCampWebService {

	public ACOWebServiceResponse ejecutarProcesoRETEnvMatCamp(
			String codigoUsuario, 
			String codigoPais,
			String codigoMarca,
			String codigoCanal,					
			String codigoAcceso,
			String codigoSubacceso,
			String periodoDesde,
			String periodoHasta) throws RemoteException;
}
