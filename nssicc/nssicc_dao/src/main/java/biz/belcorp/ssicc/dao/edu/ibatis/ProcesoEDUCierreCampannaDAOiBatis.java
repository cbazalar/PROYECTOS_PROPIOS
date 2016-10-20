package biz.belcorp.ssicc.dao.edu.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.edu.ProcesoEDUCierreCampannaDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */
@Repository("edu.procesoEDUCierreCampannaDAO")
public class ProcesoEDUCierreCampannaDAOiBatis extends 
	BaseDAOiBatis implements ProcesoEDUCierreCampannaDAO {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCierreCampannaDAO#executeBloqueoConsultoraCampanna(java.util.Map)
	 */
	public void executeBloqueoConsultoraCampanna(Map criteria) {
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executeBloqueoConsultoraCampanna", criteria);
	}

	public void executeProcesoConsultoraRezagadas(Map criteria) throws Exception {
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executeProcesoConsultoraRezagadas",	criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCierreCampannaDAO#executeRegistrarPlanillasNoProcesadas(java.util.Map)
	 */
	public void executeRegistrarPlanillasNoProcesadas(Map params) {
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executeRegistrarPlanillasNoProcesadas",	params);
		
	}


}
