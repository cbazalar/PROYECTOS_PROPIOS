/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
  * <p>
 * <a href="InterfazSAWDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@csigcomt.com">Cristhian Roman</a>
 * 
 */
public interface InterfazSAWDAO extends DAO {
	
	/**
	 * Invoca al Store procedure para generar el archivo de Demanda 
	 * SAP 
	 * @param params
	 */
	public void executeInterfazSAWEnviarDemandaSAP(Map params);
	
	/**
	 * Invoca al Store procedure para generar el archivo de Demanda 
	 * Yobel 
	 * @param params
	 */
	public void executeInterfazSAWEnviarDemandaYobel(Map params);

}
