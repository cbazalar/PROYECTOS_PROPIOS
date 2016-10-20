package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Sergio Apaza
 *
 */
public interface ProcesoINCActualizaPuntajeRetailDAO extends DAO{

	/**
	 * Actualiza disponibilidad de  Puntaje Retail x Campaa
	 * @param params
	 */
	public void executeActualizaPuntajeRetail(Map params);

}
