package biz.belcorp.ssicc.dao.spusicc.lideres;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoLIDDeterminarGanadorasCanastaLideresDAO
		extends DAO {

	/**
	 * Proceso que Determina Ganadoras de Canasta de Lideres
	 * 
	 * @param params
	 */
	public void executeDeterminarGanadorasCanastaLideres(Map params);

}

