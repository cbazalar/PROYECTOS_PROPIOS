package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazSGRDAO;

/**
 * <p>
 * <a href="InterfazSGRDAOiBatis.java.html"><i>View Source</i></a>
 * </p>
 * @author Sergio Buchelli Silva
 *
 */
@Repository("sisicc.interfazSGRDAO")
public class InterfazSGRDAOiBatis extends BaseDAOiBatis implements InterfazSGRDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSGRDAO#executeInterfazSGREnviarCanceladasPorEgreso(java.util.Map)
	 */
	public void executeInterfazSGREnviarCanceladasPorEgreso(Map params) {
		getSqlMapClientTemplate().
		update("sisicc.InterfazSGRSQL.executeEnviarCanceladasPorEgreso",params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSGRDAO#executeInterfazSGREnviarPolizasRegistradas(java.util.Map)
	 */
	public void executeInterfazSGREnviarPolizasRegistradas(Map params) {
		getSqlMapClientTemplate().
		update("sisicc.InterfazSGRSQL.executeEnviarPolizasRegistradas",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSGRDAO#executeInterfazSGREnviarPolizasVigentes(java.util.Map)
	 */
	public void executeInterfazSGREnviarPolizasVigentes(Map params) {
		getSqlMapClientTemplate().
		update("sisicc.InterfazSGRSQL.executeEnviarPolizasVigentes",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSGRDAO#executeInterfazSGREnviarErroresPoliza(java.util.Map)
	 */
	public void executeInterfazSGREnviarErroresPoliza(Map params) {
		getSqlMapClientTemplate().
		update("sisicc.InterfazSGRSQL.executeEnviarErroresPoliza",params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSGRDAO#executeInterfazSGRRecepcionarPolizasCanceladas(java.util.Map)
	 */
	public void executeInterfazSGRRecepcionarPolizasCanceladas(Map row) {
		getSqlMapClientTemplate().
		update("sisicc.InterfazSGRSQL.executeInterfazSGRRecepcionarPolizasCanceladas",row);
		
	}
	

}