package biz.belcorp.ssicc.service.spusicc.lideres.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDEvaluarNumeroPedidosSeccionCierreRegionDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDEvaluarNumeroPedidosSeccionCierreRegionService;


@Service("spusicc.procesoLIDEvaluarNumeroPedidosSeccionCierreRegionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLIDEvaluarNumeroPedidosSeccionCierreRegionServiceImpl extends BaseService
		implements  ProcesoLIDEvaluarNumeroPedidosSeccionCierreRegionService {
	
	@Resource(name="spusicc.procesoLIDEvaluarNumeroPedidosSeccionCierreRegionDAO")
	ProcesoLIDEvaluarNumeroPedidosSeccionCierreRegionDAO procesoLIDEvaluarNumeroPedidosSeccionCierreRegionDAO; 

	public void executeEvaluarNumeroPedidosSeccionCierreRegion(Map params) {
		procesoLIDEvaluarNumeroPedidosSeccionCierreRegionDAO.executeEvaluarNumeroPedidosSeccionCierreRegion(params);
		
	}

	/**
	 * @return Returns the procesoLIDEvaluarNumeroPedidosSeccionCierreRegionDAO.
	 */
	public ProcesoLIDEvaluarNumeroPedidosSeccionCierreRegionDAO getProcesoLIDEvaluarNumeroPedidosSeccionCierreRegionDAO() {
		return procesoLIDEvaluarNumeroPedidosSeccionCierreRegionDAO;
	}

}
