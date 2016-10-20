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
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDConfiguracionOfertasPorConcursosDAO;

/**
 * @author Sigcomt
 *
 */
@Repository("spusicc.mantenimientoPEDConfiguracionOfertasPorConcursosDAO")
public class MantenimientoPEDConfiguracionOfertasPorConcursosDAOiBatis extends
		BaseDAOiBatis implements
		MantenimientoPEDConfiguracionOfertasPorConcursosDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorConcursosDAO#getOfertaConcursosList(java.util.Map)
	 */
	public List getOfertaConcursosList(Map params) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getOfertaConcursosList", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorConcursosDAO#insertOfertaConcursos(java.util.Map)
	 */
	public void insertOfertaConcursos(Map params) {
		String oidOferta = ((Long)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getOidOfertaPorConcursos")).toString();
		params.put("oidOferta", oidOferta);
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertOfertaPorConcursos", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorConcursosDAO#insertRangoOfertaConcursos(java.util.Map)
	 */
	public void insertRangoOfertaConcursos(Map rango) {
		String oidRango = ((Long)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getOidRangoOfertaConcursos")).toString();
		rango.put("oidRango", oidRango);
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertRangoOfertaConcursos", rango);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorConcursosDAO#removeRangoOfertaConcursos(java.lang.String)
	 */
	public void removeRangoOfertaConcursos(String oidRango, String codigoUsuario) {
		Map criteria = new HashMap();
		criteria.put("oidRango", oidRango);
		criteria.put("codigoUsuario", codigoUsuario);
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertRangoGratisOfertaConcursosElim", criteria);
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertRangoOfertaConcursosElim", criteria);
		
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteRangoGratisOfertaConcursos", oidRango);
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteRangoOfertaConcursos", oidRango);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorConcursosDAO#getRangoOfertaConcursosList(java.util.Map)
	 */
	public List getRangoOfertaConcursosList(Map params) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getRangoOfertaConcursosList", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorConcursosDAO#getRangoGratisOfertaConcursosList(java.util.Map)
	 */
	public List getRangoGratisOfertaConcursosList(Map params) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getRangoGratisOfertaConcursosList", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorConcursosDAO#getCuvs(java.util.Map)
	 */
	public List getCuvs(Map params) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getCuvs", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorConcursosDAO#insertRangoGratisOfertaConcursos(java.util.Map)
	 */
	public void insertRangoGratisOfertaConcursos(Map regalo) {
		String oidGratis = ((Long)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getOidRangoGratisOfertaConcursos")).toString();
		regalo.put("oidGratis", oidGratis);
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertRangoGratisOfertaConcursos", regalo);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorConcursosDAO#removeRangoGratisOfertaConcursos(java.lang.String)
	 */
	public void removeRangoGratisOfertaConcursos(String oidGratis, String codigoUsuario) {
		Map criteria = new HashMap();
		criteria.put("oidGratis", oidGratis);
		criteria.put("codigoUsuario", codigoUsuario);
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertByOidRangoGratisOfertaConcursosElim", criteria);
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteByOidRangoGratisOfertaConcursos", oidGratis);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorConcursosDAO#insertCriterioOfertaConcursos(java.util.Map)
	 */
	public void insertCriterioOfertaConcursos(Map criterio) {
		String oidCriterio = ((Long)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getOidCriterioOfertaConcursos")).toString();
		criterio.put("oidCriterio", oidCriterio);		
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertCriterioOfertaConcursos", criterio);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorConcursosDAO#removeCriterioOfertaConcursos(java.lang.String)
	 */
	public void removeCriterioOfertaConcursos(String oidCriterio, String codigoUsuario) {
		Map criteria = new HashMap();
		criteria.put("oidCriterio", oidCriterio);
		criteria.put("codigoUsuario", codigoUsuario);
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertCriterioOfertaConcursosElim", criteria);
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteCriterioOfertaConcursos", oidCriterio);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorConcursosDAO#getProductoOfertaConcursosList(java.util.Map)
	 */
	public List getProductoOfertaConcursosList(Map params) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getProductoOfertaConcursosList", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorConcursosDAO#getProductoOfertaConcursosList1(java.util.Map)
	 */
	public List getProductoOfertaConcursosList1(Map params) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getProductoOfertaConcursosList1", params);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorConcursosDAO#insertProductoOfertaConcursos(java.util.Map)
	 */
	public void insertProductoOfertaConcursos(Map producto) {
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertProductoOfertaConcursos", producto);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorConcursosDAO#deleteProductosOfertaConcursos(java.lang.String)
	 */
	public void deleteProductosOfertaConcursos(String oidOferta, String codigoUsuario) {
		Map criteria = new HashMap();
		criteria.put("oidOferta", oidOferta);
		criteria.put("codigoUsuario", codigoUsuario);
		this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertProductosOfertaConcursosElim", criteria);
		
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteProductosOfertaConcursos", oidOferta);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorConcursosDAO#getCriterioOfertaConcursosList(java.lang.String)
	 */
	public List getCriterioOfertaConcursosList(String oidOferta) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getCriterioOfertaConcursosList", oidOferta);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorConcursosDAO#removeOfertaConcursos(java.lang.String)
	 */
	public void removeOfertaConcursos(String oidOferta, String codigoUsuario) {
		Map params = new HashMap();
		params.put("oidOferta", oidOferta);
		params.put("codigoUsuario", codigoUsuario);
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.insertOfertaConcursosElim", params);
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.insertAllCriteriosOfertaConcursosElim", params);
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.insertAllRangosOfertaConcursosElim", params);
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.insertAllGratisOfertaConcursosElim", params);
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.insertAllProductosOfertaConcursosElim", params);
		
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteAllGratisOfertaConcursos", oidOferta);
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteAllRangosOfertaConcursos", oidOferta);
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteAllCriteriosOfertaConcursos", oidOferta);
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteAllProductosOfertaConcursos", oidOferta);
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteOfertaConcursos", oidOferta);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorConcursosDAO#getMoneda(java.util.Map)
	 */
	public Map getMoneda(Map params) {
		return (Map)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getMoneda", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoPEDConfiguracionOfertasPorConcursosDAO#updateOfertaConcursos(java.util.Map)
	 */
	public void updateOfertaConcursos(Map params) {
		this.getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.updateOfertaConcursos", params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDConfiguracionOfertasPorConcursosDAO#updateRangoOfertaConcursos(java.util.Map)
	 */
	public void updateRangoOfertaConcursos(Map params) {
		String sprecioUpdate = (String)params.get("precioUnitario");
		BigDecimal precioUpdate = new BigDecimal(sprecioUpdate);
		BigDecimal precio = (BigDecimal)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getPrecioRangoOfertaConcursos", params);
		if (precio.compareTo(precioUpdate) != 0) {
			this.getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertRangoOfertaConcursosElim", params);
		}
		this.getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateRangoOfertaConcursos", params);
	}
	
	
}
