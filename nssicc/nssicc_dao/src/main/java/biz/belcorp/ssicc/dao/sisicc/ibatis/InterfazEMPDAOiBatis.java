package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazEMPDAO;

/**
 * Implementacion iBatis del DAO de la Interfaz del programa de Empresarias.
 * 
 * @author <a href="mailto:dooliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
@Repository("sisicc.interfazEMPDAO")
public class InterfazEMPDAOiBatis extends BaseDAOiBatis implements InterfazEMPDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazFLXDAO#executeEnviarResultadoProgramas(java.util.Map)
	 */
	public void executeEnviarMaestroEmpresarias(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazEMPSQL.executeEnviarMaestroEmpresarias", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazFLXDAO#executeEnviarConsultorasObjetadas(java.util.Map)
	 */
	public void executeEnviarBajasEmpresarias(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazEMPSQL.executeEnviarBajasEmpresarias", params);
		
	}

	public void executeEnviarVinculosEmpresarias(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazEMPSQL.executeEnviarVinculosEmpresarias", params);
	}
}