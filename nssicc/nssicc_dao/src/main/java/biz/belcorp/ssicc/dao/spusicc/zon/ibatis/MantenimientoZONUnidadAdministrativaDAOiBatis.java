package biz.belcorp.ssicc.dao.spusicc.zon.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONUnidadAdministrativaDAO;

/**
 * @author Jesse James Rios Franco
 */
@Repository("spusicc.mantenimientoZONUnidadAdministrativaDAO")
public class MantenimientoZONUnidadAdministrativaDAOiBatis extends BaseDAOiBatis implements MantenimientoZONUnidadAdministrativaDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONUnidadAdministrativaDAO#getRegionesList(java.util.Map)
	 */
	public List getRegionesList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getRegionesList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONUnidadAdministrativaDAO#getRegionesZonasList(java.util.Map)
	 */
	public List getRegionesZonasList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getRegionesZonasList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONRegionDAO#getEncuentraZonaByCodigoZona(java.util.Map)
	 */
	public Integer getEncuentraZonaByCodigoZona(Map params) {
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getEncuentraZonaByCodigoZona", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONUnidadAdministrativaDAO#insertZona(java.util.Map)
	 */
	public void insertZona(Map params) {
		getSqlMapClientTemplate().insert("spusicc.zon.MantenimientoZONSQL.insertZona", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONUnidadAdministrativaDAO#updateZona(java.util.Map)
	 */
	public void updateZona(Map params) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.updateZona", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONUnidadAdministrativaDAO#getUltimoCodigoZona(java.util.Map)
	 */
	public int getUltimoCodigoZona(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getUltimoCodigoZona", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONUnidadAdministrativaDAO#getIndicadorEliminarRegion(java.util.Map)
	 */
	public int getIndicadorEliminarRegion(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getIndicadorEliminarRegion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONUnidadAdministrativaDAO#getIndicadorEliminarZona(java.util.Map)
	 */
	public int getIndicadorEliminarZona(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getIndicadorEliminarZona", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONUnidadAdministrativaDAO#deleteRegion(java.util.Map)
	 */
	public void removeRegion(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.removeRegion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONUnidadAdministrativaDAO#removeZona(java.util.Map)
	 */
	public void removeZona(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.removeZona", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONUnidadAdministrativaDAO#getZona(java.lang.Integer)
	 */
	public Map getZona(Integer oidZona) {
		return (Map)getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getZona", oidZona);
	}
}