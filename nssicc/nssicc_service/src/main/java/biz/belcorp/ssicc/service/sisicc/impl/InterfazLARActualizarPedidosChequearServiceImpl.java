package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazLARDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazLAREnviarPedidosChequearServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="}">Jos Luis Rodriguez</a>
 */
@Service("sisicc.interfazLARActualizarPedidosChequear")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazLARActualizarPedidosChequearServiceImpl extends BaseInterfazProcesoAbstractService {

	@Resource(name="sisicc.interfazLARDAO")
	InterfazLARDAO InterfazLARDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		InterfazLARDAO
				.executeActualizarPedidosChequear(params);
	}


}
