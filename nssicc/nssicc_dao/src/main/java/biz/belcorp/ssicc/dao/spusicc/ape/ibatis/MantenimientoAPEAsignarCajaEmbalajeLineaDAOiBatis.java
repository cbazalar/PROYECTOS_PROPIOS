package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEAsignarCajaEmbalajeLineaDAO;
/**
 * @author Jose Luis Rodriguez
 *
 */
@Repository("spusicc.mantenimientoAPEAsignarCajaEmbalajeLineaDAO")
public class MantenimientoAPEAsignarCajaEmbalajeLineaDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEAsignarCajaEmbalajeLineaDAO{
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarCajaEmbalajeLineaDAO#getTipoCajaLineaList(java.util.Map)
	 */
	public List getTipoCajaLineaList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getTipoCajaLineaList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarCajaEmbalajeLineaDAO#deleteTipoCajaLinea(java.util.Map)
	 */
	public void deleteTipoCajaLinea(Map criteria){
		getSqlMapClientTemplate().delete("spusicc.ape.MantenimientoAPESQL.deleteTipoCajaLinea", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarCajaEmbalajeLineaDAO#insertTipoCajaLinea(java.util.Map)
	 */
	public void insertTipoCajaLinea(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertTipoCajaLinea", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarCajaEmbalajeLineaDAO#validaExisteTipoCajaLinea(java.util.Map)
	 */
	public String validaExisteTipoCajaLinea( Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.validaExisteTipoCajaLinea", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarCajaEmbalajeLineaDAO#validaMinimoTipoCajaLinea(java.util.Map)
	 */
	public int validaMinimoTipoCajaLinea( Map criteria){
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.validaMinimoTipoCajaLinea", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarCajaEmbalajeLineaDAO#getTipoCajaEmbalajeComboList(java.util.Map)
	 */
	public List getTipoCajaEmbalajeComboList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getTipoCajaEmbalajeComboList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarCajaEmbalajeLineaDAO#getOidTipoCajaEmbalbyCodigo(java.util.Map)
	 */
	public String getOidTipoCajaEmbalbyCodigo(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidTipoCajaEmbalbyCodigo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarCajaEmbalajeLineaDAO#getDescripcionLineabyOid(java.util.Map)
	 */
	public String getDescripcionLineabyOid(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getDescripcionLineabyOid", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEAsignarCajaEmbalajeLineaDAO#getDescripcionTipoCajabyOid(java.util.Map)
	 */
	public String getDescripcionTipoCajabyOid(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getDescripcionTipoCajabyOid", criteria);
	}
}