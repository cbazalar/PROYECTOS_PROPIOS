package biz.belcorp.ssicc.dao.spusicc.zon.ibatis;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONUnidadGeograficaDAO;
import biz.belcorp.ssicc.dao.spusicc.zon.model.UnidadGeografica;

/**
 * @author Aurelio Oviedo
 *
 */
@Repository("spusicc.mantenimientoZONUnidadGeograficaDAO")
public class MantenimientoZONUnidadGeograficaDAOiBatis extends BaseDAOiBatis implements MantenimientoZONUnidadGeograficaDAO {

	public List getUnidadesGeograficasList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getUnidadesGeograficasList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONUnidadGeograficaDAO#getUnidadesGeograficasByCriteria(java.util.Map)
	 */
	public List getUnidadesGeograficasByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getUnidadesGeograficasByCriteria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONUnidadGeograficaDAO#getEstructuraGeopoliticaList()
	 */
	public List getEstructuraGeopoliticaList() {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getEstructuraGeopoliticaList", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONUnidadGeograficaDAO#getCodigoSiguienteUnidadGeografica()
	 */
	public Integer getCodigoSiguienteUnidadGeografica() {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getCodigoSiguienteUnidadGeografica", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONUnidadGeograficaDAO#insertUnidadGeografica(biz.belcorp.ssicc.spusicc.zon.model.UnidadGeografica, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertUnidadGeografica(UnidadGeografica ug, Usuario usuario)
			throws Exception {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.insertUnidadGeografica", ug);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONUnidadGeograficaDAO#updateUnidadGeografica(biz.belcorp.ssicc.spusicc.zon.model.UnidadGeografica, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateUnidadGeografica(UnidadGeografica ug, Usuario usuario)
			throws Exception {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.updateUnidadGeografica", ug);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONUnidadGeograficaDAO#getCantidadTerritoriosByUnidadGeografica(java.lang.String)
	 */
	public List getCantidadTerritoriosByUnidadGeografica(String oidUg) {
		return  (List)getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getCantidadTerritoriosByUnidadGeografica", oidUg);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONUnidadGeograficaDAO#getCantidadUnidadesGeograficasByNivel(java.util.Map)
	 */
	public int getCantidadUnidadesGeograficasByNivel(Map criteria) {
		int ret = 0;
		
		Integer n = (Integer)getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getCantidadUnidadesGeograficasByNivel", criteria);
		
		if(n != null)
			ret = n.intValue();
		
		return ret;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONUnidadGeograficaDAO#removeUnidadGeografica(biz.belcorp.ssicc.spusicc.zon.model.UnidadGeografica, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeUnidadGeografica(UnidadGeografica ug, Usuario usuario) {
		ug.getAuditInfo().setUpdatedBy(usuario.getLogin());
		ug.getAuditInfo().setLastUpdated(new Timestamp(System.currentTimeMillis()));
		
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.removeUnidadGeografica", ug);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONUnidadGeograficaDAO#deleteUnidadGeografica(biz.belcorp.ssicc.spusicc.zon.model.UnidadGeografica, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteUnidadGeografica(UnidadGeografica ug, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.deleteUnidadGeografica", ug);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONUnidadGeograficaDAO#getUnidadesGeograficasValidar(java.util.Map)
	 */
	public List getUnidadesGeograficasValidar(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getUnidadesGeograficasValidar", criteria);
	}
}