/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO;

/**
 * @author Sigcomt
 *
 */
@Repository("spusicc.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO")
public class MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAOiBatis extends
		BaseDAOiBatis implements
		MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO#getOfertaFactorRepeticionList(java.util.Map)
	 */
	public List getOfertaFactorRepeticionList(Map params) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getOfertaFactorRepeticionList", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO#insertOfertaFactorRepeticion(java.util.Map)
	 */
	public void insertOfertaFactorRepeticion(Map params) {
		String oidOferta = ((Long)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getOidOfertaPorFactorRepeticion")).toString();
		params.put("oidOferta", oidOferta);
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertOfertaPorFactorRepeticion", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO#insertRangoOfertaFactorRepeticion(java.util.Map)
	 */
	public void insertRangoOfertaFactorRepeticion(Map rango) {
		String oidRango = ((Long)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getOidRangoOfertaFactorRepeticion")).toString();
		rango.put("oidRango", oidRango);
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertRangoOfertaFactorRepeticion", rango);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO#removeRangoOfertaFactorRepeticion(java.lang.String)
	 */
	public void removeRangoOfertaFactorRepeticion(String oidRango, String codigoUsuario) {
		Map criteria = new HashMap();
		criteria.put("oidRango", oidRango);
		criteria.put("codigoUsuario", codigoUsuario);
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertRangoGratisOfertaFactorRepeticionElim", criteria);
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertRangoOfertaFactorRepeticionElim", criteria);
		
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteRangoGratisOfertaFactorRepeticion", oidRango);
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteRangoOfertaFactorRepeticion", oidRango);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO#getRangoOfertaFactorRepeticionList(java.util.Map)
	 */
	public List getRangoOfertaFactorRepeticionList(Map params) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getRangoOfertaFactorRepeticionList", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO#getRangoGratisOfertaFactorRepeticionList(java.util.Map)
	 */
	public List getRangoGratisOfertaFactorRepeticionList(Map params) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getRangoGratisOfertaFactorRepeticionList", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO#getCuvs(java.util.Map)
	 */
	public List getCuvs(Map params) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getCuvs", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO#insertRangoGratisOfertaFactorRepeticion(java.util.Map)
	 */
	public void insertRangoGratisOfertaFactorRepeticion(Map regalo) {
		String oidGratis = ((Long)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getOidRangoGratisOfertaFactorRepeticion")).toString();
		regalo.put("oidGratis", oidGratis);
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertRangoGratisOfertaFactorRepeticion", regalo);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO#removeRangoGratisOfertaFactorRepeticion(java.lang.String)
	 */
	public void removeRangoGratisOfertaFactorRepeticion(String oidGratis, String codigoUsuario) {
		Map criteria = new HashMap();
		criteria.put("oidGratis", oidGratis);
		criteria.put("codigoUsuario", codigoUsuario);
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertByOidRangoGratisOfertaFactorRepeticionElim", criteria);
		
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteByOidRangoGratisOfertaFactorRepeticion", oidGratis);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO#insertCriterioOfertaFactorRepeticion(java.util.Map)
	 */
	public void insertCriterioOfertaFactorRepeticion(Map criterio) {
		String oidCriterio = ((Long)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getOidCriterioOfertaFactorRepeticion")).toString();
		criterio.put("oidCriterio", oidCriterio);		
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertCriterioOfertaFactorRepeticion", criterio);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO#removeCriterioOfertaFactorRepeticion(java.lang.String)
	 */
	public void removeCriterioOfertaFactorRepeticion(String oidCriterio, String codigoUsuario) {
		Map criteria = new HashMap();
		criteria.put("oidCriterio", oidCriterio);
		criteria.put("codigoUsuario", codigoUsuario);
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertCriterioOfertaFactorRepeticionElim", criteria);
		
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteCriterioOfertaFactorRepeticion", oidCriterio);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO#getProductoOfertaFactorRepeticionList(java.util.Map)
	 */
	public List getProductoOfertaFactorRepeticionList(Map params) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getProductoOfertaFactorRepeticionList", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO#getProductoOfertaFactorRepeticionList1(java.util.Map)
	 */
	public List getProductoOfertaFactorRepeticionList1(Map params) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getProductoOfertaFactorRepeticionList1", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO#insertProductoOfertaFactorRepeticion(java.util.Map)
	 */
	public void insertProductoOfertaFactorRepeticion(Map producto) {
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertProductoOfertaFactorRepeticion", producto);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO#deleteProductosOfertaFactorRepeticion(java.lang.String)
	 */
	public void deleteProductosOfertaFactorRepeticion(String oidOferta, String codigoUsuario) {
		Map criteria = new HashMap();
		criteria.put("oidOferta", oidOferta);
		criteria.put("codigoUsuario", codigoUsuario);
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertProductoOfertaFactorRepeticionElim", criteria);
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteProductosOfertaFactorRepeticion", oidOferta);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO#getCriterioOfertaFactorRepeticionList(java.lang.String)
	 */
	public List getCriterioOfertaFactorRepeticionList(String oidOferta) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getCriterioOfertaFactorRepeticionList", oidOferta);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO#removeOfertaFactorRepeticion(java.lang.String)
	 */
	public void removeOfertaFactorRepeticion(String oidOferta, String codigoUsuario) {
		Map params = new HashMap();
		params.put("oidOferta", oidOferta);
		params.put("codigoUsuario", codigoUsuario);
		
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.insertOfertaFactorRepeticionElim", params);
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.insertAllCriteriosOfertaFactorRepeticionElim", params);
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.insertAllRangosOfertaFactorRepeticionElim", params);
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.insertAllGratisOfertaFactorRepeticionElim", params);
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.insertAllProductosOfertaFactorRepeticionElim", params);
				
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteOfertaFactorRepeticion", oidOferta);
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteAllGratisOfertaFactorRepeticion", oidOferta);
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteAllRangosOfertaFactorRepeticion", oidOferta);
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteAllCriteriosOfertaFactorRepeticion", oidOferta);
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteAllProductosOfertaFactorRepeticion", oidOferta);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO#getMoneda(java.util.Map)
	 */
	public Map getMoneda(Map params) {
		return (Map)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getMoneda", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO#updateOfertaFactorRepeticion(java.util.Map)
	 */
	public void updateOfertaFactorRepeticion(Map params) {
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.updateOfertaFactorRepeticion", params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO#updateRangoOfertaFactorRepeticion(java.util.Map)
	 */
	public void updateRangoOfertaFactorRepeticion(Map params) {
		String sprecioUpdate = (String)params.get("precioUnitario");
		BigDecimal precioUpdate = new BigDecimal(sprecioUpdate);
		BigDecimal precio = (BigDecimal)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getPrecioRangoOfertaFactorRepeticion", params);
		if (precio.compareTo(precioUpdate) != 0) {
			this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertRangoOfertaFactorRepeticionElim", params);
		}
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.updateRangoOfertaFactorRepeticion", params);
	}
	
	

}
