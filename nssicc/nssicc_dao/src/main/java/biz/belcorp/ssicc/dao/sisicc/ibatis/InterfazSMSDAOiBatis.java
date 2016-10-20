/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazSMSDAO;

/**
 * 
 * <p>
 * <a href="InterfazSMSDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
@Repository("sisicc.interfazSMSDAO")
public class InterfazSMSDAOiBatis extends BaseDAOiBatis implements
	InterfazSMSDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSMSDAO#executeInterfazSMSEnviarConsultoras(java.util.Map)
	 */
	public void executeInterfazSMSEnviarConsultoras(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazSMSSQL.executeInterfazSMSEnviarConsultoras",params);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSMSDAO#executeInterfazSMSEnviarPedidosConsultoras(java.util.Map)
	 */
	public void executeInterfazSMSEnviarPedidosConsultoras(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazSMSSQL.executeInterfazSMSEnviarPedidosConsultoras",params);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSMSDAO#executeInterfazSMSEnviarMensajesTextoConsultoras(java.util.Map)
	 */
	public void executeInterfazSMSEnviarMensajesTextoConsultoras(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSMSSQL.executeInterfazSMSEnviarMensajesTextoConsultoras",params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSMSDAO#executeInterfazSMSEnviarConsultorasPagosCCRecaudoBancario(java.util.Map)
	 */
	public void executeInterfazSMSEnviarConsultorasPagosCCRecaudoBancario(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSMSSQL.executeInterfazSMSEnviarConsultorasPagosCCRecaudoBancario",params);
	}
	
}
