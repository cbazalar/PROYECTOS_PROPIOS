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

@Service("reportes.reporteCOBNumeroPagosCampannaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCOBNumeroPagosCampannaServiceImpl extends BaseReporteInterfaceImpl {
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	@Override
	public String generarNombreArchivoReporteOracle(Map<String, Object> parameterMap) {
		String nombreArchivo = Constants.PREFIJO_REPORTE_COB_NUMERO_PAGO_CAMPANNA;
		return nombreArchivo;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#obtieneKeyTituloReporteOracle(java.util.Map)
	 */
	@Override
	public String obtieneKeyTituloReporteOracle(Map<String, Object> parameterMap) {
		
		String titulo = "reporteCOBNumeroPagosCampannaForm.tituloReporteCSVConsultoraBanco";
		String tipoReporte = (String) parameterMap.get("tipoReporte");
		if (tipoReporte.equals("C"))
			titulo = "reporteCOBNumeroPagosCampannaForm.tituloReporteCSVConsultora";
		return titulo;
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#beforeExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {
		Map parameterMap = reporteParams.getQueryParams();
		reporteService.executeReporteCOBNumeroPagosCampanna(parameterMap);
		return reporteParams;
	}

	
	@Override
	public Map<String, Object> generarReporteOracle(Map<String, Object> parameterMap) {
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		log.debug("reporteCOBNumeroPagosCampannaForm - formatoExportacion: " + formatoExportacion);
		
		if (formatoExportacion.equals("OCSV"))
			reporteService.executeReporteCOBNumeroPagosCampannaCSV(parameterMap);
		
		return parameterMap;
	}	
}