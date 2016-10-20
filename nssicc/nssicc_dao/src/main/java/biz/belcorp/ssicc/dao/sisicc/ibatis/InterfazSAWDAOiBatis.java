/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazSAWDAO;

/**
 * 
 * <p>
 * <a href="InterfazSAWDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@csigcomt.com">Cristhian Roman</a>
 * 
 */
@Repository("sisicc.interfazSAWDAO")
public class InterfazSAWDAOiBatis extends BaseDAOiBatis implements	InterfazSAWDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAWDAO#executeInterfazSAWEnviarDemandaSAP(java.util.Map)
	 */
	public void executeInterfazSAWEnviarDemandaSAP(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAWSQL.executeInterfazSAWEnviarDemandaSAP",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAWDAO#executeInterfazSAWEnviarDemandaYobel(java.util.Map)
	 */
	public void executeInterfazSAWEnviarDemandaYobel(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAWSQL.executeInterfazSAWEnviarDemandaYobel",params);
		
	}	
	
	
}