package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

@Service("reportes.reporteRECReclamosMotDevolucionService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteRECReclamosMotDevolucionServiceImpl extends
		BaseReporteInterfaceImpl {

	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;

	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteRECReclamosMotDevolucionServiceImpl.beforeExecuteReporte' method");
		}
		super.beforeExecuteReporte(reporteParams, formReporte);

		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		String tipoMovimiento = (String) params.get("tipoMovimiento");
		reporteService.deleteTableReportesSQL(
				"deleteReporteRECOperacionesUnidadAdm", params);
		if (StringUtils.equals(Constants.IND_MOVIMIENTO_DEVUELVE,
				tipoMovimiento))
			reporteService.insertTableReportesSQL(
					"insertReporteRECReclamosMotDevolucionDevuelve", params);
		else
			reporteService.insertTableReportesSQL(
					"insertReporteRECReclamosMotDevolucionEnvia", params);

		reporteParams.setQueryParams(params);

		return reporteParams;

	}
	
	

}
