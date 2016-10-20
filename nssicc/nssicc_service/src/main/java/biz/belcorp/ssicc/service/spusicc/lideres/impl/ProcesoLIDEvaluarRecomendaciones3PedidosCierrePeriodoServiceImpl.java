package biz.belcorp.ssicc.service.spusicc.lideres.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoService;


@Service("spusicc.procesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoServiceImpl extends BaseService
		implements ProcesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoService {
	
	@Resource(name="spusicc.procesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoDAO")
	ProcesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoDAO procesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoDAO; 

	public void executeEvaluarRecomendaciones3PedidosCierrePeriodo(Map params) {
		procesoLIDEvaluarRecomendaciones3PedidosCierrePeriodoDAO.executeEvaluarRecomendaciones3PedidosCierrePeriodo(params);
		
	}


	

}
