package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCEvaluacionNoPasoPedidoDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso que evalua si la consultora no paso pedido en un rango de periodos
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoINCEvaluacionNoPasoPedidoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCEvaluacionNoPasoPedidoServiceImpl extends
	BaseInterfazProcesoAbstractService {
	           
	@Resource(name="spusicc.procesoINCEvaluacionNoPasoPedidoDAO")
	private ProcesoINCEvaluacionNoPasoPedidoDAO procesoINCEvaluacionNoPasoPedidoDAO;

	protected void executeStoreProcedure(Map params) {
		procesoINCEvaluacionNoPasoPedidoDAO.executeEvaluacionNoPasoPedido(params);
	}
	

	
}
