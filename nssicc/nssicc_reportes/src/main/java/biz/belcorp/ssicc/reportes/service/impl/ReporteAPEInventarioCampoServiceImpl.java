package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

@Service("reportes.reporteAPEInventarioCampoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteAPEInventarioCampoServiceImpl extends BaseReporteInterfaceImpl {

	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;
	private String tipoReporte = "";

	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteAPEInventarioCampoServiceImpl.beforeExecuteReporte' method");
		}

		Map params = (Map) reporteParams.getQueryParams().get(
				"parameterMap");
		this.tipoReporte = (String)MapUtils.getObject(params, "tipoReporte");

		reporteService.executeDeleteInsertZonasInventarioCampo(params);

		
		
		return reporteParams;
	}

	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		String nombreArchivo = "";
		if(StringUtils.isNotEmpty(tipoReporte)&&StringUtils.isNotBlank(tipoReporte)){
			if(StringUtils.equalsIgnoreCase(this.tipoReporte, "Resumen")){
				nombreArchivo = Constants.PREFIJO_REPORTE_APE_INVENT_CAMPO_RESU;
			}else{
				nombreArchivo = Constants.PREFIJO_REPORTE_APE_INVENT_CAMPO_DETA;
			}
		}
		return nombreArchivo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction
	 * #obtieneKeyTituloReporteOracle(java.util.Map)
	 */
	public String obtieneKeyTituloReporteOracle(Map parameterMap) {
		if(StringUtils.equalsIgnoreCase(this.tipoReporte, "Resumen")){
			return "reporteAPEInventarioCampoForm.tituloReporteCSVResumen";
		}else{
			return "reporteAPEInventarioCampoForm.tituloReporteCSVDetalle";
		}
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction
	 * #generarReporteOracle(java.util.Map)
	 */
	public Map generarReporteOracle(Map parameterMap) {
		
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");
		log.debug("reporteAPEInventarioCampoServiceForm - formatoExportacion: "
				+ formatoExportacion);
		if (formatoExportacion.equals("OCSV"))
			reporteService.executeReporteAPEIventarioCampo(parameterMap);
		return parameterMap;
	}

}
