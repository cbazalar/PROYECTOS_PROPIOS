package biz.belcorp.ssicc.service.spusicc.lideres.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoService;

@Service("spusicc.procesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoServiceImpl extends BaseService
		implements ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoService {
	
	@Resource(name="spusicc.procesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoDAO")
	ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoDAO procesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoService#executeEvaluarRecomendaciones2PedidosCierrePeriodo(java.util.Map)
	 */
	public void executeEvaluarRecomendaciones2PedidosCierrePeriodo(Map params) {
		procesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoDAO.executeEvaluarRecomendaciones2PedidosCierrePeriodo(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.ProcesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoService#executeEvaluarRecomendaciones2PedidosCierreRegion(java.util.Map)
	 */
	public void executeEvaluarRecomendaciones2PedidosCierreRegion(Map params) {
		procesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoDAO.executeEvaluarRecomendaciones2PedidosCierreRegion(params);
	}



}