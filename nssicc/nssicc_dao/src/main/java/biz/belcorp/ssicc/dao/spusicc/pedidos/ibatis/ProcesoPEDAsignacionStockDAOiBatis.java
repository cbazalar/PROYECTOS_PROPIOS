package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDAsignacionStockDAO;

/**
 * @author peextjrios
 */
@Repository("spusicc.pedidos.procesoPEDAsignacionStockDAO")
public class ProcesoPEDAsignacionStockDAOiBatis extends BaseDAOiBatis implements ProcesoPEDAsignacionStockDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDReemplazoProdImpNacDAO#executeReemplazoProdImpNac(java.util.Map)
	 */
	public void executeAsignacionStock(Map params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeAsignacionStock", params);
	}

}