package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.spusicc.ProcesoPRYProyeccionFaltanteDiaService;


@Service("reportes.reportePRYProyeccionFaltanteDiaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReportePRYProyeccionFaltanteDiaServiceImpl extends BaseReporteInterfaceImpl{

	@Resource(name="spusicc.procesoPRYProyeccionFaltanteDiaService")
	private ProcesoPRYProyeccionFaltanteDiaService serviceProy;
	
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReportePRYProyeccionFaltanteDiaServiceImpl.beforeExecuteReporte' method");
		}
		// TODO Auto-generated method stub
		super.beforeExecuteReporte(reporteParams, formReporte);
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");

		String descripcionPeriodo = serviceProy.getDevuelveDescripcionPeriodo(params);
		params.put("descripcionPeriodo", descripcionPeriodo);
		
		
		reporteParams.setQueryParams(params);
		
		return reporteParams;
	}	

}

