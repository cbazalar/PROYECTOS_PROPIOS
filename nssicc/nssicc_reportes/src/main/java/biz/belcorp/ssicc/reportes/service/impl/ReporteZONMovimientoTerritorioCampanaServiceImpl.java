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


@Service("reportes.reporteZONMovimientoTerritorioCampanaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteZONMovimientoTerritorioCampanaServiceImpl extends BaseReporteInterfaceImpl{

	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteZONMovimientoTerritorioCampanaServiceImpl.beforeExecuteReporte' method");
		}
		Map criteria = (Map)reporteParams.getQueryParams().get("parameterMap");
		String formatoExportacion = (String) criteria.get("formatoExportacion");
		
		if (formatoExportacion.equals("OCSV"))
			reporteService.executeCargaTemporalReporteZONMovimientoTerritorio(criteria);
	
		return reporteParams;
	}	
	
	public Map generarReporteOracle(Map parameterMap) {
		
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		
		if (formatoExportacion.equals("OCSV"))
			parameterMap = reporteService.generarReporteZONMovimientoTerritorioCSV(parameterMap);
		
		return parameterMap;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#generarNombreArchivoReporteOracle(java.util.Map)
	 */
	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		
		String formatoReporte = (String) parameterMap.get("formatoReporte");
		if ("OCSV".equals(formatoReporte))
			return "reporteZONMovimientoTerritorioCampanaCSV";
		else
			return "reporteMaestroHorizontal";
	}

}

