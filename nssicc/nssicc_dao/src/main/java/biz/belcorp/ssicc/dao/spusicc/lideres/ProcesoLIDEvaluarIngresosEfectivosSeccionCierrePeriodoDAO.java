package biz.belcorp.ssicc.dao.spusicc.lideres;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoDAO
		extends DAO {

	
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

