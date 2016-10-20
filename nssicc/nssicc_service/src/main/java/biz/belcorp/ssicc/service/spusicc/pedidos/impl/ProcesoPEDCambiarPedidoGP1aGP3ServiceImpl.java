package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author Sergio Apaza
 *
 */
@Service("spusicc.procesoPEDCambiarPedidoGP1aGP3Service")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPEDCambiarPedidoGP1aGP3ServiceImpl extends BaseInterfazProcesoAbstractService{

	@Resource(name="spusicc.procesoPEDDAO")
	private ProcesoPEDDAO procesoPEDDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		procesoPEDDAO.executeCambiarPedidoGP1aGP3(params);
	}
	
}
