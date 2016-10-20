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


@Service("reportes.reporteCCCDeudorasConMasUnaCampanhaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteCCCDeudorasConMasUnaCampanhaServiceImpl extends BaseReporteInterfaceImpl{
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCCCDeudorasConMasUnaCampanhaServiceImpl.beforeExecuteReporte' method");
		}

		super.beforeExecuteReporte(reporteParams, formReporte);

		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		params.put("identificadorProceso", null);
		params.put("codigoPeriodo01", "");
		params.put("codigoPeriodo02", "");
		params.put("codigoPeriodo03", "");
		params.put("codigoPeriodo04", "");
		params.put("codigoPeriodo05", "");
		params.put("codigoPeriodo06", "");

		
		reporteService.executeCargarTablasReporteCCCDeudorasConMasUnaCampanha(params);
		reporteParams.setQueryParams(params);
		return reporteParams;		
	}
	
	@Override	
	public ReporteParams afterExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {

		super.afterExecuteReporte(reporteParams, formReporte);
		log.debug("--- Ingrese a afterExecuteReporte ---");
		
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");		
			
		reporteService.executeLimpiarTablasReporteCCCDeudorasConMasUnaCampanha(params);
		reporteService.executeLimpiarTablasReporteCCCDeudorasConMasUnaCampanhaArray(params);
		reporteParams.setQueryParams(params);
		return reporteParams;

	}
	
	
}
