/*
 * Created on 26/09/2006 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazREUEnviarDocumentosAnuladosServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazRETEnviarInformacionVentaServiceImpl.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:">Jose Luis Rodriguez</a>
 */
@Service("sisicc.interfazRETEnviarInformacionVentaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazRETEnviarInformacionVentaServiceImpl extends
   BaseInterfazSalidaStoredProcedureAbstractService {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO.executeInterfazRETEnviarInformacionVenta(params);
		
	}

}
