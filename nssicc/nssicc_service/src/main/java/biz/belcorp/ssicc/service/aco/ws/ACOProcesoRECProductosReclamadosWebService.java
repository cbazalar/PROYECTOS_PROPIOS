/**
 * 
 */
package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

/**
 * 
 * TODO Include class description here. 
 * 
 * <p>
 * <a href="ACOProcesoRECProductosReclamadosWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public interface ACOProcesoRECProductosReclamadosWebService {

	/**
	 * 
	 * @param codigoPais
	 * @param subAccesos
	 * @param tipoIngreso
	 * @param operacion
	 * @param regiones
	 * @param zonas
	 * @param fechaInicio
	 * @param fechaFin
	 * @param codigoUsuario
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProcesoRECProductosReclamados(
				String codigoPais,
				String []subAccesos,
				String tipoIngreso,
				String operacion,
				String []regiones,
				String []zonas,
				String fechaInicio,
				String fechaFin,				
				String codigoUsuario) throws RemoteException;
}
