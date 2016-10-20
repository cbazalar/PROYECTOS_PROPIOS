package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCalculoNivelExitoLiderMasivoDAO;

/**
 * <p>
 * <a href="ProcesoLETCalculoNivelExitoLiderMasivoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Aurelio Oviedo
 *         
 */
@Repository("spusicc.procesoLETCalculoNivelExitoLiderMasivoDAO")
public class ProcesoLETCalculoNivelExitoLiderMasivoDAOiBatis extends BaseDAOiBatis implements ProcesoLETCalculoNivelExitoLiderMasivoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCalculoNivelExitoLiderMasivoDAO#executeProcesoLETCalculoNivelExitoLiderMasivo(java.util.Map)
	 */
	public void executeProcesoLETCalculoNivelExitoLiderMasivo(Map params) {
		log.info("Entro a ProcesoLETCalculoNivelExitoLiderMasivoDAOiBatis - executeProcesoLETCalculoNivelExitoLiderMasivo(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeProcesoLETCalculoNivelExitoLiderMasivo", params);
		log.info("Salio a ProcesoLETCalculoNivelExitoLiderMasivoDAOiBatis - executeProcesoLETCalculoNivelExitoLiderMasivo(java.util.Map)");
	}
}