package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:jjrios@csigcomt.com">Jesse James Rios Franco</a>
 *
 */
public interface ProcesoINCActivacionConcursoDAO extends DAO{

	/**
	 * Ejecuta el proceso de Activacion de concurso
	 * @param params
	 */
	public void executeActivacionConcurso(Map params);
	
}
