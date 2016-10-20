package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDOrigenChequeoDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.OrigenChequeo;

@Repository("spusicc.pedidos.mantenimientoPEDOrigenChequeoDAO")
public class MantenimientoPEDOrigenChequeoDAOiBatis extends BaseDAOiBatis implements MantenimientoPEDOrigenChequeoDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDOrigenPedidosDAO#getOrigenPedidoList()
	 */
	public List<OrigenChequeo> getOrigenChequeoList() {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getOrigenChequeo");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDOrigenPedidosDAO#updateOrigenChequeo(java.util.Map)
	 */
	public void updateOrigenChequeo(Map<String, Object> criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateOrigenChequeo",criteria);
	}

}
