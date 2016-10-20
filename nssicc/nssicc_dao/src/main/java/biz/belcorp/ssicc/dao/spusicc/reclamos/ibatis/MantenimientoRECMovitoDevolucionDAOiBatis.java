/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECMovitoDevolucionDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.MotivoDevolucion;


/**
 * @author Giovanni Ascarza
 *
 */		
@Repository("spusicc.mantenimientoRECMotivoDevolucionDAO")
public class MantenimientoRECMovitoDevolucionDAOiBatis extends	BaseDAOiBatis implements MantenimientoRECMovitoDevolucionDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECMovitoDevolucionDAO#getMovitoDevolucion(java.util.Map)
	 */
	public List getMovitoDevolucionList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getMovitoDevolucionList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECMovitoDevolucionDAO#getMovitoDevolucion(java.lang.String)
	 */
	public MotivoDevolucion getMovitoDevolucion(String id) {
		return (MotivoDevolucion) getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getMovitoDevolucion", id);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECMovitoDevolucionDAO#insertMotivoDevolucion(biz.belcorp.ssicc.spusicc.reclamos.model.MotivoDevolucion)
	 */
	public void insertMotivoDevolucion(MotivoDevolucion motivo) {
		getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertMovitoDevolucion", motivo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECMovitoDevolucionDAO#updateMotivoDevolucion(biz.belcorp.ssicc.spusicc.reclamos.model.MotivoDevolucion)
	 */
	public void updateMotivoDevolucion(MotivoDevolucion motivo) {
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.updateMotivoDevolucion", motivo);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECMovitoDevolucionDAO#updateMotivoDevolucionIndicador(biz.belcorp.ssicc.spusicc.reclamos.model.MotivoDevolucion)
	 */
	public void updateMotivoDevolucionIndicador(MotivoDevolucion motivo) {
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.updateMotivoDevolucionIndicador", motivo);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECMovitoDevolucionDAO#deleteMotivoDevolucion(java.lang.String)
	 */
	public void deleteMotivoDevolucion(String id) {
		getSqlMapClientTemplate().delete("spusicc.reclamos.ReclamosSQL.deleteMotivoDevolucion", id);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECMovitoDevolucionDAO#getNextOidMovitoDevolucion()
	 */
	public Integer getNextOidMovitoDevolucion() {
	
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getNextOidMovitoDevolucion");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECMovitoDevolucionDAO#insertMovitoDescripcion(biz.belcorp.ssicc.spusicc.reclamos.model.MotivoDevolucion)
	 */
	public void insertMovitoDescripcion(MotivoDevolucion motivo) {
		getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertMovitoDescripcion", motivo);
		
	}


}
