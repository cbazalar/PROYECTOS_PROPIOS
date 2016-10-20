package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

public interface ACOProcesoRETRecepcionarVentasRetailCabDetWebService {

	public ACOWebServiceResponse ejecutarProcesoRETRecepcionarVentasRetailCabDet(
			String codigoPais,
			String indicadorReproceso,
			String fechaInicio,
			String fechaFinal,
			String codigoUsuario) throws RemoteException;
}
