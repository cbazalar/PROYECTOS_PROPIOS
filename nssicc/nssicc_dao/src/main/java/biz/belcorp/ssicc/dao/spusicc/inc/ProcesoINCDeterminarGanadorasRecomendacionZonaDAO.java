package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCDeterminarGanadorasRecomendacionZonaDAO extends DAO {

	/**
	 * Proceso de Determinar Ganadoras Recomendacion en Cierre de Zona
	 * 
	 * @param params
	 */
	public void executeDeterminarGanadorasRecomendacionZona(Map params);
	
}