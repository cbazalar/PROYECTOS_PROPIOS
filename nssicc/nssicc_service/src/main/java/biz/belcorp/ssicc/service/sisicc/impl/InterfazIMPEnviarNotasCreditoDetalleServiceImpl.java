package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

@Service("sisicc.interfazIMPEnviarNotasCreditoDetalleService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazIMPEnviarNotasCreditoDetalleServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO.executeInterfazIMPEnviarNotasCreditoDetalle(params);
	}
	
}
