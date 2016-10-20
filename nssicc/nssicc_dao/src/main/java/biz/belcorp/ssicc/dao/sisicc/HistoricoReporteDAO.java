/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoReporte;

/**
 * @author Danny Amaro
 *
 */
public interface HistoricoReporteDAO extends DAO{
	
	/**
	 * Devuelve ID sgte para insertar en el Historico Reporte
	 * @return
	 */
	public Long getDevuelveIdSgteCodHistoricoReporte();
	
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
	 * @return
	 */
	public List getHistoricoReporteByUser(Map criteria);

}
