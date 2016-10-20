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

@Service("reportes.reporteCCCDetalladoConsultorasIncobrableService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCCCDetalladoConsultorasIncobrableServiceImpl extends BaseReporteInterfaceImpl
{
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	@Override
	public String generarNombreArchivoReporteOracle(Map<String, Object> parameterMap) 
	{
		String nombreArchivo = Constants.PREFIJO_REPORTE_CCC_DETALLE_CONSUL_INCOBR;		
		return nombreArchivo;
	}
	
	@Override
	public String obtieneKeyTituloReporteOracle(Map<String, Object> parameterMap) {
		String key = null;
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		
			if (formatoExportacion.equals("OCSV"))
				return "reporteDetalladoConsultorasIncobrableForm.tituloReporteCSV";
		
		return key;	
	}
	
	@Override
	public String obtieneTipoDatosReporteOracle(Map<String, Object> parameterMap) {
		String tipo = null;
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		return tipo;
	}
	

	@Override
	public Map<String, Object> generarReporteOracle(Map<String, Object> parameterMap) 
	{
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		
		System.out.println("ReporteDetalladoConsultorasIncobrableAction - formatoExportacion: " + formatoExportacion);
		log.debug("ReporteDetalladoConsultorasIncobrableAction - formatoExportacion: " + formatoExportacion);
		
		//-- Logica
		if (formatoExportacion.equals("OCSV"))
			parameterMap = reporteService.executeReporteCCCDetalladoConsultorasIncobrableCSV(parameterMap);
		
		return parameterMap;
	}
	
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception 
	{
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");		
		log.debug("Los parametros del Reporte en el before son: " + params.toString());

		//Elimina tabla temporal
		reporteService.executeDeleteInsertCCCDetalladoConsultorasIncobrable(params);
		//Llena tabla temporal
		//siccService.executeReporteCCCDetalladoConsultorasIncobrable(params);
		
		return reporteParams;
	}
}
