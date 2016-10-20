package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCalculoBajaAutomaticaLiderDAO;

/**
 * <p>
 * <a href="ProcesoLETCalculoNivelExitoLiderMasivoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Aurelio Oviedo
 *         
 */
@Repository("spusicc.procesoLETCalculoBajaAutomaticaLiderDAO")
public class ProcesoLETCalculoBajaAutomaticaLiderDAOiBatis extends BaseDAOiBatis implements ProcesoLETCalculoBajaAutomaticaLiderDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCalculoBajaAutomaticaLiderDAO#executeProcesoLETCalculoBajaAutomaticaLider(java.util.Map)
	 */
	public void executeProcesoLETCalculoBajaAutomaticaLider(Map params) {
		log.info("Entro a ProcesoLETCalculoBajaAutomaticaLiderDAOiBatis - executeProcesoLETCalculoBajaAutomaticaLider(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeProcesoLETCalculoBajaAutomaticaLider", params);
		log.info("Salio a ProcesoLETCalculoBajaAutomaticaLiderDAOiBatis - executeProcesoLETCalculoBajaAutomaticaLider(java.util.Map)");
	
		
	}
}