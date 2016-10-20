package biz.belcorp.ssicc.reportes.service;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

public interface ReporteFLXDetalladoSaldosConsultoras2Service {
	
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception;

}
