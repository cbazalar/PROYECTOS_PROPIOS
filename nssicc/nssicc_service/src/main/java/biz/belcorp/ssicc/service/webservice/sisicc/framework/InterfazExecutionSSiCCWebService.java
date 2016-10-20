/**
 * 
 */
package biz.belcorp.ssicc.service.webservice.sisicc.framework;

import java.util.Map;

import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;

/**
 * @author Carlos Bazalar
 *  
 */

public interface InterfazExecutionSSiCCWebService {

	public InterfazExecutionResult executeInterfaz(Map<String, Object> params) throws Exception;
	
}
