package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDReemplazoProdImpNacDAO;

/**
 * @author peextjrios
 */
@Repository("spusicc.pedidos.procesoPEDReemplazoProdImpNacDAO")
public class ProcesoPEDReemplazoProdImpNacDAOiBatis extends BaseDAOiBatis implements ProcesoPEDReemplazoProdImpNacDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDReemplazoProdImpNacDAO#executeReemplazoProdImpNac(java.util.Map)
	 */
	public void executeReemplazoProdImpNac(Map params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeReemplazoProdImpNac", params);
	}

	public String getIndicadorPedidosFacturadosPeriodo(Map params) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getIndicadorPedidosFacturadosPeriodo", params);
		
	}

	public String getRegistrosProcesadosReemplazoProdImpNac(Map params) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getRegistrosProcesadosReemplazoProdImpNac", params);
	}
}