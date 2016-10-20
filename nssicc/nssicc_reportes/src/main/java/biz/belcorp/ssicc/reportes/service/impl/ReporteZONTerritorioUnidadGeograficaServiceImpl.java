/**
 * 
 */
package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;

/**
 * @author Danny Amaro
 * 
 */
@Service("reportes.reporteZONTerritorioUnidadGeograficaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteZONTerritorioUnidadGeograficaServiceImpl extends
		BaseReporteInterfaceImpl {
	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;
	private String formatoExportacion;

	// public String generarNombreArchivoReporteOracle(Map parameterMap) {
	// return "reporteZONTerritorioUnidadGeograficaCSV";
	// }

	@Override
	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		// TODO Auto-generated method stub
		return "reporteZONTerritorioUnidadGeograficaCSV";
	}

	// public String obtieneKeyTituloReporteOracle(Map parameterMap) {
	// return null;
	// }

	public Map generarReporteOracle(Map parameterMap) {
		formatoExportacion=(String)parameterMap.get("formatoExportacion");
		if (this.formatoExportacion.equals("OCSV"))
			reporteService.executeReporteZONTerritorioUnidadGeograficaCSV(parameterMap);
		return parameterMap;
	}

}
