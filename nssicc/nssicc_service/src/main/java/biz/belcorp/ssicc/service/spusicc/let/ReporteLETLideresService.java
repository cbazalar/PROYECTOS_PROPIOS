package biz.belcorp.ssicc.service.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ReporteLETLideresService extends Service{

	/**
	 * Genera la data para el reporte LET de lderes
	 * @param map
	 */
	public void executeGenerarReporteLideres(Map map);

}