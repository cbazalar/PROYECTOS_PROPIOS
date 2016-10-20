package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazAVIDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service para el envio de Consultoras Bloqueadas de la Interfaz AVI.
 * 
 * @author <a href="mailto:cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 */

@Service("sisicc.InterfazAVIEnviarConsultoraBloqueadaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazAVIEnviarConsultoraBloqueadaServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazAVIDAO")
	private InterfazAVIDAO interfazAVIDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		log.info("Entro a InterfazAVIEnviarConsultoraBloqueadaServiceImpl - executeStoreProcedure(Map)");
		interfazAVIDAO.executeInterfazAVIConsultorasBloqueada(params);
		log.info("Salio a InterfazAVIEnviarConsultoraBloqueadaServiceImpl - executeStoreProcedure(Map)");
	}

	
}


