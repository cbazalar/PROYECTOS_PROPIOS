package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazDANDAO;

/**
 * <p>
 * <a href="InterfazDANDAOiBatis.java.html"><i>View Source</i></a>
 * </p>
 * @author Sergio Buchelli Silva
 *
 */
@Repository("sisicc.interfazDANDAO")
public class InterfazDANDAOiBatis extends BaseDAOiBatis implements InterfazDANDAO{
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDANDAO#executeInterfazDANEnviarCampanhas(java.util.Map)
	 */
	public void executeInterfazDANEnviarCampanhas(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazDANSQL.executeInterfazDANEnviarCampanhas", params);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDANDAO#executeInterfazDANEnviarClientes(java.util.Map)
	 */
	public void executeInterfazDANEnviarClientes(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazDANSQL.executeInterfazDANEnviarClientes", params);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDANDAO#executeInterfazDANEnviarRegiones(java.util.Map)
	 */
	public void executeInterfazDANEnviarRegiones(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazDANSQL.executeInterfazDANEnviarRegiones", params);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazDANDAO#executeInterfazDANEnviarZonas(java.util.Map)
	 */
	public void executeInterfazDANEnviarZonas(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazDANSQL.executeInterfazDANEnviarZonas", params);
	}

}