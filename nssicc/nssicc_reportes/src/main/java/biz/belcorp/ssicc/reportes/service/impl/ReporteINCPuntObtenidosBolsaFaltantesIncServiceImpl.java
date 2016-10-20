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

/**
 * The Class ReporteINCPuntObtenidosBolsaFaltantesIncServiceImpl.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 18/03/2014
 */
@Service("reportes.reporteINCPuntObtenidosBolsaFaltantesIncService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteINCPuntObtenidosBolsaFaltantesIncServiceImpl extends BaseReporteInterfaceImpl {
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;

	@Override
	public Map<String, Object> generarReporteOracle(Map<String, Object> parameterMap) {
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		
		if (formatoExportacion.equals("OCSV"))
			reporteService.executeReporteINCPuntObtenidosBolsaFaltantesIncCSV(parameterMap);
		
		return parameterMap;
	}

	@Override
	public String obtieneKeyTituloReporteOracle(Map<String, Object> parameterMap) {
		String key = null;
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		
		if (formatoExportacion.equals("OCSV"))
			return "reporteINCPuntObtenidosBolsaFaltantesIncForm.tituloReporteCSV";
		
		return key;
	}

	@Override
	public String generarNombreArchivoReporteOracle(Map<String, Object> parameterMap) {
		String nombreArchivo = "reporteINCPuntObtenidosBolsaFaltantesInc";
		
		return nombreArchivo;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#beforeExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		String formatoExportacion = (String) params.get("formatoExportacion");
		
		if (formatoExportacion.equals("OCSV")) {
			reporteParams = super.beforeExecuteReporte(reporteParams, formReporte);
			reporteService.executeBeforeReporteINCPuntObtenidosBolsaFaltantesInc(params);
		}
		
		return reporteParams;
	}
}