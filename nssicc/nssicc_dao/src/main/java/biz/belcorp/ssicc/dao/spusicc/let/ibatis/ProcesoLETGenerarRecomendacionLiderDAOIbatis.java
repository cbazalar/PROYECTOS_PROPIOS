package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETGenerarRecomendacionLiderDAO;

/**
 * Clase de la implementacin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="ProcesoLETGenerarRecomendacionLiderDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
@Repository("spusicc.procesoLETGenerarRecomendacionLiderDAO")
public class ProcesoLETGenerarRecomendacionLiderDAOIbatis extends BaseDAOiBatis implements ProcesoLETGenerarRecomendacionLiderDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETGenerarRecomendacionLiderDAO#executeProcesoLETGenerarRecomendacionesLider(java.util.Map)
	 */
	public void executeProcesoLETGenerarRecomendacionesLider(Map params) {
		log.info("Entro a ProcesoLETGenerarRecomendacionLiderDAOIbatis - executeProcesoLETGenerarRecomendacionesLider(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeProcesoLETGenerarRecomendacionesLider", params);
		log.info("Salio a ProcesoLETGenerarRecomendacionLiderDAOIbatis - executeProcesoLETGenerarRecomendacionesLider(java.util.Map)");
	}
	
}
