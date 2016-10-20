package biz.belcorp.ssicc.dao.spusicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Gonzalo Javier Huertas Agurto
 *
 */
public interface ProcesoGENBloquearConsultorasPrimerPedidoDAO extends DAO{

	/**
	 * Ejecuta el Proceso de Bloquear Consultoras Primer Pedido
	 * @param params
	 */
	public void executeProcesoGENBloquearConsultorasPrimerPedido(Map params);

}