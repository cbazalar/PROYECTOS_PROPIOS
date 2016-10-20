package biz.belcorp.ssicc.service.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ProcesoLETResultadoLideresAcumuladoConcuCierreRegionService extends Service{
	
	/**
	 * Ejecuta el proceso de resultado de lideres acumulado por concurso al cierre de region
	 * 
	 * @param params
	 */
	public void executeProcesoLETResultadoLideresAcumuladoConcuCierreRegion(Map params);
}