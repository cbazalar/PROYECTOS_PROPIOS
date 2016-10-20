package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * Clase de la declaracin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="ProcesoLETEvaluacionProductividadCierreRegionCampaniaDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
public interface ProcesoLETEvaluacionProductividadCierreRegionCampaniaDAO extends DAO {
	
	/**
	 * Mtodo que realiza el proceso de Evaluacin de productividad al cierre de regin y campaa
	 * @param params
	 */
	public void executeProcesoLETEvaluacionProductividadCierreRegionCampania(Map params);
	
}
