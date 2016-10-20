package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETEvaluacionProductividadCierreRegionCampaniaDAO;

/**
 * Clase de la implementacin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="ProcesoLETEvaluacionProductividadCierreRegionCampaniaDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
@Repository("spusicc.procesoLETEvaluacionProductividadCierreRegionCampaniaDAO")
public class ProcesoLETEvaluacionProductividadCierreRegionCampaniaDAOIbatis extends BaseDAOiBatis implements ProcesoLETEvaluacionProductividadCierreRegionCampaniaDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETEvaluacionProductividadCierreRegionCampaniaDAO#executeProcesoLETEvaluacionProductividadCierreRegionCampania(java.util.Map)
	 */
	public void executeProcesoLETEvaluacionProductividadCierreRegionCampania(Map params) {
		log.info("Entro a ProcesoLETEvaluacionProductividadCierreRegionCampaniaDAOIbatis - executeProcesoLETEvaluacionProductividadCierreRegionCampania(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeProcesoLETEvaluacionProductividadCierreRegionCampania", params);
		log.info("Salio a ProcesoLETEvaluacionProductividadCierreRegionCampaniaDAOIbatis - executeProcesoLETEvaluacionProductividadCierreRegionCampania(java.util.Map)");
	}
	
}
