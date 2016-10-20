package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEControlBalanceoDAO;

/**
 * @author Jose Luis Rodriguez
 */
@Repository("spusicc.mantenimientoAPEControlBalanceoDAO")
public class MantenimientoAPEControlBalanceoDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEControlBalanceoDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEControlBalanceoDAO#getControlBalanceoList(java.util.Map)
	 */
	public List getControlBalanceoList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getControlBalanceoList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEControlBalanceoDAO#getMapaCentroDistbyLineaList(java.util.Map)
	 */
	public List getMapaCentroDistbyLineaList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getMapaCentroDistbyLineaList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEControlBalanceoDAO#getOidAsignacionProductoAnaquel(java.util.Map)
	 */
	public String getOidAsignacionProductoAnaquel(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidAsignacionProductoAnaquel", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEControlBalanceoDAO#getDescMapaZonaCabec(java.util.Map)
	 */
	public String getDescMapaZonaCabec(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getDescMapaZonaCabec", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEControlBalanceoDAO#getDescMapaCentroCabec(java.util.Map)
	 */
	public String getDescMapaCentroCabec(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getDescMapaCentroCabec", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEControlBalanceoDAO#executeBalanceoLinea(java.util.Map)
	 */
	public void executeBalanceoLinea(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.executeBalanceoLinea", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEControlBalanceoDAO#validaBalanceoLinea(java.util.Map)
	 */
	public int validaBalanceoLinea(Map criteria){
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.validaBalanceoLinea", criteria);
	}
}