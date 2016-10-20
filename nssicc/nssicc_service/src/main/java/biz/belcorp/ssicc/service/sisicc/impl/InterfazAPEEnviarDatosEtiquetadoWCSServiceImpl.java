package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazAPEDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * <p>
 * <a href="InterfazAPEEnviarDatosEtiquetadoWCSServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:nlopez@csigcomt.com">Nicols Lpez</a>
 */
@Service("sisicc.interfazAPEEnviarDatosEtiquetadoWCSService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazAPEEnviarDatosEtiquetadoWCSServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazAPEDAO")
	protected InterfazAPEDAO interfazAPEDAO;
	
    	
	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
     */
    protected void executeStoreProcedure(Map params) {
    	interfazAPEDAO.executeInterfazAPEEnviarDatosEtiquetadoWCS(params);
    }    
    
}
