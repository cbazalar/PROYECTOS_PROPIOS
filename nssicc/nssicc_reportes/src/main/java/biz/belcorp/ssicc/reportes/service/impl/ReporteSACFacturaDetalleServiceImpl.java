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
import biz.belcorp.ssicc.service.spusicc.men.ProcesoMENCargaMasivaInformacionMensajesService;

/**
 * The Class ReporteSACFacturaDetalleServiceImpl.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 18/03/2014
 */
@Service("reportes.reporteSACFacturaDetalleService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteSACFacturaDetalleServiceImpl extends BaseReporteInterfaceImpl {
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	@Resource(name="spusicc.procesoMENCargaMasivaInformacionMensajesService")
	private ProcesoMENCargaMasivaInformacionMensajesService procesoMENCargaMasivaInformacionMensajesService;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#beforeExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
				
		params.put("serviceCMIMS", procesoMENCargaMasivaInformacionMensajesService);
		
		reporteService.executeReporteFacturaDetalle(params);
		
		return reporteParams;
	}
}