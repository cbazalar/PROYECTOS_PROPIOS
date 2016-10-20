/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.reclamos.ProcesoRECBloqueoCDRDAO;


/**
 * @author Dennys Oliva Iriarte
 * 
 */
@Repository("spusicc.procesoRECBloqueoCDRDAO")
public class ProcesoRECBloqueoCDRDAOIbatis extends BaseDAOiBatis implements ProcesoRECBloqueoCDRDAO {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECBloqueoCDRDAO#getPeriodoInicialFinal(java.util.Map)
	 */
	public List getPeriodoInicialFinal(Map map){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getPeriodoInicialFinal",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECBloqueoCDRDAO#getBloqueosCDRByCriteria(java.util.Map)
	 */
	public List getBloqueosCDRByCriteria(Map map){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getBloqueosCDRByCriteria",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECBloqueoCDRDAO#updateDesbloqueoCDR(java.util.Map)
	 */
	public void updateDesbloqueoCDR(Map map){
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.updateDesbloqueoCDR",map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECBloqueoCDRDAO#executeProcesoRECBloqueoCDR(java.util.Map)
	 */
	public void executeProcesoRECBloqueoCDR(Map params){
		getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.executeProcesoRECBloqueoCDR",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECBloqueoCDRDAO#executeProcesoRECEnviarCDRRecepcionados(java.util.Map)
	 */
	public void executeProcesoRECEnviarCDRRecepcionados(Map params){
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.executeProcesoRECEnviarCDRRecepcionados",params);
	}
}
