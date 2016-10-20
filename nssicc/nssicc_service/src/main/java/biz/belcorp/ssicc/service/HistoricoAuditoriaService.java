/**
 * 
 */
package biz.belcorp.ssicc.service;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.sisicc.model.HistoricoAuditoria;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Danny Amaro
 *
 */
public interface HistoricoAuditoriaService extends Service{
	
	/**
	 * @param historicoAuditoria
	 */
	public void insertHistoricoAuditoria(HistoricoAuditoria historicoAuditoria);
	
	/**
	 * @param historicoAuditoria
	 */
	public void updateHistoricoAuditoria(HistoricoAuditoria historicoAuditoria);
	
	/**
	 * @param criteria
	 * @return Lista de HistoricoAuditoria
	 */
	public List getHistoricoReporteByUser(Map criteria);

}
