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

@Service("reportes.reporteMAEConsejerasBloqueadasDesbloqueadasService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteMAEConsejerasBloqueadasDesbloqueadasServiceImpl extends
		BaseReporteInterfaceImpl {

	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;

	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteMAEConsejerasBloqueadasDesbloqueadasServiceImpl.beforeExecuteReporte' method");
		}

		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");

		String formatoExportacion = (String) params.get("formatoExportacion");
		if (formatoExportacion.equals("OCSV")) {
			super.beforeExecuteReporte(reporteParams, formReporte);
			reporteService
					.executeBeforeReporteMAEConsejerasBloqueadasDesbloqueadas(params);
		}
		reporteParams.setQueryParams(params);

		return reporteParams;
	}

	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		String nombreArchivo = Constants.PREFIJO_REPORTE_MAE_CONSE_BLOQU_DESBL;
		return nombreArchivo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction
	 * #obtieneKeyTituloReporteOracle(java.util.Map)
	 */
	public String obtieneKeyTituloReporteOracle(Map parameterMap) {
		return "reporteMAEConsejerasBloqueadasDesbloqueadasForm.tituloReporteCSV";
	}

	public Map generarReporteOracle(Map parameterMap) {
		// -- Variables
		
		String formatoExportacion = (String) parameterMap
				.get("formatoExportacion");
		log.debug("reporteMAEConsejerasBloqueadasDesbloqueadasForm - formatoExportacion: "
				+ formatoExportacion);
		if (formatoExportacion.equals("OCSV"))
			reporteService
					.executeReporteMAEConsejerasBloqueadasDesbloqueadasCSV(parameterMap);
		return parameterMap;
	}

}
