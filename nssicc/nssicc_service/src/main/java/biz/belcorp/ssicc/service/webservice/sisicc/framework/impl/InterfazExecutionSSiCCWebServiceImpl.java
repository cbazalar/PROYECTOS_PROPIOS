package biz.belcorp.ssicc.service.webservice.sisicc.framework.impl;

import java.util.Map;

import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.webservice.sisicc.framework.InterfazExecutionSSiCCWebService;

/**
 *  @author Carlos Bazalar
 *
 */
public class InterfazExecutionSSiCCWebServiceImpl extends InterfazExecutionWebServiceImpl implements InterfazExecutionSSiCCWebService {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.webservice.sisicc.framework.impl.InterfazExecutionWebServiceImpl#executeInterfaz(java.util.Map)
	 */
	public InterfazExecutionResult executeInterfaz(Map<String, Object> params) throws Exception {
		return this.executeInterfaz(params);
	}
	 
}
