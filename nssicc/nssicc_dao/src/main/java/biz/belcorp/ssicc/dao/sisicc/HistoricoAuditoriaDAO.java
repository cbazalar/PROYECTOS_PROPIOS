/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoAuditoria;

/**
 * @author Danny Amaro
 *
 */
public interface HistoricoAuditoriaDAO extends DAO{
	
	/**
	 * Devuelve ID sgte para insertar en el Historico Auditoria
	 * @return
	 */
	public Long getDevuelveIdSgteCodHistoricoAuditoria();
	
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
	 * @return
	 */
	public List getHistoricoAuditoriaByUser(Map criteria);

}
