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
 * <a href="InterfazDATService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Cristian Roman</a>
 * 
 */


public interface InterfazRECService extends Service {

	/**
	 * Actualiza los indicadores de Envio a Fisico en la Boletas de Recojo Procesadas
	 * @param params 
	 * @param params
	 */
	public void updateInterfazEnviarTransferenciaBoletasRecojoExitosa(Map params);

	/**
	 * @param params
	 */
	public void updateInterfazRECEnviarUnidadesAlmacenVirtualExitosa(Map params);
	
}
