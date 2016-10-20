package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author peextjrios
 */

public interface ProcesoPEDReemplazoProdImpNacService extends Service{
	
	/**
	 * Ejecuta Proceso para la Actualizacion de Codigos de Productos importados por Nacionales
	 */
	public void executeReemplazoProdImpNac(Map params);

	/**
	 * @param params
	 * @return
	 */
	public String getIndicadorPedidosFacturadosPeriodo(Map params);

	/**
	 * @param params
	 * @return
	 */
	public String getRegistrosProcesadosReemplazoProdImpNac(Map params);
}
