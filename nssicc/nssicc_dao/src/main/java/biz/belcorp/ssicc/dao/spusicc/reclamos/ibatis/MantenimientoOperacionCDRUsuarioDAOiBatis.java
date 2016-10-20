/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoOperacionCDRUsuarioDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.OperacionCDRUsuario;

/**
 * @author Danny Amaro
 *
 */
@Repository("spusicc.mantenimientoOperacionCDRUsuarioDAO")
public class MantenimientoOperacionCDRUsuarioDAOiBatis extends BaseDAOiBatis implements MantenimientoOperacionCDRUsuarioDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoOperacionCDRUsuarioDAO#getOperacionesCDRxUsuario(java.util.Map)
	 */
	public List getOperacionesCDRxUsuario(Map criteria) {
		
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getOperacionesCDRxUsuario", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoOperacionCDRUsuarioDAO#getOperacionesUsuario(java.util.Map)
	 */
	public List getOperacionesUsuario(Map criteria) {		
		
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getOperacionesUsuario", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoOperacionCDRUsuarioDAO#getTipoOperacionesByOperacion(java.util.Map)
	 */
	public List getTipoOperacionesByOperacion(Map criteria) {
		
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getTipoOperacionesByOperacion", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoOperacionCDRUsuarioDAO#insertOperacionCDRUsuario(biz.belcorp.ssicc.spusicc.reclamos.model.OperacionCDRUsuario)
	 */
	public void insertOperacionCDRUsuario(
			OperacionCDRUsuario operacionCDRUsuario, Usuario usuario) {
		
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.insertOperacionCDRUsuario", operacionCDRUsuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoOperacionCDRUsuarioDAO#insertOperacionCDRUsuarioAudit(biz.belcorp.ssicc.spusicc.reclamos.model.OperacionCDRUsuario)
	 */
	public void insertOperacionCDRUsuarioAudit(
			OperacionCDRUsuario operacionCDRUsuario, Usuario usuario) {
		
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.insertOperacionCDRUsuarioAudit", operacionCDRUsuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoOperacionCDRUsuarioDAO#removeOperacionCDRUsuario(biz.belcorp.ssicc.spusicc.reclamos.model.OperacionCDRUsuario)
	 */
	public void removeOperacionCDRUsuario(
			Map criteria) {
		
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.removeOperacionCDRUsuario", criteria);
		
	}

}
