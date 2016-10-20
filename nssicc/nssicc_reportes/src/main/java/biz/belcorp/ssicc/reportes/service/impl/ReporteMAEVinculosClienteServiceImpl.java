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

@Service("reportes.reporteMAEVinculosClienteService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteMAEVinculosClienteServiceImpl extends BaseReporteInterfaceImpl {

	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;

	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteMAEVinculosClienteServiceImpl.beforeExecuteReporte' method");
		}

		Map params = (Map) reporteParams.getQueryParams().get(
				"parameterMap");

		String formatoExportacion = (String) params.get("formatoExportacion");

		if (formatoExportacion.equals("OCSV")) {
			super.beforeExecuteReporte(reporteParams, formReporte);
			
			reporteService.executeBeforeReporteMAEVinculosCliente(params);
		}
		return reporteParams;
	}

	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		String nombreArchivo = Constants.PREFIJO_REPORTE_MAE_VINCULO_CLIENTE;
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
		return "reporteMAEVinculosClienteForm.tituloReporteCSV";
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction
	 * #generarReporteOracle(java.util.Map)
	 */
	public Map generarReporteOracle(Map parameterMap) {
		
		String formatoExportacion = (String) parameterMap
				.get("formatoExportacion");
		log.debug("reporteMAEConsejerasBloqueadasDesbloqueadasForm - formatoExportacion: "
				+ formatoExportacion);
		if (formatoExportacion.equals("OCSV"))
			reporteService.executeReporteMAEVinculosClienteCSV(parameterMap);
		return parameterMap;
	}

}
