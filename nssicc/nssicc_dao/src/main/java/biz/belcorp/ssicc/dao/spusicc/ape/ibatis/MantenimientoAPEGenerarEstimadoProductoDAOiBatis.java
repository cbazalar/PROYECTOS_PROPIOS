package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEGenerarEstimadoProductoDAO;

/**
 * @author Jose Luis Rodriguez
 *
 */
@Repository("spusicc.mantenimientoAPEGenerarEstimadoProductoDAO")
public class MantenimientoAPEGenerarEstimadoProductoDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEGenerarEstimadoProductoDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEGenerarEstimadoProductoDAO#executeGenerarEstimadoProducto(java.util.Map)
	 */
	public void executeGenerarEstimadoProducto(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.executeGenerarEstimadoProducto", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEGenerarEstimadoProductoDAO#validaExisteGeneraEstimado(java.util.Map)
	 */
	public String validaExisteGeneraEstimado(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.validaExisteGeneraEstimado", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEGenerarEstimadoProductoDAO#executeGenerarOlas(java.util.Map)
	 */
	public void executeGenerarOlas(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.executeGenerarOlas", criteria);
	}
}