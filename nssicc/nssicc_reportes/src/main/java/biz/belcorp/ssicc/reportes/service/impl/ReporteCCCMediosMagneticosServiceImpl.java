/**
 * 
 */
package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

/**
 * @author Danny Amaro
 *
 */
@Service("reportes.reporteCCCMediosMagneticosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCCCMediosMagneticosServiceImpl extends BaseReporteInterfaceImpl {
	private static final long serialVersionUID = -4390094424857702131L;
		
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#beforeExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOMComisionRecuperacionServiceImpl.beforeExecuteReporte' method");
		}
		super.beforeExecuteReporte(reporteParams,formReporte);
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		reporteService.executeBeforeReporteCCCArchivosMediosMagneticos(params);
		
		return reporteParams;
		
	}
	
	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		String nombreArchivo = "ArchivoMagneticos_" + DateUtil.convertDateToString("yyyyMMddHHMMss", new Date());
		return nombreArchivo;
	}
	
	public Map generarReporteOracle(Map parameterMap) {
	
		reporteService.executeGeneraReporteCCCArchivosMediosMagneticosCSV(parameterMap);
		return parameterMap;
	}
	
	
	
}
