package biz.belcorp.ssicc.service.spusicc.lideres.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDEvaluarRecomendaciones2PedidosFacturacionDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDEvaluarRecomendaciones2PedidosFacturacionService;


@Service("spusicc.procesoLIDEvaluarRecomendaciones2PedidosFacturacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLIDEvaluarRecomendaciones2PedidosFacturacionServiceImpl extends BaseService
		implements ProcesoLIDEvaluarRecomendaciones2PedidosFacturacionService {
	
	@Resource(name="spusicc.procesoLIDEvaluarRecomendaciones2PedidosFacturacionDAO")
	ProcesoLIDEvaluarRecomendaciones2PedidosFacturacionDAO procesoLIDEvaluarRecomendaciones2PedidosFacturacionDAO; 

	public void executeEvaluarRecomendaciones2PedidosFacturacion(Map params) {
		procesoLIDEvaluarRecomendaciones2PedidosFacturacionDAO.executeEvaluarRecomendaciones2PedidosFacturacion(params);
		
	}

	

}
