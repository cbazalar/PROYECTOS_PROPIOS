package biz.belcorp.ssicc.dao.spusicc.zon.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONCallesDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class MantenimientoZONCallesDAOIbatis.
 *
 * @author Belcorp
 * @version 1.0
 * 10:10:56 AM
 */
@Repository("spusicc.mantenimientoZONCallesDAO")
public class MantenimientoZONCallesDAOIbatis extends BaseDAOiBatis implements MantenimientoZONCallesDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONCallesDAO#getDireccionesClientesList(java.util.Map)
	 */
	public List getDireccionesClientesList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getDireccionesClientesList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONCallesDAO#getIdSgteZonCalle()
	 */
	public Long getIdSgteZonCalle() {
		return (Long) getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getIdSgteZonCalle");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONCallesDAO#getOidValoEstrGeop(java.util.Map)
	 */
	public Long getOidValoEstrGeop(Map criteria) {
		return (Long) getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getOidValoEstrGeop", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONCallesDAO#insertZonCalle(java.util.Map)
	 */
	public void insertZonCalle(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.zon.MantenimientoZONSQL.insertZonCalle", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONCallesDAO#getZonCalle(java.lang.Long)
	 */
	public Map getZonCalle(Long oidCalle) {
		return (Map) getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getZonCalle", oidCalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONCallesDAO#updateZonCalle(java.util.Map)
	 */
	public void updateZonCalle(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.updateZonCalle", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONCallesDAO#deleteZonCalle(java.util.Map)
	 */
	public void deleteZonCalle(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.deleteZonCalle", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONCallesDAO#updateDireccionCliente(java.util.Map)
	 */
	public void updateDireccionCliente(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.updateDireccionCliente", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONCallesDAO#getValidaConsultoraCalle(java.util.Map)
	 */
	public String getValidaConsultoraCalle(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getValidaConsultoraCalle", criteria);
	}
}
