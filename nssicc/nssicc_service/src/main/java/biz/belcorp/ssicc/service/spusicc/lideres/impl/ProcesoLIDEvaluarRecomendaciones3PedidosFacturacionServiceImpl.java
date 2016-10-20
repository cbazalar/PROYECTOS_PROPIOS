package biz.belcorp.ssicc.service.spusicc.lideres.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDEvaluarRecomendaciones3PedidosFacturacionDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDEvaluarRecomendaciones3PedidosFacturacionService;


@Service("spusicc.procesoLIDEvaluarRecomendaciones3PedidosFacturacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLIDEvaluarRecomendaciones3PedidosFacturacionServiceImpl extends BaseService
		implements ProcesoLIDEvaluarRecomendaciones3PedidosFacturacionService {
	
	@Resource(name="spusicc.procesoLIDEvaluarRecomendaciones3PedidosFacturacionDAO")
	ProcesoLIDEvaluarRecomendaciones3PedidosFacturacionDAO procesoLIDEvaluarRecomendaciones3PedidosFacturacionDAO; 

	public void executeEvaluarRecomendaciones3PedidosFacturacion(Map params) {
		procesoLIDEvaluarRecomendaciones3PedidosFacturacionDAO.executeEvaluarRecomendaciones3PedidosFacturacion(params);
		
	}


}
