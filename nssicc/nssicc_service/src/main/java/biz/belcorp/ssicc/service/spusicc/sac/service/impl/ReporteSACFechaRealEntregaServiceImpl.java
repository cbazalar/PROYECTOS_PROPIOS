package biz.belcorp.ssicc.service.spusicc.sac.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.sac.ReporteSACFechaRealEntregaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sac.service.ReporteSACFechaRealEntregaService;


@Service("sac.reporteSACFechaRealEntregaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteSACFechaRealEntregaServiceImpl extends BaseService implements ReporteSACFechaRealEntregaService{
	
	@Resource(name="spusicc.reporteSACFechaRealEntregaDAO")
	ReporteSACFechaRealEntregaDAO reporteSACFechaRealEntregaDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sac.service.ReporteSACFechaRealEntregaService#executeGenerarReporteLideres(java.util.Map)
	 */
	public void executeGenerarReporteLideres(Map map) {
		this.reporteSACFechaRealEntregaDAO.executeGenerarReporteFechaRealEntrega(map);		
	}

}
