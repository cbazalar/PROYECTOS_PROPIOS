package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

@Service("reportes.reporteCCCInteresCCorrienteService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteCCCInteresCCorrienteServiceImpl extends BaseReporteInterfaceImpl {
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	public Map generarReporteOracle(Map parameterMap) {
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		
		if (formatoExportacion.equals("OCSV")){
			parameterMap = reporteService.executeReporteCCCInteresCCorrienteCSV(parameterMap);
		}		
		return parameterMap;
	}
	
	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		String tipoInteres = (String) parameterMap.get("tipoReporteInteres");		
		if(StringUtils.equals(tipoInteres, "CIP"))
			return "reporteCCCInteresCCorrienteFacturadoPedidoCSV";
		if(StringUtils.equals(tipoInteres, "CIB"))
			return "reporteCCCInteresCCorrienteCalculadoBloqueoCSV";
		if(StringUtils.equals(tipoInteres, "CIF"))
			return "reporteCCCInteresCCorrienteFacturadoCSV";		
		
		return null;
	}
	
	public String obtieneKeyTituloReporteOracle(Map parameterMap) {		
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		String tipoInteres = (String) parameterMap.get("tipoReporteInteres");
		if(StringUtils.equals(formatoExportacion, "OCSV")){
			if(StringUtils.equals(tipoInteres, "CIP"))
				return "reporteCCCInteresCCorrienteForm.tituloReportePedidoCSV";
			if(StringUtils.equals(tipoInteres, "CIB"))
				return "reporteCCCInteresCCorrienteForm.tituloReporteBloqueoCSV";
			if(StringUtils.equals(tipoInteres, "CIF"))
				return "reporteCCCInteresCCorrienteForm.tituloReporteFacturadoCSV";			
		}		
		return null;	
	}
	
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {	
		Map criteria = (Map)reporteParams.getQueryParams().get("parameterMap");
		String formatoExportacion = (String) criteria.get("formatoExportacion");
		if (formatoExportacion.equals("OCSV")){		
			reporteService.executeGenerarReporteCCCInteresCCorriente(criteria);
		}
		return reporteParams;
	}

}
