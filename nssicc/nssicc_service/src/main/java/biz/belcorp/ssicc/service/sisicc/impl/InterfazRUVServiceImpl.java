/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazRUVDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.sisicc.InterfazRUVService;


/**
 * 
 * <p>
 * <a href="InterfazRUVServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
@Service("sisicc.interfazRUVService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazRUVServiceImpl extends BaseService implements InterfazRUVService {

	@Resource(name="sisicc.interfazRUVDAO")
	private InterfazRUVDAO interfazRUVDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazRUVService#ejecutarReprocesoInterfazRUV(java.util.Map)
	 */
	public void ejecutarReprocesoInterfazRUV(Map params) {
		interfazRUVDAO.ejecutarReprocesoInterfazRUV(params);
	}
	
}