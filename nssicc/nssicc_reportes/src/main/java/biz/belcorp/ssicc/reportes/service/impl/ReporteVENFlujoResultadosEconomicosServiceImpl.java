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


@Service("reportes.reporteVENFlujoResultadosEconomicosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteVENFlujoResultadosEconomicosServiceImpl extends BaseReporteInterfaceImpl{

	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteVENFlujoResultadosEconomicosServiceImpl.beforeExecuteReporte' method");
		}
		
		super.beforeExecuteReporte(reporteParams, formReporte);
		
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		String codigoRegion = (String) params.get("codigoRegion");
		String codigoZona = (String) params.get("codigoZona");
		
		if ("Todos".equals(codigoRegion)) {
			codigoRegion = null;
			params.put("codigoRegion", codigoRegion);
		}
		
		if ("Todos".equals(codigoZona)) {
			codigoZona = null;
			params.put("codigoZona", codigoZona);
		}
		
		reporteService.executeReporteSQL("executeReporteVENFlujoResultadosEconomicos", params);
		reporteParams.setQueryParams(params);
		return reporteParams;
	}
}
