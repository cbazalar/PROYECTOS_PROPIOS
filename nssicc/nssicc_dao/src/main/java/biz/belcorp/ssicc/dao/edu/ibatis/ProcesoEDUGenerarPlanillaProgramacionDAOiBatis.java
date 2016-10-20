package biz.belcorp.ssicc.dao.edu.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.edu.ProcesoEDUGenerarPlanillaProgramacionDAO;
import biz.belcorp.ssicc.dao.edu.model.ParametroProceso;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;

/**
 * @author <a href="mailto:rvela@belcorp.biz">Robinson Vela Bardales</a>
 *
 */
@Repository("edu.procesoEDUGenerarPlanillaProgramacionDAO")
public class ProcesoEDUGenerarPlanillaProgramacionDAOiBatis extends 
	BaseDAOiBatis implements ProcesoEDUGenerarPlanillaProgramacionDAO {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCalificacionAptasDAO#executeGenerarPlanillaProgramacion(java.util.Map)
	 */
	public void executeGenerarPlanillaProgramacion(Map criteria) {
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executeGenerarPlanillaProgramacion",
				criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUGenerarPlanillaProgramacionDAO#insertParametroProceso(biz.belcorp.ssicc.edu.dao.model.ParametroProceso)
	 */
	public void insertParametroProceso(ParametroProceso parametroProceso) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert(
				"edu.ProcesosEDUSQL.insertParametroProceso",
				parametroProceso);
	}

	/**
	 * Devuelve cantidad de planillas en una region
	 * @param criteria
	 * @return
	 */
	public Integer getCantidadPlanillasRegion(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject(
				"edu.ProcesosEDUSQL.getCantidadPlanillasRegion",
				criteria);
	}
	/**
	 * Ejecuta proceso de pedidios rezagados
	 * @param params
	 * @throws Exception
	 */
	public void executeProcesoConsultoraRezagadas(Map params) throws Exception {
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executeProcesoConsultoraRezagadas",	params);
		
	}
	
	/* 
	 * Ejecuta Actualizacion statis consultotra
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUGenerarPlanillaProgramacionDAO#actualizacionStatusConsultora(java.util.Map)
	 */
	public void executeActualizacionStatusConsultora(Map params) {
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executeActualizacionStatusConsultora",	params);
		
	}
	
}
