package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazADADAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("sisicc.interfazADADAO")
public class InterfazADADAOiBatis extends BaseDAOiBatis implements InterfazADADAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazADADAO#executeInterfazADAEnviarGerentesRegionZona(java.util.Map)
	 */
	public void executeInterfazADAEnviarGerentesRegionZona(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazADASQL.executeInterfazADAEnviarGerentesRegionZona", params);
	}
}