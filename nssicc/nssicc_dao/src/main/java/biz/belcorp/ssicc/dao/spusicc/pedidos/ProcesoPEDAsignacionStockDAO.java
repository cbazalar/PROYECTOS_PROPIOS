package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jose Cairampoma
 */

public interface ProcesoPEDAsignacionStockDAO extends DAO{
	
	/**
	 * Ejecuta Proceso para la Asignacion de Stocks
	 */
	public void executeAsignacionStock(Map params);

}