package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCGenerarPedidosRecomendacionDAO
		extends DAO {

	/**
	 * Proceso que calcula puntaje para pedidos multimarca
	 * 
	 * @param params
	 */
	public void executeGenerarPedidosRecomendacion(Map params);

}
