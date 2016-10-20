package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;


/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCDeterminarGanadorasRecomendacionDAO extends DAO {

	/**
	 * Proceso de Determinar Ganadoras Recomendacion
	 * 
	 * @param params
	 */
	public void executeDeterminarGanadorasRecomendacion(Map params);
	
}
