package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
/**
 * <p>
* <a href="InterfazSGRDAO.java.html"><i>View Source</i></a>
* </p>
* 
* @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
* 
*/
public interface InterfazSGRDAO  extends DAO{

	/**
	 * Proceso que se encarga del envio d elas polizas que han sido registradas
	 * @param params
	 */
	void executeInterfazSGREnviarPolizasRegistradas(Map params);

	/**
	 * Proceso que se encarga de enviar las canceladas por egreso
	 * @param params
	 */
	void executeInterfazSGREnviarCanceladasPorEgreso(Map params);

	/**
	 * Proceso que se encarga de enviar las polizas vigentes
	 * @param params
	 */
	void executeInterfazSGREnviarPolizasVigentes(Map params);

	/**
	 * Proceo que se encarga de enviar los errores de la poliza
	 * @param params
	 */
	void executeInterfazSGREnviarErroresPoliza(Map params);

	/**
	 * Proceso que se encraga de validar los registros recepcionados
	 * y grabar en la tabla de log de errors si sucede alguno
	 * @param row
	 */
	void executeInterfazSGRRecepcionarPolizasCanceladas(Map row);


}