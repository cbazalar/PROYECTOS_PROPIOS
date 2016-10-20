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
 * <a href="InterfazDATEnviarBoletasRecojoServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jjrios@csigcomt.com">Jesse J. Rios Franco</a>
 */
@Service("sisicc.interfazDATEnviarBoletasRecojoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazDATEnviarBoletasRecojoServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService{

	@Resource(name="sisicc.interfazDATDAO")
	protected InterfazDATDAO interfazDATDAO;
	
	
	protected void executeStoreProcedure(Map params) {
		interfazDATDAO.executeInterfazDATEnviarBoletasRecojo(params);
	}

}
