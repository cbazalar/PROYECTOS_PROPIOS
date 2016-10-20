package biz.belcorp.ssicc.dao.spusicc.lideres;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

public interface ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoDAO extends DAO {

	/**
	 * Metodo que evalua Recomendaciones de 2 pedidos al Cierre de la region
	 * @param params
	 */
	public void executeEvaluarRecomendaciones2PedidosCierrePeriodo(Map params);

	/**
	 * Metodo que evalua Recomendaciones de 2 pedidos al Cierre de la region
	 * @param params
	 */
	public void executeEvaluarRecomendaciones2PedidosCierreRegion(Map params);

}