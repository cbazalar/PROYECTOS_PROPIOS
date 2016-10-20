/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.sac;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Danny Amaro
 *
 */
public interface ReporteSACFechaRealEntregaDAO extends DAO{
	
	/**
	 * Genera la data para el Reporte de Fecha Real de Entrega
	 * @param map
	 */
	public void executeGenerarReporteFechaRealEntrega(Map map);

}
