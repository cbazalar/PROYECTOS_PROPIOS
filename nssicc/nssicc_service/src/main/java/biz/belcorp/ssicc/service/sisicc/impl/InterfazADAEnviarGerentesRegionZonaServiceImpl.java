package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazADADAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("sisicc.interfazADAEnviarGerentesRegionZonaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazADAEnviarGerentesRegionZonaServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService{
	
	@Resource(name="sisicc.interfazADADAO")
	private InterfazADADAO interfazADADAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazADADAO.executeInterfazADAEnviarGerentesRegionZona(params);
	}
}