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
import biz.belcorp.ssicc.service.spusicc.cup.ReporteCUPIngresoMetasService;

@Service("reportes.reporteCUPIngresoMetasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCUPIngresoMetasServiceImpl extends BaseReporteInterfaceImpl{
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	@Resource(name="spusicc.reporteCUPIngresoMetasService")
	private ReporteCUPIngresoMetasService reporteCUPIngresoMetasService;
	
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) 
	throws Exception {
		
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		log.debug("Los parametros del Reporte en el before son: " + params.toString());

		reporteCUPIngresoMetasService.executeReporteCUPIngresoMetas(params);
		
		return reporteParams;
	}	
	
	

}
