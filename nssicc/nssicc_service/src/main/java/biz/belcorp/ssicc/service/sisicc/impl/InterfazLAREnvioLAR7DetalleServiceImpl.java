package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazLARDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service InterfazLAREnvioLAR7DetalleServiceImpl.
 * 
 * @author <a href="mailto:doliva@csigcomt.com">Dennys Oliva Iriarte</a>
 */
@Service("sisicc.interfazLAREnvioLAR7DetalleService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazLAREnvioLAR7DetalleServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {
		
	@Resource(name="sisicc.interfazLARDAO")
	protected InterfazLARDAO interfazLARDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		//tipoPeriodo
		params.put("tipoPeriodo",Constants.TIPO_PERIOODO_CORPORATIVO_CAMPANIA);
		interfazLARDAO.executeInterfazLAREnvioLAR7Detalle(params);
	}	
	
}