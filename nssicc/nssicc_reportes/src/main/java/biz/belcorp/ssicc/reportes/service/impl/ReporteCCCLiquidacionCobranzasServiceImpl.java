package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

@Service("reportes.reporteCCCLiquidacionCobranzasService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteCCCLiquidacionCobranzasServiceImpl extends
		BaseReporteInterfaceImpl {

	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;
	
	private String tipoNombreReporte;
	private String formatoExportacion;

	
	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		String nombreArchivo = Constants.PREFIJO_REPORTE_CCC_LIQUI_COBRA;
		return nombreArchivo;
	}
	
	public String obtieneKeyTituloReporteOracle(Map parameterMap) {
		return "reporteCCCLiquidacionCobranzasForm" + this.tipoNombreReporte + ".tituloReporteCSV";
	}
	
	protected void beforeExecuteReporte(ReporteParams reporteParams)
			throws Exception {
		Map parameterMap = reporteParams.getQueryParams();
		Map criteria = (Map) parameterMap.get("parameterMap");
		
		this.tipoNombreReporte = (String) parameterMap.get("tipoNombreReporte");
		this.formatoExportacion = (String) parameterMap.get("formatoExportacion");
		
		
		if (formatoExportacion.equals("OCSV")) {
			if (this.tipoNombreReporte.equals("CZ")) {
				reporteService.insertReporteCCCLiquidacionCobranzasCZ(criteria);
			}
				
			if (this.tipoNombreReporte.equals("DZ")) {
				reporteService.insertReporteCCCLiquidacionCobranzasDZ(criteria);
			}			
		}
	}
	
    public Map generarReporteOracle (Map parameterMap) {
		//-- Variables;
		log.debug("ReporteCCCLiquidacionCobranzasForm - formatoExportacion: " + formatoExportacion);
		if (formatoExportacion.equals("OCSV"))
			reporteService.executeReporteCCCLiquidacionCobranzasCSV(parameterMap);
		return parameterMap;
	}

	/**
	 * @return the tipoNombreReporte
	 */
	public String getTipoNombreReporte() {
		return tipoNombreReporte;
	}

	/**
	 * @param tipoNombreReporte the tipoNombreReporte to set
	 */
	public void setTipoNombreReporte(String tipoNombreReporte) {
		this.tipoNombreReporte = tipoNombreReporte;
	}

	/**
	 * @return the formatoExportacion
	 */
	public String getFormatoExportacion() {
		return formatoExportacion;
	}

	/**
	 * @param formatoExportacion the formatoExportacion to set
	 */
	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}
}