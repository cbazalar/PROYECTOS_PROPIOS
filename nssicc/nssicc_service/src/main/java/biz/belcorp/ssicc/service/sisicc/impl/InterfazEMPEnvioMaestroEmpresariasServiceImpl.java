 package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazEMPDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * @author dooliva
 * 
 */
@Service("sisicc.interfazEMPEnvioMaestroEmpresariasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazEMPEnvioMaestroEmpresariasServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {
	
	@Resource(name="sisicc.interfazEMPDAO")
	private InterfazEMPDAO interfazEMPDAO;
	
	protected void executeStoreProcedure(Map params) {
		interfazEMPDAO.executeEnviarMaestroEmpresarias(params);
	}
}
