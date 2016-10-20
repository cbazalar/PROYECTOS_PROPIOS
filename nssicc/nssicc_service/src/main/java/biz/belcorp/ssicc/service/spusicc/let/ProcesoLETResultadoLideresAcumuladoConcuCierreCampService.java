package biz.belcorp.ssicc.service.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Jesse James Rios Franco
 *
 */

public interface ProcesoLETResultadoLideresAcumuladoConcuCierreCampService extends Service{

	/**
	 * Ejecuta el proceso de resultado de lideres acumulado por concurso al cierre de campaa
	 * @param params
	 */
	public void executeProcesoLETResultadoLideresAcumuladoConcuCierreCamp(Map params);

}