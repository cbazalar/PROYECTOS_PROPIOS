package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;

@Service("reportes.reporteCOBCargaMasivaGestionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCOBCargaMasivaGestionServiceImpl extends BaseReporteInterfaceImpl{
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	@Override
	public String generarNombreArchivoReporteOracle(Map<String, Object> parameterMap) {
		String nombreArchivo = Constants.PREFIJO_REPORTE_COB_CARG_MASI_GESTI;
		return nombreArchivo;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#obtieneKeyTituloReporteOracle(java.util.Map)
	 */
	@Override
	public String obtieneKeyTituloReporteOracle(Map<String, Object> parameterMap) {
		return "reporteCOBCargaMasivaGestionForm.tituloReporteCSV";
	}
	
	@Override
	public Map<String, Object> generarReporteOracle(Map<String, Object> parameterMap) {
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		log.debug("reporteCOBCargaMasivaGestion - formatoExportacion: " + formatoExportacion);
		
		if (formatoExportacion.equals("OCSV"))
			reporteService.executeReporteCOBCargaMasivaGestionCSV(parameterMap);
		
		return parameterMap;
	}	
}