package biz.belcorp.ssicc.reportes.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.belcorp.ssicc.reportes.BaseReportTest;
import biz.belcorp.ssicc.reportes.framework.service.ReporteExecutionService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteResult;

public class ReporteExecutionServiceTest extends BaseReportTest{
	
	@Autowired
	private ReporteExecutionService reporteExecutionService;
	
	@Test
	public void getConnectionDatasourceTest() throws Exception {
		ReporteParams reporteParams = new ReporteParams();
		reporteParams.setJasperFileName("/biz/belcorp/ssicc/reportes/jasper/reporteMaestroHorizontal.jasper");
		Map queryParams = new HashMap();
		reporteParams.setQueryParams(queryParams);
		ReporteResult ReporteResult = this.reporteExecutionService.executeReporte(reporteParams);
	}

}
