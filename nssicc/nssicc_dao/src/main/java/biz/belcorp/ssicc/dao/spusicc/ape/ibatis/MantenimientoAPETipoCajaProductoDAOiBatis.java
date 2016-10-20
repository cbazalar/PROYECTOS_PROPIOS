package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPETipoCajaProductoDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.TipoCajaProducto;

/**
 * @author Jose Luis Rodriguez
 *
 */
@Repository("spusicc.mantenimientoAPETipoCajaProductoDAO")
public class MantenimientoAPETipoCajaProductoDAOiBatis extends BaseDAOiBatis implements MantenimientoAPETipoCajaProductoDAO{
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoCajaProductoDAO#getTipoCajaProdList(java.util.Map)
	 */
	public List getTipoCajaProdList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getTipoCajaProdList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoCajaProductoDAO#getTipoCajaProductoObject(java.util.Map)
	 */
	public TipoCajaProducto getTipoCajaProductoObject(Map criteria){
		return (TipoCajaProducto)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getTipoCajaProductoObject", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoCajaProductoDAO#insertTipoCajaProducto(java.util.Map)
	 */
	public void insertTipoCajaProducto(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertTipoCajaProducto",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoCajaProductoDAO#getExisteCodTipoCajaProducto(java.util.Map)
	 */
	public int getExisteCodTipoCajaProducto(Map criteria){
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getExisteCodTipoCajaProducto", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoCajaProductoDAO#getNextOidTipoCajaProducto(java.util.Map)
	 */
	public int getNextOidTipoCajaProducto(Map criteria){
		return(Integer)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getNextOidTipoCajaProducto", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoCajaProductoDAO#deleteTipoCajaProducto(java.util.Map)
	 */
	public void deleteTipoCajaProducto(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteTipoCajaProducto", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoCajaProductoDAO#executeValidarRegistrosTipoCajaProducto(java.util.Map)
	 */
	public void executeValidarRegistrosTipoCajaProducto(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.executeValidarRegistrosTipoCajaProducto", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETipoCajaProductoDAO#deleteIdiomaTipoCajaProducto(java.util.Map)
	 */
	public void deleteIdiomaTipoCajaProducto(Map criteria){
		getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteIdiomaTipoCajaProducto", criteria);
	}
}