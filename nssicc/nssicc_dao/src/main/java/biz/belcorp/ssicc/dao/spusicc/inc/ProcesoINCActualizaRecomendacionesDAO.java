package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ProcesoINCActualizaRecomendacionesDAO extends DAO{

	/**
	 * Ejecuta la Regulariza las recomendaciones en Incentivos
	 * @param params
	 */
	public void executeProcesoINCActualizaRecomendaciones(Map params);

}
