package biz.belcorp.ssicc.reportes.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;


@Service("reportes.reporteSTORechazosSTODemandaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteSTORechazosSTODemandaServiceImpl extends BaseReporteInterfaceImpl{
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReportePERRegistroControlClienteServiceImpl.beforeExecuteReporte' method");
		}
		super.beforeExecuteReporte(reporteParams, formReporte);
		return reporteParams;
				
	}	
}