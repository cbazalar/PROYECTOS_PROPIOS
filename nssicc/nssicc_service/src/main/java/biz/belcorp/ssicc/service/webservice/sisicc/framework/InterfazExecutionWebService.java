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
public interface InterfazExecutionWebService {

	/**
	 * Ejecuta la Interfaz SiSiCC desde un WEBSERVICE
	 * 
	 * @param params
	 *            parametros de la interfaz
	 * @return List de InterfazResult con los resultados de la ejecucion.
	 */
	public InterfazExecutionResult executeInterfaz(Map<String, Object> params) throws Exception;
	
}
