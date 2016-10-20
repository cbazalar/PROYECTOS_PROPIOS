/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.zon.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONSeccionesNoAptasDAO;
import biz.belcorp.ssicc.dao.spusicc.zon.model.SeccionNoApta;

/**
 * @author tokkto
 *
 */
@Repository("spusicc.mantenimientoZONSeccionesNoAptasDAO")
public class MantenimientoZONSeccionesNoAptasDAOiBatis extends BaseDAOiBatis
		implements MantenimientoZONSeccionesNoAptasDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONSeccionesNoAptasDAO#getSeccionByRegionZonaSeccion(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Base getSeccionByRegionZonaSeccion(String codigoRegion,
			String codigoZona, String codigoSeccion) {
		
		Map params = new HashMap();
		params.put("codigoRegion", codigoRegion);
		params.put("codigoZona", codigoZona);
		params.put("codigoSeccion", codigoSeccion);
		
		return (Base)getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getSeccionByRegionZonaSeccion", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONSeccionesNoAptasDAO#getZonaByRegionZona(java.lang.String, java.lang.String)
	 */
	public Base getZonaByRegionZona(String codigoRegion, String codigoZona) {
		Map params = new HashMap();
		params.put("codigoRegion", codigoRegion);
		params.put("codigoZona", codigoZona);
		
		return (Base)getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getZonaByRegionZona", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONSeccionesNoAptasDAO#insertSeccionNoApta(biz.belcorp.ssicc.spusicc.zon.model.SeccionNoApta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertSeccionNoApta(SeccionNoApta seccion, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.zon.MantenimientoZONSQL.insertSeccionNoApta", seccion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONSeccionesNoAptasDAO#getSeccionesByRegionZona(java.lang.String, java.lang.String)
	 */
	public List getSeccionesByRegionZona(String codigoRegion, String codigoZona) {
		Map params = new HashMap();
		params.put("codigoRegion", codigoRegion);
		params.put("codigoZona", codigoZona);
		
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getSeccionesByRegionZona", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONSeccionesNoAptasDAO#deleteSeccionNoApta()
	 */
	public void deleteSeccionNoApta() {
		getSqlMapClientTemplate().delete("spusicc.zon.MantenimientoZONSQL.deleteSeccionNoApta", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONSeccionesNoAptasDAO#getSeccionNoApta(java.lang.String, java.lang.String, java.lang.String)
	 */
	public SeccionNoApta getSeccionNoApta(String codigoRegion,
			String codigoZona, String codigoSeccion) {
		Map params = new HashMap();
		params.put("codigoRegion", codigoRegion);
		params.put("codigoZona", codigoZona);
		params.put("codigoSeccion", codigoSeccion);
		
		return (SeccionNoApta)getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getSeccionNoApta", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONSeccionesNoAptasDAO#getSeccionesAptasNoAptasByCriteria(java.util.Map)
	 */
	public List getSeccionesAptasNoAptasByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getSeccionesAptasNoAptasByCriteria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONSeccionesNoAptasDAO#deleteSeccionNoAptaById(java.lang.String)
	 */
	public void deleteSeccionNoAptaById(String oidSeccionNoApta) {
		getSqlMapClientTemplate().delete("spusicc.zon.MantenimientoZONSQL.deleteSeccionNoAptaById", oidSeccionNoApta);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONSeccionesNoAptasDAO#deleteSeccionNoAptaById(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void deleteSeccionNoAptaById(String codigoRegion, String codigoZona,
			String codigoSeccion) {
		Map params = new HashMap();
		params.put("codigoRegion", codigoRegion);
		params.put("codigoZona", codigoZona);
		params.put("codigoSeccion", codigoSeccion);
		
		getSqlMapClientTemplate().delete("spusicc.zon.MantenimientoZONSQL.deleteSeccionNoAptaById", params);
	}
}
