package biz.belcorp.ssicc.service.spusicc.lideres;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza </a>
 */
public interface ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoService
		extends Service {

	/**
	 * Proceso que Evalua numero de Pedidos x Seccion al cierre de Periodo
	 * 
	 * @param params
	 */
	public void executeEvaluarNumeroPedidosSeccionCierrePeriodo(Map params);

}
