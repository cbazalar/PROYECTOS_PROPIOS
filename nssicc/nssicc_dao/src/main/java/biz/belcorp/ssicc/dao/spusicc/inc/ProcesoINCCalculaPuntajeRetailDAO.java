package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCCalculaPuntajeRetailDAO extends DAO {

	/**
	 * Proceso que Calcula Puntaje Retail de las Consultoras por Campaa
	 * 
	 * @param params
	 */
	public void executeCalculaPuntajeRetail(Map params);
	
}
