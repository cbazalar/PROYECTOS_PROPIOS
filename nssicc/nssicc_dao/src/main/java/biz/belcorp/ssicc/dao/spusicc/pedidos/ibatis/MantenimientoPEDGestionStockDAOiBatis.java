/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDGestionStockDAO;

/**
 * @author jvelasquez
 *
 */
@Repository("spusicc.mantenimientoPEDGestionStockDAO")
public class MantenimientoPEDGestionStockDAOiBatis extends BaseDAOiBatis
		implements MantenimientoPEDGestionStockDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDGestionStockDAO#getGestionStockList(java.util.Map)
	 */
	public List getGestionStockList(Map criteria) {
		return getSqlMapClientTemplate().
				queryForList("spusicc.pedidos.PedidosSQL.getGestionStockList",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDGestionStockDAO#insertGestionStock(java.util.Map)
	 */
	public void insertGestionStock(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertGestionStock",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDGestionStockDAO#getValidarCuvExistencia(java.util.Map)
	 */
	public List getValidarCuvExistencia(Map criteria) {
		return getSqlMapClientTemplate().
				queryForList("spusicc.pedidos.PedidosSQL.getValidarCuvExistencia",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDGestionStockDAO#getOidTipoCliente(java.util.Map)
	 */
	public int getOidTipoCliente(Map criteria) {
		return (Integer)getSqlMapClientTemplate().
				queryForObject("spusicc.pedidos.PedidosSQL.getOidTipoCliente",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDGestionStockDAO#getOidSubTipoCliente(java.util.Map)
	 */
	public int getOidSubTipoCliente(Map criteria) {
		return (Integer)getSqlMapClientTemplate().
				queryForObject("spusicc.pedidos.PedidosSQL.getOidSubTipoCliente",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDGestionStockDAO#getOidTipoClasificacion(java.util.Map)
	 */
	public int getOidTipoClasificacion(Map criteria) {
		return (Integer)getSqlMapClientTemplate().
				queryForObject("spusicc.pedidos.PedidosSQL.getOidTipoClasificacion",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDGestionStockDAO#getOidClasificacion(java.util.Map)
	 */
	public int getOidClasificacion(Map criteria) {
		return (Integer)getSqlMapClientTemplate().
				queryForObject("spusicc.pedidos.PedidosSQL.getOidClasificacion",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDGestionStockDAO#getValidaGestionStockUk(java.util.Map)
	 */
	public int getValidaGestionStockUk(Map criteria) {
		return (Integer)getSqlMapClientTemplate().
				queryForObject("spusicc.pedidos.PedidosSQL.getValidaGestionStockUk",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDGestionStockDAO#getValidaRegionxZona(java.util.Map)
	 */
	public int getValidaRegionxZona(Map criteria) {
		return (Integer)getSqlMapClientTemplate().
				queryForObject("spusicc.pedidos.PedidosSQL.getValidaRegionxZona",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDGestionStockDAO#updateDesactivarGestionStock(java.util.Map)
	 */
	public void updateDesactivarGestionStock(Map criteria) {
		log.debug("updateDesactivarGestionStock");
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateDesactivarGestionStock",criteria);
		log.debug("Insertar en Auditoria");
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertGestionStockAuditoria",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDGestionStockDAO#updateActivarGestionStock(java.util.Map)
	 */
	public void updateActivarGestionStock(Map criteria) {
		log.debug("updateActivarGestionStock");
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateActivarGestionStock",criteria);
		log.debug("Insertar en Auditoria");
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertGestionStockAuditoria",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDGestionStockDAO#getValidarSapExistencia(java.util.Map)
	 */
	public List getValidarSapExistencia(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getValidarSapExistencia",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDGestionStockDAO#getIndicadorEliminacionCodigoSAC(java.util.Map)
	 */
	public String getIndicadorEliminacionCodigoSAC(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getIndicadorEliminacionCodigoSAC",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDGestionStockDAO#deleteGestionStock(java.util.Map)
	 */
	public void deleteGestionStock(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteGestionStock",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDGestionStockDAO#updateEliminarGestionStock(java.util.Map)
	 */
	public void updateEliminarGestionStock(Map criteria) {
		log.debug("Insertar en Auditoria");
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertGestionStockAuditoria",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDGestionStockDAO#deleteEliminarGestionStock(java.util.Map)
	 */
	public void deleteEliminarGestionStock(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteEliminarGestionStock",criteria);
	}
	
}
