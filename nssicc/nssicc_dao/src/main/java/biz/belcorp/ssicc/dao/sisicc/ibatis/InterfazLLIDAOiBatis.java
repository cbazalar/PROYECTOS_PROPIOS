package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazLLIDAO;

/**
 * 
 * <p>
 * <a href="InterfazLLIDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
@Repository("sisicc.interfazLLIDAO")
public class InterfazLLIDAOiBatis extends BaseDAOiBatis implements InterfazLLIDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSABDAO#executeInterfazLLIEnviarVentaPeriodo(java.util.Map)
	 */
	public void executeInterfazLLIEnviarVentaPeriodo(Map params) {
		getSqlMapClientTemplate().
		update("sisicc.InterfazLLISQL.executeInterfazLLIEnviarVentaPeriodo",params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSABDAO#executeInterfazLLIEnviarVentaRealDiariaAcumulada(java.util.Map)
	 */
	public void executeInterfazLLIEnviarVentaRealDiariaAcumulada(Map params) {
		getSqlMapClientTemplate().
		update("sisicc.InterfazLLISQL.executeInterfazLLIEnviarVentaRealDiariaAcumulada",params);		
	}
	
}
