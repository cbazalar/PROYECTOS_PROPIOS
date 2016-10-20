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

@Service("reportes.reporteVENEvaluacionCoberturaNivelesSeccionService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteVENEvaluacionCoberturaNivelesSeccionServiceImpl extends BaseReporteInterfaceImpl {
	
	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;
	
	@SuppressWarnings("rawtypes")
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {

		super.beforeExecuteReporte(reporteParams, formReporte);
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		reporteService.executeReporteSQL("executeReporteVENEvaluacionCoberturaNivelesSeccion", params);
		
		reporteParams.setQueryParams(params);
		return reporteParams;
	}

}
