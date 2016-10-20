package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service del Envio de Consultoras de la Interfaz Reutilizacin.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
@Service("sisicc.interfazREUEnviarConsultorasService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)

public class InterfazREUEnviarConsultorasServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {
	/**
	 * Obtiene la data para el Envio de Consultoras de la Interfaz Reutilizacin.
	 * 
	 * @param queryParams
	 *            parametros del query.
	 * @return List con Maps con los datos de las Consultoras
	 * @throws InterfazException
	 */
	
	@Resource(name="sisicc.interfazSiCCService")
	private InterfazSiCCService interfazSiCCService;

	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO.executeInterfazREUEnviarConsultoras(params);
	}

	@Override
	protected void afterExecuteInterfaz(Map params) throws Exception {

		super.afterExecuteInterfaz(params);
		if (log.isDebugEnabled()) {
			log.debug("Dentro del metodo 'afterExecuteInterfaz'");
		}

//		if (interfazExecutionResult.ejecucionCompletada()) {
//			interfazSiCCService.updateInterfazREUIndicadorTransferenciaClientes();
//		}
		log.debug("Fin 'afterExecuteInterfaz' method");

	}

}