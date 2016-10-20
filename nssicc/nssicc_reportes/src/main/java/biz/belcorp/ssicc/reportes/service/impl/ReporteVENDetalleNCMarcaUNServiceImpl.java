package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;

@Service("reportes.reporteVENDetalleNCMarcaUNService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteVENDetalleNCMarcaUNServiceImpl extends
		BaseReporteInterfaceImpl {

	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl
	 * #generarNombreArchivoReporteOracle(java.util.Map)
	 */
	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		return "reporteVENDetalleNCMarcaUNCSV";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl
	 * #obtieneKeyTituloReporteOracle(java.util.Map)
	 */
	public String obtieneKeyTituloReporteOracle(Map parameterMap) {
		String tipoReporte=(String) parameterMap.get("tipoReporte");
		if(StringUtils.equals(tipoReporte, "NCR")){
			return "reporteVENDetalleNCMarcaUNForm.tituloReporteCSV";
		}
		return "reporteVENDetalleNCMarcaUNForm.tituloReporteDebitoCSV";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl
	 * #generarReporteOracle(java.util.Map)
	 */
	public Map generarReporteOracle(Map parameterMap) {
		// -- Variables
		String formatoExportacion = (String) parameterMap
				.get("formatoExportacion");
		String tipoReporte=(String) parameterMap.get("tipoReporte");
		if (formatoExportacion.equals("OCSV")){
			if(StringUtils.equals(tipoReporte, "NCR"))
				parameterMap = this.reporteService.generarReporteVENDetalleNCMarcaUNCSV(parameterMap);
			else
				parameterMap = this.reporteService.generarReporteVENDetalleNDebitoMarcaUNCSV(parameterMap);
		}
		return parameterMap;
	}
}
