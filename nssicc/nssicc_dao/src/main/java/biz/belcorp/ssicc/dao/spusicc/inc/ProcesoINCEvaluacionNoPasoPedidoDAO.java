package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCEvaluacionNoPasoPedidoDAO
		extends DAO {

	/**
	 * Proceso que evalua si la consultora no paso pedido en un rango de periodos
	 * 
	 * @param params
	 */
	public void executeEvaluacionNoPasoPedido(Map params);

}

