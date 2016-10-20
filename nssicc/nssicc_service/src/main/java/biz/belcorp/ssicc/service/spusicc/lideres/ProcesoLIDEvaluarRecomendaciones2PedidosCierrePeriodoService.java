package biz.belcorp.ssicc.service.spusicc.lideres;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

public interface ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoService extends Service {

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