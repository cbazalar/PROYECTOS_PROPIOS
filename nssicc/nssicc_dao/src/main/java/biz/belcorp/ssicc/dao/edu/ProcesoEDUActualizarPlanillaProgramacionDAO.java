package biz.belcorp.ssicc.dao.edu;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDUActualizarPlanillaProgramacionDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 */


public interface ProcesoEDUActualizarPlanillaProgramacionDAO extends DAO {

	/**
	 * Realiza el proceso de Actualizar Planilla Programacin 
	 * @param criteria
	 */
	public void executeActualizarPlanillaProgramacion(Map criteria);
	
	
	
}
