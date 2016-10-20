package biz.belcorp.ssicc.dao.spusicc.lec.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECCalcularObjetivosBonosDAO;

/**
 * <p>
 * <a href="ProcesoLETCalculoResultadosLiderDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Yahir Rivas L.
 *         
 */
@Repository("spusicc.procesoLECCalcularObjetivosBonosDAO")
public class ProcesoLECCalcularObjetivosBonosDAOiBatis extends BaseDAOiBatis implements ProcesoLECCalcularObjetivosBonosDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLECCalcularObjetivosBonosDAO#executeProcesoLECCalcularObjetivosBonos(java.util.Map)
	 */
	public void executeProcesoLECCalcularObjetivosBonos(Map params) {
		log.info("Entro a ProcesoLECCalcularObjetivosBonosDAOiBatis - executeProcesoLECCalcularObjetivosBonos(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.lec.ProcesosLECSQL.executeProcesoLECCalcularObjetivosBonos", params);
		log.info("Salio a ProcesoLECCalcularObjetivosBonosDAOiBatis - executeProcesoLECCalcularObjetivosBonos(java.util.Map)");
	}
}