package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCActualizaPedidosIncentivosDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.ProcesoINCActualizaPedidosIncentivosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCActualizaPedidosIncentivosServiceImpl extends BaseInterfazProcesoAbstractService{
	
	@Resource(name="spusicc.procesoINCActualizaPedidosIncentivosDAO")
	private ProcesoINCActualizaPedidosIncentivosDAO procesoINCActualizaPedidosIncentivosDAO;
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		procesoINCActualizaPedidosIncentivosDAO.executeActualizaPedidosIncentivos(params);
	}
}