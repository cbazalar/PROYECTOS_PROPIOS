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


@Service("reportes.reporteINCCuponesElectronicosService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteINCCuponesElectronicosServiceImpl extends BaseReporteInterfaceImpl{
	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;
	
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {
		
		super.beforeExecuteReporte(reporteParams, formReporte);
		
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		
		log.debug("Los parametros del Reporte en el before son: "+ params.toString());
		
		reporteService.executeReporteINCCuponesElectronicos(params); 
		reporteParams.setQueryParams(params);
		return reporteParams;
	}
	
	@Override
	public Map generarReporteOracle(Map parameterMap) {
		
		String formatoReporte = (String) parameterMap.get("formatoReporte");		
		
		Map result = parameterMap;
        if ("OCSV".equals(formatoReporte)){
			
        	result = reporteService.generarReporteINCCuponesElectronicosCSV(parameterMap);        	
		}
        
        return result;
	}
	
	@Override
	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		
		String formatoReporte = (String) parameterMap.get("formatoReporte");
		
		String nombreArchivo = null;
        if ("OCSV".equals(formatoReporte)){
        	nombreArchivo = "reporteINCCuponesElectronicosCSV";
        }
		
		return nombreArchivo;
	}
	
	@Override
	public String obtieneKeyTituloReporteOracle(Map parameterMap) {
		
		String titulo = "";
		String formatoReporte = (String) parameterMap.get("formatoReporte");

		if ("OCSV".equals(formatoReporte)){
			titulo = "reporteINCCuponesElectronicosCSV.titulo";
		}
	
	return titulo;
}

}
