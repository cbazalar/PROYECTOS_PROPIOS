/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.mae;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author itocto
 *
 */
public interface ProcesoMAEDesbloquearClienteConPedidoDAO extends DAO {

	/**
	 * Proceso que va a Desbloquear Cliente con pedido
	 * 
	 * @param params
	 */
	public void executeDesbloquearClienteConPedido(Map params);
	
}
