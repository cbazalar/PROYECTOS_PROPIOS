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
 * <a href="InterfazDATEnviarTipoBonoLetServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author Yahir Rivas L.
 */
@Service("sisicc.interfazDATEnviarTipoBonoLetService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazDATEnviarTipoBonoLetServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService{
	
	@Resource(name="sisicc.interfazDATDAO")
	protected InterfazDATDAO interfazDATDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	@Override
	protected void executeStoreProcedure(Map params) {
		interfazDATDAO.executeInterfazDATEnviarTipoBonoLet(params);
		
	}	

}
