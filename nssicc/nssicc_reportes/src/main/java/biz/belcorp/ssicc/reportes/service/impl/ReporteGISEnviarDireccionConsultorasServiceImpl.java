package biz.belcorp.ssicc.reportes.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

@Service("reportes.reporteGISEnviarDireccionConsultorasService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteGISEnviarDireccionConsultorasServiceImpl extends
		BaseReporteInterfaceImpl {

	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;

	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {
		
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");

		Map criteria = new HashMap();
		criteria.put("codigoPais", String.valueOf(params.get("codigoPais")));
		criteria.put("codigoMarca", String.valueOf(params.get("codigoMarca")));
		criteria.put("codigoCanal", String.valueOf(params.get("codigoCanal")));
		criteria.put("codigoTipoCliente",
				String.valueOf(params.get("codigoTipoCliente")));
		criteria.put("codigoPeriodoInicio",
				String.valueOf(params.get("codigoPeriodoInicio")));
		criteria.put("condicionEstatus", String.valueOf(params.get("condicionEstatus")));
		criteria.put("codigoRegion", String.valueOf(params.get("codigoRegion")));
		String condicionUA = (String) params.get("condicionUA");
		if (StringUtils.isNotBlank(condicionUA)) {
			criteria.put("condicionUA", condicionUA);
		}

		log.debug("beforeExecuteReporte: " + params);
		reporteService.executeReporteGISDireccionConsulta(criteria);
		reporteParams.setQueryParams(params);
		return reporteParams;
		
	}

	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		String nombreArchivo = "reporteGISEnviarDireccionConsultoras";
		return nombreArchivo;
	}

	public String obtieneKeyTituloReporteOracle(Map parameterMap) {
		// TODO Auto-generated method stub
		String key = null;
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		if (formatoExportacion.equals("OCSV")){
			return "reporteGISEnviarDireccionConsultorasForm.tituloReporteCSV";
		}	
		return key;
	}

	public Map generarReporteOracle(Map parameterMap) {
		// -- Variables
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		log.debug("ReporteGISEnviarDireccionConsultoras - formatoExportacion: "+ formatoExportacion);

		if (formatoExportacion.equals("OCSV")){
			parameterMap = reporteService.generarReporteGISEnviarDireccionConsultorasCSV(parameterMap);
		}

		return parameterMap;
	}
}
