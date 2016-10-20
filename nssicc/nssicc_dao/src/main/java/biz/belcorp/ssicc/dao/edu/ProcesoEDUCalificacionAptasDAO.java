package biz.belcorp.ssicc.dao.edu;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDUCalificacionAptasDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 */

public interface ProcesoEDUCalificacionAptasDAO extends DAO {

	
	/**
	 * Devuelve la lista de registros de apta de capacitacion de acuerdo al tipo de calificacion.
	 * @param map
	 * 			
	 */
	public List getCursosCalificacionAptasAutomaticaByCriteria(Map criteria);
	
	/**
	 * Efectua el proceso de Calificacion de Aptas Automatica 
	 * @param criteria
	 */
	public void executeCalificacionAptasAutomatica(Map criteria);
	
	
	/**
	 * Ejecuta proceso que actualiza Invitacion de Historico de aptas
	 * @param criteria
	 */
	public void executeActualizarInvitacionHistoAptas(Map criteria);
	
	/**
	 * Funcion que devuelve la campaa Actual de Proceso
	 * @param criteria
	 * @return
	 */
	public String getCampannaActualProceso(Map criteria);
	
	
	/**
	 * Funcion que devuelve la Fecha de Facturacion de la 
     * Campaa de Proceso
	 * @param criteria
	 * @return
	 */
	public String getFechaProcesoFacturacion(Map criteria);
	
	void ejecutarProcesosPostCalificacionEnvioAptasAutomatica(Map params);
		
	
}
