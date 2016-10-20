package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

/**
 * The Class ReporteSICPedidosAfectadosDetFaltanteNoAnunciadoServiceImpl.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 29/05/2014
 */
@Service("reportes.reporteSICPedidosAfectadosDetFaltanteNoAnunciadoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteSICPedidosAfectadosDetFaltanteNoAnunciadoServiceImpl extends BaseReporteInterfaceImpl {
	
		
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#beforeExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering beforeExecuteReporte method");
		}
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		reporteService.executeSICPedidosAfectadosDetFNA(params);
		
		log.debug("FIN beforeExecuteReporte");
		
		return reporteParams;
		
	}
	
}
