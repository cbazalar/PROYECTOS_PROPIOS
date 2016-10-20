package biz.belcorp.ssicc.dao.edu;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDUCierreCampannaDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 */


public interface ProcesoEDUCierreCampannaDAO extends DAO {

	/**
	 * Realiza el proceso de Bloqueo de consultoras al Cierre de Campaa 
	 * @param criteria
	 */
	public void executeBloqueoConsultoraCampanna(Map criteria);
	
	public void executeProcesoConsultoraRezagadas(Map params)throws Exception;
	
	/**
	 * Ejecuta las planilas no procesadss pa todas las regiones
	 * 
	 * @param params
	 */
	public void executeRegistrarPlanillasNoProcesadas(Map params);
	
	
	
}
