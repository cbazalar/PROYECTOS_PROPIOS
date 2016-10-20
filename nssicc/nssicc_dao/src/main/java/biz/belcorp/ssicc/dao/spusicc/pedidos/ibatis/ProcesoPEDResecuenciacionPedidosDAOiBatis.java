package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDResecuenciacionPedidosDAO;

/**
 * @author PEJCAIRAMPOMA
 *
 */
@Repository("spusicc.procesoPEDResecuenciacionPedidosDAO")
public class ProcesoPEDResecuenciacionPedidosDAOiBatis extends BaseDAOiBatis implements ProcesoPEDResecuenciacionPedidosDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDResecuenciacionPedidosDAO#executeResecuenciacionPedidos(java.util.Map)
	 */
	public void executeResecuenciacionPedidos(Map params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeResecuenciacionPedidos", params);
		
	}
}