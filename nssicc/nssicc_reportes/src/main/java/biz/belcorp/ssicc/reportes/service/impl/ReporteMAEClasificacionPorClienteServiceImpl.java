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
 * The Class ReporteMAEClasificacionPorClienteServiceImpl.
 *
 * @autor: Belcorp
 */
@Service("reportes.reporteMAEClasificacionPorClienteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteMAEClasificacionPorClienteServiceImpl extends BaseReporteInterfaceImpl {
	
		
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#generarReporteOracle(java.util.Map)
	 */
	public Map<String, Object> generarReporteOracle(Map<String, Object> parameterMap) {
		//-- Variables		
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		log.debug("ReporteMAEClasificacionPorClienteAction - formatoExportacion: " + formatoExportacion);
		
		//-- Logica		
		if (formatoExportacion.equals("OCSV"))
			reporteService.executeReporteMAEClasificacionXClienteCSV(parameterMap);
		
		return parameterMap;
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#generarNombreArchivoReporteOracle(java.util.Map)
	 */
	public String generarNombreArchivoReporteOracle(Map<String, Object> parameterMap) {
		String nombreArchivo = Constants.PREFIJO_REPORTE_MAE_CLASIFICACION_POR_CLIENTE;
		return nombreArchivo;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#obtieneKeyTituloReporteOracle(java.util.Map)
	 */
	public String obtieneKeyTituloReporteOracle(Map<String, Object> parameterMap) {
		return "reporteMAEClasificacionPorClienteForm.tituloReporteCSV";
	}

	@Override
	public String obtieneTipoDatosReporteOracle(Map<String, Object> parameterMap) {
		String tipo = null;
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		return tipo;
	}
	
}
