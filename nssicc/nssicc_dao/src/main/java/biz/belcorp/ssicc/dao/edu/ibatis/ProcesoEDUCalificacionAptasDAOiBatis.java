package biz.belcorp.ssicc.dao.edu.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.edu.ProcesoEDUCalificacionAptasDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */
@Repository("edu.procesoEDUCalificacionAptasDAO")
public class ProcesoEDUCalificacionAptasDAOiBatis extends 
	BaseDAOiBatis implements ProcesoEDUCalificacionAptasDAO {
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCalificacionAptasDAO#getCursosCalificacionAptasAutomaticaByCriteria(java.util.Map)
	 */
	public List getCursosCalificacionAptasAutomaticaByCriteria(Map criteria) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.ProcesosEDUSQL.getCursosCalificacionAptasAutomaticaByCriteria",
				criteria);
		return resultado;
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCalificacionAptasDAO#getCampannaActualProceso(java.util.Map)
	 */
	public String getCampannaActualProceso(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"edu.ProcesosEDUSQL.getCampannaActualProceso",
				criteria);
	}
	
	public String getFechaProcesoFacturacion(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"edu.ProcesosEDUSQL.getFechaProcesoFacturacion",
				criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCalificacionAptasDAO#executeCalificacionAptasAutomatica(java.util.Map)
	 */
	public void executeCalificacionAptasAutomatica(Map criteria) {
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executeCalificacionAptasAutomatica",
				criteria);
	}
	
	public void executeActualizarInvitacionHistoAptas(Map criteria) {
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executeActualizarInvitacionHistoAptas",
				criteria);
	}

	public void ejecutarProcesosPostCalificacionEnvioAptasAutomatica(Map params) {
		getSqlMapClientTemplate().update("edu.ProcesosEDUSQL.executeProcesosPostCalificacionEnvioAptasAutomatica", params);
	}


}
