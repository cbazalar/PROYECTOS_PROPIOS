package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCCalcularRankingReconocimientoDAO
		extends DAO {

	/**
	 * Proceso que Calcula Ranking para los concursos de Reconocimiento.
	 * 
	 * @param params
	 */
	public void executeCalcularRankingReconocimiento(Map params);

}
