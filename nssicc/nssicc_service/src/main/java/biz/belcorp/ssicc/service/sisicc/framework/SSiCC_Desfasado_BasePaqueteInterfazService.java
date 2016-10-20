package biz.belcorp.ssicc.service.sisicc.framework;

import java.util.Map;

import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;

/**
 * DESFASADO
 */
public interface SSiCC_Desfasado_BasePaqueteInterfazService {


	/**
	 * metodo a invcar despues de ejecutar la interfaz
	 * @param params
	 * @param result 
	 * @throws Exception 
	 */
	public void afterExecutePaqueteInterfaz(Map params, SSiCC_Desfasado_InterfazExecutionResult result) throws Exception;
	
	
	public SSiCC_Desfasado_InterfazExecutionResult executeInterfaz(Map params) throws Exception;
	
	
	public void setInterfazExecutionService(
			SSiCC_Desfasado_InterfazExecutionService interfazExecutionService);
	
	
}