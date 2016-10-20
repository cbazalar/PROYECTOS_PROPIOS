package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

@Service("reportes.reportePEDConsultorasChequearService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReportePEDConsultorasChequearServiceImpl extends
		BaseReporteInterfaceImpl {

	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;

	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {

		super.beforeExecuteReporte(reporteParams, formReporte);
		log.debug("--- Ingrese a beforeExecuteReporte ---");

		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		params.put("oidProceso", null);

		reporteService
				.executeCargaTemporalReportePEDConsultorasChequear(params);

		Integer oidProceso = MapUtils.getInteger(params, "oidProceso");
		params.put("oidProceso", oidProceso);
		reporteParams.setQueryParams(params);
		return reporteParams;

	}

	@Override
	public ReporteParams afterExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {

		super.afterExecuteReporte(reporteParams, formReporte);
		log.debug("--- Ingrese a afterExecuteReporte ---");

		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");

		reporteService
				.executeLimpiarTablaTemporalReportePEDConsultorasChequear(params);
		reporteParams.setQueryParams(params);
		return reporteParams;

	}

	

}
