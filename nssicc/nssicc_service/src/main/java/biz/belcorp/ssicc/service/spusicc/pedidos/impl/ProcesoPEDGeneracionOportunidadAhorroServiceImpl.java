package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO;

@Service("sisicc.procesoPEDGeneracionOportunidadAhorroService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPEDGeneracionOportunidadAhorroServiceImpl extends BaseInterfazProcesoAbstractService {

	@Resource(name="spusicc.procesoPEDDAO")
	private ProcesoPEDDAO procesoPEDDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) throws InterfazException, Exception {
		procesoPEDDAO.executeProcesoGeneracionOportunidadAhorro(params);
		
		if(log.isDebugEnabled())
			log.debug(params);
	}
	
	
}
