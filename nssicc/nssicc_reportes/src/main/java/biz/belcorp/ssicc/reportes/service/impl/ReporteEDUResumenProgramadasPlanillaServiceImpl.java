package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

@Service("reportes.reporteEDUResumenProgramadasPlanillaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteEDUResumenProgramadasPlanillaServiceImpl extends BaseReporteInterfaceImpl
{	
	@Resource(name="edu.mantenimientoEDUGenericoService")
	private MantenimientoEDUGenericoService siccService;
	
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception 
	{
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		siccService.executeGenerarResumenProgramadasPlanilla(params);		
		reporteParams.setQueryParams(params);		
		
		return reporteParams;		
	}
}
