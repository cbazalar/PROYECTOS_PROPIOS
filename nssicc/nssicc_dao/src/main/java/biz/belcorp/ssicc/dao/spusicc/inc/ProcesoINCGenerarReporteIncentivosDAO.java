package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface ProcesoINCGenerarReporteIncentivosDAO extends DAO {
	
	/**
	 * Realiza el proceso genreacion de ladata para el reporte de incentivos
	 * @param params
	 */
	void executeGenerarReporteIncentivos(Map map);

	/**
	 * Retorna el numero de registros procesados
	 * @param map
	 * @return
	 */
	Integer getSizeEstadoPremioDespacho(Map map);

}
