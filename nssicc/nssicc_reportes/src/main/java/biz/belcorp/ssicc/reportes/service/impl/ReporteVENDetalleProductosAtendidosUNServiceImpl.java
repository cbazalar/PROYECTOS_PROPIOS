package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;

@Service("reportes.reporteVENDetalleProductosAtendidosUNService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteVENDetalleProductosAtendidosUNServiceImpl extends
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
		return "reporteVENDetalleProductosAtendidosUNCSV";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl
	 * #obtieneKeyTituloReporteOracle(java.util.Map)
	 */
	public String obtieneKeyTituloReporteOracle(Map parameterMap) {
		return "reporteVENDetalleProductosAtendidosUNForm.tituloReporteCSV";
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

		if (formatoExportacion.equals("OCSV"))
			parameterMap = this.reporteService
					.generarReporteVENDetalleProductosAtendidosUNCSV(parameterMap);

		return parameterMap;
	}
}
