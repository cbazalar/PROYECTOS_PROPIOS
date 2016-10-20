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

public interface InterfazHIPDAO extends DAO {

	
	/**
	 * Inserta los registros de ventas
	 * @param row
	 */
	public void insertInterfazHIPRecepcionarRegistroVentas(Map row);

	/**executa el proceso de recpcion RUV
	 * @param params
	 */
	public void executeRecepcionRegistroVentas(Map params);

}
