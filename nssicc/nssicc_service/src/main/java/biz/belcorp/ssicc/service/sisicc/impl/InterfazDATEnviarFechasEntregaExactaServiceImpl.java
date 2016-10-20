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
 * <a href="InterfazDATEnviarFechasEntregaExactaServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author Carlos Bazalar
 */
@Service("sisicc.interfazDATEnviarFechasEntregaExactaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazDATEnviarFechasEntregaExactaServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService{
	
	@Resource(name="sisicc.interfazDATDAO")
	protected InterfazDATDAO interfazDATDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazDATDAO.executeInterfazDATEnviarFechasEntregaExacta(params);
	}	

	
}
