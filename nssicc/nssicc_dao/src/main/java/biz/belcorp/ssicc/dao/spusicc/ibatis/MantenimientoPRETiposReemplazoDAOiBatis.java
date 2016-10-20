/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.MantenimientoPRETiposReemplazoDAO;
import biz.belcorp.ssicc.dao.spusicc.model.TiposReemplazo;


/**
 * @author Gonzalo Huertas
 *
 */		
@Repository("spusicc.mantenimientoPRETiposReemplazoDAO")
public class MantenimientoPRETiposReemplazoDAOiBatis extends	BaseDAOiBatis implements MantenimientoPRETiposReemplazoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECTiposReemplazoDAO#getTiposReemplazo(java.util.Map)
	 */
	public List getTiposReemplazoList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoPRESQL.getTiposReemplazoList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECTiposReemplazoDAO#getTiposReemplazo(java.lang.String)
	 */
	public TiposReemplazo getTiposReemplazo(String id) {
		return (TiposReemplazo) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoPRESQL.getTiposReemplazo", id);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECTiposReemplazoDAO#insertTiposReemplazo(biz.belcorp.ssicc.spusicc.reclamos.model.TiposReemplazo)
	 */
	public void insertTiposReemplazo(TiposReemplazo tiposReemplazo, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoPRESQL.insertTiposReemplazo", tiposReemplazo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECTiposReemplazoDAO#updateTiposReemplazo(biz.belcorp.ssicc.spusicc.reclamos.model.TiposReemplazo)
	 */
	public void updateTiposReemplazo(TiposReemplazo tiposReemplazo, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoPRESQL.updateTiposReemplazo", tiposReemplazo);
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECTiposReemplazoDAO#deleteTiposReemplazo(java.lang.String)
	 */
	public void deleteTiposReemplazo(String id) {
		getSqlMapClientTemplate().delete("spusicc.MantenimientoPRESQL.deleteTiposReemplazo", id);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECTiposReemplazoDAO#getNextOidTiposReemplazo()
	 */
	public Integer getNextOidTiposReemplazo() {
	
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoPRESQL.getNextOidTiposReemplazo");
	}


}
