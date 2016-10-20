package biz.belcorp.ssicc.reportes.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteSTOCentroAcopioFacturadoForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;




@Service("reportes.reporteSTOCentroAcopioFacturadoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteSTOCentroAcopioFacturadoServiceImpl extends BaseReporteInterfaceImpl {

	private static final long serialVersionUID = -2863893902708122335L;

	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	

	@Override
	public void beforeGrabarReporte(BaseReporteForm form) {
		ReporteSTOCentroAcopioFacturadoForm f = (ReporteSTOCentroAcopioFacturadoForm) form;
		Map params = new HashMap();
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("fechaFacturacion", f.getFechaFacturacion());
		reporteService.executeCargaReporteCentroAcopioFacturado(params);

	}

	

	
	
	
}
