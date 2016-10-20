package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCalculoResultadosLiderDAO;

/**
 * <p>
 * <a href="ProcesoLETCalculoResultadosLiderDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Aurelio Oviedo
 *         
 */
@Repository("spusicc.procesoLETCalculoResultadosLiderDAO")
public class ProcesoLETCalculoResultadosLiderDAOiBatis extends BaseDAOiBatis implements ProcesoLETCalculoResultadosLiderDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCalculoResultadosLiderDAO#executeProcesoLETCalculoResultadosLider(java.util.Map)
	 */
	public void executeProcesoLETCalculoResultadosLider(Map params) {
		log.info("Entro a ProcesoLETCalculoResultadosLiderDAOiBatis - executeProcesoLETCalculoResultadosLider(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeProcesoLETCalculoResultadosLider", params);
		log.info("Salio a ProcesoLETCalculoResultadosLiderDAOiBatis - executeProcesoLETCalculoResultadosLider(java.util.Map)");
	}
}