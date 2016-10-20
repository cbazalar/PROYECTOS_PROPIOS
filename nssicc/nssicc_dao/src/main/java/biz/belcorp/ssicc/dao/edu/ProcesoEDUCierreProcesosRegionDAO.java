package biz.belcorp.ssicc.dao.edu;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDUCierreProcesosRegionDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 */


public interface ProcesoEDUCierreProcesosRegionDAO extends DAO {

	/**
	 * Realiza el proceso de Cerrar Cronograma Dictado
	 * @param criteria
	 */
	public void executeCerrarCronogramaDictado(Map criteria);
	
	/**
	 * Realiza el proceso de Pasar Consultoras de Programada a Pendiente
	 * @param criteria
	 */
	public void executePasarConsultorasProgramadaAPendiente(Map criteria) ;
	
	
	/**
	 * Realiza proceso de Generacion de Lista Status Consultora
	 * @param criteria
	 */
	public void executeListaStatusConsultora(Map criteria) throws Exception; 
	
	
	/**
	 * Obtiene Lista Status Consultora
	 * @param criteria
	 */
	public List getListaStatusConsultora(Map criteria) throws Exception; 
	
	public void registrarPlanillaNoProcesadas(Map params)throws Exception; 
	
}
