package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

@Service("reportes.reporteCCCDetalleCuentaCorrienteContableService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteCCCDetalleCuentaCorrienteContableServiceImpl extends
		BaseReporteInterfaceImpl {

	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;

	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {		
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		String tipo=params.get("tipoReporte").toString();
		if(StringUtils.equals(tipo, "L"))
			reporteService.executeReporteCCCPRDetalleCuentaCorriente(params);
		return reporteParams;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#generarNombreArchivoReporteOracle(java.util.Map)
	 */
	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		String nombreArchivo = null;
		String formatoreporte = (String) parameterMap.get("formatoreporte");
		nombreArchivo = Constants.PREFIJO_REPORTE_CCC_DETALLE_CTACTE + formatoreporte;
		return nombreArchivo;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#obtieneKeyTituloReporteOracle(java.util.Map)
	 */
	public String obtieneKeyTituloReporteOracle(Map parameterMap) {
		String tipoReporte = (String) parameterMap.get("tipoReporte");		
		return "reporteCCCDetalleCuentaCorrienteContable" + tipoReporte + ".tituloReporteCSV";
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#generarReporteOracle(java.util.Map)
	 */
	public Map generarReporteOracle (Map parameterMap) {
		//-- Variables
		String formatoExportacion = (String) parameterMap.get("formatoreporte");
		log.debug("reporteCCCDetalleCuentaCorrienteContable - formatoExportacion: " + formatoExportacion);
		if (formatoExportacion.equals("OCSV"))
			reporteService.executeReporteCCCDetalleCuentaCorrienteContableCSV(parameterMap);
		return parameterMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction
	 * #beforeExecuteReporte
	 * (biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected void beforeExecuteReporte(ReporteParams reporteParams)
			throws Exception {

	}

}
