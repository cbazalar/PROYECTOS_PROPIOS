/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc;


import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazMICService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */


public interface InterfazMICEnvioMicroSegurosPagoCanalesService extends Service {


	/**
	 * Realiza el envio de microseguros
	 * @param params
	 */
	public void executeEnvioMicroSeguroPagoCanales(Map params);
	
}
