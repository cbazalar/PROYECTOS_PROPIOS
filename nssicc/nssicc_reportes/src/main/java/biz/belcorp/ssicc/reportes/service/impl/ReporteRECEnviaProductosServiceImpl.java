package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECOperacionService;

@Service("reportes.reporteRECEnviaProductosService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteRECEnviaProductosServiceImpl extends BaseReporteInterfaceImpl {

	@Resource(name = "spusicc.mantenimientoRECOperacionService")
	private MantenimientoRECOperacionService operacionService;

	@Override
	public ReporteParams afterExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteRECEnviaProductosServiceImpl.afterExecuteReporte' method");
		}
		
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");

		operacionService.deleteInterfazRETProductosReclamados(params);
		
		return reporteParams;
	}
	

	
}
