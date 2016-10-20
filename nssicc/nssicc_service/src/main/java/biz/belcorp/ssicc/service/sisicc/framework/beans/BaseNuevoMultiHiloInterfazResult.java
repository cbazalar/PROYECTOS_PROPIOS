package biz.belcorp.ssicc.service.sisicc.framework.beans;

import java.util.concurrent.Callable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazPaqueteAbstractServiceImpl;

/* NUEVA REESTRUCTURACION NSSICC */
/**
 * Clase Hilo la cual sera utilizada para las Interfaces MultiHilo que devuelve un Resultado
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 * 
 */
public class BaseNuevoMultiHiloInterfazResult implements Callable<InterfazExecutionResult>   {
	
	private BaseInterfazPaqueteAbstractServiceImpl baseInterfazPaqueteAbstractService;
	private InterfazNuevoMultiHiloParams interfazNuevoMultiHiloParams;
	private InterfazParams interfazParamsPaquete;
	
	protected transient final Log log = LogFactory.getLog(getClass());
	
	/**
	 * @param interfazService
	 * @param interfazMultiHiloParams
	 */
	public BaseNuevoMultiHiloInterfazResult(
			          BaseInterfazPaqueteAbstractServiceImpl basePaqueteInterfazAbstractService, 
			          InterfazParams interfazParamsPaquete, 
			          InterfazNuevoMultiHiloParams interfazNuevoMultiHiloParams) {
		this.baseInterfazPaqueteAbstractService = basePaqueteInterfazAbstractService;
		this.interfazParamsPaquete = interfazParamsPaquete;
		this.interfazNuevoMultiHiloParams = interfazNuevoMultiHiloParams;
	}
	
	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	public InterfazExecutionResult call() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'call()' method");
		}
		InterfazExecutionResult result = this.baseInterfazPaqueteAbstractService.executeMultiHiloResult(interfazNuevoMultiHiloParams, interfazParamsPaquete);
    	if (log.isDebugEnabled()) {
			log.debug("Endering 'call()' method");
		}
    	return result;
	}

}
