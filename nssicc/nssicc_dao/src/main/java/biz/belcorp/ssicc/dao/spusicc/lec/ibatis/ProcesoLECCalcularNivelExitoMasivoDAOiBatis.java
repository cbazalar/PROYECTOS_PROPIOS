package biz.belcorp.ssicc.dao.spusicc.lec.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECCalcularNivelExitoMasivoDAO;

/**
 * <p>
 * <a href="ProcesoLECCalcularNivelExitoMasivoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Yahir Rivas L.
 *         
 */
@Repository("spusicc.procesoLECCalcularNivelExitoMasivoDAO")
public class ProcesoLECCalcularNivelExitoMasivoDAOiBatis extends BaseDAOiBatis implements ProcesoLECCalcularNivelExitoMasivoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLECCalcularNivelExitoMasivoDAO#executeProcesoLECCalcularNivelExitoMasivo(java.util.Map)
	 */
	public void executeProcesoLECCalcularNivelExitoMasivo(Map params) {
		log.info("Entro a ProcesoLECCalcularNivelExitoMasivoDAOiBatis - executeProcesoLECCalcularNivelExitoMasivo(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.lec.ProcesosLECSQL.executeProcesoLECCalcularNivelExitoMasivo", params);
		log.info("Salio a ProcesoLECCalcularNivelExitoMasivoDAOiBatis - executeProcesoLECCalcularNivelExitoMasivo(java.util.Map)");
	}
}