package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoRECEnviaMsgReclamoWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public interface ACOProcesoRECEnviaMsgReclamoWebService {
	
	/**
	 *  Ejecuta Proceso Envio Mensaje Reclamo
	 * @param codigoUsuario
	 * @param codigoPais
	 * @param codigoMarca
	 * @param codigoCanal
	 * @param codigoPeriodo
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProcesoRECEnviaMsgReclamo(
			String codigoUsuario,
			String codigoPais,			
			String codigoMarca,
			String codigoCanal,
			String codigoPeriodo) throws RemoteException;
}
