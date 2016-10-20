/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;


import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;


/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazMICDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */

public interface InterfazMICDAO extends DAO {

	/**
	 * Realiza el envio de microseguros
	 * @param params
	 */
	public void executeEnvioMicroSeguroPagoCanales(Map params);

	/**
	 * Inserta los pagos de microseguros
	 * @param row
	 */
	public void insertInterfazMICRecepcionarPagos(Map row);

	/**
	 * Realeiza el envio de informacion Aseguradora
	 * @param params
	 */
	public void executeEnvioInformacionAseguradora(Map params);
}
