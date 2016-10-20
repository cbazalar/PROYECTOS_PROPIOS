package biz.belcorp.ssicc.dao.edu.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.edu.ProcesoEDUActualizarPlanillaProgramacionDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */
@Repository("edu.procesoEDUActualizarPlanillaProgramacionDAO")
public class ProcesoEDUActualizarPlanillaProgramacionDAOiBatis extends 
	BaseDAOiBatis implements ProcesoEDUActualizarPlanillaProgramacionDAO {
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCalificacionAptasDAO#executeActualizarPlanillaProgramacion(java.util.Map)
	 */
	public void executeActualizarPlanillaProgramacion(Map criteria) {
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executeActualizarPlanillaProgramacion",
				criteria);
	}


}
