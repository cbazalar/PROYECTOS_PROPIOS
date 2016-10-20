package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCDeterminarGanadorasRecomendacionPeriodoDAO extends DAO {

	/**
	 * Proceso de Determinar Ganadoras Recomendacion en Cierre de Periodo
	 * 
	 * @param params
	 */
	public void executeDeterminarGanadorasRecomendacionPeriodo(Map params);
	
}
