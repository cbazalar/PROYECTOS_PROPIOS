package biz.belcorp.ssicc.service.spusicc.dir.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.spusicc.dir.ws.beans.ProcesoDIRWebServiceResultado;

/**
 * <p>
 * Servicio web para los procesos invocados para Directorio de Ventas <a
 * href="ProcesoEDUWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public interface ProcesoDIRWebService {

	
	/**
	 * Obtiene Listado de Clientes de Directorio de Ventas
	 * @param codigRegion
	 * @param codigZona
	 * @param codigoTipoCargo
	 * @param codigoPeriodo
	 * @param estadoCargo
	 * @return
	 * @throws RemoteException
	 */
	public ProcesoDIRWebServiceResultado obtenerClientesDirectorio(
			String codigoPais,
			String codigoRegion,
			String codigoZona, 
			String codigoTipoCargo,
			String codigoPeriodo,
			String estadoCargo) throws RemoteException;
	
	
	/**
	 * Busca un Cliente del Directorio de Ventas
	 * @param usuarioRed
	 * @param codigCUB
	 * @return
	 * @throws RemoteException
	 */
	public ProcesoDIRWebServiceResultado buscarClientesDirectorio(
			String codigoPais,
			String usuarioRed,
			String codigoCUB) throws RemoteException;
}
