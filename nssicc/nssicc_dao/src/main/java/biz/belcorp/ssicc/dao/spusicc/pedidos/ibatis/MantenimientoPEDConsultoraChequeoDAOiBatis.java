package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDConsultoraChequeoDAO;

/**
 * @author Jos Luis Rodrguez
 */
@Repository("spusicc.mantenimientoPEDConsultoraChequeoDAO")
public class MantenimientoPEDConsultoraChequeoDAOiBatis extends BaseDAOiBatis implements MantenimientoPEDConsultoraChequeoDAO{


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConsultoraChequeoDAO#getTipoChequeoPais(java.util.Map)
	 */
	public List getTipoChequeoPais(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getTipoChequeoPais",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConsultoraChequeoDAO#insertConsultoraChequear(java.util.Map)
	 */
	public void insertConsultoraChequear(Map criteria){
		getSqlMapClientTemplate().insert(
				"spusicc.pedidos.PedidosSQL.insertConsultoraChequear", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConsultoraChequeoDAO#deleteConsultoraChequear(java.util.Map)
	 */
	public void deleteConsultoraChequear(Map criteria){
		getSqlMapClientTemplate().insert(
				"spusicc.pedidos.PedidosSQL.deleteConsultoraChequear", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConsultoraChequeoDAO#getConsultoraChequear(java.util.Map)
	 */
	public List getConsultoraChequear(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getConsultoraChequear",criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDConsultoraChequeoDAO#getExisteConsultoraChequear(java.util.Map)
	 */
	public int getExisteConsultoraChequear(Map criteria){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getExisteConsultoraChequear", criteria);
	}
}
