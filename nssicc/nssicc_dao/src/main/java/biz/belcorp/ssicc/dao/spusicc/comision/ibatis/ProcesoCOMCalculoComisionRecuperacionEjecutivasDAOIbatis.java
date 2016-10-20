package biz.belcorp.ssicc.dao.spusicc.comision.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.comision.ProcesoCOMCalculoComisionRecuperacionEjecutivasDAO;

@Repository("spusicc.procesoCOMCalculoComisionRecuperacionEjecutivasDAO")
public class ProcesoCOMCalculoComisionRecuperacionEjecutivasDAOIbatis extends
		BaseDAOiBatis implements
		ProcesoCOMCalculoComisionRecuperacionEjecutivasDAO {
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.ProcesoCOMCalculoComisionRecuperacionEjecutivasDAO#executeCalculoComisionRecuperacionEjecutivas(java.util.Map)
	 */
	public void executeCalculoComisionRecuperacionEjecutivas(Map params) {
		getSqlMapClientTemplate().update("sisicc.ProcesosCOMSQL.executeCalculoComisionRecuperacionEjecutivas", params);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.ProcesoCOMCalculoComisionRecuperacionEjecutivasDAO#getCalculoComisionRecuperacionEjecutivasCount(java.util.Map)
	 */
	public Integer getCalculoComisionRecuperacionEjecutivasCount(Map params) {
		return (Integer)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosCOMSQL.getCalculoComisionRecuperacionEjecutivasCount",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.ProcesoCOMCalculoComisionRecuperacionEjecutivasDAO#executeComisionRecuperacionPerdidas(java.util.Map)
	 */
	public void executeComisionRecuperacionPerdidas(Map map) {
		getSqlMapClientTemplate().
			update("sisicc.ProcesosCOMSQL.executeComisionRecuperacionPerdidas",
					map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.ProcesoCOMCalculoComisionRecuperacionEjecutivasDAO#getComisionRecuperacionPerdidas(java.util.Map)
	 */
	public List getComisionRecuperacionPerdidas(Map params) {
		return getSqlMapClientTemplate().
			queryForList("sisicc.ProcesosCOMSQL.getComisionRecuperacionPerdidas", 
					params);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.ProcesoCOMCalculoComisionRecuperacionEjecutivasDAO#getComisionRecuperacionPerdidasRegion(java.util.Map)
	 */
	public List getComisionRecuperacionPerdidasRegion(Map params) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOMSQL.getComisionRecuperacionPerdidasRegion", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.ProcesoCOMCalculoComisionRecuperacionEjecutivasDAO#executeActualizacionEstatusEjecutivas(java.util.Map)
	 */
	public void executeActualizacionEstatusEjecutivas(Map params) {
		getSqlMapClientTemplate().
		update("sisicc.ProcesosCOMSQL.executeActualizacionEstatusEjecutivas",
				params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.ProcesoCOMCalculoComisionRecuperacionEjecutivasDAO#executeEliminarComisionRecuperacion(java.util.Map)
	 */
	public void executeEliminarComisionRecuperacion(Map params) {
		getSqlMapClientTemplate().update("sisicc.ProcesosCOMSQL.executeEliminarComisionRecuperacion", params);
	}
}