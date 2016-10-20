package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;


/**
 * @author Jose Cariampoma
 */

public interface ProcesoPEDAsignacionStockService extends Service{
	
	/**
	 * Ejecuta Proceso para la Actualizacion de Codigos de Productos importados por Nacionales
	 */
	public void executeAsignacionStock(Map params);
	
}
