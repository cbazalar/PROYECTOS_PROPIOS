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

import biz.belcorp.ssicc.dao.sisicc.HistoricoReporteDAO;
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoReporte;
import biz.belcorp.ssicc.service.HistoricoReporteService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * @author Danny Amaro
 *
 */
@Service("sisicc.historicoReporteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class HistoricoReporteServiceImpl extends BaseService implements HistoricoReporteService{

	@Resource(name="sisicc.historicoReporteDAO")
	private HistoricoReporteDAO historicoReporteDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.HistoricoReporteService#insertHistoricoReporte(biz.belcorp.ssicc.dao.sisicc.model.HistoricoReporte)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void insertHistoricoReporte(HistoricoReporte historicoReporte) {
		Long id = historicoReporteDAO.getDevuelveIdSgteCodHistoricoReporte();
		historicoReporte.setCodigoHistoricoReporte(id);
		historicoReporteDAO.insertHistoricoReporte(historicoReporte);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.HistoricoReporteService#updateHistoricoReporte(biz.belcorp.ssicc.dao.sisicc.model.HistoricoReporte)
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void updateHistoricoReporte(HistoricoReporte historicoReporte) {
		historicoReporteDAO.updateHistoricoReporte(historicoReporte);		
	}

	@Override
	public List getHistoricoReporteByUser(Map criteria) {
		return historicoReporteDAO.getHistoricoReporteByUser(criteria);
	}

}
