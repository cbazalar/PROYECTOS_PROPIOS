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
 * The Class ReporteMAEClasificacionPorClienteServiceImpl.
 * 
 * @autor: Belcorp
 */
@Service("reportes.reportePEDSimulacionFaltantesService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReportePEDSimulacionFaltantesServiceImpl extends
		BaseReporteInterfaceImpl {

	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl
	 * #generarReporteOracle(java.util.Map)
	 */
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {

		super.beforeExecuteReporte(reporteParams,formReporte);
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		
		reporteService.executeCargaReporteSimulacionFaltantes(params);
		return reporteParams;
	}

}
