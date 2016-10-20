package biz.belcorp.ssicc.service.spusicc.lideres;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza </a>
 */
public interface ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService
		extends Service {

	/**
	 * Obtiene los tipos de Evaluacion para Ingresos Efectivos
	 * 
	 * @param criteria
	 * @return
	 */
	public List getTiposEvaluacion(Map criteria);
	
	/**
	 * Verifica si una region ha sido procesada para el proceso de recomendaciones de 2 pedidos.
	 * 
	 * @param params
	 * @return
	 */
	public boolean verificaRegionProcesada(Map params);
	
	/**
	 * Proceso que Evalua Ingresos Efectivos de la Seccion al Cierre de Region
	 * 
	 * @param params
	 */
	public void executeEvaluarIngresosEfectivosSeccionCierreRegion(Map params);

}
