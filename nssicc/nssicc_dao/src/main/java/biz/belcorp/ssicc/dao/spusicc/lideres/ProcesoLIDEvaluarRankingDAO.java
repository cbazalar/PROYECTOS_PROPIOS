package biz.belcorp.ssicc.dao.spusicc.lideres;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsbuchelli
 *
 */
public interface ProcesoLIDEvaluarRankingDAO
		extends DAO {

	/**
	 * Proceso que Permite evaluar ranking lideres por campana
	 * 
	 * @param params
	 */
	public void executeEvaluarRankingLideresCampana(Map params);
	
	/**
	 * Proceso que Permite evaluar ranking lideres por periodo
	 * 
	 * @param params
	 */
	public void executeEvaluarRankingLideresPeriodo(Map params);
}
