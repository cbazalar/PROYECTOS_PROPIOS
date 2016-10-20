package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazIVRDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazIVREnviarTablaRechazosPostVentaCorporativoServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jos A. Cairampoma</a>
 */
@Service("sisicc.interfazIVREnviarTablaRechazosPostVentaCorporativoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazIVREnviarTablaRechazosPostVentaCorporativoServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {
	
	@Resource(name="sisicc.interfazIVRDAO")
	InterfazIVRDAO interfazIVRDAO;
	

	protected void executeStoreProcedure(Map params) {
		interfazIVRDAO.executeInterfazIVREnviarTablaRechazosPostVentaCorporativo(params);
	}

}
