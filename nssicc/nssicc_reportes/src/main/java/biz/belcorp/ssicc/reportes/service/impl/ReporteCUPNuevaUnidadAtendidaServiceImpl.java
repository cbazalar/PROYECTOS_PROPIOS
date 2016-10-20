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

@Service("reportes.reporteCUPNuevaUnidadAtendidaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteCUPNuevaUnidadAtendidaServiceImpl extends
		BaseReporteInterfaceImpl {

	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;

	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCUPNuevaUnidadAtendidaServiceImpl.beforeExecuteReporte' method");
		}

		return reporteParams;
	}

	public String generarNombreArchivoReporteOracle(Map parameterMap) {

		String nombreArchivo = null;
		String formatoreporte = (String) parameterMap.get("formatoreporte");
		if ("OCSV".equals(formatoreporte)) {
			nombreArchivo = "reporteCUPNuevaUnidadAtendidaCSV";
		}

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
		String titulo = "";
		String formatoreporte = (String) parameterMap.get("formatoreporte");
		if ("OCSV".equals(formatoreporte)) {
			titulo = "reporteCUPNuevaUnidadAtendidaCSV.titulo";
		}

		return titulo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction
	 * #generarReporteOracle(java.util.Map)
	 */
	public Map generarReporteOracle(Map parameterMap) {

		Map result = parameterMap;
		String formatoreporte = (String) result.get("formatoreporte");
		if ("OCSV".equals(formatoreporte)) {

			result = reporteService
					.generarReporteCUPNuevaUnidadAtendidaCSV(parameterMap);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction
	 * #beforeExecuteReporte
	 * (biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected void beforeExecuteReporte(ReporteParams reporteParams)
			throws Exception {

	}

}
