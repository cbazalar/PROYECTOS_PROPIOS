package biz.belcorp.ssicc.service.spusicc.sac.service;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * The Interface ReporteSACFechaRealEntregaService.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 04/06/2014
 */
public interface ReporteSACFechaRealEntregaService extends Service{
	
	/**
	 * Execute generar reporte lideres.
	 *
	 * @param map the map
	 */
	public void executeGenerarReporteLideres(Map map);
}
