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

@Service("reportes.reporteCCCDetalladoMovimientosCtaCteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCCCDetalladoMovimientosCtaCteServiceImpl extends BaseReporteInterfaceImpl {
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#beforeExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		//params.put("secuencial1", secuencial1);
		//params.put("secuencial2", secuencial2);
		reporteService.executeReporteSQL("executeGenerarReporteCCCDetalleMovimientosCtaCte", params);		
		reporteParams.setQueryParams(params);
		return reporteParams;	
	}
}