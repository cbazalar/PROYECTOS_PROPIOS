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


@Service("reportes.reporteINCPuntosConsultoraService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteINCPuntosConsultoraServiceImpl extends BaseReporteInterfaceImpl {
	
	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;
	
	
	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		String tipoReporte = (String) parameterMap.get("tipoReporte");
		String nombreArchivo = null;
		
		if("1".equals(tipoReporte))
			nombreArchivo = "reporteINCPuntosConsultora";
		else
			nombreArchivo = "reporteINCPuntosCampania";
			
		return nombreArchivo;
	}
	
	
	@Override
	public String obtieneKeyTituloReporteOracle(Map parameterMap) {
		String tipoReporte = (String) parameterMap.get("tipoReporte");
		
		if("1".equals(tipoReporte))
			return "reporteINCPuntosConsultoraForm.tituloReporteCSV";
		else
			return "reporteINCPuntosConsultoraForm.tituloReporteCampaniaCSV";
	}
	
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception{
		
		super.beforeExecuteReporte(reporteParams, formReporte);		
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		String tipoReporte = (String) params.get("tipoReporte");
		String formatoReporte=(String) params.get("formatoReporte");
		
		if (formatoReporte.equals("OCSV")) {
						
			if("1".equals(tipoReporte))
				reporteService.executeBeforeReporteINCPuntosConsultora(params);
			else
				reporteService.executeBeforeReporteINCPuntosCampania(params);
		}
		
		reporteParams.setQueryParams(params);
		return reporteParams;
	}
	
	@Override
    public Map generarReporteOracle (Map parameterMap) {
		
		String formatoReporte = (String) parameterMap.get("formatoReporte");
		String tipoReporte = (String) parameterMap.get("tipoReporte");
		
		log.debug("reporteINCPuntosConsultoraForm - formatoExportacion: " + formatoReporte);
		
		if (formatoReporte.equals("OCSV"))
			if("1".equals(tipoReporte))
				reporteService.executeReporteINCPuntosConsultoraCSV(parameterMap);
			else
				reporteService.executeReporteINCPuntosCampaniaCSV(parameterMap);
		return parameterMap;
	}
}
