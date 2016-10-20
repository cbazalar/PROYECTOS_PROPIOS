package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECOperacionesReclamoDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.OperacionReclamo;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.TipoOperacion;

/**
 * @author <a href="aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *
 */			 
@Repository("spusicc.mantenimientoRECOperacionesReclamoDAO")
public class MantenimientoRECOperacionesReclamoDAOiBatis extends BaseDAOiBatis implements MantenimientoRECOperacionesReclamoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionesReclamoDAO#getOperacionesReclamoList(java.util.Map)
	 */
	public List getOperacionesReclamoList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getOperacionesReclamoList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionesReclamoDAO#getMotivosBloqueoList(java.util.Map)
	 */
	public List getMotivosBloqueoList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getMotivosBloqueoList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionesReclamoDAO#getMovimientosAlmacenList(java.util.Map)
	 */
	public List getMovimientosAlmacenList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getMovimientosAlmacenList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionesReclamoDAO#getOperacionReclamo(java.util.Map)
	 */
	public OperacionReclamo getOperacionReclamo(Map criteria) {
		return (OperacionReclamo) getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getOperacionReclamo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionesReclamoDAO#getExisteOperacionReclamoByCodigo(java.util.Map)
	 */
	public int getExisteOperacionReclamoByCodigo(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getExisteOperacionReclamoByCodigo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionesReclamoDAO#getNextOidOperacionReclamo(java.util.Map)
	 */
	public int getNextOidOperacionReclamo(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getNextOidOperacionReclamo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionesReclamoDAO#insertOperacionReclamo(biz.belcorp.ssicc.spusicc.reclamos.model.OperacionReclamo)
	 */
	public void insertOperacionReclamo(OperacionReclamo operacionReclamo) {
		getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertOperacionReclamo", operacionReclamo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionesReclamoDAO#updateOperacionReclamo(biz.belcorp.ssicc.spusicc.reclamos.model.OperacionReclamo)
	 */
	public void updateOperacionReclamo(OperacionReclamo operacionReclamo) {
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.updateOperacionReclamo", operacionReclamo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionesReclamoDAO#deleteOperacionReclamo(java.lang.String)
	 */
	public void deleteOperacionReclamo(String id) {
		getSqlMapClientTemplate().delete("spusicc.reclamos.ReclamosSQL.deleteOperacionReclamo", id);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionesReclamoDAO#getMotivosRechazoDesbloqueoList(java.util.Map)
	 */
	public List getMotivosRechazoDesbloqueoList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getMotivosRechazoDesbloqueoList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionesReclamoDAO#getNextOidTipoOperacion(java.util.Map)
	 */
	public int getNextOidTipoOperacion(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getNextOidTipoOperacion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionesReclamoDAO#getExisteTipoOperacionByCodigo(java.util.Map)
	 */
	public int getExisteTipoOperacionByCodigo(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getExisteTipoOperacionByCodigo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionesReclamoDAO#getTipoOperacionListByOperacion(java.util.Map)
	 */
	public List getTipoOperacionListByOperacion(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getTipoOperacionListByOperacion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionesReclamoDAO#insertTipoOperacion(biz.belcorp.ssicc.spusicc.reclamos.model.TipoOperacion)
	 */
	public void insertTipoOperacion(TipoOperacion tipoOperacion) {
		getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertTipoOperacion", tipoOperacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionesReclamoDAO#updateTipoOperacion(biz.belcorp.ssicc.spusicc.reclamos.model.TipoOperacion)
	 */
	public void updateTipoOperacion(TipoOperacion tipoOperacion) {
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.updateTipoOperacion", tipoOperacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionesReclamoDAO#deleteTipoOperacion(java.util.Map)
	 */
	public void deleteTipoOperacion(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.reclamos.ReclamosSQL.deleteTipoOperacion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionesReclamoDAO#getTiposSolicitudList()
	 */
	public List getTiposSolicitudList() {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getTiposSolicitudList", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionesReclamoDAO#getTiposSolicitudGeneraList()
	 */
	public List getTiposSolicitudGeneraList() {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getTiposSolicitudGeneraList", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECOperacionesReclamoDAO#getAlmacenList(java.util.Map)
	 */
	public List getAlmacenList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getAlmacenList", criteria);
	}
}