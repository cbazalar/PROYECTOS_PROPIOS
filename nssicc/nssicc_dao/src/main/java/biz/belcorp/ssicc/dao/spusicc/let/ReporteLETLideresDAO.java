package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ReporteLETLideresDAO extends DAO{

	/**
	 * Genera la data para el reporte LET de Lderes
	 * @param map
	 */
	public void executeGenerarReporteLideres(Map map);

}