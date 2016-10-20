package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

/**
 * DAO de la Interfaz del Programa de Empresarias
 * 
 * @author <a href="mailto:dooliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
public interface InterfazEMPDAO {

	/**
	 * @param params
	 */
	public void executeEnviarMaestroEmpresarias(Map params);	
	
	/**
	 * @param params
	 */
	public void executeEnviarBajasEmpresarias(Map params);
	
	/**
	 * @param params
	 */
	public void executeEnviarVinculosEmpresarias(Map params);
	
}