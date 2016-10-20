package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;



@Service("reportes.reportePERResumenDiarioPercepcionesSunatService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReportePERResumenDiarioPercepcionesSunatServiceImpl extends BaseReporteInterfaceImpl{
	
	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#generarReporteOracle(java.util.Map)
	 */
	public Map<String, Object> generarReporteOracle(Map<String, Object> parameterMap) {
	
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");

			if (StringUtils.equals(formatoExportacion, "OTXT"))				 
			parameterMap = reporteService.executeReportePERResumenDiarioPercepcionesSunatTXT(parameterMap);
			if (StringUtils.equals(formatoExportacion, "OCSV"))
				parameterMap= reporteService.executeReportePERResumenDiarioPercepcionesSunatCSV(parameterMap);
			
		return parameterMap;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#obtieneKeyTituloReporteOracle(java.util.Map)
	 */
	public String obtieneKeyTituloReporteOracle(Map<String, Object> parameterMap) {
		String key = null;		
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		
		if (formatoExportacion.equals("OCSV"))
			return "reportePERResumenDiarioPercepcionesSunatForm.tituloReporteCSV";
		
		return key;	
	}
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#generarNombreArchivoReporteOracle(java.util.Map)
	 */
	@Override
	public String generarNombreArchivoReporteOracle(Map<String, Object> parameterMap) {
		String nombreArchivo="";
		String fecha = (String) parameterMap.get("fechaGeneraNombre");
		String nombre = (String) parameterMap.get("nombreReportePer");
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		
		if (formatoExportacion.equals("OCSV"))		
			nombreArchivo = String.format("%s-%s", nombre, fecha);			
		
		if (formatoExportacion.equals("OTXT"))			
			nombreArchivo = String.format("%s-%s-%s", nombre, fecha, Constants.VALOR_SECU_PERCEPCION_SUNAT);	
		
		return nombreArchivo;
	}	

}
