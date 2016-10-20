package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDMontoMinimoDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.MontoMinimo;

@Repository("spusicc.mantenimientoPEDMontoMinimoDAO")
public class MantenimientoPEDMontoMinimoDAOiBatis extends BaseDAOiBatis implements MantenimientoPEDMontoMinimoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMinimoDAO#getTipoSolicitud()
	 */
	public List getTipoSolicitud() {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getTipoSolicitud");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMinimoDAO#getMontominimoList(java.util.Map)
	 */
	public List getMontominimoList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getMontominimoList",criteria);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMinimoDAO#getMontoMinimoObject(java.util.Map)
	 */
	public MontoMinimo getMontoMinimoObject(Map criteria){
		return (MontoMinimo)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getMontoMinimoObject", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMinimoDAO#insertMontoMinimo(java.util.Map)
	 */
	public void insertMontoMinimo(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertMontoMinimo",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMinimoDAO#updateMontoMinimo(java.util.Map)
	 */
	public void updateMontoMinimo(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateMontoMinimo",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMinimoDAO#deleteMontoMinimo(java.util.Map)
	 */
	public void deleteMontoMinimo(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteMontoMinimo",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMinimoDAO#getIndicadorActualizaMontominimo(java.util.Map)
	 */
	public String getIndicadorActualizaMontominimo(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getIndicadorActualizaMontominimo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMinimoDAO#updateErrorMontoMinimo(java.util.Map)
	 */
	public void updateErrorMontoMinimo(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateErrorMontoMinimo",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMinimoDAO#insertAuditoriaMontoMinimo(java.util.Map)
	 */
	public void insertAuditoriaMontoMinimo(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertAuditoriaMontoMinimo",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMinimoDAO#getNextOidMontoMinimo(java.util.Map)
	 */
	public int getNextOidMontoMinimo(Map criteria){
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getNextOidMontoMinimo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDMontoMinimoDAO#getMontominimoHistoricoList(java.util.Map)
	 */
	public List getMontominimoHistoricoList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getMontominimoHistoricoList", criteria);
	}
}