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

@Service("reportes.reporteCUPNuevasCuponesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCUPNuevasCuponesServiceImpl extends BaseReporteInterfaceImpl {	
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {	
		log.debug("entro al beforeExecute");
		
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		
		reporteService.executeBeforeReporteCUPNuevasCupones(params);
		reporteParams.setQueryParams(params);
		
		return reporteParams;		
	}
	
	public Map generarReporteOracle (Map parameterMap){	
		log.debug("entro al generar reporte oracle");
		
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		log.debug("ReporteCUPNuevasCuponesForm - formatoExportacion: " + formatoExportacion);
		if (formatoExportacion.equals("OCSV"))
			reporteService.executeReporteCUPNuevasCuponesCSV(parameterMap);
		return parameterMap;
	}
	
	public String obtieneKeyTituloReporteOracle(Map parameterMap){
		return "reporteCUPNuevasCuponesForm.tituloReporteCSV";
	}
	
	public String generarNombreArchivoReporteOracle(Map parameterMap){
		String nombreArchivo = Constants.PREFIJO_REPORTE_CUP_NUEVAS_CUPONES;
		return nombreArchivo;
	}
}
