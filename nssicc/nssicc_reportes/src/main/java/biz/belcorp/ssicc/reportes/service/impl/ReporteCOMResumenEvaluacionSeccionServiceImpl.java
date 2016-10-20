package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOMResumenEvaluacionSeccionForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;




@Service("reportes.reporteCOMResumenEvaluacionSeccionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCOMResumenEvaluacionSeccionServiceImpl extends BaseReporteInterfaceImpl {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6918298286893193327L;
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;

	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOMResumenEvaluacionSeccionServiceImpl.beforeExecuteReporte' method");
		}
		
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		log.debug("Los parametros del Reporte en el before son: " + params.toString());

		ReporteCOMResumenEvaluacionSeccionForm reporteForm =  (ReporteCOMResumenEvaluacionSeccionForm)formReporte;
		
		if ("1".equals(reporteForm.getTipoResumen())){
			reporteService.executeResumenEvaluacionSeccion(params);
		}
		if ("2".equals(reporteForm.getTipoResumen())){
			reporteService.executeResumenEvaluacionZona(params);
		}
		reporteParams.setQueryParams(params);
		return reporteParams;
	}	

}
