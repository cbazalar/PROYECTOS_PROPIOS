package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCPagoConcursoDAO;
import biz.belcorp.ssicc.dao.spusicc.inc.model.BonoPremioPago;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.mantenimientoINCPagoConcursoDAO")
public class MantenimientoINCPagoConcursoDAOIbatis extends BaseDAOiBatis implements
	MantenimientoINCPagoConcursoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPagoConcursoDAO#getListPagoConcurso(java.util.Map)
	 */
	public List getListPagoConcurso(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getListPagoConcurso", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPagoConcursoDAO#getMaxPagoConcurso()
	 */
	public String getMaxPagoConcurso() {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.MantenimientoINCSQL.getMaxPagoConcurso");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPagoConcursoDAO#insertPagoConcurso(java.util.Map)
	 */
	public void insertPagoConcurso(Map params) {
		getSqlMapClientTemplate().insert(
				"spusicc.incentivos.MantenimientoINCSQL.insertPagoConcurso", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPagoConcursoDAO#updatePagoConcurso(java.util.Map)
	 */
	public void updatePagoConcurso(Map params) {
		getSqlMapClientTemplate().update(
				"spusicc.incentivos.MantenimientoINCSQL.updatePagoConcurso", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPagoConcursoDAO#deletePagoConcurso(java.util.Map)
	 */
	public void deletePagoConcurso(Map params) {
		getSqlMapClientTemplate().update(
				"spusicc.incentivos.MantenimientoINCSQL.deletePagoConcurso", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPagoConcursoDAO#cerrarPagoConcurso(java.util.Map)
	 */
	public void cerrarPagoConcurso(Map params) {
		getSqlMapClientTemplate().update(
				"spusicc.incentivos.MantenimientoINCSQL.cerrarPagoConcurso", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPagoConcursoDAO#getListClasificacionesPagoConcurso(java.util.Map)
	 */
	public List getListClasificacionesPagoConcurso(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getListClasificacionesPagoConcurso", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPagoConcursoDAO#getPeriodosDespachoConcurso(java.util.Map)
	 */
	public Map getPeriodosDespachoConcurso(Map map) {
		return (Map)getSqlMapClientTemplate().queryForObject(
				"spusicc.incentivos.MantenimientoINCSQL.getPeriodosDespachoConcurso", map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPagoConcursoDAO#getOidPeriodoByCodigoPeriodo(java.lang.String)
	 */
	public String getOidPeriodoByCodigoPeriodo(String codigoPeriodo) {
		return (String) getSqlMapClientTemplate().queryForObject(
					"spusicc.incentivos.MantenimientoINCSQL.getOidPeriodoByCodigoPeriodo", codigoPeriodo);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPagoConcursoDAO#insertPagoBonoPremio(biz.belcorp.ssicc.spusicc.inc.dao.model.BonoPremioPago)
	 */
	public void insertPagoBonoPremio(BonoPremioPago bonoPremioPago) {
		getSqlMapClientTemplate().insert(
				"spusicc.incentivos.MantenimientoINCSQL.insertPagoBonoPremio", bonoPremioPago);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPagoConcursoDAO#deletePagoBonoPremio(java.util.Map)
	 */
	public void deletePagoBonoPremio(Map params) {
		getSqlMapClientTemplate().update(
				"spusicc.incentivos.MantenimientoINCSQL.deletePagoBonoPremio", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPagoConcursoDAO#getListPagoBonoPremio(java.util.Map)
	 */
	public List getListPagoBonoPremio(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getListPagoBonoPremio", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCPagoConcursoDAO#getListConcursosPago(java.util.Map)
	 */
	public List getListConcursosPago(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getListConcursosPago", criteria);
	}
	
}
