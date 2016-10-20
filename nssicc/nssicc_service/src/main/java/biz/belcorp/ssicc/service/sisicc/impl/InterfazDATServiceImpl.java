/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazDATDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.sisicc.InterfazDATService;


/**
 * 
 * <p>
 * <a href="InterfazDATServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@belcorp.biz">Cristian Roman</a>
 * 
 */
@Service("sisicc.interfazDATService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazDATServiceImpl extends BaseService implements InterfazDATService {

	@Resource(name="sisicc.interfazDATDAO")
	private InterfazDATDAO interfazDATDAO;
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazDATService#executeCargarTablaInterfaz(java.util.Map)
	 */
	public void executeCargarTablaInterfaz(Map params){
		interfazDATDAO.executeCargarTablaInterfaz(params);
	}

	@Override
	public String getValidacionInterfazDatamart(Map params) {
		return interfazDATDAO.getValidacionInterfazDatamart(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.InterfazDATService#getValidacionCierreCampanyaPendiente(java.util.Map)
	 */
	@Override
	public String getValidacionCierreCampanyaPendiente(Map criteria) {
		return interfazDATDAO.getValidacionCierreCampanyaPendiente(criteria);
	}
	
}