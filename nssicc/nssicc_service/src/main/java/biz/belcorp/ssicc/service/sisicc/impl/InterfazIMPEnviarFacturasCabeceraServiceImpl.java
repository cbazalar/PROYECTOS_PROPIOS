package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazIMPEnviarFacturasCabeceraImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jperez@sigcomt.com">Juan Carlos Prez </a>
 */
@Service("sisicc.interfazIMPEnviarFacturasCabeceraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazIMPEnviarFacturasCabeceraServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO.executeInterfazIMPEnviarFacturasCabecera(params);
	}
    
}
