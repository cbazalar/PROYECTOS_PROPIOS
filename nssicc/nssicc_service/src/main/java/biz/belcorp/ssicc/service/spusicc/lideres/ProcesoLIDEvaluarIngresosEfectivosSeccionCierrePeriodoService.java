package biz.belcorp.ssicc.service.spusicc.lideres;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza </a>
 */
public interface ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoService
		extends Service {

	/**
	 * Verifica si una campaa ha sido procesada para el proceso de recomendaciones de 2 pedidos.
	 * 
	 * @param params
	 * @return
	 */
	public boolean verificaCampanaProcesada(Map params);
	
	/**
	 * Proceso que Evalua Ingresos Efectivos de la Seccion al Cierre de Periodo
	 * 
	 * @param params
	 */
	public void executeEvaluarIngresosEfectivosSeccionCierrePeriodo(Map params);

}