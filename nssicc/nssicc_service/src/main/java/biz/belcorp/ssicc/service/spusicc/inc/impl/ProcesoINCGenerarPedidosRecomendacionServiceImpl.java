package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCGenerarPedidosRecomendacionDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el proceso que reemplazara al proceso P440 de SICC
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoINCGenerarPedidosRecomendacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCGenerarPedidosRecomendacionServiceImpl extends
	BaseInterfazProcesoAbstractService {
	      
	@Resource(name="spusicc.procesoINCGenerarPedidosRecomendacionDAO")
	private ProcesoINCGenerarPedidosRecomendacionDAO procesoINCGenerarPedidosRecomendacionDAO;

	protected void executeStoreProcedure(Map params) {
		procesoINCGenerarPedidosRecomendacionDAO.executeGenerarPedidosRecomendacion(params);
	}
	
	
}
