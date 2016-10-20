package biz.belcorp.ssicc.service.sisicc.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazSATDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * <p>
 * <a href="InterfazSATRecepcionarDivisionArmadoCDPServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
@Service("sisicc.interfazSATRecepcionarDivisionArmadoCDPService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSATRecepcionarDivisionArmadoCDPServiceImpl extends BaseInterfazEntradaAbstractService {

	@Resource(name="sisicc.interfazSATDAO")
	protected InterfazSATDAO interfazSATDAO;
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams)throws InterfazException {
		log.debug("Entering 'beforeReadData' method");
		
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());
		interfazSATDAO.executeInterfazSATRecepcionarDivisionArmadoCDP(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#readData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, java.io.File, java.util.List)
	 */
	protected int readData(InterfazParams interfazParams, File tempFile, List data) throws InterfazException {
		return 0;
	}
}