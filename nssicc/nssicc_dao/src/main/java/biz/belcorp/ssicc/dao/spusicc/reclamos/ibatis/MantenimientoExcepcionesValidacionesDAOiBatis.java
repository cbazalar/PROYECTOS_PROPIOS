/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoExcepcionesValidacionesDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.ExcepcionesValidaciones;

/**
 * @author Danny Amaro
 *
 */
@Repository("spusicc.mantenimientoExcepcionesValidacionesDAO")
public class MantenimientoExcepcionesValidacionesDAOiBatis extends BaseDAOiBatis implements MantenimientoExcepcionesValidacionesDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoExcepcionesValidacionesDAO#getDevuelveIdSgteCodExcepcionesValidaciones()
	 */
	public Long getDevuelveIdSgteCodExcepcionesValidaciones() {
		Long id = (Long)getSqlMapClientTemplate().
					queryForObject("spusicc.reclamos.ReclamosSQL.getDevuelveIdSgteCodExcepcionesValidaciones");		
		return id;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoExcepcionesValidacionesDAO#insertExcepcionesValidaciones(biz.belcorp.ssicc.spusicc.reclamos.model.ExcepcionesValidaciones, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertExcepcionesValidaciones(
			ExcepcionesValidaciones excepcionesValidaciones, Usuario usuario) {
		
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.insertExcepcionesValidaciones", excepcionesValidaciones);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoExcepcionesValidacionesDAO#insertExcepcionesValidacionesHistorico(biz.belcorp.ssicc.spusicc.reclamos.model.ExcepcionesValidaciones, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertExcepcionesValidacionesHistorico(
			ExcepcionesValidaciones excepcionesValidaciones, Usuario usuario) {
		
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.insertExcepcionesValidacionesHistorico", excepcionesValidaciones);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoExcepcionesValidacionesDAO#removeExcepcionesValidacione(java.util.Map)
	 */
	public void removeExcepcionesValidacione(Map criteria) {
		
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.removeExcepcionesValidaciones", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoExcepcionesValidacionesDAO#getExcepcionesValidaciones(java.util.Map)
	 */
	public List getExcepcionesValidaciones(Map criteria) {
		
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getExcepcionesValidaciones", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoExcepcionesValidacionesDAO#getExcepcionesValidacionesExist(java.util.Map)
	 */
	public List getExcepcionesValidacionesExist(Map criteria) {
		
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getExcepcionesValidacionesExist", criteria);
		
	}

}
