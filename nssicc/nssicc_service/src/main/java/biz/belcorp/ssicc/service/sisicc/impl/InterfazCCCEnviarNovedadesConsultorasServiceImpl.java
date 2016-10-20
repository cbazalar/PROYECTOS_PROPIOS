package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazCCCDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service de la Interfaz de Enviar Novedades Consultora
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio</a>
 */
@Service("sisicc.interfazCCCEnviarNovedadesConsultorasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazCCCEnviarNovedadesConsultorasServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazCCCDAO")
	protected InterfazCCCDAO interfazCCCDAO;

	

	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
     */
    protected void executeStoreProcedure(Map params) {
    	
    	if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'executeStoreProcedure' method");
			log.debug(params.toString() );
		}
    	
    	interfazCCCDAO.executeInterfazCCCEnviarNovedadesConsultoras(params);
    }
}