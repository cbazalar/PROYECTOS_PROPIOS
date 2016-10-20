package biz.belcorp.ssicc.dao.edu;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDUActualizarParametrosCursoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 */


public interface ProcesoEDUActualizarParametrosCursoDAO extends DAO {

	/**
	 * Realiza el proceso de Cerrar Cursos Vigentes
	 * @param criteria
	 */
	public void executeActualizarParametrosCurso(Map criteria);
	
	
	
}
