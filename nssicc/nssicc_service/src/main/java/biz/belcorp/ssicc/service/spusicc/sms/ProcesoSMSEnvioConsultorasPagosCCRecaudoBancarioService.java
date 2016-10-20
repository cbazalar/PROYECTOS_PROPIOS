/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.sms;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * <p>
 * <a href="ProcesoSMSEnvioConsultorasPagosCCRecaudoBancarioService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sguerra@sigcomt.com"> </a>
 */
public interface ProcesoSMSEnvioConsultorasPagosCCRecaudoBancarioService extends Service {

	/**
	 * Ejecuta la interfaz envia a consultora los pagos de cuentas corrientes por recaudaci�n bancaria
	 * @throws Exception
	 */
	public void executeInterfazSMSEnviarConsultorasPagosCCRecaudoBancario() throws Exception;
}
