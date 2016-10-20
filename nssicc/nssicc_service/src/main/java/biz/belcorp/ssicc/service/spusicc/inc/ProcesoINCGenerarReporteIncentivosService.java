package biz.belcorp.ssicc.service.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface ProcesoINCGenerarReporteIncentivosService  extends Service{

	
	/**
	 * Realiza el proceso genreacion de ladata para el reporte de incentivos
	 * @param params
	 */
	void executeGenerarReporteIncentivos(Map map);

	/**
	 * Retorna lista Estad de Premios Despachados
	 * @param map
	 * @return
	 */
	Integer getSizeEstadoPremioDespacho(Map map);
	
}
