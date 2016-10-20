package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;

/**
 * The Class ReporteCOBDetalladoCobranza31DiasServiceImpl.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 18/03/2014
 */
@Service("reportes.reporteSACFacturacionDetalleService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteSACFacturacionDetalleServiceImpl extends BaseReporteInterfaceImpl {
	
		
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#generarReporteOracle(java.util.Map)
	 */
	public Map<String, Object> generarReporteOracle(Map<String, Object> parameterMap) {
		//-- Variables		
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		log.debug("ReporteSACFacturacionDetalleAction - formatoExportacion: " + formatoExportacion);
		
		//-- Logica		
		if (formatoExportacion.equals("OCSV"))
			parameterMap = reporteService.executeReporteSACFacturacionDetalle(parameterMap);
		
		return parameterMap;
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#generarNombreArchivoReporteOracle(java.util.Map)
	 */
	public String generarNombreArchivoReporteOracle(Map<String, Object> parameterMap) {
		String nombreArchivo = Constants.PREFIJO_REPORTE_SAC_FACTUR_DETAL;		
		return nombreArchivo;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#obtieneKeyTituloReporteOracle(java.util.Map)
	 */
	public String obtieneKeyTituloReporteOracle(Map<String, Object> parameterMap) {
		String key = null;
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		
			if (formatoExportacion.equals("OCSV"))
				return "reporteSACFacturacionDetalleForm.tituloReporteCSV";
		
		return key;	
	}

	@Override
	public String obtieneTipoDatosReporteOracle(Map<String, Object> parameterMap) {
		String tipo = null;
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		return tipo;
	}
	
	
}
