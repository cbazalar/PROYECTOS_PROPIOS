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

/**
 * The Class ReporteSICDetalleUnidadesAtendidasFaltanteServiceImpl.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 18/03/2014
 */
@Service("reportes.reporteSICDetalleUnidadesAtendidasFaltanteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteSICDetalleUnidadesAtendidasFaltanteServiceImpl extends BaseReporteInterfaceImpl {
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#beforeExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		String formatoExportacion = (String) params.get("formatoExportacion");
		
		String fechaFacturacionInicio = (String) params.get("fechaFacturacionInicio");
		String fechaFacturacionFin = (String) params.get("fechaFacturacionFin");
		if (StringUtils.isBlank(fechaFacturacionInicio)) fechaFacturacionInicio = null;
		if (StringUtils.isBlank(fechaFacturacionFin)) fechaFacturacionFin = null;
		
		if (formatoExportacion.equals("OCSV")){
			reporteService.executeBeforeReporteSICDetalleUnidadesAtendidasFaltantes(params);
		}
		
		return reporteParams;
	}
	
	@Override
	public Map<String, Object> generarReporteOracle(Map<String, Object> parameterMap) {
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		
		if (formatoExportacion.equals("OCSV"))
			reporteService.executeReporteSICDetalleUnidadesAtendidasFaltantesCSV(parameterMap);
		
		return parameterMap;
	}

	@Override
	public String obtieneKeyTituloReporteOracle(Map<String, Object> parameterMap) {
		String key = null;
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		
		if (formatoExportacion.equals("OCSV"))
			return "reporteSICDetalleUnidadesAtendidasFaltanteForm.tituloReporteCSV";
		
		return key;
	}

	@Override
	public String generarNombreArchivoReporteOracle(Map<String, Object> parameterMap) {
		String nombreArchivo = Constants.PREFIJO_REPORTE_SIC_DETAL_UNIDA_FALTA;
		
		return nombreArchivo;
	}
}