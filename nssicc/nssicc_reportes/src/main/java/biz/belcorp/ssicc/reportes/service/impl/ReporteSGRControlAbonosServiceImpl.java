package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.spusicc.sgr.MantenimientoSGRGenericoService;

/**
 * The Class ReporteSGRControlAbonosServiceImpl.
 *
 * @autor: Hernando Huaman
 * @version: 1.0
 * 12/11/2014
 */
@Service("reportes.reporteSGRControlAbonosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteSGRControlAbonosServiceImpl extends BaseReporteInterfaceImpl {
	
	@Resource(name="spusicc.mantenimientoSGRGenericoService")
	private MantenimientoSGRGenericoService mantenimientoSGRGenericoService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.service.MantenimientoSGRGenericoService#executeGenerarReporteControlAbonos(java.util.Map)
	 */
	@SuppressWarnings("rawtypes")
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteSGRControlAbonosServiceImpl.beforeExecuteReporte' method");
		}		
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		mantenimientoSGRGenericoService.executeGenerarReporteControlAbonos(params);				
		reporteParams.setQueryParams(params);		
		return reporteParams;
	}
	
}
