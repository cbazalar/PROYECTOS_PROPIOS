package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("reportes.reporteCOBRecuperacionCobranzaFFVVServiceImpl")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteCOBRecuperacionCobranzaFFVVServiceImpl extends BaseReporteInterfaceImpl{
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {
		
		super.beforeExecuteReporte(reporteParams,formReporte);
		Map params = (Map)reporteParams.getQueryParams().get("parameterMap");
		
		String codigoRegion = MapUtils.getString(params, "codigoRegion");
		
		if(StringUtils.isBlank(codigoRegion))
			params.put("codigoRegion", null);
		
		reporteService.executePrepararReporteCOBRecuperacionCobranzaFFVVTXT(params);
		
		return reporteParams;
		
	}
	
	@Override
	public String generarNombreArchivoReporteOracle(
			Map<String, Object> parameterMap) {
		// TODO Auto-generated method stub
		return "ReporteCOBRecuperacionCobranzaFFVV";
	}
	
	@Override
	public String obtieneKeyTituloReporteOracle(Map<String, Object> parameterMap) {
		// TODO Auto-generated method stub
		return "reporteCOBRecuperacionCobranzaFFVVForm.tituloReporte";
	}

	@Override
	public Map<String, Object> generarReporteOracle(
			Map<String, Object> parameterMap) {
		
		reporteService.executeGenerarReporteCOBRecuperacionCobranzaFFVVTXT(parameterMap);		
		return parameterMap;
		
	}
	
}
