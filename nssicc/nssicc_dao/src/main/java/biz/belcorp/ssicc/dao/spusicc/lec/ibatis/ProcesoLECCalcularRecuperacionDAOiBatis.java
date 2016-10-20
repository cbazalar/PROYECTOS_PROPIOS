package biz.belcorp.ssicc.dao.spusicc.lec.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECCalcularRecuperacionDAO;

/**
 * <p>
 * <a href="ProcesoLECCalcularRecuperacionDAOibatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Juan Altamirano
 *         
 */
@Repository("spusicc.procesoLECCalcularRecuperacionDAO")
public class ProcesoLECCalcularRecuperacionDAOiBatis extends BaseDAOiBatis implements ProcesoLECCalcularRecuperacionDAO{
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.ProcesoLECCalcularRecuperacionDAO#executeProcesoLECCalcularRecuperacion(java.util.Map)
	 */
	public void executeProcesoLECCalcularRecuperacion(Map params) {
		log.info("Entro a ProcesoLECCalcularRecuperacionDAOibatis - executeProcesoLECCalcularRecuperacion(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.lec.ProcesosLECSQL.executeProcesoLECCalcularRecuperacion", params);
		log.info("Salio a ProcesoLECCalcularRecuperacionDAOibatis - executeProcesoLECCalcularRecuperacion(java.util.Map)");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.ProcesoLECCalcularRecuperacionDAO#getDevuelvePeriodoByCodigoPeriodo(java.util.Map)
	 */
	public String getDevuelvePeriodoByCodigoPeriodo(Map params) {
		log.info("Entro a ProcesoLECCalcularRecuperacionDAOibatis - getDevuelvePeriodoByCodigoPeriodo(java.util.Map)");
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.lec.ProcesosLECSQL.getDevuelvePeriodoByCodigoPeriodo", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.ProcesoLECCalcularRecuperacionDAO#getIndicadorCampannaRecaudo(java.util.Map)
	 */
	public String getIndicadorCampannaRecaudo(Map params) {
		log.info("Entro a ProcesoLECCalcularRecuperacionDAOibatis - getIndicadorCampannaRecaudo(java.util.Map)");
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.lec.ProcesosLECSQL.getIndicadorCampannaRecaudo", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECCalcularRecuperacionDAO#executeProcesoLECCalcularRecuperacionNuevo(java.util.Map)
	 */
	public void executeProcesoLECCalcularRecuperacionNuevo(Map params) {
		log.info("Entro a ProcesoLECCalcularRecuperacionDAOibatis - executeProcesoLECCalcularRecuperacionNuevo(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.lec.ProcesosLECSQL.executeProcesoLECCalcularRecuperacionNuevo", params);
		log.info("Salio a ProcesoLECCalcularRecuperacionDAOibatis - executeProcesoLECCalcularRecuperacionNuevo(java.util.Map)");
	}
}
