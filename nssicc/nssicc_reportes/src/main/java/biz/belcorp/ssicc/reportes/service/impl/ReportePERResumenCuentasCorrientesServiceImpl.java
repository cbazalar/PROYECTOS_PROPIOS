package biz.belcorp.ssicc.reportes.service.impl;

import java.util.HashMap;
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
 * The Class ReportePERResumenCuentasCorrientesServiceImpl.
 *
 * @autor: Hernando Huaman
 * @version: 1.0
 * 18/11/2014
 */
@Service("reportes.reportePERResumenCuentasCorrientesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReportePERResumenCuentasCorrientesServiceImpl extends BaseReporteInterfaceImpl {

	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#beforeExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	@SuppressWarnings("rawtypes")
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReportePERResumenCuentasCorrientesServiceImpl.beforeExecuteReporte' method");
		}
		
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		ReporteService reporteService = (ReporteService) this.reporteService;
		/*
		 * reporteService.deleteTableReportesSQL(
		 * "deleteReportePERGttResumenCuentaCorriente", params);
		 * reporteService.insertTableReportesSQL(
		 * "insertReportePERGttResumenCuentaCorriente", params);
		 */
		reporteService.executeReporteSQL("executeReporteRECGttResumenAbonosCobrar", params);
		reporteService.executeReporteSQL("executeReporteRECGttResumenCuentaCorriente", params);		
		return reporteParams;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#beforeExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void afterExecuteReporte(ReporteParams reporteParams) throws Exception {
		ReporteService reporteService = (ReporteService) this.reporteService;
		Map params = new HashMap();
		params.put("query", "drop table PER_REPOR_RESUM_CUENT_" + params.get("secuencial1").toString());
		reporteService.executeReporteSQL("executeQueryDinamico", params);
		params.put("query", "drop table PER_REPOR_RESUM_ABONO_" + params.get("secuencial2").toString());
		reporteService.executeReporteSQL("executeQueryDinamico", params);
	}
	
}
