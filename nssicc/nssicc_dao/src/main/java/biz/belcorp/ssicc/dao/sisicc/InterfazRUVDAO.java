/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
* <p>
* <a href="InterfazRUVDAO.java.html"> <i>View Source</i> </a>
* </p>
* 
* @author <a href="mailto:ttataje@csigcomt.com">Telly Tataje</a>
* 
*/
public interface InterfazRUVDAO extends DAO {

	/**
	 * @param params
	 */
	public void executeInterfazRUVEnviarRegistroUnicoVentas(Map params);
	
	/**
	 * Ejecuta el reproceso de las interfaces RUV
	 * @param params
	 */
	public void ejecutarReprocesoInterfazRUV(Map params);
}
