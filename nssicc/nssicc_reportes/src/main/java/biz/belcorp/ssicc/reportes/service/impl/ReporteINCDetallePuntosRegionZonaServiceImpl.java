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


@Service("reportes.reporteINCDetallePuntosRegionZonaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteINCDetallePuntosRegionZonaServiceImpl extends BaseReporteInterfaceImpl{
	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;
	
	@Override
    public Map generarReporteOracle (Map parameterMap) {
		
		String formatoReporte = (String) parameterMap.get("formatoReporte");
		String tipoReporte = (String) parameterMap.get("tipoReporte");
				
		if (formatoReporte.equals("OCSV")){
			if("N".equals(tipoReporte))
				reporteService.executeReporteINCDetallePuntosRegionZonaByNacionalCSV(parameterMap);
			if("R".equals(tipoReporte))
				reporteService.executeReporteINCDetallePuntosRegionZonaByRegionCSV(parameterMap);
			if("Z".equals(tipoReporte))
				reporteService.executeReporteINCDetallePuntosRegionZonaByZonaCSV(parameterMap);
			if("CO".equals(tipoReporte))
				reporteService.executeReporteINCDetallePuntosRegionZonaByConsultoraCSV(parameterMap);
			if("CA".equals(tipoReporte))
				reporteService.executeReporteINCDetallePuntosRegionZonaByCampaniaCSV(parameterMap);
		}
		return parameterMap;
	}
	
	@Override
	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		
		String formatoReporte = (String) parameterMap.get("formatoReporte");
		
		String nombreArchivo = null;
        if ("OCSV".equals(formatoReporte))
        	nombreArchivo = "reporteINCDetallePuntosRegionZonaCSV";       
		
		return nombreArchivo;
	}
	
	@Override
	public String obtieneKeyTituloReporteOracle(Map parameterMap) {
		String tipoReporte = (String) parameterMap.get("tipoReporte");
		String titulo ="";
		if(tipoReporte.equals("N"))
			titulo = "reporteINCDetallePuntosRegionZonaForm.tituloByNacional";
		
		if(tipoReporte.equals("R"))
			titulo = "reporteINCDetallePuntosRegionZonaForm.tituloByRegion";
		
		if(tipoReporte.equals("Z"))
			titulo = "reporteINCDetallePuntosRegionZonaForm.tituloByZona";
		
		if(tipoReporte.equals("CO"))
			titulo = "reporteINCDetallePuntosRegionZonaForm.tituloByConsultora";
		
		if(tipoReporte.equals("CA"))
			titulo = "reporteINCDetallePuntosRegionZonaForm.tituloByCampania";
		
		return titulo;
	}

}

