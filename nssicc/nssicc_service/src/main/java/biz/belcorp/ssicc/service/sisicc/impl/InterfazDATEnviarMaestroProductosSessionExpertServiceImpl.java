package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazDATEnviarMaestroProductosSessionExpertServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jos A. Cairampoma</a>
 */
@Service("sisicc.interfazDATEnviarMaestroProductosSessionExpertService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazDATEnviarMaestroProductosSessionExpertServiceImpl extends
	BaseInterfazSalidaStoredProcedureAbstractService {

	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO
				.executeInterfazDATEnviarMaestroProductosSessionExpert(params);
	}


}
