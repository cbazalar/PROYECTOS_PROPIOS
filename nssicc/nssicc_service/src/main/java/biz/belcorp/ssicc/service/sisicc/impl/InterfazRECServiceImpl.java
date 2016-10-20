/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazRECDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.sisicc.InterfazRECService;


/**
 * 
 * <p>
 * <a href="InterfazRECServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:j@belcorp.biz">Cristian Roman</a>
 * 
 */
@Service("sisicc.interfazRECService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazRECServiceImpl extends BaseService implements InterfazRECService {

	@Resource(name="sisicc.interfazRECDAO")
	private InterfazRECDAO interfazRECDAO;

		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazRECService#updateInterfazEnviarTransferenciaBoletasRecojoExitosa(java.util.Map)
	 */
	public void updateInterfazEnviarTransferenciaBoletasRecojoExitosa(Map params) {

		interfazRECDAO.updateInterfazEnviarTransferenciaBoletasRecojo(params);
		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazRECService#updateInterfazRECEnviarUnidadesAlmacenVirtualExitosa(java.util.Map)
	 */
	public void updateInterfazRECEnviarUnidadesAlmacenVirtualExitosa(Map params) {

		interfazRECDAO.updateInterfazEnvioAlmacenVirtual(params);
		
	}
	
	
	
	
}