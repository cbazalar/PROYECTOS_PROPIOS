package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;


@Service("reportes.reporteZONUnidadesGeograficasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteZONUnidadesGeograficasServiceImpl extends BaseReporteInterfaceImpl{

	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	
	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		String nombreArchivo = "reporteZONUnidadesGeograficas";
		return nombreArchivo;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#obtieneKeyTituloReporteOracle(java.util.Map)
	 */
	public String obtieneKeyTituloReporteOracle(Map parameterMap) {
		// TODO Auto-generated method stub
		String key = null;
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		if (formatoExportacion.equals("OCSV"))
			return "reporteZONUnidadesGeograficasForm.tituloReporteCSV";
		return key;	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#generarReporteOracle(java.util.Map)
	 */
	public Map generarReporteOracle (Map parameterMap) {
		
		//-- Variables
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		log.debug("ReporteVENRegistroVentasIngresosAction - formatoExportacion: " + formatoExportacion);
		
		if (formatoExportacion.equals("OCSV"))
			parameterMap = reporteService.generarReporteZONUnidadesGeograficasCSV(parameterMap);
		return parameterMap;
	}

}
