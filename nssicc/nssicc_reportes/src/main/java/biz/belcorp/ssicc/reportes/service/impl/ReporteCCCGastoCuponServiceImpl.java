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


@Service("reportes.reporteCCCGastoCuponService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCCCGastoCuponServiceImpl extends BaseReporteInterfaceImpl{

	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCCCGastoCuponServiceImpl.beforeExecuteReporte' method");
		}
	
		return reporteParams;
	}	
	
	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		String tiporeporte = (String) parameterMap.get("tiporeporte");
		String nombreArchivo = Constants.PREFIJO_REPORTE_CCC_GASTO_CUPON + tiporeporte;
		return nombreArchivo;
	}
	

	public String obtieneKeyTituloReporteOracle(Map parameterMap) {
		String titulo = "";
		String tiporeporte = (String) parameterMap.get("tiporeporte");
		if(StringUtils.equals(tiporeporte, "Banco")){
			titulo = "reporteCCCGastoCuponForm.tituloReporteBancoCSV";
		}else{
			titulo = "reporteCCCGastoCuponForm.tituloReporteZonaCSV";
		}
		
		return titulo;
	}
		

    public Map generarReporteOracle (Map parameterMap) {
		
    	String formatoexportacion = (String) parameterMap.get("formatoexportacion");
		if (formatoexportacion.equals("OCSV"))
			reporteService.executeReporteCCCGastoCuponCSV(parameterMap);
		
		return parameterMap;
	}
	



	
}


