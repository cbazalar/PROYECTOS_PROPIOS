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

@Service("reportes.reporteRECOperacionesUnidadAdmService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteRECOperacionesUnidadAdmServiceImpl extends BaseReporteInterfaceImpl{

	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		
		
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		String nextValue = reporteService.getSecuenciaNextValue();
		params.put("nextValue", nextValue);
		String tipoMovimiento = (String) params.get("tipoMovimiento");
		if (StringUtils.equals(Constants.IND_MOVIMIENTO_DEVUELVE,tipoMovimiento))
			reporteService.insertTableReportesSQL("insertReporteRECOperacionesUnidadAdmDevuelve", params);
		else
			reporteService.insertTableReportesSQL("insertReporteRECOperacionesUnidadAdmEnvia", params);
		
		//-- Actualizar Tipo pedido
		reporteService.executeActualizarTipoPedido();
		
		/*reporteService.deleteTableReportesSQL(
				"deleteReporteRECGttOperaUnidaAdmin", params);
		reporteService.insertTableReportesSQL(
				"insertReporteRECGttOperaUnidaAdmin", params);
		reporteService.executeReporteSQL(
				"executeReporteRECOperacionesUnidadAdm", params);
        */
		reporteParams.setQueryParams(params);
		return reporteParams;
	}
	
	@Override
	public ReporteParams afterExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {
		// TODO Auto-generated method stub
		super.afterExecuteReporte(reporteParams, formReporte);		
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		log.debug("Delete to report ");
		reporteService.deleteTableReportesSQL("deleteReporte", params);
		reporteParams.setQueryParams(params);
		return reporteParams;
	}
}