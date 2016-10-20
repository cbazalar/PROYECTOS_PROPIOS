package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazCOBDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.sisicc.InterfazCOBGenerarInformacionDatacreditoService;

/**
 * 
 * <p>
 * <a href="InterfazCOBGenerarInformacionDatacreditoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 * 
 */
@Service("sisicc.interfazCOBGenerarInformacionDatacreditoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazCOBGenerarInformacionDatacreditoServiceImpl extends BaseService implements InterfazCOBGenerarInformacionDatacreditoService{
	
	@Resource(name="sisicc.interfazCOBDAO")
	private InterfazCOBDAO interfazCOBDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazCOBGenerarInformacionDatacreditoService#executeInterfazCOBGenerarInformacionDatacredito(java.util.Map)
	 */
	public void executeInterfazCOBGenerarInformacionDatacredito(Map params) {
		interfazCOBDAO.executeInterfazCOBGenerarInformacionDatacredito(params);
	}
}