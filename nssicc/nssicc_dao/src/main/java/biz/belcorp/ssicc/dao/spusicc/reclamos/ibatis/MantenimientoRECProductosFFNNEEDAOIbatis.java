/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECProductosFFNNEEDAO;


/**
 * @author Cristhian Roman
 * 
 */
@Repository("spusicc.mantenimientoRECProductosFFNNEEDAO")
public class MantenimientoRECProductosFFNNEEDAOIbatis extends		BaseDAOiBatis implements MantenimientoRECProductosFFNNEEDAO {

	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECProductosFFNNEEDAO#getProductosFFNNEEList(java.util.Map)
	 */
	public List getProductosFFNNEEList(Map map){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getProductosFFNNEEList",map);
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECProductosFFNNEEDAO#getDescripcionProducto(java.util.Map)
	 */
	public String getDescripcionProducto(Map map){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getDescripcionProductoSAP",map);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECProductosFFNNEEDAO#getOidProducto(java.util.Map)
	 */
	public String getOidProducto(Map map){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getOidProducto",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECProductosFFNNEEDAO#deleteProductosFFNNEE(java.util.Map)
	 */
	public void deleteProductosFFNNEE(Map map){
		getSqlMapClientTemplate().delete(
                "spusicc.reclamos.ReclamosSQL.deleteProductosFFNNEE", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECProductosFFNNEEDAO#insertProductosFFNNEE(java.util.Map)
	 */
	public void insertProductosFFNNEE(Map map){
		getSqlMapClientTemplate().insert(
				"spusicc.reclamos.ReclamosSQL.insertProductosFFNNEE", map);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECProductosFFNNEEDAO#validaProductoFFNNEE(java.util.Map)
	 */
	public String validaProductoFFNNEE(Map map){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.validaProductoFFNNEE",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECProductosFFNNEEDAO#obtenerPathUpload(java.lang.String)
	 */
	public String obtenerPathUpload(String codigoPais) {
		return (String) getSqlMapClientTemplate().
					queryForObject("spusicc.reclamos.ReclamosSQL.getPathUpload", codigoPais);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECProductosFFNNEEDAO#getCodigoCUVFicticioProducto(java.util.Map)
	 */
	public String getCodigoCUVFicticioProducto(Map map){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getCodigoCUVFicticioProducto",map);
	}
}
