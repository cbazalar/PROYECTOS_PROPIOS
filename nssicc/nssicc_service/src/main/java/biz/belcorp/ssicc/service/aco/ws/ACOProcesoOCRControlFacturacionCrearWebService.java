package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoOCRControlFacturacionCrearWebService {

	public ACOWebServiceResponse ejecutarProceso (
			String codigoPais,
			String fechaFacturacion,
			String montoMinimoDeuda,
			String unidadesMaximo,
			String codigoUsuario) throws RemoteException;
}
