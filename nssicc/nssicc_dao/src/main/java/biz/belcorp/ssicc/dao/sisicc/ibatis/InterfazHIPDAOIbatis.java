/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;


import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazHIPDAO;


/**
 * 
 * <p>
 * <a href="InterfazHIPDAOIbatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */
@Repository("sisicc.interfazHIPDAO")
public class InterfazHIPDAOIbatis extends BaseDAOiBatis implements InterfazHIPDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazHIPDAO#insertInterfazHIPRecepcionarRegistroVentas(java.util.Map)
	 */
	public void insertInterfazHIPRecepcionarRegistroVentas(Map row) {
		getSqlMapClientTemplate().insert("sisicc.InterfazHIPSQL.insertInterfazHIPRecepcionarRegistroVentas"
				, row);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazHIPDAO#executeRecepcionRegistroVentas(java.util.Map)
	 */
	public void executeRecepcionRegistroVentas(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazHIPSQL.executeRecepcionRegistroVentas"
				, params);		
	}
	
}	