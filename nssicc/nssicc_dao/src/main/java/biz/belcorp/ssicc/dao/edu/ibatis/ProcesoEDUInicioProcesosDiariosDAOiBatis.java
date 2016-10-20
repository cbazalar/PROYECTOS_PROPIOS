package biz.belcorp.ssicc.dao.edu.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.edu.ProcesoEDUInicioProcesosDiariosDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */
@Repository("edu.procesoEDUInicioProcesosDiariosDAO")
public class ProcesoEDUInicioProcesosDiariosDAOiBatis extends 
	BaseDAOiBatis implements ProcesoEDUInicioProcesosDiariosDAO {
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUInicioProcesosDiariosDAO#executeRegistrarPlanillasNoProcesadas(java.util.Map)
	 */
	public void executeRegistrarPlanillasNoProcesadas(Map criteria) throws Exception {
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executeRegistrarPlanillasNoProcesadas",	criteria);
	}
	
	public void executeProcesoConsultoraRezagadas(Map criteria) throws Exception {
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executeProcesoConsultoraRezagadas",	criteria);
		
	}
	

}
