/**
 * 
 */
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
 * @author César Estrada
 * 
 */
@Service("reportes.reporteRECNoAtendidosBajoStockService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteRECNoAtendidosBajoStockServiceImpl extends
		BaseReporteInterfaceImpl {

	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl
	 * #beforeExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.
	 * ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	@Override
	public ReporteParams afterExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {

		super.afterExecuteReporte(reporteParams, formReporte);
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		log.debug("Delete to report ");
		reporteService.deleteTableReportesSQL("deleteReporte", params);
		return reporteParams;
	}

	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {
		super.beforeExecuteReporte(reporteParams,formReporte);

		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		String nextValue = reporteService.getSecuenciaNextValue();
		params.put("nextValue", nextValue);
		reporteService.insertTableReportesSQL(
				"insertReporteRECNoAtendidosBajoStock", params);
		return reporteParams;
	}

}
