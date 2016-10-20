package biz.belcorp.ssicc.service.sisicc.framework.beans;

import java.util.concurrent.Callable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import biz.belcorp.ssicc.service.sisicc.framework.impl.SSiCC_Desfasado_InterfazExecutionServiceImpl;

/* POR REESTRUCTURAR NSSICC */
/**
 * Clase Hilo la cual sera utilizada para las Interfaces MultiHilo que devuelve un Resultado
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 * 
 */
public class SSiCC_Desfasado_BaseMultiHiloInterfazResult implements Callable<SSiCC_Desfasado_InterfazExecutionResult>   {
	
	private SSiCC_Desfasado_InterfazExecutionServiceImpl interfazExecutionService;
	private InterfazMultiHiloParams interfazMultiHiloParams;
	
	protected transient final Log log = LogFactory.getLog(getClass());
	
	/**
	 * @param interfazService
	 * @param interfazMultiHiloParams
	 */
	public SSiCC_Desfasado_BaseMultiHiloInterfazResult(SSiCC_Desfasado_InterfazExecutionServiceImpl interfazService, InterfazMultiHiloParams interfazMultiHiloParams) {
		this.interfazExecutionService = interfazService;
		this.interfazMultiHiloParams = interfazMultiHiloParams;
	}
	
	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	/* POR MODIFICAR NSSICC */
	public SSiCC_Desfasado_InterfazExecutionResult call() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'call()' method");
		}
		SSiCC_Desfasado_InterfazExecutionResult result = this.interfazExecutionService.executeMultiHiloResult(interfazMultiHiloParams);
    	if (log.isDebugEnabled()) {
			log.debug("Endering 'call()' method");
		}
    	return result;
	}

}
