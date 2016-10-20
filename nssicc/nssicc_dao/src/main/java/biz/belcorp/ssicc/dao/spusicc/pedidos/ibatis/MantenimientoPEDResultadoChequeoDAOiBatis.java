package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDResultadoChequeoDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ResultadoChequeo;

/**
 * @author Jesse J. Rios Franco
 *
 */
@Repository("spusicc.pedidos.mantenimientoPEDResultadoChequeoDAO")
public class MantenimientoPEDResultadoChequeoDAOiBatis extends BaseDAOiBatis implements MantenimientoPEDResultadoChequeoDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDResultadoChequeoDAO#getResultadosChequeoList(java.util.Map)
	 */
	public List getResultadosChequeoList(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getResultadosChequeoList",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDResultadoChequeoDAO#deleteResultadosChequeo(java.util.Map)
	 */
	public void deleteResultadosChequeo(Map map) {
		getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteResultadosChequeo",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDResultadoChequeoDAO#getResultadoChequeoObject(java.lang.String)
	 */
	public ResultadoChequeo getResultadoChequeoObject(String id) {
		return (ResultadoChequeo)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getResultadoChequeoObject",id);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDResultadoChequeoDAO#insertResultadoChequeo(java.util.Map)
	 */
	public void insertResultadoChequeo(Map params) {
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertResultadoChequeo",params);
	}

	public void updateResultadoChequeo(Map params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateResultadoChequeo",params);
	}
}