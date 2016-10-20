/**
 * 
 */
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
 * @author Danny Amaro
 *
 */
@Service("reportes.reporteCCCDetalladoProvisionIncobrableService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCCCDetalladoProvisionIncobrableServiceImpl extends BaseReporteInterfaceImpl {
	private static final long serialVersionUID = -4390094424857702131L;
		
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	private String formatoExportacion;
	
	
	@Override
	public String generarNombreArchivoReporteOracle(
			Map<String, Object> parameterMap) {
	
		String pre_nom="";
		String nombreArchivo= "";
		String tipoReporte=parameterMap.get("tipoReporte").toString();
		
		System.out.println("TIPOREPORTE::"+tipoReporte);
		
		if(tipoReporte.equals("DC")){
			nombreArchivo = Constants.PREFIJO_REPORTE_CCC_DETALLE_PROVIS_INCOBR_DC;
		}else if(tipoReporte.equals("DM")){
			nombreArchivo = Constants.PREFIJO_REPORTE_CCC_DETALLE_PROVIS_INCOBR_DM;
		}
		
		return  nombreArchivo;
	}
	
	public String obtieneKeyTituloReporteOracle(Map parameterMap) {
		String key = null;
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		String tipoReporte=parameterMap.get("tipoReporte").toString();
			if (formatoExportacion.equals("OCSV") && tipoReporte.equals("DC")){return "reporteDetalladoProvisionIncobrableForm.tituloReporteConsultorasCSV";}
			if (formatoExportacion.equals("OCSV") && tipoReporte.equals("DM")){return "reporteDetalladoProvisionIncobrableForm.tituloReporteCSV";}
		return key;	
	}
	public String obtieneTipoDatosReporteOracle(Map parameterMap) {
		String tipo = null;
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		return tipo;
	}
	
	public Map generarReporteOracle(Map parameterMap) {
		String tipoReporte=parameterMap.get("tipoReporte").toString();
		
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		
		System.out.println("ReporteDetalladoProvisionIncobrableAction - formatoExportacion: " + formatoExportacion);
		log.debug("ReporteDetalladoConsultorasProvisionAction - formatoExportacion: " + formatoExportacion);
		
		//-- Logica
		if (formatoExportacion.equals("OCSV") && tipoReporte.equals("DC")){ parameterMap = reporteService.executeReporteCCCDetalladoProvisionIncobrableCSV(parameterMap); }
		if (formatoExportacion.equals("OCSV") && tipoReporte.equals("DM")){ parameterMap = reporteService.executeReporteCCCDetalladoProvisionIncobrableMovCSV(parameterMap); }
		
		return parameterMap;
	}

	
}
