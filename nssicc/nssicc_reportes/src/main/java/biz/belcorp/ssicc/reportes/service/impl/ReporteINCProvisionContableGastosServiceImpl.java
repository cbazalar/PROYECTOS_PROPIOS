package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;


@Service("reportes.reporteINCProvisionContableGastosService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteINCProvisionContableGastosServiceImpl extends BaseReporteInterfaceImpl{
	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;
	
	@Override
    public Map generarReporteOracle (Map parameterMap) {
		
		String formatoReporte = (String) parameterMap.get("formatoReporte");
				
		if (formatoReporte.equals("OCSV"))
				reporteService.executeReporteINCProvisionContableGastosCSV(parameterMap);
			
		return parameterMap;
	}
	
	@Override
	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		
		String formatoReporte = (String) parameterMap.get("formatoReporte");
		
		String nombreArchivo = null;
        if ("OCSV".equals(formatoReporte))
        	nombreArchivo = "reporteINCProvisionContableGastosCSV";       
		
		return nombreArchivo;
	}
	
	@Override
	public String obtieneKeyTituloReporteOracle(Map parameterMap) {
		String titulo = "reporteINCProvisionContableGastosForm.tituloOCSV";			
		
		return titulo;
	}

}



