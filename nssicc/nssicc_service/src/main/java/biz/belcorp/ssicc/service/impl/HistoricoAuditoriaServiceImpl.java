/**
 * 
 */
package biz.belcorp.ssicc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.HistoricoAuditoriaDAO;
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoAuditoria;
import biz.belcorp.ssicc.service.HistoricoAuditoriaService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * @author Danny Amaro
 *
 */
@Service("sisicc.historicoAuditoriaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class HistoricoAuditoriaServiceImpl extends BaseService implements HistoricoAuditoriaService{
	
	@Resource(name="sisicc.historicoAuditoriaDAO")
	private HistoricoAuditoriaDAO historicoAuditoriaDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.HistoricoAuditoriaService#insertHistoricoAuditoria(biz.belcorp.ssicc.dao.sisicc.model.HistoricoAuditoria)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void insertHistoricoAuditoria(HistoricoAuditoria historicoAuditoria) {
		Long id = this.historicoAuditoriaDAO.getDevuelveIdSgteCodHistoricoAuditoria(); 
		historicoAuditoria.setCodigoHistoricoAuditoria(id);
		this.historicoAuditoriaDAO.insertHistoricoAuditoria(historicoAuditoria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.HistoricoAuditoriaService#updateHistoricoAuditoria(biz.belcorp.ssicc.dao.sisicc.model.HistoricoAuditoria)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void updateHistoricoAuditoria(HistoricoAuditoria historicoAuditoria) {
		this.historicoAuditoriaDAO.updateHistoricoAuditoria(historicoAuditoria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.HistoricoAuditoriaService#getHistoricoReporteByUser(java.util.Map)
	 */
	@Override
	public List getHistoricoReporteByUser(Map criteria) {
		return this.historicoAuditoriaDAO.getHistoricoAuditoriaByUser(criteria);				
	}

}
