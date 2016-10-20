package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETResultadoLideresAcumuladoConcuCierreCampDAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("spusicc.procesoLETResultadoLideresAcumuladoConcuCierreCampDAO")
public class ProcesoLETResultadoLideresAcumuladoConcuCierreCampDAOiBatis extends BaseDAOiBatis implements ProcesoLETResultadoLideresAcumuladoConcuCierreCampDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETResultadoLideresAcumuladoConcuCierreCampDAO#executeProcesoLETResultadoLideresAcumuladoConcuCierreCamp(java.util.Map)
	 */
	public void executeProcesoLETResultadoLideresAcumuladoConcuCierreCamp(Map params) {
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeProcesoLETResultadoLideresAcumuladoConcuCierreCamp", params);
	}

}
