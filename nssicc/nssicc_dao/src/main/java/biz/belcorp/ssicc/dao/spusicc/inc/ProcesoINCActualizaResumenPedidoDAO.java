package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */

public interface ProcesoINCActualizaResumenPedidoDAO extends DAO{

	/**
	 * Ejecuta el proceso que actualiza la tabla de Pedidos por Campaa de las consultoras
	 * @param params
	 */
	public void executeActualizaResumenPedido(Map params);
	
	/**
	 * Ejecuta el proceso que actualiza la tabla de Pedidos diario de las consultoras
	 * @param params
	 */
	public void executeActualizaResumenPedidoDiario(Map params);



}