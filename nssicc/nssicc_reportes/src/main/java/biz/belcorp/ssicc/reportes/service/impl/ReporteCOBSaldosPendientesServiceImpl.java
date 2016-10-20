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

@Service("reportes.reporteCOBSaldosPendientesService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteCOBSaldosPendientesServiceImpl extends
		BaseReporteInterfaceImpl {

	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;

	protected void beforeExecuteReporte(ReporteParams reporteParams)
			throws Exception {
	}

	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {
		String formatoExportacion = (String) reporteParams.getQueryParams()
				.get("formatoExportacion");
		if (formatoExportacion.equals("OCSV")) {
			Map params = (Map) reporteParams.getQueryParams().get(
					"parameterMap");
			reporteService.insertReporteCOBSaldosPendientes(params);
			reporteParams.setQueryParams(params);
		}
		return reporteParams;
	}

	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		String nombreArchivo = Constants.PREFIJO_REPORTE_COB_SALDO_PENDIENTE;
		return nombreArchivo;
	}

	public String obtieneKeyTituloReporteOracle(Map parameterMap) {
		return "reporteCOBSaldosPendientesForm.tituloReporteCSV";
	}

	public Map generarReporteOracle(Map parameterMap) {
		// -- Variables
		String formatoExportacion = (String) parameterMap
				.get("formatoExportacion");
		log.debug("ReporteCOBDetalladoCobranza31DiasAction - formatoExportacion: "
				+ formatoExportacion);
		if (formatoExportacion.equals("OCSV"))
			reporteService.executeReporteCOBSaldosPendientesCSV(parameterMap);
		return parameterMap;
	}
}
