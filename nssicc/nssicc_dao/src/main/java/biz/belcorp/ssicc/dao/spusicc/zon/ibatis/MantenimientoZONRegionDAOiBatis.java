package biz.belcorp.ssicc.dao.spusicc.zon.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONRegionDAO;

/**
 * @author Jesse James Rios Franco
 */
@Repository("spusicc.mantenimientoZONRegionDAO")
public class MantenimientoZONRegionDAOiBatis extends BaseDAOiBatis implements MantenimientoZONRegionDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONRegionDAO#getRegionesList(java.util.Map)
	 */
	public List getRegionesList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getRegionesList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONRegionDAO#deleteRegion(java.util.Map)
	 */
	public void deleteRegion(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.deleteRegion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONRegionDAO#getZonasIndActivasByOidRegion(java.lang.Integer)
	 */
	public List getZonasIndActivasByOidRegion(Integer oidRegion) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getZonasIndActivasByOidRegion", oidRegion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONRegionDAO#getRegion(java.lang.Integer)
	 */
	public Map getRegion(Integer oidRegion) {
		return (Map)getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getRegion", oidRegion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONRegionDAO#getEncuentraRegionByCodigoRegion(java.lang.String)
	 */
	public Integer getEncuentraRegionByCodigoRegion(String codigoRegionRegistro) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getEncuentraRegionByCodigoRegion", codigoRegionRegistro);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONRegionDAO#insertRegion(java.util.Map)
	 */
	public void insertRegion(Map params) {
		getSqlMapClientTemplate().insert("spusicc.zon.MantenimientoZONSQL.insertRegion", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONRegionDAO#updateRegion(java.util.Map)
	 */
	public void updateRegion(Map params) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.updateRegion", params);
	}

	public void deleteRegionFisicamente(Integer oidRegion) {
		getSqlMapClientTemplate().delete("spusicc.zon.MantenimientoZONSQL.deleteRegionFisicamente",oidRegion);
	}
}