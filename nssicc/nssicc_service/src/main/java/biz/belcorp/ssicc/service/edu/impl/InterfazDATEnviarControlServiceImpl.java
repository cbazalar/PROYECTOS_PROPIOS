/*
 * Created on 09/10/2006 04:27:06 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazMYEEnviarRegionesServiceImpl
 */
package biz.belcorp.ssicc.service.edu.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Clase que realiza el servicio de ejecutar una interface
 * <p>
 * <a href="InterfazDATEnviarControlServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cdiaz@csigcomt.com">Carlos Diaz Valverde </a>
 */
@Service("sisicc.interfazDATEnviarControlService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazDATEnviarControlServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		log.info("Entro a InterfazDATEnviarControlServiceImpl - executeStoreProcedure(Map)");
		interfazSiCCDAO.executeInterfazDATEnviarControl(params);
		log.info("Salio a InterfazDATEnviarControlServiceImpl - executeStoreProcedure(Map)");
	}

        
}
