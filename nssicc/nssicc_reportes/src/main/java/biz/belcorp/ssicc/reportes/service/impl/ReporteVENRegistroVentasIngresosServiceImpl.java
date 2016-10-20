package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;

@Service("reportes.reporteVENRegistroVentasIngresosService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteVENRegistroVentasIngresosServiceImpl extends
		BaseReporteInterfaceImpl {

	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#generarReporteOracle(java.util.Map)
	 */
	@Override
	public Map<String, Object> generarReporteOracle(
			Map<String, Object> parameterMap) {
		//-- Variables
		String tipoReporte = (String) parameterMap.get("tipoReporte");
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		log.debug("ReporteVENRegistroVentasIngresosAction - formatoExportacion: " + formatoExportacion);
		
		//-- Logica
		if(tipoReporte.trim().equalsIgnoreCase("n")){
			if (formatoExportacion.equals("OTXT"))
				parameterMap = reporteService.generarReporteRUVSunatTXT(parameterMap);
			if (formatoExportacion.equals("OCSV"))
				parameterMap = reporteService.generarReporteRUVSunatCSV(parameterMap);
			if (formatoExportacion.equals("OXLS"))
				parameterMap = reporteService.generarReporteRUVSunatExcel(parameterMap);
		}else{//-- "e"
			if (formatoExportacion.equals("OTXT"))
				parameterMap = reporteService.generarReporteRUVSunatErrorTXT(parameterMap);
			if (formatoExportacion.equals("OCSV"))
				parameterMap = reporteService.generarReporteRUVSunatErrorCSV(parameterMap);
		}

		return parameterMap;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#obtieneKeyTituloReporteOracle(java.util.Map)
	 */
	@Override
	public String obtieneKeyTituloReporteOracle(Map<String, Object> parameterMap) {
		// TODO Auto-generated method stub
		String key = null;
		String tipoReporte = (String) parameterMap.get("tipoReporte");
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		if(tipoReporte.trim().equalsIgnoreCase("n")){
			if (formatoExportacion.equals("OCSV"))
				return "reporteVENRegistroVentasIngresosForm.tituloReporteCSV";
			if (formatoExportacion.equals("OXLS"))
				return "reporteVENRegistroVentasIngresosForm.tituloReporteExcel";
		}
		else { 
			if (formatoExportacion.equals("OCSV"))
				return "reporteVENRegistroVentasIngresosForm.tituloReporteErrorCSV";
		}	
		return key;	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#generarNombreArchivoReporteOracle(java.util.Map)
	 */
	@Override
	public String generarNombreArchivoReporteOracle(
			Map<String, Object> parameterMap) {
		String codigoPeriodoInformar = (String) parameterMap.get("codigoPeriodoInformar");
		String nombreArchivo = Constants.PREFIJO_REPORTE_RUV_SUNAT + Constants.RUC_BELCORP + codigoPeriodoInformar + Constants.SUFIJO_REPORTE_RUV_SUNAT;
		return nombreArchivo;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#obtieneTipoDatosReporteOracle(java.util.Map)
	 */
	@Override
	public String obtieneTipoDatosReporteOracle(Map<String, Object> parameterMap) {
		String tipo = null;
		String tipoReporte = (String) parameterMap.get("tipoReporte");
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		if(tipoReporte.trim().equalsIgnoreCase("n")){
			if (formatoExportacion.equals("OXLS"))
				tipo = "C;C;C;C;C;C;C;C;C;C;C;N;N;N;N;N;N;N;N;N;N;N;C;C;C;C;C";
		}
		return tipo;
	}

	

}

