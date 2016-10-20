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

@Service("reportes.reporteSTORechazadasService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteSTORechazadasServiceImpl extends
		BaseReporteInterfaceImpl {

	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;

	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {

		String secuencia = (String) reporteParams.getQueryParams()
				.get("secuencia");
		String flagFechaFact = (String) reporteParams.getQueryParams()
				.get("flagFechaFact");
		
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		log.debug("Los parametros del Reporte en el before son: "
				+ params.toString());
		params.put("secuencia", secuencia);
		params.put("flagFechaFact", flagFechaFact);

		log.debug("Los nuevos parametros del Reporte en el before son!!!!: "
				+ params.toString());
		
		
		
		reporteParams.setQueryParams(params);
	
		return reporteParams;
	}
	
	
	
	
}
