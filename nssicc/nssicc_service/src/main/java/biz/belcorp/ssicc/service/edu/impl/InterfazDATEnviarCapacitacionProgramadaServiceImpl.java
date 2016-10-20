/*
 * Created on 09/10/2006 04:27:06 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazMYEEnviarRegionesServiceImpl
 */
package biz.belcorp.ssicc.service.edu.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazDATEnviarCapacitacionProgramadaServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa </a>
 */
@Service("sisicc.interfazDATEnviarCapacitacionProgramadaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazDATEnviarCapacitacionProgramadaServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		String tipoEnvio = (String) params.get("tipoEnvio");
		if (Constants.EDU_TIPO_ENVIO_NORMAL.equals(tipoEnvio))
			interfazSiCCDAO.executeInterfazDATEnviarCapacitacionProgramada(params);
		else
			interfazSiCCDAO.executeInterfazDATEnviarCapacitacionProgramadaReenvio(params);
	}

        
}
