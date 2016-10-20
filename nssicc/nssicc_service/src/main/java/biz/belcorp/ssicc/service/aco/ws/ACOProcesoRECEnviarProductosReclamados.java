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
 * <a href="ACOProcesoRECEnviarProductosReclamados.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public interface ACOProcesoRECEnviarProductosReclamados {

	public ACOWebServiceResponse ejecutarProceso(String codigoPais, String []subAccesos, String tipoIngreso, String codigoOperacionHomologada, String []regiones, String []zonas, String fechaInicio, String fechaFin, String codigoUsuario) throws RemoteException;
	
}
