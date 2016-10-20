package biz.belcorp.ssicc.dao.edu;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDUInicioProcesosDiariosDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 */


public interface ProcesoEDUInicioProcesosDiariosDAO extends DAO {


	/**
	 * Efectua el Registro de Planillas no procesadas
	 * @param criteria
	 */
	public void executeRegistrarPlanillasNoProcesadas(Map criteria) throws Exception;	

	public void executeProcesoConsultoraRezagadas(Map params)throws Exception;
}
