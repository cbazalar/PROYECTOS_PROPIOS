package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOCalificacionesOrdenTransporteDAO;

/**
 * @author peextdoliva
 */
@Repository("spusicc.mantenimientoSTOCalificacionesOrdenTransporteDAO")
public class MantenimientoSTOCalificacionesOrdenTransporteDAOiBatis extends BaseDAOiBatis implements MantenimientoSTOCalificacionesOrdenTransporteDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOCalificacionesOrdenTransporteDAO#getCalificacionesOrdenTransporte(java.util.Map)
	 */
	public List getCalificacionesOrdenTransporte(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getCalificacionesOrdenTransporte", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOCalificacionesOrdenTransporteDAO#deleteCalificacionesOrdenTransporte(java.util.Map)
	 */
	public void deleteCalificacionesOrdenTransporte(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.deleteCalificacionesOrdenTransporte", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOCalificacionesOrdenTransporteDAO#insertCalificacionesOrdenTransporte(java.util.Map)
	 */
	public void insertCalificacionesOrdenTransporte(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertCalificacionesOrdenTransporte", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOCalificacionesOrdenTransporteDAO#updateCalificacionesOrdenTransporte(java.util.Map)
	 */
	public void updateCalificacionesOrdenTransporte(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.updateCalificacionesOrdenTransporte", criteria);
	}
}