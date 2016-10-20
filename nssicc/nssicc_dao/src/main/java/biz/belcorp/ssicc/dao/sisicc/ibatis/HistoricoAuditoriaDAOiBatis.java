/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.HistoricoAuditoriaDAO;
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoAuditoria;

/**
 * @author Danny Amaro
 *
 */
@Repository("sisicc.historicoAuditoriaDAO")
public class HistoricoAuditoriaDAOiBatis  extends BaseDAOiBatis implements HistoricoAuditoriaDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.HistoricoAuditoriaDAO#getDevuelveIdSgteCodHistoricoAuditoria()
	 */
	@Override
	public Long getDevuelveIdSgteCodHistoricoAuditoria() {
		Long id = (Long)getSqlMapClientTemplate().
				queryForObject("sisicc.HistoricoAuditoriaSQL.getDevuelveIdSgteCodHistoricoAuditoria");		
		return id;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.HistoricoAuditoriaDAO#insertHistoricoAuditoria(biz.belcorp.ssicc.dao.sisicc.model.HistoricoAuditoria)
	 */
	@Override
	public void insertHistoricoAuditoria(HistoricoAuditoria historicoAuditoria) {
		getSqlMapClientTemplate().update("sisicc.HistoricoAuditoriaSQL.insertHistoricoAuditoria", historicoAuditoria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.HistoricoAuditoriaDAO#updateHistoricoAuditoria(biz.belcorp.ssicc.dao.sisicc.model.HistoricoAuditoria)
	 */
	@Override
	public void updateHistoricoAuditoria(HistoricoAuditoria historicoAuditoria) {
		getSqlMapClientTemplate().update("sisicc.HistoricoAuditoriaSQL.updateHistoricoAuditoria", historicoAuditoria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.HistoricoAuditoriaDAO#getHistoricoAuditoriaByUser(java.util.Map)
	 */
	@Override
	public List getHistoricoAuditoriaByUser(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.HistoricoAuditoriaSQL.getHistoricoAuditoriaByUser", criteria);
	}

}
