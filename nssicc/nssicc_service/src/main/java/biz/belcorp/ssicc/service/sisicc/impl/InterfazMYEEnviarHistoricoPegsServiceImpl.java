package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service de la Interfaz de Envio de interfaz de historico de posibles egressos
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */
@Service("sisicc.interfazMYEEnviarHistoricoPegsService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazMYEEnviarHistoricoPegsServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO
				.executeInterfazMYEEnviarHistoricoPegs(params);
	}
}