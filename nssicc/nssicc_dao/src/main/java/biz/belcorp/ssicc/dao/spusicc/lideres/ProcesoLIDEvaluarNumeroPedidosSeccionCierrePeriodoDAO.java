package biz.belcorp.ssicc.dao.spusicc.lideres;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoDAO
		extends DAO {

	/**
	 * Proceso que Evalua numero de Pedidos x Seccion al cierre de Periodo
	 * 
	 * @param params
	 */
	public void executeEvaluarNumeroPedidosSeccionCierrePeriodo(Map params);

}
