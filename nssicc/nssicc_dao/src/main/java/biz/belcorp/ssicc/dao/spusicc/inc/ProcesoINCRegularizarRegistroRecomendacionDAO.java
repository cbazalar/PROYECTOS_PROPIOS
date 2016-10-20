package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoINCRegularizarRegistroRecomendacionDAO extends DAO {
	
	/**
	 * Ejecuta el proceso de regularizar registro de recomendacion
	 * @param params
	 */
	void executeRegularizarRegistroRecomendacion(Map params);

	/**
	 * Ejecuta el proceso de regularizar registro de recomendacion al inicio de campaa
	 * @param params
	 */
	void executeRegularizarRegistroRecomendacionInicioCampana(Map params);	
}
