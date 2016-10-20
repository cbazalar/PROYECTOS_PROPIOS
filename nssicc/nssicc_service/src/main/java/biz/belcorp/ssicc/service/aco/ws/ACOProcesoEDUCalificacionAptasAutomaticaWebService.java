package biz.belcorp.ssicc.service.aco.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;

/**
 * <p>
 * Servicio web para los procesos EDU Calificacion Aptas Automatica invocados desde ACO <a
 * href="ProcesoEDUWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public interface ACOProcesoEDUCalificacionAptasAutomaticaWebService {

	/**
	 * Ejecuta el Proceso EDU de Calificaci�n y Envio de Aptas Automatica
	 * 
	 * @param codigoPais
	 *            codigo del pais
	 * @param codigoSistema
	 *            codigo de Sistema
	 * @param codigoProcesoBatch
	 *            codigo de Proceso Batch
	 * @param codigoEmpresa
	 *            codigo de la empresa
	 * @param codigoUsuario
	 *            codigo de usuario
	 * @return
	 */

	public ACOWebServiceResponse ejecutarProcesoEDUCalificacionEnviarAptasAutomatica(
			String codigoPais, String codigoEmpresa, String codigoUsuario) throws RemoteException;

	

}
