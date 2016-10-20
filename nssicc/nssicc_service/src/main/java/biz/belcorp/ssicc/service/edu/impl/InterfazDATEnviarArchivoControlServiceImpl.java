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
 * TODO Include class description here.
 * <p>
 * <a href="InterfazDATEnviarArchivoControlServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:rvela@belcorp.biz">Robinson Vela Bardales </a>
 */
@Service("sisicc.interfazDATEnviarArchivoControlService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazDATEnviarArchivoControlServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO.executeInterfazDATEnviarArchivoControl(params);
	}

        
}
