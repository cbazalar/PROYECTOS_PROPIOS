package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazPOLDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service InterfazPOLEnviarMatrizFacturacionServiceImpl.
 * 
 * @author <a href="mailto:dtorres@sigcomt.com">Diego Torres L.</a>
 */
@Service("sisicc.interfazPOLEnviarMatrizFacturacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPOLEnviarMatrizFacturacionServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {
	
	@Resource(name="sisicc.interfazPOLDAO")
	private InterfazPOLDAO interfazPOLDAO;
	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		this.interfazPOLDAO.executeInterfazPOLEnviarMatrizFacturacion(params);
	}	


}