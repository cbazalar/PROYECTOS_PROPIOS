package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

/**
 * <p>
 * Servicio web para los procesos invocados desde ACO <a
 * href="ProcesoEDUWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public interface ProcesoACOWebService {

	
	

	
	
	
	/**
	 * @param codigoPais
	 * @param fechaFacturacion
	 * @param numeroVersion
	 * @param numeroProdGrupo
	 * @param presentacion
	 * @param codigoUsuario
	 * @param formatoExportacion
	 * @param tipoReporte
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProcesoFaltanteFacturacionDia(String codigoPais,
			String fechaFacturacion, String numeroVersion,
			String numeroProdGrupo, String presentacion, 
			String codigoUsuario, String formatoExportacion,
			String tipoReporte)throws RemoteException;
}
