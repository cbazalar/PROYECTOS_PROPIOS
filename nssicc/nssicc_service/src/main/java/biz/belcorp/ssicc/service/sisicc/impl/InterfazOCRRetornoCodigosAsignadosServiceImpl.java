package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazOCRDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * <p>
 * <a href="InterfazOCRRetornoCodigosAsignadosServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cluque@csigcomt.com">Christian Luque</a>
 */
@Service("sisicc.interfazOCRRetornoCodigosAsignadosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazOCRRetornoCodigosAsignadosServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazOCRDAO")
	protected InterfazOCRDAO interfazOCRDAO;
	    
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
     */
    protected void executeStoreProcedure(Map params) {
    	interfazOCRDAO.executeInterfazOCRRetornoCodigosAsignados(params);
    }       
}
