package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazCOBDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * @author Gonzalo Javier Huertas Agurto
 *
 */
@Service("sisicc.interfazCOBEnviarInformacionTransUnionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazCOBEnviarInformacionTransUnionServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService{
	
	@Resource(name="sisicc.interfazCOBDAO")
	protected InterfazCOBDAO interfazCOBDAO;
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazCOBDAO.executeInterfazCOBEnviarInformacionTransUnion(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazAbstractService#beforeExecuteInterfaz(java.util.Map)
	 */
	@Override
	protected void beforeExecuteInterfaz(Map params) throws Exception {
		// Antes de generar la interface se debe invocar al siguiente store procedure:
		// INT_PKG_COB. INT_PR_COB_ENVIO_TRANS_UNION
		interfazCOBDAO.executeInterfazCOBEnviarInformacionTU(params);
	}
}