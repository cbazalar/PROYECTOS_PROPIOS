package biz.belcorp.ssicc.reportes.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;


@Service("reportes.reporteCCCAuditoriaSaldoCuentasPorCobrarService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCCCAuditoriaSaldoCuentasPorCobrarServiceImpl extends BaseReporteInterfaceImpl{

	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCCCAuditoriaSaldoCuentasPorCobrarServiceImpl.beforeExecuteReporte' method");
		}
		
		super.beforeExecuteReporte(reporteParams, formReporte);
				
		Map params = (Map)reporteParams.getQueryParams().get("parameterMap");
		log.debug("Los parametros del Reporte en el before son: "
				+ params.toString());
		reporteService.executeAuditoriaSaldoCuentasPorCobrar();
		reporteParams.setQueryParams(params);
		return reporteParams;
	}	
	
	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		String codigoPais = (String) parameterMap.get("codigoPais");
		String nombreReporte = (String) parameterMap.get("nombreReporteCSV");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");   
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("nombreReporte", nombreReporte);
		Map paramReporte = reporteService.getParametrosReporte(criteria);  
		
		String nombreArchivo = (String) paramReporte.get("prefijoArchivo") + "_"         
							+ sdf.format(new Date(System.currentTimeMillis()));    
		return nombreArchivo;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#obtieneKeyTituloReporteOracle(java.util.Map)
	 */
	public String obtieneKeyTituloReporteOracle(Map parameterMap) {
		return "reporteCCCAuditoriaSaldoCuentasPorCobrarForm.tituloReporteCSV";
	}
	
	
	 /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#generarReporteOracle(java.util.Map)
     */
    public Map generarReporteOracle (Map parameterMap) {
		//-- Variables
		reporteService.executeReporteCCCAuditoriaSaldoCuentasPorCobrarCSV(parameterMap);
		return parameterMap;
	}
	
    
    public String getMailService() {
		return "ccc.mailReporteCCCAuditoriaSaldoCuentasPorCobrarService";
	}

	
}

