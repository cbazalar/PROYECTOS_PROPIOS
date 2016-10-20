package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jose Luis Rodriguez
 *
 */
public interface ProcesoINCCerrarConcursosDAO extends DAO {

	/**
	 * Proceso que Cierra los concursos
	 * 
	 * @param params
	 */
	public void executeCerrarConcursos(Map params);

}
