/*
 * Created on 09/10/2006 04:27:06 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazMYEEnviarRegionesServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazDATDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazDATEnviarEmpresaSiCCServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:	sbuchelli@belcorp.biz">Sergio Buchelli </a>
 */
@Service("sisicc.interfazDATEnviarEmpresaSiCCService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazDATEnviarEmpresaSiCCServiceImpl extends
	BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazDATDAO")
	protected InterfazDATDAO interfazDATDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazDATDAO.executeInterfazDATEnviarEmpresaSiCC(params);
	}

    
    
}
