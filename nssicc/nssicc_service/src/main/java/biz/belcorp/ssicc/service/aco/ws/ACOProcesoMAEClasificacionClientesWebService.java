package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoMAEClasificacionClientesWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public interface ACOProcesoMAEClasificacionClientesWebService{
	
	
	public ACOWebServiceResponse ejecutarProcesoMAEUpdateClasificacionCliente(
			String codigoUsuario,
			String codigoPais,			
			String codigoMarca,
			String codigoCanal,
			String codigoPeriodo,
			String codigoTipoCliente,			
			String nroCampaniasEvaluar) throws RemoteException;
}
