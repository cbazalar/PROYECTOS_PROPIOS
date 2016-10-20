package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazMYEDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

@Service("sisicc.interfazMYEEnviarClientesWebService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazMYEEnviarClientesWebServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazMYEDAO")
	private InterfazMYEDAO interfazMYEDAO;
	


	@Override
	protected void executeStoreProcedure(Map params) {
		interfazMYEDAO
		.executeInterfazMYEEnviarClientesWeb(params);
		
	}


}
