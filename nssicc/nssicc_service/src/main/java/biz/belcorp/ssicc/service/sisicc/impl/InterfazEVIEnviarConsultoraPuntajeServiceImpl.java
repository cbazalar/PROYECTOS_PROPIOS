package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazEVIDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service para el envio de Puntajes de Consultoras de la Interfaz EVI.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
@Service("sisicc.interfazEVIEnviarConsultoraPuntajeService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazEVIEnviarConsultoraPuntajeServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazEVIDAO")
	private InterfazEVIDAO interfazEVIDAO;

	protected void executeStoreProcedure(Map params) {
		interfazEVIDAO.executeInterfazEVIConsultoraPuntaje(params);
	}

	
}
