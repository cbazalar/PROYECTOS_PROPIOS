package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETResultadoSeccionCampaniaCierreCampaniaDAO;

/**
 * Clase de la implementacin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="ProcesoLETResultadoSeccionCampaniaCierreCampaniaDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
@Repository("spusicc.procesoLETResultadoSeccionCampaniaCierreCampaniaDAO")
public class ProcesoLETResultadoSeccionCampaniaCierreCampaniaDAOIbatis extends BaseDAOiBatis implements ProcesoLETResultadoSeccionCampaniaCierreCampaniaDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETResultadoSeccionCampaniaCierreCampaniaDAO#executeProcesoLETResultadoSeccionesCampaniaCieCam(java.util.Map)
	 */
	public void executeProcesoLETResultadoSeccionesCampaniaCieCam(Map params) {
		log.info("Entro a ProcesoLETResultadoSeccionCampaniaCierreCampaniaDAOIbatis - executeProcesoLETResultadoSeccionesCampaniaCieCam(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeProcesoLETResultadoSeccionesCampaniaCieCam", params);
		log.info("Salio a ProcesoLETResultadoSeccionCampaniaCierreCampaniaDAOIbatis - executeProcesoLETResultadoSeccionesCampaniaCieCam(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETResultadoSeccionCampaniaCierreCampaniaDAO#getValidaProcesoCierreRegionLET(java.util.Map)
	 */
	public String getValidaProcesoCierreRegionLET(Map criteria) {
		log.info("Entro a ProcesoLETResultadoSeccionCampaniaCierreCampaniaDAOIbatis - getValidaProcesoCierreRegionLET(java.util.Map)");
		String resultado = (String)getSqlMapClientTemplate().queryForObject("spusicc.let.ProcesosLETSQL.getValidaProcesoCierreRegionLET", criteria);
		log.info("Salio a ProcesoLETResultadoSeccionCampaniaCierreCampaniaDAOIbatis - getValidaProcesoCierreRegionLET(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}
	
	
	
}
