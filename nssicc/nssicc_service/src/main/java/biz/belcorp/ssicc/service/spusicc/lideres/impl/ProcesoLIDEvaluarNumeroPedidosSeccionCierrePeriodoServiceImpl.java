package biz.belcorp.ssicc.service.spusicc.lideres.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoService;

/**
 * Service que ejecutara que ejecutara los metodos del proceso de Evaluar Numero de Pedidos de Seccion por Periodo
 *  
 * <p>
 * <a href="ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoServiceImpls.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
@Service("spusicc.procesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoServiceImpl extends BaseService
		implements  ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoService {
	
	@Resource(name="spusicc.procesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoDAO")
	ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoDAO procesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoDAO; 

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoService#executeEvaluarNumeroPedidosSeccionCierrePeriodo(java.util.Map)
	 */
	public void executeEvaluarNumeroPedidosSeccionCierrePeriodo(Map params) {
		procesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoDAO.executeEvaluarNumeroPedidosSeccionCierrePeriodo(params);
		
	}



}
