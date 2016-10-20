package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPREActualizarTipoCambioDAO;

/**
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 *
 */
@Repository("spusicc.procesoPREActualizarTipoCambioDAO")
public class ProcesoPREActualizarTipoCambioDAOiBatis extends BaseDAOiBatis implements ProcesoPREActualizarTipoCambioDAO
{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPREActualizarTipoCambioDAO#updateTipoCambio(java.util.Map)
	 */
	@Override
	public void updateTipoCambio(Map<String, Object> params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateTipoCambio", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPREActualizarTipoCambioDAO#updateTipoCambioConcursos(java.util.Map)
	 */
	@Override
	public void updateTipoCambioConcursos(Map<String, Object> params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateTipoCambioConcursos", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPREActualizarTipoCambioDAO#updateTipoCambioNX(java.util.Map)
	 */
	@Override
	public void updateTipoCambioNX(Map<String, Object> params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateTipoCambioNX", params);
	}

}
