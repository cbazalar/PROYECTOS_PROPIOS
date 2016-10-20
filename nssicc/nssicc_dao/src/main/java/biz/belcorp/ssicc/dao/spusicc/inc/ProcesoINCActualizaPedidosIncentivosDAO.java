package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ProcesoINCActualizaPedidosIncentivosDAO extends DAO{

	/**
	 * Ejecuta la actualizacion de Pedidos
	 * @param params
	 */
	public void executeActualizaPedidosIncentivos(Map params);

}