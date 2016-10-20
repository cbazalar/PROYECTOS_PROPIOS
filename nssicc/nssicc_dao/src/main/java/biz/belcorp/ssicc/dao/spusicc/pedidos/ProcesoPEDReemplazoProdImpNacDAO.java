package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextjrios
 */

public interface ProcesoPEDReemplazoProdImpNacDAO extends DAO{
	
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