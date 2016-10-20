/**
 * 
 */
package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

/**
 * @author Danny Amaro
 *
 */
@Service("reportes.reportePERDetalleCtaCteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReportePERDetalleCtaCteServiceImpl extends BaseReporteInterfaceImpl {
	private static final long serialVersionUID = -4390094424857707131L;
		
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#beforeExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		
	
		Map params = (Map)reporteParams.getQueryParams().get("parameterMap");
		log.debug("Los parametros del Reporte en el before son: "
				+ params.toString());
		reporteService.executeDetalleCtaCte(params);
		String codigoZona = (String)params.get("codigoZona");
		if (StringUtils.isBlank(codigoZona)) 
			params.put("codigoZona","TODAS");
		String codigoSeccion = (String)params.get("codigoSeccion");
		if (StringUtils.isBlank(codigoSeccion)) 
			params.put("codigoSeccion","TODAS");
		String codigoTerritorio = (String)params.get("codigoTerritorio");
		if (StringUtils.isBlank(codigoTerritorio)) 
			params.put("codigoTerritorio","TODAS");
		
		return reporteParams;
		
	}
	

	
	
	
}
