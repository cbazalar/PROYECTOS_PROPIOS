/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="InterfazSMSDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
public interface InterfazSMSDAO extends DAO {

	/**
	 * executa la interfaz de envio de Consultoras del programa SMS
	 * @param parametros
	 */
	public void executeInterfazSMSEnviarConsultoras(Map parametros);
	
	/**
	 * executa la interfaz de envio de los Pedidos de las Consultoras del programa SMS
	 * @param parametros
	 */
	public void executeInterfazSMSEnviarPedidosConsultoras(Map parametros);
	
	/**
	 * executa la interfaz de Envio de mensajes a Consultoras
	 * @param parametros
	 */
	public void executeInterfazSMSEnviarMensajesTextoConsultoras(Map parametros);

	/**
	 * executa la interfaz de Envio a consultora de pagos de sus cuentas corrientes por recaudo bancario
	 * @param params
	 */
	public void executeInterfazSMSEnviarConsultorasPagosCCRecaudoBancario(Map params);
	
}
