package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

/**
 * The Class ReporteINCPuntObtenidosPuntFaltantesServiceImpl.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 18/03/2014
 */
@Service("reportes.reporteINCPuntObtenidosPuntFaltantesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteINCPuntObtenidosPuntFaltantesServiceImpl extends BaseReporteInterfaceImpl {
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;

	@Override
	public Map<String, Object> generarReporteOracle(Map<String, Object> parameterMap) {
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		
		if (formatoExportacion.equals("OCSV"))
			reporteService.executeReporteINCPuntObtenidosPuntFaltantesCSV(parameterMap);
		
		return parameterMap;
	}

	@Override
	public String obtieneKeyTituloReporteOracle(Map<String, Object> parameterMap) {
		String key = null;
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		
		if (formatoExportacion.equals("OCSV"))
			return "reporteINCPuntObtenidosPuntFaltantes" + MapUtils.getString(parameterMap, "tipoReporte") + ".tituloReporteCSV";
		
		return key;
	}

	@Override
	public String generarNombreArchivoReporteOracle(Map<String, Object> parameterMap) {
		String nombreArchivo = Constants.PREFIJO_REPORTE_INC_PUNT_OBTE_PUNT_FALTA + MapUtils.getString(parameterMap, "tipoReporte");
		
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
			reporteService.executeBeforeReporteINCPuntObtenidosPuntFaltantes(params);
		}
		
		return reporteParams;
	}
}