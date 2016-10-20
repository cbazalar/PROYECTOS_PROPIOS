package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDTipoChequeoDAO;

@Repository("spusicc.pedidos.mantenimientoPEDTipoChequeoDAO")
public class MantenimientoPEDTipoChequeoDAOiBatis extends BaseDAOiBatis implements MantenimientoPEDTipoChequeoDAO{
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDTipoChequeoDAO#getTipoChequeoAll()
	 */
	public List getTipoChequeoAll(){
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getTipoChequeo");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDTipoChequeoDAO#updateTipoChequeo(java.util.Map)
	 */
	public void updateTipoChequeo(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateTipoChequeo", criteria);
	}
}