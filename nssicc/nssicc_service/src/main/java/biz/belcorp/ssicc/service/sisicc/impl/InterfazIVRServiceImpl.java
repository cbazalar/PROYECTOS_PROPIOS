/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazIVRDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.sisicc.InterfazIVRService;


/**
 * 
 * <p>
 * <a href="InterfazRECServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:j@belcorp.biz">Cristian Roman</a>
 * 
 */
@Service("sisicc.interfazIVRService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazIVRServiceImpl extends BaseService implements InterfazIVRService {

    
	@Resource(name="sisicc.interfazIVRDAO")
	private InterfazIVRDAO interfazIVRDAO;

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazIVRService#deleteTablaControlIVRCorporativo()
	 */
	public void deleteTablaControlIVRCorporativo() {
		interfazIVRDAO.deleteTablaControlIVRCorporativo();
		
	}

	
	
	
	
}