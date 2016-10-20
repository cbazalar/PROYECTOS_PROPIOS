/**
 * 
 */
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

/**
 * @author fochoa
 *
 */
@Service("reportes.reporteCOBHistoricoOperacionCrediticiaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCOBHistoricoOperacionCrediticiaServiceImpl extends BaseReporteInterfaceImpl{
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#beforeExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		
		log.debug("ReporteCOBHistoricoOperacionCrediticiaAction - beforeExecuteReporte");
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		reporteService.executeGenerarHistoricoOperacionCrediticia(params);
		
		reporteParams.setQueryParams(params);
		return reporteParams;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#generarReporteOracle(java.util.Map)
	 */
	@Override
	public Map<String, Object> generarReporteOracle(Map<String, Object> parameterMap) {
		
		log.debug("ReporteCOBHistoricoOperacionCrediticiaAction - generarReporteOracle");		
		parameterMap = reporteService.generarHistoricoOperacionCrediticiaTXT(parameterMap);
		return parameterMap;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#generarNombreArchivoReporteOracle(java.util.Map)
	 */
	@Override
	public String generarNombreArchivoReporteOracle(Map<String, Object> parameterMap) {
		
		log.debug("ReporteCOBHistoricoOperacionCrediticiaAction - generarNombreArchivoReporteOracle");
		String mesAnnio = (String) parameterMap.get("mesAnnio");
		String nombreArchivo = Constants.COB_REPORTE_HISTORICO_OPERACION_CREDITICIA + mesAnnio;
		return nombreArchivo;
	}
}
