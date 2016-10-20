package biz.belcorp.ssicc.service;

import java.util.Map;

/**
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
public interface SapConnectorService {
	public Map execute(String functionName, Map functionParams,
			String[] outputParams) throws Exception;
}
