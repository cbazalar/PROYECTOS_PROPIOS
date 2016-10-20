/**
 * 
 */
package biz.belcorp.ssicc.service;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.sisicc.model.HistoricoReporte;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Danny Amaro
 *
 */
public interface HistoricoReporteService extends Service{
	
	/**
	 * @param historicoReporte
	 */
	public void insertHistoricoReporte(HistoricoReporte historicoReporte);
	
	/**
	 * @param historicoReporte
	 */
	public void updateHistoricoReporte(HistoricoReporte historicoReporte);
	
	/**
	 * @param criteria
	 * @return Lista de HistoricoReporte
	 */
	public List getHistoricoReporteByUser(Map criteria);

}
