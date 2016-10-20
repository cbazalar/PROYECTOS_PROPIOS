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

@Service("reportes.reporteRECListadoRecEstatusService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteRECListadoRecEstatusServiceImpl extends
		BaseReporteInterfaceImpl {

	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;

	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteRECListadoRecEstatusServiceImpl.beforeExecuteReporte' method");
		}
		super.beforeExecuteReporte(reporteParams, formReporte);

		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		String nextValue = reporteService.getSecuenciaNextValue();
		params.put("nextValue", nextValue);
		reporteService.insertTableReportesSQL(
				"insertReporteRECListadoRecEstatus", params);
		reporteParams.setQueryParams(params);

		return reporteParams;
	}

	@Override
	public ReporteParams afterExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {

		super.afterExecuteReporte(reporteParams, formReporte);

		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		log.debug("Delete to report ");
		reporteService.deleteTableReportesSQL("deleteReporte", params);
		reporteParams.setQueryParams(params);
		return reporteParams;
	}

}
