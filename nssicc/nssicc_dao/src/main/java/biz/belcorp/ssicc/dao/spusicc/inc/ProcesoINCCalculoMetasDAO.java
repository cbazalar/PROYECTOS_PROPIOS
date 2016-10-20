package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:avillavicencio@csigcomt.com">AV</a>
 *
 */
public interface ProcesoINCCalculoMetasDAO extends DAO {

	
	/**
	 * Ejecuta el proceso de calculo de metas
	 * @param params
	 */
	void executeCalculoMetas(Map params);

	
}
