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
@Service("sisicc.interfazPaqueteOCRRecepcionarMicaOCSCorpService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPaqueteOCRRecepcionarMicaOCSCorpServiceImpl extends SSiCC_Desfasado_BasePaqueteInterfazServiceImpl {

	@Override
	public void beforeExecutePaqueteInterfaz(Map params) {
		log.debug("execute beforeExecutePaqueteInterfaz");
	}

	@Override
	public SSiCC_Desfasado_InterfazExecutionResult executePaqueteInterfaz(Map params) throws Exception {
		log.debug("execute paquete interfaz");
		return super.executeInterfaz(params);
	}

}