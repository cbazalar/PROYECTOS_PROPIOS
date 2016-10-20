package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazLARDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazLAREnviarArchivoTIS2ServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="}">Ivan Tocto</a>
 */
@Service("sisicc.interfazLAREnviarArchivoTIS2Service")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazLAREnviarArchivoTIS2ServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazLARDAO")
	InterfazLARDAO InterfazLARDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		InterfazLARDAO.executeInterfazLAREnviarArchivoTIS2(params);
	}

}
