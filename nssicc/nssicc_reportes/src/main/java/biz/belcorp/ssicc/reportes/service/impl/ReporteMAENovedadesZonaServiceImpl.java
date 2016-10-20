package biz.belcorp.ssicc.reportes.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

@Service("reportes.reporteMAENovedadesZonaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteMAENovedadesZonaServiceImpl extends BaseReporteInterfaceImpl {
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#beforeExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	public ReporteParams afterExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		// TODO Auto-generated method stub
		super.afterExecuteReporte(reporteParams, formReporte);
		Map map = new HashMap();
		map.put("indProcEnvio", "E");
		
		
		Map params  = reporteParams.getQueryParams();
		Integer cont = (Integer) params.get("cont");
		Integer count = (Integer) params.get("count");
		
		if (cont.intValue() == count.intValue()) {
		    reporteService.updateReporteMAENovedadesZona(map);
		}
		return reporteParams;
	}
}