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

@Service("reportes.ReporteVENVentaVariableService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteVENVentaVariableServiceImpl extends BaseReporteInterfaceImpl {

	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;

	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteVENVentaVariableServiceImpl.beforeExecuteReporte' method");
		}

		Map params = (Map) reporteParams.getQueryParams().get(
				"parameterMap");
   
		
		String formatoExportacion = (String) params.get("formatoExportacion");

		if (formatoExportacion.equals("XLS")) {
			super.beforeExecuteReporte(reporteParams, formReporte);
			
			reporteService.executeVentaVariable(params);
		}
		return reporteParams;
	}
	
	


}
