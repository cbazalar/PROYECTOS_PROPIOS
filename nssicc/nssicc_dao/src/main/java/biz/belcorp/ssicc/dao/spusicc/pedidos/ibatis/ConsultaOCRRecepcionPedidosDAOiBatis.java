package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ConsultaOCRRecepcionPedidosDAO;

@Repository("spusicc.pedidos.consultaOCRRecepcionPedidosDAO")
public class ConsultaOCRRecepcionPedidosDAOiBatis extends
		BaseDAOiBatis implements ConsultaOCRRecepcionPedidosDAO {
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ConsultaOCRRecepcionPedidosDAO#getListaRecepcionPedidosActual(java.util.Map)
	 */
	public List getListaRecepcionPedidosActual(Map criteria) {
        return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getListaRecepcionPedidosActual",
				criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ConsultaOCRRecepcionPedidosDAO#getListaRecepcionPedidosHistorico(java.util.Map)
	 */
	public List getListaRecepcionPedidosHistorico(Map criteria) {
        return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getListaRecepcionPedidosHistorico",
				criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ConsultaOCRRecepcionPedidosDAO#getDetalleRecepcionPedidosHistorico(java.util.Map)
	 */
	public List getDetalleRecepcionPedidosHistorico(Map criteria) {
        return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getDetalleRecepcionPedidosHistorico",
				criteria);
	}
	
	
	public List getDetalleRecepcionPedidosActual(Map criteria) {
        return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getDetalleRecepcionPedidosActual",
				criteria);
	}
	
	
	
}