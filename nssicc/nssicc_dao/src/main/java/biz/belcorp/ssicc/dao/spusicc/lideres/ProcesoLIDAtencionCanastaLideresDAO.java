package biz.belcorp.ssicc.dao.spusicc.lideres;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoLIDAtencionCanastaLideresDAO
		extends DAO {

	/**
	 * Proceso que Permite despachar los productos que forman la canasta de Lideres
	 * 
	 * @param params
	 */
	public void executeAtencionCanastaLideres(Map params);

}
