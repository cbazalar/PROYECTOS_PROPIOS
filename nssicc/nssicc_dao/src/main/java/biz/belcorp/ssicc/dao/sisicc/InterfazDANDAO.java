package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
/**
 * <p>
* <a href="InterfazDANDAO.java.html"><i>View Source</i></a>
* </p>
* 
* @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
* 
*/
public interface InterfazDANDAO  extends DAO{

	/**
	 * Ejecuta el envio de campanhas
	 * @param params
	 */
	public void executeInterfazDANEnviarCampanhas(Map params);
	
	/**
	 * Ejecuta el envio de clientes
	 * @param params
	 */
	public void executeInterfazDANEnviarClientes(Map params);
	
	/**
	 * Ejecuta el envio de regiones
	 * @param params
	 */
	public void executeInterfazDANEnviarRegiones(Map params);
	
	/**
	 * Ejecuta el envio de zonas
	 * @param params
	 */
	public void executeInterfazDANEnviarZonas(Map params);	

}