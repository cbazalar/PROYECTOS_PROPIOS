package biz.belcorp.ssicc.dao.edu.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.edu.ProcesoEDUCierreProcesosRegionDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */
@Repository("edu.procesoEDUCierreProcesosRegionDAO")
public class ProcesoEDUCierreProcesosRegionDAOiBatis extends 
	BaseDAOiBatis implements ProcesoEDUCierreProcesosRegionDAO {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCierreProcesosRegionDAO#executeCerrarCronogramaDictado(java.util.Map)
	 */
	public void executeCerrarCronogramaDictado(Map criteria) {
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executeCerrarCronogramaDictado", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCierreProcesosRegionDAO#executePasarConsultorasProgramadaAPendiente(java.util.Map)
	 */
	public void executePasarConsultorasProgramadaAPendiente(Map criteria) {
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executePasarConsultorasProgramadaAPendienteRegion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCierreProcesosRegionDAO#executeListaStatusConsultora(java.util.Map)
	 */
	public void executeListaStatusConsultora(Map criteria) throws Exception {
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executeListaStatusConsultora", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCierreProcesosRegionDAO#getListaStatusConsultora(java.util.Map)
	 */
	public List getListaStatusConsultora(Map criteria) throws Exception {
		List lista = getSqlMapClientTemplate().queryForList(
				"edu.ProcesosEDUSQL.getListaStatusConsultora", criteria);
		return lista;
	}

	public void registrarPlanillaNoProcesadas(Map params) throws Exception {
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executePlanillaNoProcesadas", params);
		
	}
}
