package biz.belcorp.ssicc.dao.edu.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.edu.ProcesoEDURecodificacionConsultoraDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;

/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 *
 */
@Repository("edu.procesoEDURecodificacionConsultoraDAO")
public class ProcesoEDURecodificacionConsultoraDAOiBatis extends 
	BaseDAOiBatis implements ProcesoEDURecodificacionConsultoraDAO {
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDURecodificacionConsultoraDAO#executeActualizarParametrosCurso(java.util.Map)
	 */
	public void executeRecodificacionConsultora(Map criteria) {
		logger.debug("executeRecodificacionConsultora");
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executeRecodificacionConsultora", criteria);		
		
		
	}


}
