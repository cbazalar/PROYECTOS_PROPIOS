/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.impl.SSiCC_Desfasado_BasePaqueteInterfazServiceImpl;

/**
 * 
 * <p>
 * <a href="InterfazPaqueteOCRRecepcionarOCSServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */
public class InterfazPaqueteOCRRecepcionarPostVentaServiceImpl extends SSiCC_Desfasado_BasePaqueteInterfazServiceImpl {

	@Override
	public void beforeExecutePaqueteInterfaz(Map params) {
		log.debug("execute beforeExecutePaqueteInterfaz");
	}

	@Override
	public SSiCC_Desfasado_InterfazExecutionResult executePaqueteInterfaz(Map params) throws Exception {
		log.debug("execute paquete interfaz");
		return super.executeInterfaz(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BasePaqueteInterfazService#afterExecutePaqueteInterfaz(java.util.Map, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazExecutionResult)
	 */
	public void afterExecutePaqueteInterfaz(Map params, SSiCC_Desfasado_InterfazExecutionResult result) throws Exception{
		super.afterExecutePaqueteInterfaz(params, result);			
	}
	
	
}