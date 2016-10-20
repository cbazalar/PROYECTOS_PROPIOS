package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETAsignarDesvincularLiderDAO;

/**
 * Clase de la implementacin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="ProcesoLETAsignarDesvincularLiderDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
@Repository("spusicc.procesoLETAsignarDesvincularLiderDAO")
public class ProcesoLETAsignarDesvincularLiderDAOIbatis extends BaseDAOiBatis implements ProcesoLETAsignarDesvincularLiderDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETAsignarDesvincularLiderDAO#executeAsignarDesvincularLider(java.util.Map)
	 */
	public void executeAsignarDesvincularLider(Map params) {
		log.info("Entro a ProcesoLETAsignarDesvincularLiderDAOIbatis - executeAsignarDesvincularLider(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeAsignarDesvincularLider", params);
		log.info("Salio a ProcesoLETAsignarDesvincularLiderDAOIbatis - executeAsignarDesvincularLider(java.util.Map)");
	}
	
}
