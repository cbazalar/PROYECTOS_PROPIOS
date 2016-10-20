/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;


import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazMICDAO;


/**
 * 
 * <p>
 * <a href="InterfazMICDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */

@Repository("sisicc.interfazMICDAO")
public class InterfazMICDAOIbatis extends BaseDAOiBatis implements InterfazMICDAO {


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazMICDAO#executeEnvioMicroSeguroPagoCanales(java.util.Map)
	 */
	public void executeEnvioMicroSeguroPagoCanales(Map params) {
		getSqlMapClientTemplate().
		update("sisicc.InterfazMICSQL.executeEnvioMicroSeguroPagoCanales",params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazMICDAO#insertInterfazMICRecepcionarPagos(java.util.Map)
	 */
	public void insertInterfazMICRecepcionarPagos(Map row) {
		getSqlMapClientTemplate().insert("sisicc.InterfazMICSQL.insertInterfazMICRecepcionarPagos"
				, row);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazMICDAO#executeEnvioInformacionAseguradora(java.util.Map)
	 */
	public void executeEnvioInformacionAseguradora(Map params) {
		getSqlMapClientTemplate().
		update("sisicc.InterfazMICSQL.executeEnvioInformacionAseguradora",params);
		
	}
}	