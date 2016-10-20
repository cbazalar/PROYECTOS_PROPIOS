package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.reclamos.ProcesoRECActivacionReclamosGratisDAO;

/**
 * @author Aurelio Oviedo
 *
 */
@Repository("spusicc.procesoRECActivacionReclamosGratisDAO")
public class ProcesoRECActivacionReclamosGratisDAOIbatis extends BaseDAOiBatis implements ProcesoRECActivacionReclamosGratisDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECActivacionReclamosGratisDAO#getActivacionReclamosGratisList(java.util.Map)
	 */
	public List getActivacionReclamosGratisList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getActivacionReclamosGratisList",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECActivacionReclamosGratisDAO#getBuscarCUVReemplazo(java.util.Map)
	 */
	public String getBuscarCUVReemplazo(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getBuscarCUVReemplazo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECActivacionReclamosGratisDAO#getBuscarSAPReemplazo(java.util.Map)
	 */
	public String getBuscarSAPReemplazo(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getBuscarSAPReemplazo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECActivacionReclamosGratisDAO#executeProcesoActivacionReclamosGratis(java.util.Map)
	 */
	public void executeProcesoActivacionReclamosGratis(Map params) {
		getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.executeProcesoActivacionReclamosGratis",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECActivacionReclamosGratisDAO#getDataMemoriaNByCriteria(java.util.Map)
	 */
	public List getDataMemoriaNByCriteria(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getDataMemoriaNByCriteria",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECActivacionReclamosGratisDAO#getDataMemoriaSByCriteria(java.util.Map)
	 */
	public List getDataMemoriaSByCriteria(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getDataMemoriaSByCriteria",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECActivacionReclamosGratisDAO#updateActivacionReclamosGratisAsignar(java.util.Map)
	 */
	public void updateActivacionReclamosGratisAsignar(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.updateActivacionReclamosGratisAsignar",criteria);
	}
}