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

@SuppressWarnings("rawtypes")
@Service("reportes.reportePEDSeguimientoConsultoraServiceImpl")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReportePEDSeguimientoConsultoraServiceImpl extends BaseReporteInterfaceImpl{

	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {
		
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		reporteService.insertReportePEDSeguimientoConsultora(params);
		return super.beforeExecuteReporte(reporteParams, formReporte);
	}
	
	@Override
	public Map<String, Object> generarReporteOracle(Map<String, Object> parameterMap) {
		
		//-- Variables
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		log.debug("ReportePEDSeguimientoConsultoraAction - formatoExportacion: " + formatoExportacion);
		if (formatoExportacion.equals("OCSV"))
			reporteService.executeReportePEDSeguimientoConsultoraCSV(parameterMap);
		return parameterMap;
	
//		return super.generarReporteOracle(parameterMap);
	}
	
	@Override
	public String generarNombreArchivoReporteOracle(Map<String, Object> parameterMap) {
		String nombreArchivo = Constants.PREFIJO_REPORTE_PED_SEGUIMIENTO_CONSULTORA;
		return nombreArchivo;
	}
	
	@Override
	public String obtieneKeyTituloReporteOracle(Map<String, Object> parameterMap) {
		// TODO Auto-generated method stub
		return "reportePEDSeguimientoConsultoraForm.tituloReporteCSV";
	}
	
}
