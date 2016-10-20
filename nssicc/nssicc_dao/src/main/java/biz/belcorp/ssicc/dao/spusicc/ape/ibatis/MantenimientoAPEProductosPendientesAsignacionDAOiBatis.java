package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEProductosPendientesAsignacionDAO;

/**
 * Implementacion del DAO que ejecutara el metodo de listado de productos pendientes de asignacion
 * <p>
 * <a href="MantenimientoAPEProductosPendientesAsignacionDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:cgonzales@csigcomt.com">Christian gonzales</a>
 */
@Repository("spusicc.mantenimientoAPEProductosPendientesAsignacionDAO")
public class MantenimientoAPEProductosPendientesAsignacionDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEProductosPendientesAsignacionDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEProductosPendientesAsignacionDAO#getProductosPendientesAsignacionList(java.util.Map)
	 */
	public List getProductosPendientesAsignacionList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getProductosPendientesAsignacionList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEProductosPendientesAsignacionDAO#getOidPeriodobyMarcaCanal(java.util.Map)
	 */
	public String getOidPeriodobyMarcaCanal(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidPeriodobyMarcaCanal", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEProductosPendientesAsignacionDAO#getAnaquelesSinProductosAsignados(java.util.Map)
	 */
	public List getAnaquelesSinProductosAsignados(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getAnaquelesSinProductosAsignadosList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEProductosPendientesAsignacionDAO#getDescripconProductoAPE(java.util.Map)
	 */
	public String getDescripconProductoAPE(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getDescripconProductoAPE", criteria);
	}
}
