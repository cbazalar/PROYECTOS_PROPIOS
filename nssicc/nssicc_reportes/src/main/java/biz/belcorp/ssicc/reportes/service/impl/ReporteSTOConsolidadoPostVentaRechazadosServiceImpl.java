package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;


@Service("reportes.reporteSTOConsolidadoPostVentaRechazadosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteSTOConsolidadoPostVentaRechazadosServiceImpl extends BaseReporteInterfaceImpl {
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#beforeExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {


		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		String nextValue = reporteService.getSecuenciaNextValue();
		params.put("nextValue", nextValue);
		if((params.get("tipoReporte").toString().equals(Constants.STO_TIPO_REPORTE_CABECERA))){
			reporteService.insertTableReportesSQL("insertSTOConsolidadoPostVentaRechazadosCabecera", params);
		}
		if((params.get("tipoReporte").toString().equals(Constants.STO_TIPO_REPORTE_DETALLE))){
			reporteService.insertTableReportesSQL("insertSTOConsolidadoPostVentaRechazadosDetalle", params);
			
		}
		
		reporteParams.setQueryParams(params);
		
		return reporteParams;		
	}	
	
	@Override
	public ReporteParams afterExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		log.debug("Delete to report ");
		reporteService.deleteTableReportesSQL("deleteReporte", params);
		return super.afterExecuteReporte(reporteParams, formReporte);
	}
	
}