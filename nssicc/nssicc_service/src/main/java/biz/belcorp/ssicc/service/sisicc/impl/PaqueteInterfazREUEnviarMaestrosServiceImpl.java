package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazPaqueteAbstractServiceImpl;

@Service("sisicc.paqueteInterfazREUEnviarMaestrosService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PaqueteInterfazREUEnviarMaestrosServiceImpl extends
		BaseInterfazPaqueteAbstractServiceImpl {

	@Resource(name = "sisicc.interfazSiCCService")
	private InterfazSiCCService interfazSiCCService;

	@Override
	protected void afterExecuteInterfaz(Map params) throws Exception {
		// TODO Auto-generated method stub
		super.afterExecuteInterfaz(params);
		if (log.isDebugEnabled()) {
			log.debug("Dentro del metodo 'afterExecuteInterfaz'");
		}
		// Validamos el resultado de la ejecucion
		if (interfazExecutionResult.ejecucionCompletada()) {
			interfazSiCCService.updateInterfazREUIndicadorTransferenciaClientes();
		}

	}

}
