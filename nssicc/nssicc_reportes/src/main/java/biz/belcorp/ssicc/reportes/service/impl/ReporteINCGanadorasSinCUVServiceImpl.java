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


@Service("reportes.reporteINCGanadorasSinCUVService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteINCGanadorasSinCUVServiceImpl extends BaseReporteInterfaceImpl{
	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;
	
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {
		
		super.beforeExecuteReporte(reporteParams, formReporte);
		
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		
		log.debug("Los parametros del Reporte en el before son: "+ params.toString());
		
		
		//llenamos la tabla inc_tempo_ganad_repor_clie_cuv 
		
		reporteService.deleteTableReportesSQL("deleteReporteGanadorasSinCUVClie", params);
		reporteService.deleteTableReportesSQL("deleteReporteGanadorasSinCUV", params);
		reporteService.insertTableReportesSQL("insertReporteGanadorasSinCUVClie", params);
		reporteService.insertTableReportesSQL("insertReporteGanadorasSinCUV", params);
		
		reporteParams.setQueryParams(params);
		return reporteParams;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#afterExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	@Override
	public ReporteParams afterExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {
		super.afterExecuteReporte(reporteParams, formReporte);
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		//eliminamos la tabla temporal 
		reporteService.deleteTableReportesSQL("deleteReporteGanadorasSinCUVClie", params);
		reporteService.deleteTableReportesSQL("deleteReporteGanadorasSinCUV", params);
		reporteParams.setQueryParams(params);
		return reporteParams;
	}
}
